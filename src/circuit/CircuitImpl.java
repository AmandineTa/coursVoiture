package circuit;

import java.util.ArrayList;

import geometry.Vecteur;

public class CircuitImpl implements Circuit {
	private Terrain[][] track;
	private Vecteur sta, dirSta, dirFin;
	
	
	
	public CircuitImpl(Terrain[][] track, Vecteur sta, Vecteur dirSta,
			Vecteur dirFin) {
		super();
		this.track = track;
		this.sta = sta;
		this.dirSta = dirSta;
		this.dirFin = dirFin;
	}

	
	
	public Terrain getTerrain(int i, int j){
		return this.track[i][j];
	}
	
	public Terrain getTerrain(Vecteur v){
		return this.track[(int)v.getX()][(int)v.getY()];
	}
	
	public Vecteur getPointDepart(){
		return this.sta;
	}
	public Vecteur getDirectionDepart(){
		return this.dirSta;
	}
    public Vecteur getDirectionArrivee(){
    	return this.dirFin;
    }
    
    public int getWidth(){
    	return this.track[0].length;
    }
    public int getHeight(){
    	return this.track.length;
    }
    
    public ArrayList<Vecteur> getArrivees(){
    	ArrayList<Vecteur> pArrive = new ArrayList<Vecteur>();
		for (int i = 0; i< this.track.length;i++){
			for (int j = 0; j<this.track[0].length; j++){
				if(track[i][j] == Terrain.EndLine){
					pArrive.add(new Vecteur(i,j));
				}
			}
		}
    	return pArrive;
    }

	@Override
	public double getDist(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
