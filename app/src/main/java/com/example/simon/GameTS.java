package com.example.simon;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.Nullable;

import java.io.Serializable;

/*class GameService extends Service
{
    GameThread gameThread;
    String serviceName = GameService.class.getSimpleName();
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if(gameThread == null)
        {
            //Intent intent1 = Intent.getIntent();
            Serializable obj = intet.getSerializableExtra("ThreadClass");
            gameThread = new ();
            gameThread.start();
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy()
    {
        this.gameThread = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}*/
class GameThread extends Thread
{
    private Handler scoreHandler, stageHandler;
    private ButtonLst btnsLst;
    private int score = 0;
    private GameButton[] btns;
    private boolean runGame;
    private int stage;
    private boolean run;
    private int counter = 0;
    private int maxStage = 0;
    private Activity con;
    private int difficult = 0;
    GameThread(GameButton[] btns, Handler scoreHandler, Handler stageHandler, Activity con, int maxStage, int difficult)
    {
        this.btns = btns;
        this.runGame = true;
        this.btnsLst = new ButtonLst(this.btns);
        this.scoreHandler = scoreHandler;
        this.stageHandler = stageHandler;
        this.stage = 0;
        this.run = true;
        this.con = con;
        this.maxStage = maxStage;
        this.difficult = difficult;
        this.sendStage(this.stage);
    }

    public GameThread(GameThread another)
    {
        this.btns = another.getBtns();
        this.runGame = true;
        this.btnsLst = new ButtonLst(this.btns);
        this.scoreHandler = another.getScoreHandler();
        this.stage = 0;
    }

    public Handler getScoreHandler() {
        return scoreHandler;
    }

    public GameButton[] getBtns() {
        return btns;
    }

    public int getScore() {
        return score;
    }

    public int getStage() {
        return stage;
    }

    public void await(int millySeconds)
    {
        try {
            Thread.sleep(millySeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAnimation()
    {
        btnsLst.ableClick(false);
        this.await(1000);
        for (int index : btnsLst.selectedIds) {
            (btns[index]).doAnimation(this.difficult);
            this.await(1000);
        }
        this.await(250);
        btnsLst.ableClick(true);
    }

    public void sendScore(int score)
    {
        Message msg = new Message();
        msg.arg1 = score;
        scoreHandler.sendMessage(msg);
    }

    public void sendStage(int stage)
    {
        Message msg = new Message();
        msg.arg1 = stage;
        stageHandler.sendMessage(msg);
    }

    public void endGame(boolean win)
    {
        Intent intent = new Intent(this.con, EndScreen.class);
        intent.putExtra("score", this.score);
        intent.putExtra("stage", this.stage);
        intent.putExtra("win", win);
        intent.putExtra("lvl", this.difficult);
        con.startActivity(intent);
        con.finish();
    }

    @Override
    public void run() {
        Looper.prepare();
        while(this.runGame) {
            if(this.stage >= this.maxStage)
            {
                endGame(true);
            }
            this.stage += 1;
            this.sendStage(this.stage);
            this.btnsLst.rndBtn();
            this.run = true;
            this.await(500);
            this.runAnimation();
            this.counter = 0;
            while (this.run)
            {
                if(this.counter >= this.btnsLst.selectedIds.size()) {
                    this.run = false;
                    this.btnsLst.ableClick(false);
                }
                for(int m = 0; m < this.btns.length; m++)
                {
                    if(this.btns[m].isClicked)
                    {
                        if(!this.btns[m].isRightButton(this.counter, this.btnsLst.selectedIds)) {
                            this.runGame = false;
                            this.run = false;
                            break;
                        }
                        else
                            this.score += 10;
                        this.counter++;
                        this.btns[m].isClicked = false;
                    }
                }
                this.sendScore(this.score);
                this.await(250);
            }
        }
        endGame(false);
    }
}