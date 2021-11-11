package data.object;

/*
import object.Rencontre;
import strategy.Strategy;
import strategy.StrategyFactory;
*/

public class Joueur {

	private String name;
	private boolean haveLeave = false;
	//private Strategy strategy;
	
	public Joueur(String name) {
		super();
		this.name = name;
	}
	
	public void leave(int id_strategy) {
		//this.strategy = StrategyFactory.getStrategy(id_strategy);
		this.haveLeave = true;		
	}
	
	/*
	public Rencontre createParty(int nb_tours) {
		return new Rencontre(nb_tours, this);
	}
	*/
	
	/*
	public boolean joinParty(Rencontre r) {
		return r.joinParty(this);
	}
	*/

	public String getName() {
		return name;
	}

	public boolean isHaveLeave() {
		return haveLeave;
	}

	/*
	public Strategy getStrategy() {
		return strategy;
	}
	*/
	

}
