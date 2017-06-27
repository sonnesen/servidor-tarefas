package com.totvs.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TarefaCliente implements Runnable {

	private Socket socket;

	public TarefaCliente(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try (Scanner scanner = new Scanner(socket.getInputStream())) {
			System.out.println("Executando tarefa do cliente " + socket.getPort());
			
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
