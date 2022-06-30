package com.example.simon;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

abstract class GameButton implements View.OnClickListener
{
    //-----Vars-----
    protected String color;
    protected int ID;
    protected static int clickCounter;
    public boolean isClicked;
    protected Button btn;
    public boolean canClick;
    public Context con;
    protected boolean lost;
    protected long lastTimeClicked = 0;
    //-----Constarcturs-----
    public GameButton(Button btn, Context con)
    {
        this.btn = btn;
        this.canClick = false;
        this.con = con;
        this.lost = false;
        btn.setOnClickListener(this);
    }
    //-----Getters & Setters-----
    public String getColor() {
        return this.color;
    }

    public static int getClickCounter() {
        return clickCounter;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public int getID() {
        return ID;
    }
    //-----Methods-----
    @Override
    public void onClick(View v) {
        if(canClick && System.currentTimeMillis() > lastTimeClicked + 500)
        {
            this.isClicked = true;
            this.doAnimation(4);
            this.lastTimeClicked = System.currentTimeMillis();
        }
    }

    public void reqClick()
    {
        this.canClick = true;
        btn.setOnClickListener(this);
    }

    public boolean isRightButton(int num, ArrayList<Integer> lst)
    {
        if(lst.get(num) == this.ID)
            return true;
        return false;
    }

    public void doAnimation(int level)
    {
        Animation anim;
        switch(level)
        {
            case(1):
                anim = AnimationUtils.loadAnimation(con,R.anim.animeasy);
                break;
            case(3):
                anim = AnimationUtils.loadAnimation(con,R.anim.animhard);
                break;
            default:
                 anim = AnimationUtils.loadAnimation(con,R.anim.animmedium);
                break;
        }

        btn.startAnimation(anim);
    }
}

class GreenButton extends GameButton
{
    public GreenButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 0;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

class YellowButton extends GameButton
{
    public YellowButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 1;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}


class RedButton extends GameButton
{
    public RedButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 2;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

class BlueButton extends GameButton
{
    public BlueButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 3;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

class PurpleButton extends GameButton
{
    public PurpleButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 4;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

class LightBlueButton extends GameButton
{
    public LightBlueButton(Button btn, Context con)
    {
        super(btn, con);
        this.ID = 5;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}