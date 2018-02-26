package com.abasc.game;

import com.abasc.game.GuiBoard;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardTest {
    @Test
    public void initFill() throws Exception {
    }

    @Test
    public void switchElementsAndUnToggle()  {
        GuiBoard board= new GuiBoard();
        byte item1x=0;
        byte item1y=0;
        byte item2x=0;
        byte item2y=1;
        board.board[item1x][item1y]=  ItemType.SPECIALHIGHLIGHT  ;
        board.board[item2x][item2y]= ItemType.DEFENDHIGHLIGHT ;
        ItemType item1 =board.board[item1x][item1y];
        ItemType item2 =board.board[item2x][item2y];
        Assert.assertNotEquals(item1,item2);

        board.switchElementsAndUnToggle(item1x,item1y,item2x,item2y);

        System.out.println(board.board[item1x][item1y] +" "+board.board[item2x][item2y]);
        Assert.assertFalse(ItemType.isHighLighted(board.board[item1x][item1y]));
        Assert.assertFalse(ItemType.isHighLighted(board.board[item2x][item2y]));

    }

    @Test
    public void testswitchElements(){

        GuiBoard board= new GuiBoard();
        byte item1x=0;
        byte item1y=0;
        byte item2x=0;
        byte item2y=1;
        ItemType item1 =board.board[item1x][item1y];
        ItemType item2 =board.board[item2x][item2y];
        Assert.assertNotEquals(item1,item2);
        board.switchElements(item1x,item1y,item2x,item2y);
        Assert.assertEquals(item1,board.board[item2x][item2y]);
        Assert.assertEquals(item2,board.board[item1x][item1y]);

    }
}
