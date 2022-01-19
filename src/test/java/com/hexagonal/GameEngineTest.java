package com.hexagonal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.server.Run;
import com.structure.Coup;

class GameEngineTest {
	
    
    private IRequestPlay requesterGameEngine;
    
    @BeforeEach 
    void init() {
    	this.requesterGameEngine = new GameEngine();
    }

	@Test
	void createPlayerTest() {
		int idJoueur = requesterGameEngine.createJoueur("ismael");
		assertEquals("ismael", requesterGameEngine.getAllJoueur().get(0).getName());
		assertEquals(idJoueur, requesterGameEngine.getAllJoueur().get(0).getId());
	}
	
	@Test
	void createTwoPlayerTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");
		assertEquals("alice", requesterGameEngine.getAllJoueur().get(0).getName());
		assertEquals(idJoueur1, requesterGameEngine.getAllJoueur().get(0).getId());
		assertEquals("titou", requesterGameEngine.getAllJoueur().get(1).getName());
		assertEquals(idJoueur2, requesterGameEngine.getAllJoueur().get(1).getId());
	}

	@Test
	void createGame() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);
		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());
		assertEquals(idRencontre, requesterGameEngine.getRencontreOpen().get(0).getId());
	}

	@Test
	void getAllGameOpenTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);
		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());
		assertEquals(idRencontre, requesterGameEngine.getRencontreOpen().get(0).getId());
		int idRencontre2 = requesterGameEngine.createRencontre(10, idJoueur1);
		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(1).getCreateur());
		assertEquals(10, requesterGameEngine.getRencontreOpen().get(1).getNumberOfTurn());
		assertEquals(idRencontre2, requesterGameEngine.getRencontreOpen().get(1).getId());
		assertEquals(2, requesterGameEngine.getRencontreOpen().size());
	}

	@Test
	void leaveGameTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");
		
		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());

		requesterGameEngine.joinRencontre(idRencontre, idJoueur2);
		requesterGameEngine.leaveRencontre(idRencontre, idJoueur1, 0);
		assertEquals(true, requesterGameEngine.getRencontreClosed().get(0).isHaveLeaveJ1());
	}

	@Test
	void joinAndCloseGame() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");
		assertEquals(2, requesterGameEngine.getAllJoueur().size());

		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());

		assertEquals(1, requesterGameEngine.getRencontreOpen().size());
		assertEquals(0, requesterGameEngine.getRencontreClosed().size());
		assertEquals(true, requesterGameEngine.joinRencontre(idRencontre, idJoueur2));
		assertEquals(requesterGameEngine.getAllJoueur().get(1), requesterGameEngine.getRencontreClosed().get(0).getJoueur2());
		assertEquals(0, requesterGameEngine.getRencontreOpen().size());
		assertEquals(1, requesterGameEngine.getRencontreClosed().size());
	}
	
	@Test
	void getAllGameCloseTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");
		assertEquals(2, requesterGameEngine.getAllJoueur().size());

		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());
		assertEquals(idRencontre, requesterGameEngine.getRencontreOpen().get(0).getId());
		assertEquals(1, requesterGameEngine.getRencontreOpen().size());
		assertEquals(0, requesterGameEngine.getRencontreClosed().size());
		assertEquals(true, requesterGameEngine.joinRencontre(idRencontre, idJoueur2));
		assertEquals(requesterGameEngine.getAllJoueur().get(1), requesterGameEngine.getRencontreClosed().get(0).getJoueur2());
		assertEquals(0, requesterGameEngine.getRencontreOpen().size());
		assertEquals(1, requesterGameEngine.getRencontreClosed().size());

		int idRencontre2 = requesterGameEngine.createRencontre(40, idJoueur1);
		
		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(40, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());
		assertEquals(idRencontre2, requesterGameEngine.getRencontreOpen().get(0).getId());
		assertEquals(1, requesterGameEngine.getRencontreOpen().size());
		assertEquals(1, requesterGameEngine.getRencontreClosed().size());
		assertEquals(true, requesterGameEngine.joinRencontre(idRencontre2, idJoueur2));
		assertEquals(requesterGameEngine.getAllJoueur().get(1), requesterGameEngine.getRencontreClosed().get(1).getJoueur2());
		assertEquals(0, requesterGameEngine.getRencontreOpen().size());
		assertEquals(2, requesterGameEngine.getRencontreClosed().size());
		
	}

	@Test
	void haveJoinGameTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");
		assertEquals(2, requesterGameEngine.getAllJoueur().size());
		
		int idRencontre = requesterGameEngine.createRencontre(0, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(0, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());
		
		assertEquals(1, requesterGameEngine.getRencontreOpen().size());
		assertEquals(0, requesterGameEngine.getRencontreClosed().size());
		assertEquals(true, requesterGameEngine.joinRencontre(idRencontre, idJoueur2));
		assertEquals(true, requesterGameEngine.haveJoin(idRencontre));
		assertEquals(requesterGameEngine.getAllJoueur().get(1), requesterGameEngine.getRencontreClosed().get(0).getJoueur2());
		assertEquals(0, requesterGameEngine.getRencontreOpen().size());
		assertEquals(1, requesterGameEngine.getRencontreClosed().size());
	}

	@Test
	void gameFinishedTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");		
		int idRencontre = requesterGameEngine.createRencontre(1, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(1, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());

		requesterGameEngine.joinRencontre(idRencontre, idJoueur2);
		requesterGameEngine.leaveRencontre(idRencontre, idJoueur1, 0);
		assertEquals(true, requesterGameEngine.getRencontreClosed().get(0).isHaveLeaveJ1());
		assertEquals(false, requesterGameEngine.rencontreFinished(idRencontre));
		requesterGameEngine.play(idRencontre, idJoueur2, 0);
		assertEquals(true, requesterGameEngine.rencontreFinished(idRencontre));
	}

	@Test
	void playTest() {
		int idJoueur1 = requesterGameEngine.createJoueur("alice");
		int idJoueur2 = requesterGameEngine.createJoueur("titou");		
		int idRencontre = requesterGameEngine.createRencontre(3, idJoueur1);

		assertEquals(requesterGameEngine.getAllJoueur().get(0), requesterGameEngine.getRencontreOpen().get(0).getCreateur());
		assertEquals(3, requesterGameEngine.getRencontreOpen().get(0).getNumberOfTurn());

		requesterGameEngine.joinRencontre(idRencontre, idJoueur2);
		requesterGameEngine.leaveRencontre(idRencontre, idJoueur1, 0);

		assertEquals(true, requesterGameEngine.getRencontreClosed().get(0).isHaveLeaveJ1());
		assertEquals(false, requesterGameEngine.rencontreFinished(idRencontre));
		requesterGameEngine.play(idRencontre, idJoueur2, 0);
		requesterGameEngine.play(idRencontre, idJoueur2, 0);
		requesterGameEngine.play(idRencontre, idJoueur2, 0);
		assertEquals(true, requesterGameEngine.rencontreFinished(idRencontre));

		assertEquals(0, requesterGameEngine.getRencontreClosed().get(0).getScore1());
		assertEquals(15, requesterGameEngine.getRencontreClosed().get(0).getScore2());
		assertEquals(Coup.TRAHIR, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ1());
		assertEquals(Coup.TRAHIR, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ1());
		assertEquals(Coup.TRAHIR, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, requesterGameEngine.getRencontreClosed().get(0).getAllTurn().get(0).getCoupJ1());
	}
	
}
