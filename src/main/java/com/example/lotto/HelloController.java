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

    private Random random = new Random();
    public List<Integer> mentett = new ArrayList<>();
    private boolean animacioAktiv = false;
    private boolean sorsolasFlag = false;
    private boolean rendezettFlag = false;
    private int sorsolt = 0;
    public int allas = 0;

    public void sorsolVagyRendezVagyTorol() {
        if (sorsolasFlag) {
            if (rendezettFlag) {
                reset();
            } else {
                rendez();
            }
        } else {
            sorsolas();
        }
    }

    public void reset() {
        sorsolt = 90;
        nagy.setText(Integer.toString(sorsolt));
        mentett = new ArrayList<>();
        allas = 0;
        for (int i = 0; i < 5; i++) {
            betoltes("", i);
        }
        buttonOfAll.setText("Sorsol");
        rendezettFlag = false;
        sorsolasFlag = false;
    }

    public void rendez() {
        buttonOfAll.setText("Újra");
        mentett.sort(Comparator.naturalOrder());
        for (int i = 0; i < 5; i++) {
            betoltes(Integer.toString(mentett.get(i)), i);
        }
        rendezettFlag = true;
    }

    public void sorsolas() {
        if (animacioAktiv) {
            return;
        }
        animacioAktiv = true;
        sorsolt = 0;
        while (mentett.contains(sorsolt) || sorsolt == 0) {
            sorsolt = random.nextInt(90) + 1;
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    nagy.setText(Integer.toString(random.nextInt(90) + 1));
                });
            }
        }, 0, 1);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                Platform.runLater(() -> {
                    nagy.setText(Integer.toString(sorsolt));
                    betoltes(sorsolt);
                    if (allas == 5) {
                        buttonOfAll.setText("Rendez");
                        sorsolasFlag = true;
                    }
                    animacioAktiv = false;
                });
            }
        }, 1000);
    }

    public void betoltes(String eredmeny, int aktualis) {
        switch (aktualis) {
            case 0 -> elso.setText(eredmeny);
            case 1 -> masodik.setText(eredmeny);
            case 2 -> harmadik.setText(eredmeny);
            case 3 -> negyedik.setText(eredmeny);
            case 4 -> otodik.setText(eredmeny);
        }
    }

    public void betoltes(int eredmeny) {
        mentett.add(eredmeny);
        betoltes(Integer.toString(eredmeny), allas);
        allas++;
    }
}