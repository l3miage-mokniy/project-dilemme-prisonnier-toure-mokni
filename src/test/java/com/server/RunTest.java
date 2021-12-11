package com.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.structure.Coup;

class RunTest {

	@Test
	void createPlayerTest() {
		Run r = new Run();
		int idPlayer = r.newPlayer("ismael");
		assertEquals("ismael", r.getPlayers().get(0).getName());
		assertEquals(idPlayer, r.getPlayers().get(0).getId());
	}

	@Test
	void getAllPlayerTest() {
		Run r = new Run();
		int idPlayer = r.newPlayer("ismael");
		assertEquals("ismael", r.getPlayers().get(0).getName());
		assertEquals(idPlayer, r.getPlayers().get(0).getId());
		assertEquals("Nom : ismael, ID : "+idPlayer+"\n", r.getAllPlayers());
	}
	
	@Test
	void createTwoPlayerTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("alice");
		int idPlayer2 = r.newPlayer("titou");
		assertEquals("alice", r.getPlayers().get(0).getName());
		assertEquals(idPlayer1, r.getPlayers().get(0).getId());
		assertEquals("titou", r.getPlayers().get(1).getName());
		assertEquals(idPlayer2, r.getPlayers().get(1).getId());

	}

	@Test
	void createGame() {
		Run r = new Run();
		int idPlayer = r.newPlayer("alice");
		int idGame = r.newGame(0, idPlayer);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());
		assertEquals(idGame, r.getGameOpen().get(0).getId());
	}

	@Test
	void getAllGameOpenTest() {
		Run r = new Run();
		int idPlayer = r.newPlayer("alice");
		int idGame = r.newGame(0, idPlayer);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());
		assertEquals(idGame, r.getGameOpen().get(0).getId());
		assertEquals("Rencontre num : "+idGame+" elle se joue en 0 tours.", r.getAllGameOpen());
		int idGame2 = r.newGame(10, idPlayer);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(1).getCreateur());
		assertEquals(10, r.getGameOpen().get(1).getNumberOfTurn());
		assertEquals(idGame2, r.getGameOpen().get(1).getId());
		assertEquals("Rencontre num : "+idGame+" elle se joue en 0 tours.#Rencontre num : "+idGame2+" elle se joue en 10 tours.", r.getAllGameOpen());
	}

	@Test
	void leaveGameTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");

		int idGame = r.newGame(0, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());

		r.joinGame(idGame, idPlayer2);
		r.leaveGame(idPlayer1, 0, idGame);
		assertEquals(true, r.getGameClosed().get(0).isHaveLeaveJ1());
	}

	@Test
	void joinAndCloseGame() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");
		assertEquals(2, r.getPlayers().size());

		int idGame = r.newGame(0, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());

		assertEquals(1, r.getGameOpen().size());
		assertEquals(0, r.getGameClosed().size());
		assertEquals(true, r.joinGame(idGame, idPlayer2));
		assertEquals(r.getPlayers().get(1), r.getGameClosed().get(0).getJoueur2());
		assertEquals(0, r.getGameOpen().size());
		assertEquals(1, r.getGameClosed().size());
	}
	

	@Test
	void getAllGameCloseTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");
		assertEquals(2, r.getPlayers().size());

		int idGame = r.newGame(0, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());
		assertEquals(idGame, r.getGameOpen().get(0).getId());

		assertEquals(1, r.getGameOpen().size());
		assertEquals(0, r.getGameClosed().size());
		assertEquals(true, r.joinGame(idGame, idPlayer2));
		assertEquals(r.getPlayers().get(1), r.getGameClosed().get(0).getJoueur2());
		assertEquals(0, r.getGameOpen().size());
		assertEquals(1, r.getGameClosed().size());
		assertEquals("Rencontre num : "+idGame+" elle se joue en 0 tours.", r.getAllGameClose());
		

		int idGame2 = r.newGame(40, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(40, r.getGameOpen().get(0).getNumberOfTurn());
		assertEquals(idGame2, r.getGameOpen().get(0).getId());
		assertEquals(1, r.getGameOpen().size());
		assertEquals(1, r.getGameClosed().size());
		assertEquals(true, r.joinGame(idGame2, idPlayer2));
		assertEquals(r.getPlayers().get(1), r.getGameClosed().get(0).getJoueur2());
		assertEquals(0, r.getGameOpen().size());
		assertEquals(2, r.getGameClosed().size());
		assertEquals("Rencontre num : "+idGame+" elle se joue en 0 tours.#Rencontre num : "+idGame2+" elle se joue en 40 tours.", r.getAllGameClose());
	}


	@Test
	void haveJoinGameTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");
		assertEquals(2, r.getPlayers().size());

		int idGame = r.newGame(0, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(0, r.getGameOpen().get(0).getNumberOfTurn());

		assertEquals(1, r.getGameOpen().size());
		assertEquals(0, r.getGameClosed().size());
		assertEquals(true, r.joinGame(idGame, idPlayer2));
		assertEquals(true, r.haveJoin(idGame));
		assertEquals(r.getPlayers().get(1), r.getGameClosed().get(0).getJoueur2());
		assertEquals(0, r.getGameOpen().size());
		assertEquals(1, r.getGameClosed().size());
	}

	@Test
	void gameFinishedTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");
		int idGame = r.newGame(1, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(1, r.getGameOpen().get(0).getNumberOfTurn());

		r.joinGame(idGame, idPlayer2);
		r.leaveGame(idPlayer1, 0, idGame);
		assertEquals(true, r.getGameClosed().get(0).isHaveLeaveJ1());
		assertEquals(false, r.gameFinished(idGame));
		r.play(idPlayer2, idGame, 0);
		assertEquals(true, r.gameFinished(idGame));
	}

	@Test
	void playTest() {
		Run r = new Run();
		int idPlayer1 = r.newPlayer("titou");
		int idPlayer2 = r.newPlayer("alice");

		int idGame = r.newGame(3, idPlayer1);
		assertEquals(r.getPlayers().get(0), r.getGameOpen().get(0).getCreateur());
		assertEquals(3, r.getGameOpen().get(0).getNumberOfTurn());

		r.joinGame(idGame, idPlayer2);
		r.leaveGame(idPlayer1, 0, idGame);
		assertEquals(true, r.getGameClosed().get(0).isHaveLeaveJ1());
		assertEquals(false, r.gameFinished(idGame));
		r.play(idPlayer2, idGame, 0);
		r.play(idPlayer2, idGame, 0);
		r.play(idPlayer2, idGame, 0);
		assertEquals(true, r.gameFinished(idGame));

		assertEquals(0, r.getGameClosed().get(0).getScore1());
		assertEquals(15, r.getGameClosed().get(0).getScore2());
		assertEquals(Coup.TRAHIR, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ1());
		assertEquals(Coup.TRAHIR, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ1());
		assertEquals(Coup.TRAHIR, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ2());
		assertEquals(Coup.COOPERER, r.getGameClosed().get(0).getAllTurn().get(0).getCoupJ1());
	}
}
