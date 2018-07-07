package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SalaDao {

    @PersistenceContext
    private EntityManager manager;

    public Sala findOne(Integer id) {

        return manager.find(Sala.class, id);
    }

    public void save(Sala sala) {
        manager.merge(sala);
    }

    public List<Sala> findAll() {
        return manager.createQuery("select s from Sala s", Sala.class).getResultList();
    }
    
    public List<Sessao> buscaSessoesDoFilme(Filme filme){
    	return manager.createQuery("select s from Sessao s where s.filme = :filme", Sessao.class).setParameter("filme", filme).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
