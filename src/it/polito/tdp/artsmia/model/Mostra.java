package it.polito.tdp.artsmia.model;

import java.util.List;

public class Mostra {

	private int id;
	private String dipartimento;
	private String titolo;
	private int annoInizio;
	private int annoFine;
	
	private List<Integer> artObjectId;
	
	public Mostra(int id, String dipartimento, String titolo, int annoInizio, int annoFine) {
		super();
		this.id = id;
		this.dipartimento = dipartimento;
		this.titolo = titolo;
		this.annoInizio = annoInizio;
		this.annoFine = annoFine;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getAnnoInizio() {
		return annoInizio;
	}
	public void setAnnoInizio(int annoInizio) {
		this.annoInizio = annoInizio;
	}
	public int getAnnoFine() {
		return annoFine;
	}
	public void setAnnoFine(int annoFine) {
		this.annoFine = annoFine;
	}
	@Override
	public String toString() {
		return String.format("%d %s ", id, titolo);
	}
	
	public List<Integer> getArtObjectId() {
		return artObjectId;
	}
	public void setArtObjectId(List<Integer> artObjectId) {
		this.artObjectId = artObjectId;
	}
	
	
}
