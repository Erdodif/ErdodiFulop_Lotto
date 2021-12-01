package com.example.lotto;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.*;

public class HelloController {
    public Button buttonOfAll;
    public Label nagy;
    public Label elso;
    public Label masodik;
    public Label harmadik;
    public Label negyedik;
    public Label otodik;
    public int allas = 0;
    public List<Integer> mentett = new ArrayList<>();
    private Random random = new Random();
    private int sorsolt = 0;

    public void sorsolas() {
        sorsolt = 0;
        while (mentett.contains(sorsolt) || sorsolt == 0){
            sorsolt = random.nextInt(90)+1;
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    nagy.setText(Integer.toString(random.nextInt(90)+1));
                });
            }
        },0,1000);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    nagy.setText(Integer.toString(sorsolt));
                    betoltes(sorsolt);
                });
            }
        },1000);
    }

    public void betoltes(int eredmeny) {
        String ki = Integer.toString(eredmeny);
        mentett.add(eredmeny);
        switch (allas) {
            case 0 -> elso.setText(ki);
            case 1 -> masodik.setText(ki);
            case 2 -> harmadik.setText(ki);
            case 3 -> negyedik.setText(ki);
            case 4 -> otodik.setText(ki);
        }
        allas++;
    }
}