/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker_bgn;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//αντι να γραφουμε αυτα τα παρακατω μπορουμε να γραψουμε αυτα τα παραπανω
/*import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Rectangle;*/

/*import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;
  import java.awt.event.KeyEvent;
  import java.awt.event.KeyListener;*/

/*import javax.swing.JPanel;
    import javax.swing.Timer;*/


/**
 *
 * @author User
 */
class Gameplay extends JPanel implements KeyListener,ActionListener{
    private boolean play = false; //gia na min ksekinai mono tou to pexnidi
    private int score=0; //arxiko score
    
    /* ΠΡΟΣΟΧΗ ΝΑ ΜΗΝ ΑΛΛΑΞΟΥΝ ΟΝΟΜΑΤΑ ΑΥΤΕΣ ΟΙ ΤΡΕΙΣ ΜΕΤΑΒΛΗΤΕΣ */  //τα αλαξα γιατι δεν τεριαζανε με της υπολιπες μεταβλητες
    //dimiourgo 3 metavlites typou Color gia kathena apo 3 border tou paixnidiou. Arxiko xroma prasino
    private Color rightColor=Color.GREEN;
    private Color topColor=Color.GREEN;
    private Color leftColor=Color.GREEN;
    private Color ballColor=Color.WHITE;
    /*ΤΕΛΟΣ ΠΡΟΣΟΧΗΣ */
    
    private int totalBricks=20;//total bricks
    //ta epomena 2 gia tin taxitita tis balas
    //genika to Timer ine enas etimos tipos tis java kai mia metavliti aftou tou tipou xrisimopiite gia na pirodotei gegonota
    
    private Timer timer;//orizoume mia metavliti timer tipou Timer
    
    private int delay=6;//taxitita balas kai kinisi tis platformas the smaller number->the faster
    
    private int platformΧ=310;//arxiki thesi tis platformas (x)
    //ta epomena 2 gia tin arxiki thesi tis balas        
    private int ballX=220; 
    private int ballY=250;
     //ta epomena 2 gia tin kateuthisni tis balas
    private int ballVelocityX=2;
    private int ballVelocityY=2;
    //domitis gia na valo tis parapano times otan dimiourgithi adikimeno apo aftin tin klasi
    
    private Bricks map;//metavliti map tipou bricks
    
    public Gameplay(){
        map=new Bricks(4,5); // dimiourgi grammes kai stiles gia ta bricks
        //to parapano prostethike argotera meta tin dimiourgia tis klasis Bricks
        
        //akolouthoun malakies mesa ston domiti pou to video den exigi kan ti ine
        addKeyListener(this);
        setFocusable(true);// indicates whether a component can gain the focus if it is requested to do so
        setFocusTraversalKeysEnabled(false);//decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus
        timer = new Timer(delay,this); //vazoume ena timer gia na pirodoti gegonota ana 6 delay 
        timer.start();
    }
    //i parakato methodos ine gia ton sxediasmo adikimenon (bala,platforma klp)
    // prepi na kanoume import ta graphics prota 
    //genikotera prepi na kanoume import xilia pramata pou den kserame kan tin iparksi tous
    //efxaristoume dipae
    @Override
    public void paint(Graphics g){
        //xroma gia to backgroung 2 epomena   
        //an kai exo dei methodous se alla tutorial gia na vazeis eikona piso.tha ta alaksoume ola
        g.setColor(Color.black);
        g.fillRect(1,1,1010, 700);
        //na thimaste o tipos tis fillRect ine:g.fillRect(x, y, WIDTH, HEIGHT) kai emis gemizoume tis times
     
        //dimiourgia ton bricks apo kato
        map.draw((Graphics2D)g); //draws the bricks
        // malakizete ligo sto lepto 36miso 
     
        
        //ta giro giro
        //opote an kano 3 den tha dimiourgithei to kato gia na pefti i bala
        
        //δεν τα χρειαζομαστε γιατι τα διμιουγιτε ποιο κατο
        /*g.setColor(Color.green);
        g.fillRect(691,70 ,320, 5);
        g.fillRect(691,140 ,320, 5);
        g.fillRect(691,0,320,5);*/
     
        //to kathe border diaforetiko g oste allazodas ena apo ta 3 c na allazei to analogo border
        g.setColor(rightColor);
        g.fillRect(691,0,5 ,1000);
        
     
        g.setColor(topColor);
        g.fillRect(0,0,691,5);
     
        g.setColor(leftColor);
        g.fillRect(0, 0, 5, 1000);
     
     
        // gia tin emfanisi tou score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Score:"+score, 781, 42);
     
        //posa bricks menoun
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Remaining Bricks:"+totalBricks, 713, 113);
     
        //made by
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD, 30));
        g.drawString("Created by: ", 745, 204);
     
        //karaxristos
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Karachristos Georgios", 713, 269);
     
        //tsantilis
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Tsantilis Vasileios", 729,334);
     
        //fotopoulou
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("Fotopoulou Nikoletta", 716,399);
     
        //gia tin platforma
        g.setColor(Color.green);
        g.fillRect(platformΧ,550,100,15);
     
        //gia tin bala
        g.setColor(ballColor);
        g.fillOval(ballX, ballY, 20, 20);
        
        if(totalBricks <=0){ //otan spaseis ola ta toubla
            play=false; //teleionei to paixnidi
            ballVelocityX=0; //bala stamataei na kineitai
            ballVelocityY=0;
            g.setColor(Color.red); //gia na emfanisoume ta apotelesmata sto telos
            g.setFont(new Font("serif",Font.BOLD, 30)); // name,style,size 
            g.drawString("You Won", 260, 300); //zografizei ti tha leei, orizoume x,y
         
            g.setFont(new Font("serif",Font.BOLD, 20)); //kainourgio font
            g.drawString("Press Enter to Restart", 230, 350);
        }
        //aftes edo oi edoles ine gia to pote tha sou vgazei game over
        if(ballY> 650){ //an pesei kato dld
            play=false; //stamatei to paixnidi
            ballVelocityX=0; //stamata na kineitai i bala
            ballVelocityY=0;//axriasto
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Game Over,Score:"+score, 190, 300);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        g.dispose(); //katastrefei to jframe    
    }
    //override apo KeyListen interface pou kaname implement
    //to apache mou evgaze mono tou throws profanos kai ta evgala
    //ta dio prota den tha xrisimopoiithoun alla an ta vgaloume tha petaksi error
    //πρεπει να τα βαλουμε ομος γιατι κανουμε implements την κλαση KeyListener
    @Override
    public void keyTyped(KeyEvent e){}//δεν τα χρισιμοποιουμε
    @Override
    public void keyReleased(KeyEvent e){}//δεν τα χρισιμοποιουμε
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //gia tin kinisi tis balas
        if(play){ //tha ine true an exoume patisi ena apo ta 2 velakia ή το εντερ
        //me afto to terastio if tou les apla oti an akoubisi to rectangle tis balas me afto tis platformas na alaksei poreia i bala
            if( new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(platformΧ,550,100,15))){ // αν τα ορια της μπαλας χτιπισουν τα ορια της πλατφορμας αλλαξε πορια
                
                //an i bala xtipisei kato apo tin platforma, dld plagia, na allaksei x, ara na pesei kato
                if(ballY>535)
                    ballVelocityX= -ballVelocityX;
                else
                    ballVelocityY= -ballVelocityY;
            }    
            //oi parakato edelos prostethikan argotera kai ine gia na anixnevei i bala ta bricks
            
            /* ΠΡΟΣΟΧΗ Η ΛΕΞΗ LOOP ΕΙΝΑΙ ΗΔΗ ΑΛΛΑΓΜΕΝΗ ΠΑΛΙΑ ΗΤΑΝ Α */
            LOOP:   for(int i=0; i<map.map.length; i++){
                for(int j = 0;j<map.map[0].length;j++){//to ti ine to map.map to eksigi kala sto 41:00
                    if(map.map[i][j]>0){//an i timi enos brick ine megaliteri tou 0
                        //δημιοργουμε τα ορια για καθε brick
                        int brickX= j * map.brickWidth + 80;
                        int brickY= i * map.brickHeight + 50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;  
                        //pali dimiourgoume ena Rectangle apo giro
                        Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        //kai giro apo tin bala tora
                        Rectangle ballRect=new Rectangle(ballX,ballY,20,20);
                        if(ballRect.intersects(brickRect)){ // αν τα ορια της μπαλας χτιπισουν τα ορια τον brick αλλαξε πορια
                            map.setBrickValue(0,i,j); // θετουμε 0 ετσι οστε να μην το ξανα ζωγραφισει η repaint
                            totalBricks--; // μιονουμε τον αριθμο των Bricks
                            score +=5; //ενημερονουμε το score
                            
                            /*ΠΡΟΣΟΧΗ ΝΑ ΜΗΝ ΑΛΛΑΞΕΙ ΠΟΥΘΕΝΑ*/
                            if((i%2==0)&&(j%2==0))
                                ballColor=Color.orange;
                            else
                                ballColor=Color.cyan;
                            /*ΤΕΛΟΣ ΠΡΟΣΟΧΗΣ*/
                            
                            // afto ine gia ta dexia kai ta aristera meroi ton bricks
                            //ta x gekina einai i aristeri pleura tou sximatos. diametros ballas 20, ara to x einai to proto pixel kai meta +19 i ipolypi bala
                            //stin aristeri pleura tou || les an i dexia pleura tis balas akoubisei tin aristeri pleura tou brick
                            //stin dexia pleura tou || les an i aristeri pleura tis balas akoubisei tin dexia tou brick, pou einai to x tou brick+to platos tou
                            
                            if(ballX + 19 <=brickRect.x || ballX + 1 >=brickRect.x + brickRect.width){
                                ballVelocityX = -ballVelocityX;
                            } else { //an diladi xtipisei apo pano i kato to brick
                                ballVelocityY = -ballVelocityY;
                            }
                            break LOOP;//psaxnoume akoma pos akribos boithaei afto 
                        }
                    }
                }
            }
            //analoga me tin timi toy direction allazoun ta x,y tis balas
            ballX += ballVelocityX;
            ballY += ballVelocityY;
            //pano kato tora tou les an akoubisei kapio border na pari tin aditheti poria apo aftin pou ixe
            //to proto if ine gia to aristero border  to deutero gia to pano to trito gia to dexi
            
            
            /* ΠΡΟΣΟΧΗ ΑΠΟ ΑΥΤΟ ΤΟ ΣΗΜΕΙΟ ΝΑ ΑΛΛΑΞΟΥΝ ΜΟΝΟ ballVelocityY,ballVelocityX,ballX,ballY */
            if(ballX <0){
                ballVelocityX = -ballVelocityX;
                //kodikas pou epanalambanetai alles 2 fores apo kato, an einai to ena xroma allakse to se allo
                if(leftColor==Color.green)
                    leftColor=Color.yellow;
                else if(leftColor==Color.yellow)
                    leftColor=Color.cyan;
                else if(leftColor==Color.cyan)
                    leftColor=Color.green;
                
            }
            if(ballY < 0){
                ballVelocityY = -ballVelocityY;
                if(topColor==Color.green)
                    topColor=Color.yellow;
                else if(topColor==Color.yellow)
                    topColor=Color.cyan;
                else if(topColor==Color.cyan)
                    topColor=Color.green;
            }
            if(ballX >670){
                ballVelocityX = -ballVelocityX;
                if(rightColor==Color.green)
                    rightColor=Color.yellow;
                else if(rightColor==Color.yellow)
                    rightColor=Color.cyan;
                else if(rightColor==Color.cyan)
                    rightColor=Color.green;
            }
        }
        repaint(); //afto tha ksanazigrafisi otidipote vriskete stin puclib void paint
    }
    
    /*ΤΕΛΟΣ ΠΡΟΣΟΧΗΣ */
    
    
    //gia tis kinisis tis platformas
    @Override
    public void keyPressed(KeyEvent e) { //μεθοδος για το οταν πατιθει ενα πληκτρο
    //an patao to deksi velaki kai den exi figi i platforma ekso apo ta borders kounisou dexia
        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(platformΧ+20>590) //το εκανα για να ειναι στα ορια
                platformΧ=590;
            else
                moveRight();
        }  //ta idia gia aristera tora
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(platformΧ-20<5) //το εκανα για να ειναι στα ορια
                platformΧ=5;
            else
                moveLeft();
        } 
        
        // gia na boris na kaneis retry patontas ENTER
        if(e.getKeyCode()==KeyEvent.VK_ENTER ){
            if(!play){//θα μπει οταν το play ειναι ψευδες  //pano kato tou les ksanavalta ola apo tin arxi an isxiei afti i sinthiki
                play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
                //η αρχικη θεση της μπαλας
                ballX=220;
                ballY=250;
                //το πως θα κυνηθει
                ballVelocityX=2;
                ballVelocityY=2;
                //η αρχικη θεση της πλατφορμας
                platformΧ=310;
                //το σκορ
                score=0;
                //ποσα Bricks θα εμφανισει
                totalBricks=20;
                map= new Bricks(4,5); //prosoxi to MapGenetor emeis to leme Bricks
                
                //το εβαλα ετσι οστε αν χασει να ξανα θεσει τα αρχικα χροματα
                rightColor=Color.GREEN;
                topColor=Color.GREEN;
                leftColor=Color.GREEN;
                ballColor=Color.WHITE;
                
                repaint(); //καλουμε την repaint ετσι οστε να ξανα ζωγραφισουμε το "παιχνιδι" με της μεταβλιτες που θεσαμε απο πανω
            }
        }
    }
   // dimiourgo tis methodous kinisis gia na  boroun na litourgisoun oi pano sinthikes 
    public void moveRight(){
        play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
        platformΧ+=20; // i platforma tha kounithi 20 pixel dexia       
    }
    
    public void moveLeft(){
        play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
        platformΧ-=20; // i platforma tha kounithi 20 pixel dexia       
    }
    
    //το εβαλα σε σχολια και δεν αλλαξε
    /*private void addKeyListener(Gameplay aThis) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
