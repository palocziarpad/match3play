package com.abasc.game;

public enum ItemType {
    BLANK,
    ATTACK,
    DEFEND,
    EVADE,
    SPECIAL,
    ATTACKHIGHLIGHT,
    DEFENDHIGHLIGHT,
    EVADEHIGHLIGHT,
    SPECIALHIGHLIGHT;

    public static ItemType toggle(ItemType item) {
        switch (item) {
            case ATTACK:
                return ATTACKHIGHLIGHT;
            case ATTACKHIGHLIGHT:
                return ATTACK;
            case EVADE:
                return EVADEHIGHLIGHT;
            case EVADEHIGHLIGHT:
                return EVADE;
            case DEFEND:
                return DEFENDHIGHLIGHT;
            case DEFENDHIGHLIGHT:
                return DEFEND;
            case SPECIAL:
                return SPECIALHIGHLIGHT;
            case SPECIALHIGHLIGHT:
                return SPECIAL;
        }
        return BLANK;
    }

    public static boolean isHighLighted(ItemType item) {
        switch (item) {
            case ATTACKHIGHLIGHT:
            case EVADEHIGHLIGHT:
            case DEFENDHIGHLIGHT:
            case SPECIALHIGHLIGHT:
                return true;
        }
        return false;
    }
    public static boolean isHighLighted(byte item) {
      return isHighLighted(  ItemType.values()[ (item)]);
    }
}
