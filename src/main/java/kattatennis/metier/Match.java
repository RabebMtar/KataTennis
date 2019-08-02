package kattatennis.metier;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import kattatennis.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
	final static Logger logger = Logger.getLogger(Match.class);
	private Map<String, String> sets;
	private Set set;

	public Match(Set set) {
		super();
		this.sets = new LinkedHashMap<String, String>();
		this.set = set;
	}

	public void playMatch() {
		int index=0;
		while (this.set.getGame().getPlayer1().getNumberSetWon() < 3 && this.set.getGame().getPlayer2().getNumberSetWon() < 3) {
			logger.info("********************* SET:" + String.valueOf(++index) + " *********************");
			logger.info("Player One :"+set.getGame().getPlayer1().getName());
			logger.info("Player Two :"+set.getGame().getPlayer2().getName());
			logger.info("Score :"+set.getGame().getOldScores()+"("+set.getGame().getPlayer1().getNbrGameWon()+","+set.getGame().getPlayer2().getNbrGameWon()+")");
			logger.info("Current game status :"+set.getGame().getScore());
			logger.info("\n\n");
			this.set.playSet();
			sets.put("SET" + String.valueOf(index)+": ", this.set.getScore());
			
		}
	}

	public Player getWinner() {
		if (this.set.getGame().getPlayer1().getNumberSetWon()>this.set.getGame().getPlayer2().getNumberSetWon())
			return this.set.getGame().getPlayer1();
		else
			return this.set.getGame().getPlayer2();
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public Map<String, String> getSets() {
		return sets;
	}

	public void setSets(Map<String, String> sets) {
		this.sets = sets;
	}

}
