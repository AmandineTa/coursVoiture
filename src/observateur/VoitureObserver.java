package observateur;


import java.awt.Color;
import java.awt.image.BufferedImage;




import voiture.Voiture;


public class VoitureObserver implements ObservesImage{
    private Voiture v;

    public VoitureObserver(Voiture voit) {
            super();
            this.v = voit;
    }

    public int getX(){ // a inverser pour l'affichage horizontal
            return (int) v.getPosition().getX();
    }
    public int getY(){
            return (int) v.getPosition().getY();
    }

    public Color getColor() {
        if(v.getVitesse()<0.3) // vitesse faible -> cyan
           return new Color(0, (int)(v.getVitesse()*255*2), (int) (v.getVitesse()*255*2));

        if(v.getVitesse() == 0.9)
          return new Color((int)(v.getVitesse()*255),  (int) (v.getVitesse()*255), 0);

        return new Color((int)(v.getVitesse()*255), 0, (int) (v.getVitesse()*255));
    }

    public void print(BufferedImage im) {
            im.setRGB( getY(),getX(), getColor().getRGB());
    }
}