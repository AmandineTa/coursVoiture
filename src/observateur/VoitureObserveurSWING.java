package observateur;

import java.awt.Color;
import java.awt.Graphics;

import voiture.*;

public class VoitureObserveurSWING implements ObserveurSWING{
    private Voiture voit;
    private Color color = Color.yellow;

    public VoitureObserveurSWING(Voiture v) {
            super();
            this.voit = v;
    }

    public int getX(){
            return (int) voit.getPosition().getX();
    }
    public int getY(){
            return (int) voit.getPosition().getY();
    }

    public void print(Graphics g){
            // Attention a l'inversion eventuelle des coordonnees
            g.setColor(color);
            g.drawRect( (int) voit.getPosition().getY(),(int) voit.getPosition().getX(), 2, 2);
            g.setColor(Color.red);
            g.drawLine((int) voit.getPosition().getY(),(int) voit.getPosition().getX(),
            		(int) (voit.getPosition().getX()+voit.getDirection().getX()*10),
            		(int) (voit.getPosition().getY()+voit.getDirection().getY()*10));


    }
}
