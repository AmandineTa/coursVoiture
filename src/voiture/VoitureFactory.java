package voiture;

import geometry.Vecteur;
import circuit.Circuit;

public class VoitureFactory {
	private static double[] tabVitesse     = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.};
	private static double[] tabTurn        = {1.,  0.9, 0.75, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05};
	
	private static double vmax = 0.9;
	private static double alpha_c = 0.005;
	private static double braquage = 0.1;
	private static double alpha_f = 0.0002;
	private static double beta_f = 0.0005;

	private static double vitesse = 0.; // vitesse initiale
	  
	public static Voiture build(Circuit c){
		Vecteur pos = c.getPointDepart();
		Vecteur dir = c.getDirectionDepart();
		Voiture v =  new VoitureImpl(tabVitesse, tabTurn, vmax,
				braquage, alpha_c, alpha_f, beta_f,
				vitesse, pos,dir);
		return v;
	}
}
