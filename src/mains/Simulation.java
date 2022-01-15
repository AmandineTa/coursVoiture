package mains;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import observateur.VoitureObserver;
import circuit.Circuit;
import circuit.Terrain;
import circuit.TerrainTools;
import strategy.Strategy;
import voiture.Commande;
import voiture.Voiture;
import mvcswing.UpdateEventListener;
import mvcswing.UpdateEventSender;

public class Simulation implements UpdateEventSender{
	private  ArrayList<Commande> listCom = new ArrayList<Commande>();
	private ArrayList<UpdateEventListener> listUEL = 
			new ArrayList<UpdateEventListener>();
	private Voiture voit;
	private Circuit cir;
	private Strategy strat;
	private VoitureObserver voitObs;
	
	public Simulation(Voiture voit, Circuit cir, Strategy strat){
		super();
		this.voit = voit;
		this.cir = cir;
		this.strat = strat;
		this.voitObs = new VoitureObserver(voit);
		
	}
	
	public void play() throws IOException{
		BufferedImage im = TerrainTools.imageFromCircuit(cir);
		
		while (TerrainTools.isRunnable(cir.getTerrain(voit.getPosition())) && 
        		cir.getTerrain(voit.getPosition()) != Terrain.EndLine){
        	Commande c = strat.getCommande();
        	listCom.add(c);
        	voit.drive(c);
            System.out.println("position : "+ voit.getPosition());
            voitObs.print(im);

		}
        ImageIO.write(im, "png", new File("test" + ".png"));
        
        Commande.saveListeCommande(listCom, "ListCom");
		
	}

	
	public ArrayList<Commande> getRecord(){
		return listCom;
	}

	@Override
	public void add(UpdateEventListener listener) {
		this.listUEL.add(listener);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for(UpdateEventListener listener : listUEL) {
			listener.manageUpdate();
		}
	}	
	

}
