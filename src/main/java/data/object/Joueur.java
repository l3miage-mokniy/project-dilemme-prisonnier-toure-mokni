package data.object;

import data.object.Rencontre;
import com.strategy.Strategy;
import com.strategy.StrategyFactory;

public class Joueur {

	private int id;
	private String name;
	private boolean haveLeave = false;
	private Strategy strategy;
	
	public Joueur(String name) {
		super();
		this.name = name;
	}
	
	public void leave(int id_strategy) {
		this.strategy = StrategyFactory.getStrategy(id_strategy);
		this.haveLeave = true;		
	}
	
	public boolean joinParty(Rencontre r) {
		return r.joinParty(this);
	}

	public String getName() {
		return name;
	}

	public boolean isHaveLeave() {
		return haveLeave;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
