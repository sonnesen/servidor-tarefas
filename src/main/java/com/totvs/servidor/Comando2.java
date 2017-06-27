package com.totvs.servidor;

import java.io.PrintStream;

public class Comando2 implements Runnable {

private PrintStream printStream;
	
	public Comando2(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void run() {
		System.out.println("Executando comando 2");
		try {
			// simulando demora na execucao
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		// forcando uma excecao
		throw new RuntimeException("Excecao no comando 2");
		
		// printStream.println("Comando 2 executado com sucesso!");
	}
}
