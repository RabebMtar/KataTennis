import org.apache.log4j.Logger;

import kattatennis.metier.Game;
import kattatennis.metier.Match;
import kattatennis.metier.Set;
import kattatennis.model.Player;


public class ExampleKattaTennis {

	final static Logger logger = Logger.getLogger(ExampleKattaTennis.class);

	public static void main(String[] args) {
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		Game game = new Game(player1, player2);
		Set set = new Set(game);
		Match match = new Match(set);
		match.playMatch();
		logger.info("\n\n");
		logger.info("Player One :"+player1.getName());
		logger.info("Player Two :"+player2.getName());
		logger.info("Score :"+game.getOldScores());
		logger.info("Match Status : "+match.getWinner().toString()+ "  Winner in the Match ");
		logger.info("\n\n");
	}

}
