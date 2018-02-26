package com.abasc.game;

import com.badlogic.gdx.graphics.Texture;

public class ItemTexture {

    Texture attack;
    Texture defend;
    Texture evade;
    Texture special;


    Texture attackHighLight;
    Texture defendHighLight;
    Texture evadeHighLight;
    Texture specialHighLight;
    Texture blank;



    public ItemTexture(){
        attack = new Texture("blank.png");
        attack = new Texture("attack.png");
        defend = new Texture("defend.png");
        evade = new Texture("evade.png");
        special = new Texture("special.png");
        blank = new Texture("blank.png");
        attackHighLight = new Texture("attackhighlight.png");
        defendHighLight = new Texture("defendhighlight.png");
        evadeHighLight = new Texture("evadehighlight.png");
        specialHighLight = new Texture("specialhighlight.png");
    }

    public Texture textureBasedOnType(ItemType type){

        switch (type){
            case EVADE: return evade;
            case ATTACK: return attack;
            case SPECIAL: return special;
            case DEFEND: return defend;
            case EVADEHIGHLIGHT: return evadeHighLight;
            case ATTACKHIGHLIGHT: return attackHighLight;
            case SPECIALHIGHLIGHT: return specialHighLight;
            case DEFENDHIGHLIGHT: return defendHighLight;
            case BLANK: return  blank;
            default: return null;
        }

    }
}
