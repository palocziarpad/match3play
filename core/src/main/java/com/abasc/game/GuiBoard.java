package com.abasc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiBoard {

	public static final int CELLSIZE = 32;
	public ItemType[][] board;
	byte DEAFAULT_BOARD_WIDTH = 15;
	byte DEAFAULT_BOARD_HEIGHT = 20;

	Texture attack;
	Texture defend;
	Texture evade;
	Texture special;

	public GuiBoard() {
		this.board = new ItemType[DEAFAULT_BOARD_HEIGHT][DEAFAULT_BOARD_WIDTH];
		initFill();

	}

	public void initFill() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int derived = (j + i) % 4;
				switch (derived) {
				case 0:
					board[i][j] = ItemType.EVADE;
					break;
				case 1:
					board[i][j] = ItemType.DEFEND;
					break;
				case 2:
					board[i][j] = ItemType.ATTACK;
					break;
				case 3:
					board[i][j] = ItemType.SPECIAL;
					break;
				default:
					board[i][j] = ItemType.BLANK;
				}
			}
		}
	}

	public void draw(SpriteBatch batch) {
		ItemTexture texture = new ItemTexture();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				batch.draw(texture.textureBasedOnType(board[i][j]), i * CELLSIZE, j * CELLSIZE, CELLSIZE, CELLSIZE);
			}
		}
	}

	public void switchElements(int x, int y, int a, int b) {
		ItemType temp = board[x][y];
		board[x][y] = board[a][b];
		board[a][b] = temp;
	}

	public void switchElementsAndUnToggle(int x, int y, int a, int b) {
		if(!isSwitchPossible(x, y, a, b)){
			return;
		}
		if (ItemType.isHighLighted(board[x][y])) {
			board[x][y] = ItemType.toggle(board[x][y]);
		}

		if (ItemType.isHighLighted(board[a][b])) {
			board[a][b] = ItemType.toggle(board[a][b]);
		}
		switchElements(x, y, a, b);
	}
	
	public boolean isSwitchPossible(int x, int y, int a, int b){
		return true;// Math.abs(x-a)<=1&&Math.abs(y-b)<=1;
	}

}
