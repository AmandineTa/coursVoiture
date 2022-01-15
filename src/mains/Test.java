package mains;


import java.io.*;

import mvcswing.Controleur;
import observateur.IHMSwing;
import observateur.VoitureObserver;
import observateur.VoitureObserveurSWING;
import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import dijkstra.Dijkstra;
import radar.RadarClassique;
import radar.RadarDijk;
import strategy.Strategy;
import strategy.StrategyDijk;
import strategy.StrategyRadar;
import voiture.Voiture;
import voiture.VoitureFactory;

public class Test {
	public static void main(String[] args) throws IOException{
		String filename = "/Users/margueritebenidir/IdeaProjects/coursVoiture/track/1_safe.trk";

        Circuit track = CircuitFactoryFromFile.build(filename); // factory static
        Voiture v = VoitureFactory.build(track); // factory static

      
        
        double[] thetas = {Math.PI/2,Math.PI/3,Math.PI/4,Math.PI/5 ,Math.PI/6,Math.PI/12,Math.PI/24,0
        		,Math.PI/24,-Math.PI/12,-Math.PI/6,-Math.PI/5,-Math.PI/4,-Math.PI/3,-Math.PI/2};

        Dijkstra dijk = new Dijkstra(track);
        
        dijk.algo();

        RadarDijk r = new RadarDijk(v, track,thetas,dijk);
        

        Strategy strat=new StrategyDijk(r,v);
        
        VoitureObserver voitObs = new VoitureObserver(v);
        Simulation sim = new Simulation(v,track, strat);
        sim.play();
//        
//        sim.getRecord();
     
	}	
}
