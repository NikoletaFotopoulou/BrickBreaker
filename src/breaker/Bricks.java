/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker; // i main
//ta imports pou xreiazomaste
import java.awt.BasicStroke;//morfopiei afta pou sxediazontai apo Graphics2D (allagi paxous,morfopiisi gramon stin arxi kai sto telos tous,morfopiisi tou xorou anamesa apo 2 grammes kai alla)
import java.awt.Color;//gia ta xromata
import java.awt.Graphics2D;//voithaei sto na xirizomaste kalitera geometrika sximata 2 diastaseon ,metasximatismous sidetagmenon,diataksis kimenou kai xromata
//den ton ida ton malaka na kani import to Color kai to Graphics2D ta ekana ego 




//opou exi MapGenerator exo valei Bricks ine pio adiprosopeutiko
//ola afta gia ta touvla ginontai se aftin tin class
public class Bricks { //anoigoume tin klasi bricks
    public int map[][]; //aftos o pinakas ine gia ola ta bricks
    public int brickWidth; //dilonoume tin metavliti tou platous ton touvlon
    public int brickHeight; //dilonoume tin metavliti tou ipsous ton touvlon
    public Bricks(int row,int col){//enas constructor gia na paroume poses grames kai stiles prepi na dimiourgithoune  
    map=new int[row][col]; //dilono ton pinaka me tis diastasis tou 
    for(int i=0;i<map.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka map kata 1
        for(int j=0; j < map[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka map kata 1
            map[i][j]=1;//kanoume tin timi olon ton bricks 1 an i timi enos sigekrimenou brick ine 1 tote den exi erthi se epafi me tin bala
            // otan spaei ena brick tha kanoume na allazei i timi tou brick se 0
        }
    }
    //eisago stixia gia ta bricks me arxikopoiisi pou legame kai sto aepp
    brickWidth = 540/col; //platos
    brickHeight= 150/row; //ipsos
  }
    public void draw(Graphics2D g) { //gia tin sxediasi ton bricks
    //poli simadiko!!! tha kanoume aftin tin methodo na zografizei bricks mono eki pou i timi ton bricks ine 1
    //diladi eki pou den exi akoubisi i bala
    for(int i=0;i<map.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka map kata 1 
        for(int j=0; j < map[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka map kata 1
            if (map[i][j]>0){ //poli simadiko tou leme edo na ksanazografizei ta bricks an i timi tous ine pano apo 0 diladi 1
             g.setColor(Color.white);//epilegoume lefko xroma sta bricks (argotera tin kori tou)
             g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight );//!!! poli disnoito komati tou kodika alla ola kala
             //pano kato sxediazis tis kathetes kai tis orizonties grammes to thema ine pos to kaneis.
             //afou to brickwidth ine 540/col diladi 540/7=77.14 skeftite oti i proti stili tha ine j*brickWidth+80 ara
             //0*77.14+80 kai paei legontas gia ta epomena ara pano kato tou les kathe 80 pixels (pou tha borouse na ine kai 77)
             //ftiakse mia katheti grammi pou oso auksanete to j toso pio pera paei
             //ta idia kai gia to i diladi brickHeight=150/3=50 ara i*brickHeight+50=0*50+50=50 kai oso auksanete to i toso parapera tha paei
             
             
             //an den valoume tis parakato edoles ta bricks tha emfanizontai os ena megalo brick
             //pano kato me aftes tis edoles vazoume grames gia na xorisoume ta bricks to xromma tha ine mavro
             // dioti mavro ine kai to backround omos katalavenete oti me afton ton tropo
             //ine diskolo na valoume eikona piso tha to vroume omos 
             //sto sigekrimeno komati aftos sto video malakizete apistefta
             g.setStroke(new BasicStroke(3)); //gia to poso megales tha ine oi grammes anamesa sta bricks genika gia to paxos
             g.setColor(Color.black); //to xroma ton grammon
             g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);//ta idia me pano alla gia to paxos ton grammon tora <3
          }
        }
      }
    }
    //o parakato kodikas ine gia ta pragmata pou ginontai otan vriskei i bala ta bricks 
    public void setBrickValue(int value,int row,int col){ // afto paei sto gameplay sto deutero miso tou kodika sto map.setBrickValue(0,i,j)
        map[row][col] = value; //an akoubisi bala brick alazoume tin timi enos brick kathe fora se 0 ara opos exoume pei kai pio pano den ksanazografizete
    }
    
}
