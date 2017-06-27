package com.totvs.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class Comando3 implements Callable<String> {

private PrintStream printStream;
	
	public Comando3(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public String call() throws InterruptedException {
		System.out.println("Executando comando 3");
		printStream.println("Executando comando 3");
		// simulando demora na execucao de um webservice
		Thread.sleep(15000);
		int valor = new Random().nextInt(100) + 1;
		printStream.println("Comando 3 executado com sucesso!");
		return Integer.toString(valor);
	}
}
