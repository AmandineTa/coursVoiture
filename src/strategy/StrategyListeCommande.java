package strategy;

import java.util.ArrayList;

import voiture.Commande;

public  class StrategyListeCommande implements Strategy {

	private ArrayList<Commande> listCom;
	
	public StrategyListeCommande(ArrayList<Commande> listCom) {
		super();
		this.listCom.addAll(listCom);
	}

	public Commande getCommande() {
		return this.listCom.get(0);
	}
}
