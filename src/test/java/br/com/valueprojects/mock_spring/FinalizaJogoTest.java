package br.com.valueprojects.mock_spring;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.valueprojects.mock_spring.builder.CriadorDeJogo;
import br.com.valueprojects.mock_spring.model.FinalizaJogo;
import br.com.valueprojects.mock_spring.model.Jogo;
import br.com.valueprojects.mock_spring.service.SMSService;
import infra.JogoDao;
import infra.SMS;

public class FinalizaJogoTest {
	 @Test
	    public void deveFinalizarJogosDaSemanaAnterior() {

	        Calendar antiga = Calendar.getInstance();
	        antiga.set(1999, 1, 20);

	        Jogo jogo1 = new CriadorDeJogo().para("Ca�a moedas")
	            .naData(antiga).constroi();
	        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras")
	            .naData(antiga).constroi();
	        
	        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

	        SMSService service = mock(SMSService.class);
	        JogoDao daoFalso = mock(JogoDao.class);

	        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);
	        
	        FinalizaJogo finalizador = new FinalizaJogo(daoFalso, service);
	        finalizador.finaliza();

	        assertTrue(jogo1.isFinalizado());
	        assertTrue(jogo2.isFinalizado());
	        assertEquals(2, finalizador.getTotalFinalizados());
	    }
	 
	 @Test
		public void deveVerificarSeMetodoAtualizaFoiInvocado() {
			Calendar antiga = Calendar.getInstance();
			antiga.set(1999, 1, 20);

			Jogo jogo1 = new CriadorDeJogo().para("Cata moedas").naData(antiga).constroi();
			Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras").naData(antiga).constroi();

			// mock no lugar de dao falso
			List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

			SMSService service = mock(SMSService.class);
			JogoDao daoFalso = mock(JogoDao.class);

			when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

			FinalizaJogo finalizador = new FinalizaJogo(daoFalso, service);
			finalizador.finaliza();

			verify(daoFalso, times(1)).atualiza(jogo1);
			//Mockito.verifyNoInteractions(daoFalso);			
		}
	 
	 @Test
		public void deveVerificarSeMetodoEnviarFoiInvocado() {
			Calendar antiga = Calendar.getInstance();
			antiga.set(1999, 1, 20);

			Jogo jogo1 = new CriadorDeJogo().para("Cata moedas").naData(antiga).constroi();
			Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras").naData(antiga).constroi();

			// mock no lugar de dao falso
			List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

			SMSService service = mock(SMSService.class);
			JogoDao daoFalso = mock(JogoDao.class);

			FinalizaJogo finalizador = new FinalizaJogo(daoFalso, service);
			finalizador.finaliza();

			verify(service, times(2)).enviar();			
		}
	}