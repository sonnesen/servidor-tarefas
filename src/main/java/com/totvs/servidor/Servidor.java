package com.totvs.servidor;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		System.out.println("Iniciando servidor");
		ServerSocket server = new ServerSocket(55555);
		server.accept();
	}

}
