package strategy;


import radar.RadarDijk;
import voiture.Commande;
import voiture.Voiture;

public class StrategyDijk implements Strategy{
	//	private Commande c; 
	private Voiture voit;
	private RadarDijk rad;

	public StrategyDijk(RadarDijk r, Voiture voit) {
		super();
		this.voit = voit;
		this.rad = r;
	}


	@Override
	public Commande getCommande() {
		rad.scores();
		double acc = 0.1;
		int bestID = rad.getBestIndex();
		double rAngle = rad.thetas()[bestID];
		double cturn = Math.min( Math.abs(rAngle/voit.getBraquage()),
				voit.getMaxTurn())*Math.signum(rAngle);
		if(Math.abs(rAngle/voit.getBraquage()) < voit.getMaxTurn()){
			acc = 0.2;
		}
		if(Math.abs(rAngle/voit.getBraquage()) > voit.getMaxTurn()){
			acc = -0.1;
		}
	return new Commande(acc,cturn);
	}
}