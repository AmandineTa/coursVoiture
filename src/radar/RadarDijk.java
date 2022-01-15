package radar;

import geometry.Vecteur;
import voiture.Voiture;
import circuit.Circuit;
import circuit.TerrainTools;
import dijkstra.Dijkstra;

public class RadarDijk implements Radar {
	private double epsilon = 0.1;
	private double[] scor;
	private double[] disIP;
	private int getBIS;
	private double[] thetas;
	private double[] dijkScor;
	private int getBID;
	private Voiture v;
	private Circuit cir;
	private Dijkstra dijk;
	
	
	public RadarDijk( Voiture v, Circuit cir, double[] thetas, Dijkstra dijk) {
		super();
		this.thetas = thetas;
		this.v = v;
		this.cir = cir;
		this.dijk = dijk;
	}

	private double algo(Voiture v, Circuit cir, double ang){
		Vecteur p = v.getPosition().clone();
		Vecteur d = v.getDirection().clone().rot(ang);
		int cpt = 0;
		double end = 0;
		while (TerrainTools.isRunnable(cir.getTerrain(p))){
				cpt++;
				Vecteur ed = d.multi(this.epsilon);
				p = p.add(ed);
				if(d.scal(cir.getDirectionArrivee())<0){
					end = Double.POSITIVE_INFINITY;
				}
		}
		return cpt+end;	
		}
	
	public double[] scores(){
		int bestI = 0;
		this.scor = new double[this.thetas.length];
		this.disIP = new double [this.thetas.length];
		for(int i=0 ; i< this.scor.length;i++){
			this.scor[i]= this.algo(this.v,this.cir,this.thetas()[i]);
			this.disIP[i] = this.scor[i]*this.epsilon;
			if (this.scor[i]>this.scor[bestI]&&this.scor[i]<Double.POSITIVE_INFINITY){
				bestI = i;
			}
		}
		this.getBIS = bestI;
		return scor;
		
	}
	
	public double[] dijkScore(){
		int bestD = 0;
		this.dijkScor = new double[this.thetas.length];
		for(int i = 0; i<this.dijkScor.length;i++){
			this.dijkScor[i] = this.dijk.getDist()
					[(int) v.getDirection().addb(this.disIP[i]).rot(this.thetas[i]).getY()]
					[(int) v.getDirection().addb(this.disIP[i]).rot(this.thetas[i]).getX()];
			if(dijkScor[bestD]> dijkScor[i]){
				bestD = i;
			}
		this.getBID = bestD;
		}
		
		return this.dijkScor;
	}
	
	public double[] distancesInPixels(){
		return this.disIP;
	}
	public int getBestIndex(){
		return this.getBIS;
	}
	public double[] thetas(){
		return this.thetas;
	}
	public int getBestIndexDijk(){
		return this.getBID;
	}

}