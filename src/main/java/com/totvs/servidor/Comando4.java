package com.totvs.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;

public class Comando4 implements Callable<String> {

	private PrintStream printStream;

	public Comando4(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public String call() throws InterruptedException {
		System.out.println("Executando comando 4");
		// simulando demora na execucao ao acesso ao banco de dados
		Thread.sleep(20000);
		printStream.println("Comando 4 executado com sucesso!");
		return null;
	}
}
