/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;

import java.awt.Color;//katigoriopoiei ta xromata sto default sRGB color space
import java.awt.Font;//grammatoseires
import java.awt.Graphics;//epitrepei stin efarmogi na zografisei sta sistatika
import java.awt.Graphics2D;//kalitero elegxo sti geometria , sidetagmenes, diaxeirisi xromaton, morfi keimenou
import java.awt.Rectangle;//kathorizei mia perioxi se ena plaisio sidetagmenon pou peribaletai apo tou adikeimenou Rectangle upper-left points (x,y) stie sidetagmenes, to platos kai to ipsos tou
import java.awt.event.ActionEvent; //paragetai otan se ena sistatiko (px koubi) tou simbainei mia energeia (px na patithei)
import java.awt.event.ActionListener;//I diepafi listener gia na dexetai action events. i klasi pou epeksergazetai ena action event ilopoiei tin diepafi
import java.awt.event.KeyEvent;//ena event pou ypodilonei oti ena patima koubiou sinebei se ena sistatiko
import java.awt.event.KeyListener;//i diepafi listener gia na dexetai keyboard events (patimata koubion). i klasi pou epeksergazetai ena keyboard event ilopoiei tin diepafi
import javax.swing.JPanel;//einai san ena doxeio gia na baloume ekei ta sistatika
import javax.swing.Timer;//pirodotei ena i perissotera ActionEvents se sigekrimena xronika diastimata


//to keylistener gia na anixnevi ta koubia pou patame (velakia)
//actionlistener gia tin kinisi tis balas
public class Gameplay extends JPanel implements KeyListener,ActionListener {
    private boolean play = false; //gia na min ksekinai mono tou to pexnidi
    private int score=0; //arxiko score
    
    /* ΠΡΟΣΟΧΗ ΝΑ ΜΗΝ ΑΛΛΑΞΟΥΝ ΟΝΟΜΑΤΑ ΑΥΤΕΣ ΟΙ ΤΡΕΙΣ ΜΕΤΑΒΛΗΤΕΣ */
    //dimiourgo 3 metavlites typou Color gia kathena apo 3 border tou paixnidiou. Arxiko xroma prasino
    private Color cright=Color.green;
    private Color cup=Color.green;
    private Color cleft=Color.green;
    private Color cball=Color.yellow;
    /*ΤΕΛΟΣ ΠΡΟΣΟΧΗΣ */
    
    private int totalBricks=20;//total bricks
   //ta epomena 2 gia tin taxitita tis balas
    //genika to Timer ine enas etimos tipos tis java kai mia metavliti aftou tou tipou xrisimopiite gia na pirodotei gegonota
    
    private Timer timer;//orizoume mia metavliti timer tipou Timer
    
    private int delay=6;//taxitita balas kai kinisi tis platformas the smaller number->the faster
    
    private int playerX=310;//arxiki thesi tis platformas (x)
    //ta epomena 2 gia tin arxiki thesi tis balas        
    private int ballposX=220; 
    private int ballposY=250;
     //ta epomena 2 gia tin kateuthisni tis balas
    private int ballXdir=1;
    private int ballYdir=2;
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
    public void drawrightborder(Color cc, Graphics g){
        g.setColor(cc);
        g.fillRect(691,1,3 ,1000); 
    }
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
     
     g.setColor(Color.green); 
     g.fillRect(691,70 ,320, 5);
     g.fillRect(691,140 ,320, 5);
     g.fillRect(691,0,320,5);
     
     //to kathe border diaforetiko g oste allazodas ena apo ta 3 c na allazei to analogo border
     g.setColor(cright);
     g.fillRect(0, 0, 5, 1000);
     
     g.setColor(cup);
     g.fillRect(0,0,691,5);
     
     g.setColor(cleft);
     g.fillRect(691,0,5 ,1000);
     
     
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
     g.drawString("Karaxristos Georgios", 713, 269);
     
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
     g.fillRect(playerX,550,100,15);
     
     //gia tin bala
     g.setColor(cball);
     g.fillOval(ballposX, ballposY, 20, 20);
     
     if(totalBricks <=0){ //otan spaseis ola ta toubla
         play=false; //teleionei to paixnidi
         ballXdir=0; //bala stamataei na kineitai
         ballYdir=0;
         g.setColor(Color.red); //gia na emfanisoume ta apotelesmata sto telos
         g.setFont(new Font("serif",Font.BOLD, 30)); // name,style,size 
         g.drawString("You Won", 260, 300); //zografizei ti tha leei, orizoume x,y
         
         g.setFont(new Font("serif",Font.BOLD, 20)); //kainourgio font
         g.drawString("Press Enter to Restart", 230, 350);
         
         
     }
     
     //aftes edo oi edoles ine gia to pote tha sou vgazei game over
     if(ballposY> 650){ //an pesei kato dld
         play=false; //stamatei to paixnidi
         ballXdir=0; //stamata na kineitai i bala
         ballYdir=0;//axriasto
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
    public void keyTyped(KeyEvent e) {}//δεν τα χρισιμοποιουμε
    @Override
    public void keyReleased(KeyEvent e) {}//δεν τα χρισιμοποιουμε
   

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //gia tin kinisi tis balas
        if(play){ //tha ine true an exoume patisi ena apo ta 2 velakia ή το εντερ
        //me afto to terastio if tou les apla oti an akoubisi to rectangle tis balas me afto tis platformas na alaksei poreia i bala
            
            if( new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,15))){ // αν τα ορια της μπαλας χτιπισουν τα ορια της πλατφορμας αλλαξε πορια
                
                //an i bala xtipisei kato apo tin platforma, dld plagia, na allaksei x, ara na pesei kato
                if(ballposY>535)
                ballXdir= -ballXdir;
                else
                ballYdir= -ballYdir;
            }    
            //oi parakato edelos prostethikan argotera kai ine gia na anixnevei i bala ta bricks
            
            /* ΠΡΟΣΟΧΗ Η ΛΕΞΗ LOOP ΕΙΝΑΙ ΗΔΗ ΑΛΛΑΓΜΕΝΗ ΠΑΛΙΑ ΗΤΑΝ Α */
            LOOP:  for(int i=0; i<map.map.length; i++){
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
                        Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                        if(ballRect.intersects(brickRect)){ // αν τα ορια της μπαλας χτιπισουν τα ορια τον brick αλλαξε πορια
                            map.setBrickValue(0,i,j); // θετουμε 0 ετσι οστε να μην το ξανα ζωγραφισει η repaint
                            totalBricks--; // μιονουμε τον αριθμο των Bricks
                            score +=5; //ενημερονουμε το score
                            if (map.getbrickcolor() != Color.white)
                                cball=map.getbrickcolor();
                            
                            // afto ine gia ta dexia kai ta aristera meroi ton bricks
                            //ta x gekina einai i aristeri pleura tou sximatos. diametros ballas 20, ara to x einai to proto pixel kai meta +19 i ipolypi bala
                            //stin aristeri pleura tou || les an i dexia pleura tis balas akoubisei tin aristeri pleura tou brick
                            //stin dexia pleura tou || les an i aristeri pleura tis balas akoubisei tin dexia tou brick, pou einai to x tou brick+to platos tou
                            
                            if(ballposX + 19 <=brickRect.x || ballposX + 1 >=brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            } else { //an diladi xtipisei apo pano i kato to brick
                                ballYdir = -ballYdir;
                            }
                            break LOOP;//psaxnoume akoma pos akribos boithaei afto
                        }
                    }
                }
            }
            //analoga me tin timi toy direction allazoun ta x,y tis balas
            ballposX += ballXdir;
            ballposY += ballYdir;
            //pano kato tora tou les an akoubisei kapio border na pari tin aditheti poria apo aftin pou ixe
            //to proto if ine gia to aristero border  to deutero gia to pano to trito gia to dexi
            
            
            /* ΠΡΟΣΟΧΗ ΑΠΟ ΑΥΤΟ ΤΟ ΣΗΜΕΙΟ ΝΑ ΑΛΛΑΞΟΥΝ ΜΟΝΟ ballYdir,ballXdir,ballposX,ballposY */
            if(ballposX <0){
                ballXdir = -ballXdir;
                //kodikas pou epanalambanetai alles 2 fores apo kato, an einai to ena xroma allakse to se allo
                if(cright==Color.green)
                    cright=Color.yellow;
                else if(cright==Color.yellow)
                    cright=Color.cyan;
                else if(cright==Color.cyan)
                    cright=Color.green;
            }
            if(ballposY < 0){
                ballYdir = -ballYdir;
                if(cup==Color.green)
                    cup=Color.yellow;
                else if(cup==Color.yellow)
                    cup=Color.cyan;
                else if(cup==Color.cyan)
                    cup=Color.green;
            }
            if(ballposX >670){
                ballXdir = -ballXdir;
                if(cleft==Color.green)
                    cleft=Color.yellow;
                else if(cleft==Color.yellow)
                    cleft=Color.cyan;
                else if(cleft==Color.cyan)
                    cleft=Color.green;
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
            if(playerX >=590){
                playerX=590;
            } else{
                moveRight();
            }
        }  //ta idia gia aristera tora
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX <5){
                playerX=5;
            } else{
                moveLeft();
            }
        } 
        
        // gia na boris na kaneis retry patontas ENTER
        if(e.getKeyCode()==KeyEvent.VK_ENTER ){
            if(!play){//θα μπει οταν το play ειναι ψευδες  //pano kato tou les ksanavalta ola apo tin arxi an isxiei afti i sinthiki
                play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
                //η αρχικη θεση της μπαλας
                ballposX=120;
                ballposY=350;
                //το πως θα κυνηθει
                ballXdir=-1;
                ballYdir=-2;
                //η αρχικη θεση της πλατφορμας
                playerX=310;
                //το σκορ
                score=0;
                //ποσα Bricks θα εμφανισει
                totalBricks=20;
                map= new Bricks(4,5); //prosoxi to MapGenetor emeis to leme Bricks
                
                repaint(); //καλουμε την repaint ετσι οστε να ξανα ζωγραφισουμε το "παιχνιδι" με της μεταβλιτες που θεσαμε απο πανω
            }
        
        }
        
        
    }
   // dimiourgo tis methodous kinisis gia na  boroun na litourgisoun oi pano sinthikes 
    public void moveRight(){
        play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
        playerX+=20; // i platforma tha kounithi 20 pixel dexia       
    }
    
    public void moveLeft(){
        play=true;//θετουμε την τιμη true ετσι ωστε να ξεκινησει το παιχνιδι
        playerX-=20; // i platforma tha kounithi 20 pixel dexia       
    }
    
}
