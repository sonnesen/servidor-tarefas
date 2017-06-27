package com.totvs.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TarefaCliente implements Runnable {

	private Socket socket;
	private Servidor server;

	public TarefaCliente(Socket socket, Servidor server) {
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
					break;
				case "c2":
					printStream.println("Comando 2 recebido");
					break;
				case "fim":
					printStream.println("Finalizando servidor");
					server.stop();
					break;
				default:
					printStream.println("Comando desconhecido");
				}
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
