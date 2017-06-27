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
		printStream.println("c1");
		
		// aguardando a tecla ENTER
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		printStream.close();
		scanner.close();
		socket.close();

	}

}
