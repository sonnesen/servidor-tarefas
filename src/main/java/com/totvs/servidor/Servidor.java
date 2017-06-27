package com.totvs.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Iniciando servidor");
		ServerSocket server = new ServerSocket(55555);
		
		while(true) {
			Socket socket = server.accept();
			System.out.printf("Novo cliente conectado na porta: %d\n", socket.getPort());
			Thread.sleep(10000);
		}
	}

}
