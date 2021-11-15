package com.strategy;

import java.util.List;

import com.tools.Coup;

/*
 * @author Allassane 
 * Adaptatif Commencer avec c,c,c,c,c,c,t,t,t,t,t et choisir c ou le t qui a donné le meilleur score 
 * */

class StrategyAdaptatif implements Strategy {
	

	private int tour = 0;

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if( tour < 6) {
			tour++;
			return Coup.COOPERER;
		}
		else if (tour>5 && tour < 11) {
			tour++;
			return Coup.TRAHIR;
		}
		else {
			tour++;
			return bestMiddleChoice(mineList,ennemiesList);
		}
	}

	private Coup bestMiddleChoice(List<Coup> mineList, List<Coup> ennemiesList) {
		int trahir = 0;
		int cooperer = 0;
		for(int i = mineList.size()-1; (mineList.size()-1)-i< 11; i--) {
			cooperer = scoreCoperer(mineList, ennemiesList);
			trahir = scoreTrahir(mineList, ennemiesList);
		}
	
		return cooperer > trahir ? Coup.COOPERER: Coup.TRAHIR;
	}

	private int scoreTrahir(List<Coup> mineList, List<Coup> ennemiesList) {
		int scoore = 0;

		for(int i = 6; i< mineList.size()-1; i++) {
			if((ennemiesList.get(i) == Coup.COOPERER)) {
				scoore += 5;
			}
			else{
				scoore += 1;
			}

		}

		return scoore;
	}
	
	private int scoreCoperer(List<Coup> mineList, List<Coup> ennemiesList) {
		int scoore = 0;
		int finCooperation = mineList.size()-6;
		
		for(int i = 0; i<= finCooperation; i++) {
			if((ennemiesList.get(i) == Coup.COOPERER)) {
				scoore += 3;
			}
			else{
				scoore += 0;
			}
			
		}
		
		return scoore;
	}
}
