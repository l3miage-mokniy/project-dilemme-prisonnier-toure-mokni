package com.strategy;

import java.util.List;

import strategy.*;
import com.tools.Coefficient;

/*
 * @author Allassane 
 * Adaptatif Commencer avec c,c,c,c,c,c,t,t,t,t,t et choisir c ou le t qui a donné le meilleur score 
 * 
 */
class StrategyAdaptatif implements Strategy {
	private Coup toSend = null;
	private int tour = 0;

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		tour++;
		if (tour <= 6) {
			return Coup.COOPERER;
		} else if (tour <= 11) {
			return Coup.TRAHIR;
		} else {
			if (this.toSend == null) {
				this.setCoup(mineList, ennemiesList);
			}
			return this.toSend;
		}
	}

	private void setCoup(List<Coup> mineList, List<Coup> ennemiesList) {
		int scoreTrahir = 0;
		int scoreCooperer = 0;

		for (int i = 1; i < mineList.size(); i++) {
			if (i < 5) {
				if (ennemiesList.get(ennemiesList.size() - i) == Coup.COOPERER) {
					scoreTrahir += Coefficient.T.getPoint();
				} else {
					scoreTrahir += Coefficient.P.getPoint();
				}
			} else {
				if (ennemiesList.get(ennemiesList.size() - i) == Coup.COOPERER) {
					scoreCooperer += Coefficient.C.getPoint();
				} else {
					scoreCooperer += Coefficient.D.getPoint();
				}
			}
		}
		if ((scoreTrahir / 5) > (scoreCooperer / 6)) {
			this.toSend = Coup.TRAHIR;
		} else {
			this.toSend = Coup.COOPERER;
		}
	}
}
