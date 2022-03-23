package controller;

import java.util.concurrent.Semaphore;

public class contIngressos extends Thread {

	private int idPessoa;
	private Semaphore semaforo;
	private static int totalIngressos = 100;
	private static int compra;

	public contIngressos(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (Login())
			return;
		if (Processo_Compra())
			return;
		try {
			semaforo.acquire();
			Validacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private boolean Login() {
		int tempoMaximo = 1000;
		int espera = (int) (Math.random() * 1951) + 50;

		try {
			sleep(tempoMaximo);
			if (espera > tempoMaximo) {
				System.out.println(
						"Cliente #" + idPessoa + ": Login timeout , a compra não será efetuda.");
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;

	}

	private boolean Processo_Compra() {
		int tempoMaximo = 2500;
		int espera = (int) (Math.random() * 2001) + 1000;

		try {
			sleep(1000);
			if (espera > tempoMaximo) {
				System.out.println(
						"Cliente #" + idPessoa + ": Compra timeout, a compra não será efetuda.");
				return true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;

	}

	private void Validacao() {
		int tempo = 1000;
		compra = (int) ((Math.random() * 4) + 1);

		if (compra <= totalIngressos) {
			totalIngressos -= compra;

			System.out.println("Cliente #" + idPessoa + " comprou " + compra + " ingresso(s), sobraram "
					+ totalIngressos + " ingresso(s)");
		} else {
			System.out.println("Não existem mais ingressos disponíveis");
		}
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
