package br.com.ambientinformatica.onzevencedor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambientinformatica.onzevencedor.entity.Atleta;
import br.com.ambientinformatica.onzevencedor.entity.Clube;


@Repository("atletaDao")
public class AtletaDAO {
	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findAll() {
		String jpql = " SELECT a from Atleta a order by a.nome";
		Query query = entityManager.createQuery(jpql);
		List<Atleta> atletas = (List<Atleta>) query.getResultList();
		return atletas;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findByName(String nomeAtleta) {
		//String jpql = " SELECT a from Atleta a where nome like :nome order by a.nome";
		//String jpql = "SELECT  a from Clube c inner join c.idf_clube a  where c.nome = :nome ";
		String jpql = "SELECT a.idf, a.nome, a.dta_nascimento, a.idf_posicao, c.idf_clube  FROM Atleta a INNER JOIN a.idf c INNER JOIN c.idf_clube WHERE c.nome = :nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nome", nomeAtleta + "%");
		List<Atleta> atletas = (List<Atleta>) query.getResultList();
		return atletas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findByNameClube(String nomeAtleta) {
		String jpql = "SELECT a from Atleta a inner join Clube c on a.idf_atleta = c.id where nome = :nome ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nome", nomeAtleta + "%");
		List<Atleta> atletas = (List<Atleta>) query.getResultList();
		return atletas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Clube> findByClubeId(String idClube) {
		String jpql = " SELECT a from Clube a where idf_clube = :idf_clube";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idf_clube", idClube + "%");
		List<Clube> clubes = (List<Clube>) query.getResultList();
		return clubes;
	}

	@SuppressWarnings("unchecked")
	public List<Atleta> findAtletaByClubeId(int clubeId) {
		Query query = entityManager.createQuery("select a from Atleta a"
				+ " join fetch a.clube c"
				+ " where c.id = :clubeId");
		query.setParameter("clubeId", clubeId);
		return (List<Atleta>) query.getResultList();
	}

	@Transactional
	public Atleta create(Atleta atleta) {
		entityManager.persist(atleta);
		return atleta;
	}

	@Transactional
	public void update(Atleta atleta) {
		entityManager.merge(atleta);
	}

	@Transactional
	public void delete(Atleta atleta) {
		atleta = entityManager.find(Atleta.class, atleta.getIdf());
		entityManager.remove(atleta);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
