package com.totvs.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 55555);
		System.out.println("Cliente Conectado");

		// enviando dados para o servidor
		new Thread(new Runnable() {
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
		}).start();

		// recebendo dados do servidor
		new Thread(new Runnable() {
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
		}).start();

		socket.close();

	}

}
