package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento>{
	
	private Studente studente;
	private Mostra mostraVisitata;
	
	public Evento(Studente studente, Mostra mostraVisitata) {
		super();
		this.studente = studente;
		this.mostraVisitata = mostraVisitata;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public Mostra getMostraVisitata() {
		return mostraVisitata;
	}
	public void setMostraVisitata(Mostra mostraVisitata) {
		this.mostraVisitata = mostraVisitata;
	}
	
	@Override
	public int compareTo(Evento other) {
		return this.studente.getId() - other.getStudente().getId();
	}
	

}
