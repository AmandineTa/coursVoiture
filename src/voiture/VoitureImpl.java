package voiture;

import geometry.Vecteur;

public class VoitureImpl implements Voiture {
	// outils pour la gestion des limites de rotation en fonction de la vitesse
	private double[] tabVitesse;    
	private double[] tabTurn;  

	// capacités   
	private final double vmax, braquage;
	private final double alpha_c, alpha_f, beta_f;

	
	public VoitureImpl(double[] tabVitesse, double[] tabTurn, double vmax,
			double braquage, double alpha_c, double alpha_f, double beta_f,
			double vitesse, Vecteur position, Vecteur direction) {
		super();
		this.tabVitesse = tabVitesse;
		this.tabTurn = tabTurn;
		this.vmax = vmax;
		this.braquage = braquage;
		this.alpha_c = alpha_c;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
		this.vitesse = vitesse;
		this.position = position;
		this.direction = direction;
	}

	// état à l'instant t
	private double vitesse;
	private Vecteur position;
	private Vecteur direction;

	public double getMaxTurn(){
		int i = 0;
		while (this.vitesse>this.tabVitesse[i]*this.vmax){
			i++;
		}
		return this.tabTurn[i];
	}
	
	public void drive(Commande c) {
         // VERIFICATIONS !!!
         // 1) Est ce que la rotation et l'accélération sont entre -1 et 1, sinon, throw new RuntimeException
		 if (c.acc<-1 || c.acc>1 ||c.trun<-1||c.trun>1){
			 throw new RuntimeException("Erreure de vitesse ou de rotation");
		 }
         // 2) Est ce que la rotation demandée est compatible avec la vitesse actuelle, sinon, throw new RuntimeException
		 
		 if(c.getTurn()*this.braquage > this.getMaxTurn()){
			 throw new RuntimeException("Erreure la rotation n'est pas compatible a la vitesse, v ="+this.getVitesse()+"turn = "+c.getTurn()+"turn max = "+this.getMaxTurn());
		 }
         // approche normale
         // 1.1) gestion du volant
         direction = direction.rot(c.getTurn() * braquage); // modif de direction
         // Note: on remarque bien que l'on tourne d'un pourcentage des capacités de la voiture

         // 1.2) garanties, bornes...
         direction = direction.unitVec(); // renormalisation pour éviter les approximations

         // 2.1) gestion des frottements

         vitesse -= alpha_f;
         vitesse -= beta_f*vitesse;

         // 2.2) gestion de l'acceleration/freinage

         vitesse += c.getAcc() * alpha_c;

         vitesse = Math.max(0., vitesse); // pas de vitesse négative (= pas de marche arriere)
         vitesse = Math.min(vmax, vitesse); // pas de dépassement des capacités

         // 3) mise à jour de la position

         position = position.add(direction.fact(vitesse));
 }
	
	public double getVitesse(){
		return this.vitesse;
	}
    public Vecteur getPosition(){
    	return this.position;
    }
    public Vecteur getDirection(){
    	return this.direction;
    }
    public double getBraquage(){
    	return this.braquage;
    }
}
