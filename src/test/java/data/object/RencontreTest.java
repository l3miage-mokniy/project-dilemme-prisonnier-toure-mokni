package data.object;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tools.Coup;

class RencontreTest {
	@Test
	void haveJoinTest() {
		Joueur j1 = new Joueur("Yanis");
		Joueur j2 = new Joueur("Antoine");
		Joueur j3 = new Joueur("Nathan");
		
		Rencontre r1 = new Rencontre(0, j1);		
		assertEquals(false,r1.haveJoin());
		assertEquals(j1,r1.getCreateur());
		
		boolean joinOne = j2.joinParty(r1);
		assertEquals(true,joinOne);
		assertEquals(true,r1.haveJoin());
		
		joinOne = j3.joinParty(r1);
		assertEquals(false,joinOne);
		
		assertEquals(j2,r1.getJoueur2());
	}
	
	@Test
	void gameFinishedTest() {
		Joueur j1 = new Joueur("Yanis");
		j1.setId(999);
		Joueur j2 = new Joueur("Antoine");
		Joueur j3 = new Joueur("Nathan");
		
		Rencontre r1 = new Rencontre(1, j1);		
		assertEquals(false,r1.haveJoin());
		assertEquals(j1,r1.getCreateur());
		
		boolean joinOne = j2.joinParty(r1);
		assertEquals(true,joinOne);
		assertEquals(true,r1.haveJoin());
		
		joinOne = j3.joinParty(r1);
		assertEquals(false,joinOne);
		
		assertEquals(j2,r1.getJoueur2());
		
		assertEquals(false,r1.gameFinished());
		r1.leave(j2, 0);
		r1.play(999, Coup.COOPERER);
		assertEquals(true,r1.gameFinished());
		assertEquals(1,r1.getNumberOfTurn());
		
	}
	
	@Test
	void leaveTest() {
		Joueur j1 = new Joueur("Yanis");
		Joueur j2 = new Joueur("Antoine");
		
		Rencontre r1 = new Rencontre(1, j1);		
		assertEquals(false,r1.haveJoin());
		assertEquals(j1,r1.getCreateur());
		boolean joinOne = j2.joinParty(r1);
		assertEquals(true, joinOne);
		assertEquals(true,r1.haveJoin());
		assertEquals(j2,r1.getJoueur2());

		assertEquals(false,r1.isHaveLeaveJ1());
		assertEquals(false,r1.isHaveLeaveJ2());
		r1.leave(j1, 0);
		assertEquals(true,r1.isHaveLeaveJ1());
		r1.leave(j2, 0);
		assertEquals(true,r1.isHaveLeaveJ2());
		
	}
}
