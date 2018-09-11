package br.com.ambientinformatica.onzevencedor.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.onzevencedor.dao.AtletaDAO;
import br.com.ambientinformatica.onzevencedor.entity.Atleta;


@Service("atletaService")
public class AtletaService {
	
	@Autowired
	private AtletaDAO dao;

	public List<Atleta> getAllAtletas() {
		return dao.findAll();
	}

	public List<Atleta> getAtletasByName(String nomeAtleta) {
		return dao.findByName(nomeAtleta);
	}
	public List<Atleta> getAtletasByNameClube(String nomeAtleta) {
		return dao.findByNameClube(nomeAtleta);
	}

	public Atleta createAtleta(Atleta atleta) {
		return dao.create(atleta);
	}

	public void updateAtleta(Atleta atleta) {
		dao.update(atleta);
	}

	public void deleteAtleta(Atleta atleta) {
		dao.delete(atleta);
	}

	public void setDao(AtletaDAO dao) {
		this.dao = dao;
	}

	public List<Atleta> getAtletasByIdClube(int clubeId) {
		return dao.findAtletaByClubeId(clubeId);
	}
}

