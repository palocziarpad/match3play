package com.abasc.game.board.rules;

import com.abasc.game.GuiBoard;
import com.abasc.game.ItemType;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalRule implements Rule {
	GuiBoard board;

	List<Element> applyrulesTo;

	public NormalRule(GuiBoard board) {
		this.board = board;
	}

	public boolean check() {
		Gdx.app.log("MyTag", "check called");
		applyrulesTo = new ArrayList<Element>();
		for (byte i = 0; i < board.board.length  ; i++) {

			for (byte j = 0; j < board.board[0].length  ; j++) {
				Gdx.app.log("MyTag", i + " " + j + " " + board.board[i][j].toString());
				// check for horizontal 3
				
				if (j<board.board[0].length - 2&&board.board[i][j] == board.board[i][j + 1] && board.board[i][j] == board.board[i][j + 2]
						&& board.board[i][j] != ItemType.BLANK) {
					Element position = new Element(i, j, (byte) 0);
					addIfNotContains(position);
					position = new Element(i, (byte) (j + 1), (byte) 0);
					addIfNotContains(position);
					position = new Element(i, (byte) (j + 2), (byte) 0);
					addIfNotContains(position);
				} // check for vertical 3
				if (i<board.board.length - 2&&board.board[i][j] == board.board[i + 1][j] && board.board[i + 2][j] == board.board[i][j]
						&& board.board[i][j] != ItemType.BLANK) {
					Element position = new Element(i, j, (byte) 0);
					addIfNotContains(position);
					position = new Element((byte) (i + 1), j, (byte) 0);
					addIfNotContains(position);
					position = new Element((byte) (i + 2), j, (byte) 0);
					addIfNotContains(position);
				}
			}
		}
		return !applyrulesTo.isEmpty();
	}

	public void apply() {
		// Make them empty
		for (Element elem : applyrulesTo) {
			board.board[elem.x][elem.y] = ItemType.BLANK;
		}

	}

	private void addIfNotContains(Element position) {
		if (!applyrulesTo.contains(position)) {
			applyrulesTo.add(position);
		}
	}

	private void addIfNotContains(Element position, List<Element> applyrulesTo) {
		if (!applyrulesTo.contains(position)) {
			applyrulesTo.add(position);
		}
	}

	public void refill() {

		moveBlanksUp();
		 
	/*	 for (Element elem : applyrulesTo) { board.board[elem.x][elem.y] =
		   randInt(1, 4); }*/
		for (byte i = 0; i < board.board.length  ; i++) {

			for (byte j = 0; j < board.board[0].length  ; j++) {
				if( board.board[i][j]==ItemType.BLANK){
					board.board[i][j] = randInt(1, 4);
				}
			}
		 	
			
		}
	}

	public void moveBlanksUp() {
	 //	List<Element> newapplyrulesTo = new ArrayList<Element>();

		for (Element elem : applyrulesTo) {
		 	for (int j = 0; j < board.board[0].length; j++) {
				if (board.board[elem.x][j] == ItemType.BLANK) {
					for (int k = j + 1; k < board.board[0].length; k++) {
						if (board.board[elem.x][k] != ItemType.BLANK) {
							board.switchElements(elem.x, j, elem.x, k);
							//newapplyrulesTo.remove(new Element((byte) elem.x, (byte) j, (byte) 0));
							//addIfNotContains(new Element((byte) (elem.x), (byte) k, (byte) 0), newapplyrulesTo);

						}

					}
				}
			}
		}
	//	applyrulesTo = newapplyrulesTo;

	}

	public static ItemType randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return ItemType.values()[(byte) randomNum];
	}

	public class Element {
		byte x;
		byte y;
		byte action;

		public Element(byte x, byte y, byte action) {
			this.x = x;
			this.y = y;
			this.action = action;
		}
	}
}
