package dijkstra;

import geometry.Vecteur;

import java.util.ArrayList;
import java.util.Collections;

import circuit.Circuit;
import circuit.Terrain;
import circuit.TerrainTools;

public class Dijkstra {
	private double[][] dist;
	private ArrayList<Vecteur> q ;
	private Circuit cir;
	
	public Dijkstra(Circuit cir){
		this.cir = cir;
		this.dist = new double [cir.getHeight()][cir.getWidth()];
		this.q = new ArrayList<Vecteur>();
		for(int x = 0; x< dist.length; x++){
			for (int y  = 0; y < dist[x].length;y++){
				if(cir.getTerrain(x, y) != Terrain.EndLine){
					this.dist[x][y] = Double.POSITIVE_INFINITY;
				}
				else{
					this.dist[x][y] = 0;
					this.q.add(new Vecteur(x,y));
				}
			}
		}
		
	}
	
	public void algo(){
		
		ComparatorDijk comp = new ComparatorDijk(dist);
		
		while (!q.isEmpty()){
			
			Vecteur ref = Collections.min(q,comp);
			q.remove(ref);
			
			for (int i = -1; i<=1; i++){
				for (int j = -1;j <= 1; j++){
					Vecteur voisin = new Vecteur(i+ref.x ,j+ref.y);
					// Si le terrain n'est runnable on continue;
					if(!TerrainTools.isRunnable(cir.getTerrain(voisin))){
						continue;
					}
					//si on est sur la ligne d'arrivée.
					if(cir.getTerrain(ref) == Terrain.EndLine){
						if(cir.getDirectionArrivee().scal(voisin.sous(ref))>0){
							continue;
						}
					}
					//point de base
					if(i==0 && j==0){
						continue;
					}
					//en angle
					double d = 10;
					if((i == -1 || i == 1 )&&( j== -1 || j == 1)){
						d = 14;
					}
					if(dist[(int) (ref.x+i)][(int) (ref.y+j)] == Double.POSITIVE_INFINITY){
						q.add(voisin);
					}
					dist[(int) (ref.x+i)][(int) (ref.y+j)] = 
							Math.min(dist[(int) (ref.x+i)][(int) (ref.y+j)], 
									dist[(int) ref.x][(int) ref.y]+d);
					
					
					
					
				}
				
			}
			
		}
	}
	
	public double[][] getDist(){
		return this.dist;
	}
	
	
}
