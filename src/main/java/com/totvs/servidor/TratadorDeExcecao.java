package com.totvs.servidor;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Ocorreu uma excecao na thread " + t.getName() + ", " + e.getMessage());
	}

}
