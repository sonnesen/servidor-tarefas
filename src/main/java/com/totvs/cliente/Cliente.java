package com.totvs.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 55555);
		System.out.println("Cliente Conectado");
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		scanner.close();
		socket.close();

	}

}
