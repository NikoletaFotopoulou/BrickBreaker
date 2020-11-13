/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker_bgn;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class BrickBreaker_BGN {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        Gameplay  game= new Gameplay();//dimiourgoume ena adikimeno klasis Gameplay
        //edo tha akolouthisoun ta pada gia tin sxediasi tou parathirou(megethos titlos klp)
        frame.setBounds(10,10,965,650);//x,y from top-left corner, width-height
        frame.setTitle("Brick Breaker");//title
        frame.setResizable(false);//user can't resize it
        frame.setVisible(true);//makes component displayed on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops operating when you close it
        frame.add(game);//vazoume to adikimeno  gamePlay meso sto adikemo tou JFrame
        //to pano tha vgazei error mexri na kanoume extends to JPanel sto Gameplay class  
    }   
}