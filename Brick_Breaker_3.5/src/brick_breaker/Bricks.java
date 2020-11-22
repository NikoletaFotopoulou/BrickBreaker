/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brick_breaker;

import java.awt.*;
/**
 *
 * @author User
 */
class Bricks {
    private Color brickColor;//anoigoume tin klasi bricks
    public int map[][]; //aftos o pinakas ine gia ola ta bricks
    public int brickWidth; //dilonoume tin metavliti tou platous ton touvlon
    public int brickHeight; //dilonoume tin metavliti tou ipsous ton touvlon
    int i,j;
    public Bricks(int row,int col){//enas constructor gia na paroume poses grames kai stiles prepi na dimiourgithoune
        map=new int[row][col]; //dilono ton pinaka me tis diastasis tou 
        
        for(i=0;i<map.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka map kata 1
            for(j=0; j < map[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka map kata 1
                map[i][j]=1;//kanoume tin timi olon ton bricks 1 an i timi enos sigekrimenou brick ine 1 tote den exi erthi se epafi me tin bala
                // otan spaei ena brick tha kanoume na allazei i timi tou brick se 0
            }
        }
        //eisago stixia gia ta bricks me arxikopoiisi pou legame kai sto aepp
        brickWidth = 540/col; //platos
        brickHeight= 150/row; //ipsos
    }
    public void draw(Graphics2D g) { //kanoume aftin tin methodo na zografizei bricks mono eki pou i timi ton bricks ine 1 diladi eki pou den exi akoubisi i bala
        for(i=0;i<map.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka map kata 1
            for(j=0; j < map[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka map kata 1
                if(i==j || i+2==j || i+4==j || i-2==j || i-4==j)
                    brickColor=Color.orange;
                else
                    brickColor=Color.cyan;
                if (map[i][j]>0){ //poli simadiko tou leme edo na ksanazografizei ta bricks an i timi tous ine pano apo 0 diladi 1 
                    g.setColor(brickColor);//epilegoume lefko xroma sta bricks (argotera tin kori tou)
                    g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);//!!! poli disnoito komati tou kodika alla ola kala
                    //ftiaxnoyme ena brick poy tha exei brickwidth 540/στηλες. Αρα 540/5= 108 kai brickheight 150/grammes=37.5. Οποτε pada dimiourgo kai sbroxno ένα brick 80 pixels dexia kai 50 pixels kato
                    //Άρα το πρώτο brick των 108 pixels θα δημιουργηθεί στην θέση => j=0*brickwidth=108+80pixels και i=0*brickheight=37,5+50 pixels και πάει λέγοντας.
                    //Το j*brickWidth+80 πάνω κάτω τα σπρώχνει 80 pixels δεξιά και το i*brickHeight+50 τα σπρώχνει 50 pixels κάτω.
                    //an den valoume tis parakato edoles ta bricks tha emfanizontai os ena megalo brick
                    //pano kato me aftes tis edoles vazoume grames gia na xorisoume ta bricks to xromma tha ine mavro
                    // dioti mavro ine kai to backround omos katalavenete oti me afton ton tropo
                    //ine diskolo na valoume eikona piso tha to vroume omos 
                    //sto sigekrimeno komati aftos sto video malakizete apistefta
                    g.setStroke(new BasicStroke(4)); //gia to poso megales tha ine oi grammes anamesa sta bricks genika gia to paxos
                    g.setColor(Color.black); //to xroma ton grammon
                    g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);//ta idia me pano alla gia to paxos ton grammon tora. 
                    //Απλά χρησιμοποιώ την drawRect αντί για την fillRect για να δημιουργήσω μόνο το μαύρο περίγραμμα γύρω από τα λευκά bricks.
                }
            }
        }
    }
    //o parakato kodikas ine gia ta pragmata pou ginontai otan vriskei i bala ta bricks 
    public void setBrickValue(int value,int row,int col){ // afto paei sto gameplay sto deutero miso tou kodika sto map.setBrickValue(0,i,j)
        map[row][col] = value; //an akoubisi bala brick alazoume tin timi enos brick kathe fora se 0 ara opos exoume pei kai pio pano den ksanazografizete
    }
}
