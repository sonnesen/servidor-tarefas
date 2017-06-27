package com.totvs.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;

public class Comando3 implements Callable<String> {

private PrintStream printStream;
	
	public Comando3(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public String call() throws InterruptedException {
		System.out.println("Executando comando 3");
		// simulando demora na execucao de um webservice
		Thread.sleep(20000);
		printStream.println("Comando 3 executado com sucesso!");
		return null;
	}
}
