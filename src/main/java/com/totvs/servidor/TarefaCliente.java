package com.totvs.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TarefaCliente implements Runnable {

	private Socket socket;
	private Servidor server;
	private ExecutorService threadPool;
	private BlockingQueue<String> comandos;

	public TarefaCliente(ExecutorService threadPool, BlockingQueue<String> comandos, Socket socket, Servidor server) {
		this.threadPool = threadPool;
		this.comandos = comandos;
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try (Scanner scanner = new Scanner(socket.getInputStream());
				PrintStream printStream = new PrintStream(socket.getOutputStream())) {
			System.out.println("Executando tarefa do cliente " + socket.getPort());
			
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				System.out.println("Comando recebido: " + nextLine);
				
				switch (nextLine) {
				case "c1":
					printStream.println("Comando 1 recebido");
					threadPool.execute(new Comando1(printStream));
					break;
				case "c2":
					printStream.println("Comando 2 recebido");
					threadPool.execute(new Comando2(printStream));
					break;
				case "c3":
					printStream.println("Comando 3 recebido");
					Future<String> submit = threadPool.submit(new Comando3(printStream));
					Future<String> submit2 = threadPool.submit(new Comando4(printStream));
					threadPool.submit(new Callable<Void>() {

						@Override
						public Void call() throws InterruptedException, ExecutionException, TimeoutException{
							String result1 = submit.get(20, TimeUnit.SECONDS);
							String result2 = submit2.get(20, TimeUnit.SECONDS);
							printStream.println("Resultado do comando c3: " + result1 + ", " + result2);
							return null;
						}
						
					});
					break;
				case "c4":
					printStream.println("Comando 4 recebido");
					comandos.put(nextLine);
					printStream.println("Comando 4 adicionado na fila");					
					break;
				case "fim":
					printStream.println("Finalizando servidor");
					server.stop();
					break;
				default:
					printStream.println("Comando desconhecido");
				}
			}
			
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
