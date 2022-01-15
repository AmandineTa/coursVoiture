package mvcswing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circuit.*;
import observateur.ObservesImage;

public class Controleur implements UpdateEventListener{
	private ArrayList<ObservesImage> listObs = new ArrayList<ObservesImage>();
	private BufferedImage image;
	
	
	
	
	public Controleur( Circuit cir) {
		super();
		BufferedImage im = TerrainTools.imageFromCircuit(cir);
		this.image = im;
	}
	
	public void add(ObservesImage obs){
		listObs.add(obs);
	}


	@Override
	public void manageUpdate() {
		// TODO Auto-generated method stub
		for(ObservesImage o: listObs){
			o.print(image);
		}
		
	}

}
