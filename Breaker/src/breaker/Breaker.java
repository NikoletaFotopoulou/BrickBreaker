
package breaker;
        
import javax.swing.JFrame;// to kaneis import gia na dimiourgisis adikimeno obj tipou JFrame


public class Breaker  {

    public static void main(String[] args) {
        JFrame frame = new JFrame();//adikeimeno Jframe
        Game game = new Game();//dimiourgoume ena adikimeno klasis Gameplay
        //edo tha akolouthisoun ta pada gia tin sxediasi tou parathirou(megethos titlos klp)     
        frame.setSize(1000,650);//platos-ipsos tou frame
        frame.setLocationRelativeTo(null);// gia na einai sto kedro to parathiro
        frame.setTitle("Brick Breaker");//titlos frame
        frame.setResizable(false);//den allazei megethos to parathiro
        frame.setVisible(true);//emfanizetai stin othoni
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stamata na trexei otan to kleiseis
        frame.add(game);//vazoume to adikimeno  gamePlay meso sto adikemo tou JFrame
        //to pano tha vgazei error mexri na kanoume extends to JPanel sto Gameplay class  
    }
    
}