package com.adapter;

import newstrategy.AlwaysBetray;
import newstrategy.AlwaysCooperate;
import newstrategy.GiveOrTake;
import local.round.Choice;
import local.round.Player;
import java.util.List;

import com.structure.Coup;
import com.structure.Strategy;

import com.tools.ConverterChoice;

public class StrategyAdapter implements Strategy {

	private newstrategy.Strategy stratChoosObject;
	private Player me;
	private Player ennemies;
	
	public StrategyAdapter(int stratChoose) {
		super();
		this.stratChoosObject = this.defineStrategy(stratChoose-18);
		this.me = new Player();
		this.ennemies = new Player();
	}
	
	private newstrategy.Strategy defineStrategy(int stratChoose) {
		switch(stratChoose) {
		case 0:
			return new AlwaysBetray();
		case 1:
			return new AlwaysCooperate();			
		case 2:
			return new GiveOrTake();				
		default:
			return new AlwaysCooperate();			
		}
	}

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		this.me.setChoices(ConverterChoice.convertListCoupToListChoice(mineList));
		this.ennemies.setChoices(ConverterChoice.convertListCoupToListChoice(ennemiesList));
		Choice c = this.stratChoosObject.getChoice(me, ennemies);
		return ConverterChoice.convertChoiceToCoup(c);
	}

	public newstrategy.Strategy getStratChoosObject() {
		return stratChoosObject;
	}

}
