package com.totvs.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("localhost", 55555);
		System.out.println("Cliente Conectado");

		// enviando dados para o servidor
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Enviando dados para o servidor");
				try (PrintStream printStream = new PrintStream(socket.getOutputStream());
						Scanner scanner = new Scanner(System.in)) {

					while (scanner.hasNextLine()) {
						String nextLine = scanner.nextLine();
						if (("").equals(nextLine))
							break;
						printStream.println(nextLine);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});

		// recebendo dados do servidor
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Recebendo dados do servidor");
				try (Scanner scanner2 = new Scanner(socket.getInputStream())) {
					while (scanner2.hasNextLine()) {
						System.out.println(scanner2.nextLine());
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});

		t1.start();
		t2.start();
		t2.join(); // a thread principal ira esperar esta thread terminar
		
		System.out.println("Fechando a conexao do cliente");
		socket.close();

	}

}
