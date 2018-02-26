package com.abasc.game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemTypeTest  {
    @Test
   public void testToggle() {
        ItemType item = ItemType.ATTACK;
        ItemType item2 = ItemType.toggle(ItemType.ATTACK);
        Assert.assertNotEquals(item,item2);
        Assert.assertEquals(ItemType.ATTACKHIGHLIGHT,item2);
    }
    @Test
    public void testIsHighLighted() {
        Assert.assertTrue(ItemType.isHighLighted(ItemType.ATTACKHIGHLIGHT));
        Assert.assertFalse(ItemType.isHighLighted(ItemType.ATTACK));
    }
    @Test
    public void testIsHighLighted1() {
        Assert.assertTrue(ItemType.isHighLighted((byte)ItemType.SPECIALHIGHLIGHT.ordinal()));
        Assert.assertFalse(ItemType.isHighLighted( (byte)ItemType.SPECIAL.ordinal()));
    }
}
