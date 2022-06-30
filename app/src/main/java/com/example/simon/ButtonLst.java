package com.example.simon;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ButtonLst {
    ArrayList<Integer> selectedIds;
    GameButton[] btns;

    public ButtonLst(GameButton[] btns) {
        this.selectedIds = new ArrayList<Integer>();
        this.btns = btns;
    }
    public ButtonLst(ButtonLst btnLst)
    {
        this.selectedIds = btnLst.selectedIds;
        this.btns = btnLst.btns;
    }

    public void ableClick(boolean status)
    {
        for(int i = 0; i < btns.length; i++)
        {
            btns[i].canClick = status;
        }
    }
    public void rndBtn()
    {
        Random rnd = new Random();
        int random = rnd.nextInt(this.btns.length);
        selectedIds.add(random);
    }

}
