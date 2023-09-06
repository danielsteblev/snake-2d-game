package ru.vsu.cs.util.steblev_d_v.my_games.snake2d;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setTitle("SNAKE GAME");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GAME_FIELD());
        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mw = new MainWindow();
    }
}