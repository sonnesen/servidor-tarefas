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
		
		PrintStream printStream = new PrintStream(socket.getOutputStream());
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {			
			String nextLine = scanner.nextLine();
			if (("").equals(nextLine)) break;
			printStream.println(nextLine);
		}
		
		System.out.println("Recebendo dados do servidor");
		Scanner scanner2 = new Scanner(socket.getInputStream());
		while (scanner2.hasNextLine()) {
			System.out.println(scanner2.nextLine());
		}
		scanner2.close();
		
		printStream.close();
		scanner.close();
		socket.close();

	}

}
