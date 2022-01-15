package radar;

import geometry.Vecteur;
import circuit.Circuit;
import circuit.TerrainTools;
import voiture.Voiture;

public class RadarClassique implements Radar {
	private double epsilon = 0.1;
	private double[] scor;
	private double[] disIP;
	private int getBI;
	private double[] thetas;
	private Voiture v;
	private Circuit cir;
	
	public RadarClassique( Voiture v, Circuit cir, double[] thetas) {
		super();
		this.thetas = thetas;
		this.v = v;
		this.cir = cir;
	}

	private int algo(Voiture v, Circuit cir, double ang){
		Vecteur p = v.getPosition().clone();
		Vecteur d = v.getDirection().clone().rot(ang);
		int cpt = 0;
		while (TerrainTools.isRunnable(cir.getTerrain(p))){
				cpt++;
				Vecteur ed = d.multi(this.epsilon);
				p = p.add(ed);
		}
		return cpt;	
		}
	
	public double[] scores(){
		int bestI = 0;
		this.scor = new double[this.thetas.length];
		this.disIP = new double [this.thetas.length];
		for(int i=0 ; i< this.scor.length;i++){
			this.scor[i]= this.algo(this.v,this.cir,this.thetas()[i]);
			this.disIP[i] = this.scor[i]*this.epsilon;
			if (this.scor[i]>this.scor[bestI]){
				bestI = i;
			}
		this.getBI = bestI;
		}
		return scor;
		
	}
	public double[] distancesInPixels(){
		return this.disIP;
	}
	public int getBestIndex(){
		return this.getBI;
	}
	public double[] thetas(){
		return this.thetas;
	}
}
