package strategy;


import voiture.Commande;

public  class StrategyToutDroit implements Strategy{

	
	public StrategyToutDroit() {
		super();
	
	}
	
	public Commande getCommande(){
		return new Commande(1,0);
	}


}
