package mains;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.*;

import circuit.Circuit;
import circuit.CircuitFactoryFromFile;
import circuit.TerrainTools;

public class test3 {
	public static void main(String[] args) throws FileNotFoundException{
//	JButton[]  bouton ;
//	bouton = new JButton[3];
//	for(int i = 0;i<3;i++){
//		bouton[i] = JButton("Bouton"+(i+1));
//	}
		
	String filename = "track/8_safe.trk";

    Circuit track = CircuitFactoryFromFile.build(filename); 
    BufferedImage im = TerrainTools.imageFromCircuit(track);
    JPanel monPanel = new JPanel();
    monPanel.add(new JLabel(new ImageIcon(im)));
    
	JFrame fenetre = new JFrame("mon test");
	fenetre.setSize(1024, 768);
	
	
	fenetre.add(monPanel);
	
	
	fenetre.setVisible(true);
	fenetre.pack();
}
}
