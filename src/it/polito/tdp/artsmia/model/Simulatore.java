package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Simulatore {
	
	PriorityQueue<Evento> queue = new PriorityQueue<Evento>();

	// stato del mondo
	SimpleDirectedGraph<Mostra, DefaultEdge> grafo ;
	
	//parametri
	int numeroStudenti;
	Mostra partenza;
	
	//output
	List<Studente> studenti;
	

	public void init(Integer numeroStudenti, SimpleDirectedGraph<Mostra, DefaultEdge> grafo,
			Mostra mostraIniziale) {
		
		this.numeroStudenti = numeroStudenti;
		this.grafo = grafo;
		this.partenza = mostraIniziale;
		
		studenti = new ArrayList<>();
		
		for(int i = 1; i<= numeroStudenti; i++) {
			studenti.add(new Studente(i));
		}
		
		queue.clear();
		
		for(Studente s : studenti) {
			queue.add(new Evento (s, partenza));
			
		}
	}

	public void run() {
		
		Random rand = new Random();
		while(!queue.isEmpty()) {
			Evento ev = queue.poll();
			
			Mostra visitata = ev.getMostraVisitata();
			
			// aggiungo al set dello studente tutte le opere in esposizione per quella mostra
			ev.getStudente().getOpere().addAll(visitata.getArtObjectId());
			
			//Controllo se la simulazione puo' continuare
			if(this.grafo.outDegreeOf(visitata)>0) {
				List<Mostra> vicini = Graphs.successorListOf(grafo, visitata);
				Mostra successiva = vicini.get(rand.nextInt(vicini.size()));
				
				queue.add(new Evento(ev.getStudente(), successiva));
			}
		}	
	}
	
	public List<Studente> getListaStudenti(){
		return this.studenti;
	}

}
