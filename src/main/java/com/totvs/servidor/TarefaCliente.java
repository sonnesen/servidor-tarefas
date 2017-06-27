package com.totvs.servidor;

import java.net.Socket;

public class TarefaCliente implements Runnable {

	private Socket socket;

	public TarefaCliente(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Executando tarefa do cliente " + socket.getPort());
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
