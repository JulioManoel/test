package br.com.valueprojects.mock_spring.model;

import java.util.Calendar;
import java.util.List;

import br.com.valueprojects.mock_spring.interfac.IEnviar;
import infra.JogoDao;

public class FinalizaJogo {

	private int total = 0;
	private final JogoDao dao;
	private final IEnviar serviceComunica;

	public FinalizaJogo(JogoDao dao, IEnviar serviceComunica) {
		this.dao = dao;
		this.serviceComunica = serviceComunica;
	}

	public void finaliza() {
		List<Jogo> todosJogosEmAndamento = dao.emAndamento();

		for (Jogo jogo : todosJogosEmAndamento) {
			if (iniciouSemanaAnterior(jogo)) {
				jogo.finaliza();
				total++;
				dao.atualiza(jogo);
				serviceComunica.enviar();
			}
		}
	}

	private boolean iniciouSemanaAnterior(Jogo jogo) {
		return diasEntre(jogo.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalFinalizados() {
		return total;
	}
}
