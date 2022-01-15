package observateur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import circuit.Circuit;
import circuit.TerrainTools;
import mvcswing.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener{
	private ArrayList<ObserveurSWING> listObs = new ArrayList<ObserveurSWING>();
	public Graphics graph;
	
	
	
	
	public IHMSwing( Circuit cir) {
		super();
		Graphics g = TerrainTools.imageFromCircuit(cir).createGraphics();
		this.graph = g;
	}
	
	
	@Override
	public void manageUpdate() {
		repaint(); // ordre de mise à jour  
        try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void paint(Graphics g){
		super.paint(g);
		
		for(ObserveurSWING o: listObs){
			o.print(g);
		}
	}


	public Graphics getGraph() {
		return graph;
	}


}
