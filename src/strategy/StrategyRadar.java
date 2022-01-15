package strategy;


import radar.Radar;
import radar.RadarClassique;
import voiture.Commande;
import voiture.Voiture;

public class StrategyRadar implements Strategy{
	//	private Commande c; 
	private Voiture voit;
	private Radar rad;

	public StrategyRadar(RadarClassique r, Voiture voit) {
		super();
		this.voit = voit;
		this.rad = r;
	}


	@Override
	public Commande getCommande() {
		rad.scores();
		double acc = 0.1;
		int indRayon = rad.getBestIndex();
		double rAngle = rad.thetas()[indRayon];
		double cturn = Math.min( Math.abs(rAngle/voit.getBraquage()),
				voit.getMaxTurn())*Math.signum(rAngle);
		if(Math.abs(rAngle/voit.getBraquage()) < voit.getMaxTurn()){
			acc = 0.1;
		}
		if(Math.abs(rAngle/voit.getBraquage()) > voit.getMaxTurn()){
			acc = -0.3;
		}
	return new Commande(acc,cturn);
	}
}


