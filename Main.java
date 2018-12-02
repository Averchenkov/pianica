package Pianica;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Player one = new Player();
        Player two = new Player();
        Game g = new Game(one, two);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setSize(500, 700);
        g.setResizable(true);
        Thread.sleep(1000);
        g.play(10);
    }
}