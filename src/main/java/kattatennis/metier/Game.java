package kattatennis.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import kattatennis.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
	final static Logger logger = Logger.getLogger(Game.class);
	private Player player1;
	private Player player2;
	private boolean isGameEnded;
	private boolean isTieBreakEnded;
	List<String> oldSets=new ArrayList<>();

	public Game(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.isGameEnded = false;
		this.isTieBreakEnded = false;
	}

	//get Score for a tie break game
	public String getTieBreakScore() {
		if((player1.getTieBreakPoint()>=7 && player2.getTieBreakPoint()<=player1.getTieBreakPoint()-2) || (player2.getTieBreakPoint()>=7 && player1.getTieBreakPoint()<=player2.getTieBreakPoint()-2)) {
			winTieBreakGame(player1);
			return "_________________" + player1.getName() + " won the Tie Break game" + "_________________ \n\n\n";
		}else if(player2.getTieBreakPoint()>=7 && player1.getTieBreakPoint()<=player2.getTieBreakPoint()-2) {
			winTieBreakGame(player2);
			return "_________________" + player2.getName() + " won the Tie Break game" + "_________________ \n\n\n";
		}
		return player1.getTieBreakPoint() + " - " + player2.getTieBreakPoint();
	}
   
	/**
	 * return the name of player win a game
	 * @return
	 */
	private String getPlayerNameWinGame() {
		if (player1.getGameScore() == 4) {
			winGame(player1);
			return "_________________" + player1.getName() + " won the game" + "_________________ ";
		}
		if (player2.getGameScore() == 4) {
			winGame(player2);
			return "_________________" + player2.getName() + " won the game" + "_________________ ";
		}
		return player1.getRealScore() + " - " + player2.getRealScore();
	
	}
	/**
	 * return the name of player deuce state
	 * @return
	 */
	private String getPlayerDeuceState() {
		StringBuilder gameStatut=new StringBuilder();
		// Avantage to player1
		if (player1.getGameScore() == 4 && player2.getGameScore() == 3)
			gameStatut.append("AVANTAGE " ).append( player1.getName());
		// Avantage to player2
		if (player1.getGameScore() == 3 && player2.getGameScore() == 4) 
			gameStatut.append("AVANTAGE ").append( player2.getName());
		// Point + Avantage Player1
		if (player1.getGameScore() == 5 && player2.getGameScore() == 3) {
			winGame(player1);
			gameStatut.append("_________________" ).append(player1.getName()).append( " won the game _________________");
		}
		// Point + Avantage Player2
		if (player1.getGameScore() == 3 && player2.getGameScore() == 5) {
			winGame(player2);
			gameStatut.append("_________________").append(player2.getName()).append(" won the game _________________ ");
		}
		return gameStatut.toString();
	
	}
	//get Score for a normal game
	public String getScore() {
		// Equality
		if (player1.getGameScore() == player2.getGameScore())
			return translateScore(player1.getGameScore());
		// Deuce
		if (player1.getGameScore() >= 3 && player2.getGameScore() >= 3) {
			return getPlayerDeuceState();
		} else {
			return getPlayerNameWinGame();
		}
	}

	public void updateScoreAfterDeuce() {
		player1.setGameScore(3);
		player2.setGameScore(3);
	}

	public void winGame(Player player) {
		this.isGameEnded = true;
		player1.setGameScore(0);
		player2.setGameScore(0);
		player.setNbrGameWon(player.getNbrGameWon() + 1);
	}

	public void winTieBreakGame(Player player) {
		this.isTieBreakEnded = true;
		player.setNbrGameWon(player.getNbrGameWon() + 1);
	}

	public void initializeNbrGameWon() {
		player1.setNbrGameWon(0);
		player2.setNbrGameWon(0);
	}

	public void initializeGameScore() {
		player1.setGameScore(0);
		player2.setGameScore(0);
	}
	private Integer randomPointWinner() {
        return new Random().nextInt(2) + 1;
    }
	public void whoIsThePlayerWinPoint() {
		switch (randomPointWinner()) {
		case 1:
			this.player1.winPoint();
			break;
		case 2:
			this.player2.winPoint();
			break;
		default:
			break;
		}
	}

	public void whoIsThePlayerWinTieBreak() {
		switch (randomPointWinner()) {
		case 1:
			this.player1.winTieBreakPoint();
			break;
		case 2:
			this.player2.winTieBreakPoint();
			break;
		default:
			break;
		}
	}
	/**
	 * play the game
	 */
	public void playGame() {
		while (!this.isGameEnded) {
			whoIsThePlayerWinPoint();
			logger.info("Player One :"+player1.getName());
			logger.info("Player Two :"+player2.getName());
			logger.info("Current game status :"+getScore());
			logger.info("Score :"+getOldScores()+"("+getPlayer1().getNbrGameWon()+","+getPlayer2().getNbrGameWon()+")");
			logger.info("Match Status :in progress");
			logger.info("\n\n");
		}
		this.setGameEnded(false);
		if((!(player1.getNumberSetWon()==3 || player2.getNumberSetWon()==3 ))
				&& !((getPlayer1().getNbrGameWon() == 6 || getPlayer2().getNbrGameWon()==6)
						&& Math.abs(getPlayer1().getNbrGameWon()-getPlayer2().getNbrGameWon()) >=2 )
						|| (getPlayer1().getNbrGameWon() == 7 || getPlayer2().getNbrGameWon()==7)) {
			logger.info("Player One :"+player1.getName());
			logger.info("Player Two :"+player2.getName());
			logger.info("Score :"+getOldScores()+"("+getPlayer1().getNbrGameWon()+","+getPlayer2().getNbrGameWon()+")");
			logger.info("Current game status :"+getScore());
			logger.info("\n\n");
		}
		
	}
	/**
	 * get list of old scores
	 * @return
	 */
    public String getOldScores() {
    	final StringBuilder oldScores=new StringBuilder(player1.getGameScore());
    	getOldSets().stream().forEach(set-> oldScores.append(set));
		return oldScores.toString();
    }
	
	/**
	 * get score of current players
	 * @param score
	 * @return
	 */
	private String translateScore(int score) {
		switch (score) {
		case 0:
			return "0 - 0";
		case 1:
			return "15 - 15";
		case 2:
			return "30 - 30";
		case 3:
			return "40 - 40";
		default :
			updateScoreAfterDeuce();
			return "40 - 40";

		}
	}
	public void playTieBreakGame() {
		while (!this.isTieBreakEnded) {
			whoIsThePlayerWinTieBreak();
			logger.info(getTieBreakScore());
		}
		this.setTieBreakEnded(false);
	}

	public boolean isGameEnded() {
		return isGameEnded;
	}

	public void setGameEnded(boolean isGameEnded) {
		this.isGameEnded = isGameEnded;
	}

	public boolean isTieBreakEnded() {
		return isTieBreakEnded;
	}

	public void setTieBreakEnded(boolean isTieBreakEnded) {
		this.isTieBreakEnded = isTieBreakEnded;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public List<String> getOldSets() {
		return oldSets;
	}

	public void setOldSets(List<String> oldSets) {
		this.oldSets = oldSets;
	}

}
