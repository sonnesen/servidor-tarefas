package com.totvs.servidor;

import java.io.PrintStream;

public class Comando1 implements Runnable {
	
	private PrintStream printStream;
	
	public Comando1(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void run() {
		System.out.println("Executando comando 1");
		try {
			// simulando demora na execucao
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		printStream.println("Comando 1 executado com sucesso!");
	}

}
