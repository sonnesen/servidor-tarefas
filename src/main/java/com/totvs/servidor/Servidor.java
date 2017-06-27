package com.totvs.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Servidor {

	private AtomicBoolean isRunning;
	private ExecutorService threadPool;
	private ServerSocket server;
	
	public Servidor() throws IOException {
		System.out.println("Iniciando servidor");
		server = new ServerSocket(55555);
		threadPool = Executors.newCachedThreadPool();
		isRunning = new AtomicBoolean(true);
	}
	
	public void run() throws IOException {
		while(isRunning.get()) {
			Socket socket = server.accept();
			System.out.printf("Novo cliente conectado na porta: %d\n", socket.getPort());
			threadPool.execute(new TarefaCliente(socket, this));
		}	
	}
	
	public void stop() throws IOException {
		isRunning.set(false);
		threadPool.shutdown();
		server.close();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Servidor servidor = new Servidor();
		servidor.run();
	}

}
