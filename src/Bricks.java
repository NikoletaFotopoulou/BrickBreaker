/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
//den ton ida ton malaka na kani import to Color kai to Graphics2D ta ekana ego 




//opou exi MapGenerator exo valei Bricks ine pio adiprosopeutiko
//ola afta gia ta touvla ginontai se aftin tin class
public class Bricks {
    public int map[][]; //aftos o pinakas ine gia ola ta bricks
    public int brickWidth;
    public int brickHeight;
    public Bricks(int row,int col){//enas constructor ine aftos gia na paroume poses grames kai stiles prepi na dimiourgithoune  
    map=new int[row][col];
    for(int i=0;i<map.length; i++){
        for(int j=0; j < map[0].length; j++ ){
            map[i][j]=1;//an i timi enos sigekrimenou brick ine 1 tote den exi erthi se epafi me tin bala
            // otan spaei ena brick tha kanoume na allazei i timi tou brick se 0
        }
    }
    //eisago stixia gia ta bricks me arxikopoiisi pou legame kai sto aepp
    brickWidth = 540/col;
    brickHeight= 150/row;
  }
    public void draw(Graphics2D g) { //gia tin sxediasi ton bricks
    //poli simadiko!!! tha kanoume aftin tin methodo na zografizei bricks mono eki pou i timi ton bricks ine 1
    //diladi eki pou den exi akoubisi i bala
    for(int i=0;i<map.length; i++){
        for(int j=0; j < map[0].length; j++ ){
            if (map[i][j]>0){
             g.setColor(Color.white);//epilegoume lefko xroma sta bricks (argotera tin kori tou)
             g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight );
             
             
             //an den valoume tis parakato edoles ta bricks tha emfanizontai os ena megalo brick
             //pano kato me aftes tis edoles vazoume grames gia na xorisoume ta bricks to xromma tha ine mavro
             // dioti mavro ine kai to backround omos katalavenete oti me afton ton tropo
             //ine diskolo na valoume eikona piso tha to vroume omos 
             //sto sigekrimeno komati aftos sto video malakizete apistefta
             g.setStroke(new BasicStroke(3));
             g.setColor(Color.black);
             g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
          }
        }
      }
    }
    //o parakato kodikas ine gia na vriski i bala ta bricks 
    public void setBrickValue(int value,int row,int col){
        map[row][col] = value;
    }
    
}
