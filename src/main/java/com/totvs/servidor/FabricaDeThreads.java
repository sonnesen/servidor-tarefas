package com.totvs.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

	private ThreadFactory defaultThreadFactory;
	private static int numeroThread = 1;

	public FabricaDeThreads(ThreadFactory defaultThreadFactory) {
		this.defaultThreadFactory = defaultThreadFactory;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = defaultThreadFactory.newThread(r);
		t.setName("Thread " + numeroThread);
		t.setUncaughtExceptionHandler(new TratadorDeExcecao());
		numeroThread ++;
		return t;
	}

}
