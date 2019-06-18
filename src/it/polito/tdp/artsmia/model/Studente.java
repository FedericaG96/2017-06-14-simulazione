package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Studente implements Comparable<Studente>{
	
	private int id;
	private Set<Integer>opere;
	//Uso un set per soddisfare il vincolo: se la stessa opera è presente in più mostre a cui lo studente partecipa
	//deve essere contata una sola volta. ->Set non contiene elementi duplicati
	
	public Studente(int id) {
		super();
		this.id = id;
		opere = new HashSet<Integer>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Integer> getOpere() {
		return opere;
	}
	public void setOpere(Set<Integer> opere) {
		this.opere = opere;
	}
	@Override
	public String toString() {
		return String.format("%s, opere: %s", id, opere.size());
	}
	@Override
	public int compareTo(Studente o) {
		return o.getOpere().size() - this.getOpere().size();
	}
	
}
