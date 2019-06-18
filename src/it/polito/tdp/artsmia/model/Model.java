package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private SimpleDirectedGraph<Mostra, DefaultEdge> grafo;
	private List<Integer> anni;
	List<Mostra> mostre;
	List<ArtObject> opere;
	Map<Integer, Mostra> idMapMostre;
	Map<Mostra, Integer> mostraOpere ;
	Simulatore sim = new Simulatore();
	Integer annoScelto = null;
	
	public Model() {
		dao = new ArtsmiaDAO();
		anni =dao.getAllYears();
		opere = dao.listObject();
		idMapMostre =  new HashMap<>();	//mostra-numeroOpere
	}

	public List<Integer> getAllYears() {
		return this.anni;
	}

	public void creaGrafo(Integer annoScelto) {
		
		this.annoScelto = annoScelto;
		grafo = new SimpleDirectedGraph<Mostra, DefaultEdge>(DefaultEdge.class);
		mostre = dao.getMostre(annoScelto);
		
		for(Mostra m : mostre) {
			idMapMostre.put(m.getId(), m);
		}
		
		Graphs.addAllVertices(grafo, mostre);
		
		for(Mostra m1 : mostre) {
			for(Mostra m2 : mostre) {
				if(!m1.equals(m2)) {
					if(m1.getAnnoInizio()<m2.getAnnoInizio() && m1.getAnnoFine() > m2.getAnnoInizio()) {
						grafo.addEdge(m1, m2);
					}
						
				}
			}
		}
	}

	public Mostra getMostraMassima(Integer annoScelto) {

		mostraOpere = new HashMap<>();
		
		mostraOpere = dao.getMostraOpere(annoScelto, idMapMostre);
		int massimo = Integer.MIN_VALUE;
		Mostra mostraMassima = null;
		for(Mostra m: mostraOpere.keySet()) {
			if(mostraOpere.get(m) > massimo) {
				massimo = mostraOpere.get(m) ;
				mostraMassima = m;
			}
		}
		
		return mostraMassima;
	}
	
	public boolean isStronglyConnected() {
		KosarajuStrongConnectivityInspector<Mostra, DefaultEdge> ksci = new KosarajuStrongConnectivityInspector<Mostra, DefaultEdge>(this.grafo);
		return ksci.isStronglyConnected();
	}

	public void simula(Integer numeroStudenti) {
		
		for(Mostra m : grafo.vertexSet()) {
			m.setArtObjectId(dao.getIdOpereMostra(m));
		}
		
		List<Mostra> mostreInAnno = new ArrayList<>();
		for(Mostra m : grafo.vertexSet()) {
			if(m.getAnnoInizio() == this.annoScelto) {
				mostreInAnno.add(m);
			}
		}
		
		Random rand = new Random();
		Mostra mostraIniziale = mostreInAnno.get(rand.nextInt(mostreInAnno.size()));
		sim.init(numeroStudenti, this.grafo, mostraIniziale);
		sim.run();
	}

	public List<Studente> getListaStudenti(){
		return sim.getListaStudenti();
	}


}
