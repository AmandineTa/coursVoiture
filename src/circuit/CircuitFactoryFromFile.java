package circuit;

import geometry.Vecteur;

import java.io.FileNotFoundException;

public class CircuitFactoryFromFile {
	public final static Vecteur dirSta = new Vecteur(0,1);
	public final static Vecteur dirFin= new Vecteur(0,1);
	
	public static Circuit build(String filename) throws FileNotFoundException{
		Terrain[][] t = TerrainTools.buldTerrain(filename);
		boolean b=false;
		int x=0;
		int y=0;
		for (int i = 0; i< t[0].length;i++){
			for (int j = 0; j<t.length; j++){
				if(t[j][i] == Terrain.StartPoint){
					x = j;
					y = i;
					b = true;
					break;
				}
			}
		}
		if(b == false){
			System.out.println("Erreur il n'y a pas de point de départ");
			return null;
		}
		Vecteur sta = new Vecteur(x,y);
		Circuit c =  new CircuitImpl(t,sta ,dirSta,dirFin);
		return c;
		
	}
	
	


}
