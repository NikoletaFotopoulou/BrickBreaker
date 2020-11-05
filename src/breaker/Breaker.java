/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;
        
import javax.swing.JFrame;// to kaneis import gia na dimiourgisis adikimeno obj tipou JFrame


public class Breaker  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      JFrame obj = new JFrame();
      Gameplay  gamePlay = new Gameplay();//dimiourgoume ena adikimeno klasis Gameplay
      //edo tha akolouthisoun ta pada gia tin sxediasi tou parathirou(megethos titlos klp)     
      obj.setBounds(10,10,965,650);//x,y from top-left corner, width-height
      obj.setTitle("Brick Breaker");//title
      obj.setResizable(false);//user can't resize it
      obj.setVisible(true);//makes component displayed on the screen
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops operating when you close it
      obj.add(gamePlay);//vazoume to adikimeno  gamePlay meso sto adikemo tou JFrame
      //to pano tha vgazei error mexri na kanoume extends to JPanel sto Gameplay class  
    }
    
}
