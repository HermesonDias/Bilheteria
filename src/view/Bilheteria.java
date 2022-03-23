package view;

import java.util.concurrent.Semaphore;

import controller.contIngressos;

public class Bilheteria {

	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(1);

		for (int idPessoa = 1; idPessoa <= 300; idPessoa++) {
			Thread contIng = new contIngressos(idPessoa, semaforo);
			contIng.start();
		}

	}

}
