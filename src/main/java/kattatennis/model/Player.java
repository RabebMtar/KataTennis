package kattatennis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

	private String name;
	private int gameScore;
	private int nbrGameWon;
	private int numberSetWon;
	private int tieBreakPoint;

	public Player() {
		super();
	}

	public Player(String name) {
		super();
		this.name = name;
		this.gameScore = 0;
		this.nbrGameWon = 0;
	}
	
	public void winPoint(){
		this.gameScore++;
	}
	
	public void winTieBreakPoint() {
		this.tieBreakPoint++;
	}

	public int getRealScore() {
		switch (this.gameScore) {
		case 0:
			return 0;
		case 1:
			return 15;
		case 2:
			return 30;
		default:
			return 40;
		}
	}

	@Override
	public String toString() {
		return name ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public int getNbrGameWon() {
		return nbrGameWon;
	}

	public void setNbrGameWon(int nbrGameWon) {
		this.nbrGameWon = nbrGameWon;
	}

	public int getNumberSetWon() {
		return numberSetWon;
	}

	public void setNumberSetWon(int numberSetWon) {
		this.numberSetWon = numberSetWon;
	}

	public int getTieBreakPoint() {
		return tieBreakPoint;
	}

	public void setTieBreakPoint(int tieBreakPoint) {
		this.tieBreakPoint = tieBreakPoint;
	}

}
