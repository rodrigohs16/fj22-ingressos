package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala){
		this.sessoesDaSala = sessoesDaSala;
	}
	
	private boolean isConflitante(Sessao sessaoExistente, Sessao sessaoNova) { LocalDate hoje = LocalDate.now();

    	LocalDateTime horarioInicioExistente = sessaoExistente.getHorario().atDate(hoje);
    
    	LocalDateTime horarioInicioNova = sessaoNova.getHorario().atDate(hoje);
    
    	LocalDateTime horarioTerminoExistente = horarioInicioExistente.plus(sessaoExistente.getFilme().getDuracao());
    
    	LocalDateTime horarioTerminoNova = horarioInicioNova.plus(sessaoNova.getFilme().getDuracao());
    
    
       	boolean terminaAntes = horarioTerminoNova.isBefore(horarioInicioExistente);
    	boolean comecaDepois = horarioInicioNova.isAfter(horarioTerminoExistente);
    
    	if (comecaDepois || terminaAntes) {
    		return false;
    	}
    	return true;
		}
	
	public boolean cabe(Sessao sessaoNova){
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> isConflitante(sessaoExistente, sessaoNova));
		
	}
		
}


