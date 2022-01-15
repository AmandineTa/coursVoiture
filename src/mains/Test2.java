package mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.Terrain;
import circuit.TerrainTools;
import dijkstra.Dijkstra;
import radar.RadarDijk;
import strategy.Strategy;
import strategy.StrategyDijk;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureFactory;

public class Test2 {
	private  ArrayList<Commande> record;
	
	public ArrayList<Commande> getRecord(){
		return record;
	}
	
	public static void main(String[] args) throws IOException{
		String filename = "track/8_safe.trk";

        Circuit track = CircuitFactoryFromFile.build(filename); // factory static
        Voiture v = VoitureFactory.build(track); // factory static

        BufferedImage im = TerrainTools.imageFromCircuit(track);
        
        double[] thetas = {Math.PI/2,Math.PI/3,Math.PI/4,Math.PI/5 ,Math.PI/6,Math.PI/12,Math.PI/24,0
        		,Math.PI/24,-Math.PI/12,-Math.PI/6,-Math.PI/5,-Math.PI/4,-Math.PI/3,-Math.PI/2};

        Dijkstra dijk = new Dijkstra(track);
        
        dijk.algo();
        
        ArrayList<Commande> listCom = new ArrayList<Commande>();

        RadarDijk r = new RadarDijk(v, track,thetas,dijk);

//        double[] sc = r.scores();
//        for(double d:sc)
//        	System.out.print(d+" ");
//        System.out.println();
//        if(true)
//        	return;
        
        Strategy strat= new StrategyDijk(r,v);

        while (TerrainTools.isRunnable(track.getTerrain(v.getPosition())) && 
        		track.getTerrain(v.getPosition()) != Terrain.EndLine){
        	
        	Commande c = strat.getCommande();
        	listCom.add(c);
        	v.drive(c);
            System.out.println("position : "+ v.getPosition());
            im.setRGB((int) v.getPosition().getY(), (int) v.getPosition().getX(), 
            		Color.orange.getRGB());

		}
        ImageIO.write(im, "png", new File("test3" + ".png"));
        
        Commande.saveListeCommande(listCom, "ListCom");
	}	

}