package br.com.ambientinformatica.onzevencedor.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.ambientinformatica.onzevencedor.entity.Atleta;
import br.com.ambientinformatica.onzevencedor.entity.Clube;
import br.com.ambientinformatica.onzevencedor.entity.Posicao;
import br.com.ambientinformatica.onzevencedor.service.AtletaService;

@ManagedBean(name = "atletaBean")
@ViewScoped
public class AtletaBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{atletaService}")
	private AtletaService model;

	private Atleta atleta;

	private Clube clube;

	private Integer idfClube;
	
	private Integer idfclube;

	private String idClubeNome;

	private List<Atleta> atletas;

	private boolean editMode;
	
	private String nomeAtleta;
	
	private String nome;

	private Posicao posicoesDisponiveis;

	private Clube clubeSelecionado;

	public Atleta getAtleta() {
		if (atleta == null) {
			atleta = new Atleta();
		}
		return atleta;
	}

	public void salvar() {
		Clube clube = new Clube();
		clube.setId(idfClube);
		atleta.setClube(clube);

		System.out.println("id atleta " + atleta.getIdf());
		if (atleta.getIdf() == null || atleta.getIdf().intValue() == 0) {
			atleta = model.createAtleta(atleta);
			atleta = new Atleta();
			addInfoMessage("Atleta criado com sucesso.");
		} else {
			model.updateAtleta(atleta);
			addInfoMessage("Atleta alterado com sucesso.");
		}
	}

	public void delete() {
		model.deleteAtleta(atleta);
		if (nomeAtleta != null && !nomeAtleta.isEmpty())
			atletas = model.getAtletasByName(nomeAtleta);
		else
			atletas = model.getAllAtletas();
	}

	public void create() {
		this.atleta = new Atleta();
		this.editMode = true;
	}

	public void update() {
		this.editMode = true;
	}

	public void cancel() {
		this.editMode = false;
	}

	public void filtrarAtleta(AjaxBehaviorEvent event) {
		if (idClubeNome != null && !idClubeNome.isEmpty()) {
			atletas = model.getAtletasByIdClube(Integer.parseInt(idClubeNome));
			
		} else {
			atletas = model.getAllAtletas();
		}
	}

	public Integer getIdfClube() {
		return idfClube;
	}

	public void setIdfClube(Integer idfClube) {
		this.idfClube = idfClube;
	}

	public AtletaService getModel() {
		return model;
	}

	public void setModel(AtletaService model) {
		this.model = model;
	}

	public List<Atleta> getAtletas() {
		return atletas;
	}

	public void setAtletas(List<Atleta> atletas) {
		this.atletas = atletas;
	}

	public String getNomeAtleta() {
		return idClubeNome;
	}

	public void setNomeAtleta(String nomeAtleta) {
		this.idClubeNome = nomeAtleta;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}

	public Posicao[] getPosicoesDisponiveis() {
		return Posicao.values();
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

	public String getIdClubeNome() {
		return idClubeNome;
	}

	public void setIdClubeNome(String idClubeNome) {
		this.idClubeNome = idClubeNome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Clube getClubeSelecionado() {
		return clubeSelecionado;
	}

	public void setClubeSelecionado(Clube clubeSelecionado) {
		this.clubeSelecionado = clubeSelecionado;
	}
	
	
	

	public Integer getIdfclube() {
		return idfclube;
	}

	public void setIdfclube(Integer idfclube) {
		this.idfclube = idfclube;
	}

	@Override
	public String toString() {
		return "AtleraBean [model=" + model + ", atleta=" + atleta + ", atletas=" + atletas + ", nomeAtleta="
				+ idClubeNome + ", editMode=" + editMode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atleta == null) ? 0 : atleta.hashCode());
		result = prime * result + ((atletas == null) ? 0 : atletas.hashCode());
		result = prime * result + (editMode ? 1231 : 1237);
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((idClubeNome == null) ? 0 : idClubeNome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtletaBean other = (AtletaBean) obj;
		if (atleta == null) {
			if (other.atleta != null)
				return false;
		} else if (!atleta.equals(other.atleta))
			return false;
		if (atletas == null) {
			if (other.atletas != null)
				return false;
		} else if (!atletas.equals(other.atletas))
			return false;
		if (editMode != other.editMode)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (idClubeNome == null) {
			if (other.idClubeNome != null)
				return false;
		} else if (!idClubeNome.equals(other.idClubeNome))
			return false;
		return true;
	}

}
