package com.object;

import com.strategy.StrategyFactory;
import strategy.Strategy;

public class Joueur {

	private int id;
	private String name;
	private Strategy strategy;
	
	public Joueur(String name) {
		super();
		this.name = name;
	}
	
	public void leave(int idStrategy) {
		this.strategy = StrategyFactory.getStrategy(idStrategy);
	}
	
	public boolean joinGame(Rencontre r) {
		return r.joinGame(this);
	}

	public String getName() {
		return name;
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
