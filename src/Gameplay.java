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
    
    private int totalBricks=21;//total bricks
   //ta epomena 2 gia tin taxitita tis balas 
    private Timer timer;//DEN TO PIASA
    //STO 22 O MALAKAS POU TA DIXNEI TA GAMAEI TIN MANA KAI ADI GIA TIMER GRAFEI TIME
    //META TO DIORTHONI ALLA DEN DIXNEI OTI TO DIORTHOSE KAI APORIS TI EXIS KANEI LATHOS KAI AFTOUNOU TREXEI
    private int delay=6;//ball speed, the smaller number->the faster
    
    private int playerX=310;//arxiki thesi tis platformas (x)
    //ta epomena 2 gia tin arxiki thesi tis balas        
    private int ballposX=220; 
    private int ballposY=250;
     //ta epomena 2 gia tin kateuthisni tis balas
    private int ballXdir=1;
    private int ballYdir=2;
    //domitis gia na valo tis parapano times otan dimiourgithi adikimeno apo aftin tin klasi
    
    private Bricks map;//prosoxi!! ipenthimizo oti opou grafei MapGenerator aftos  ego grafo Bricks 
    //afou outos i allos tha ta alaksoume ola ta onomata
    
    public Gameplay(){ //sto video aftos den vazei mesa sto gameplay to Timer timer sto apache omos den trexei allios
        map=new Bricks(3,7); // dimiourgi grammes kai stiles gia ta bricks
        //to parapano prostethike argotera meta tin dimiourgia tis klasis Bricks
        
        //akolouthoun malakies mesa ston domiti pou to video den exigi kan ti ine
        addKeyListener(this);
        setFocusable(true);// indicates whether a component can gain the focus if it is requested to do so
        setFocusTraversalKeysEnabled(false);//decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus
        timer = new Timer(delay,this);
        timer.start();
    }
    //i parakato methodos ine gia ton sxediasmo adikimenon (bala,platforma klp)
    // prepi na kanoume import ta graphics prota 
    //genikotera prepi na kanoume import xilia pramata pou den kserame kan tin iparksi tous
    //efxaristoume dipae
    public void paint(Graphics g){
     //xroma gia to backgroung 2 epomena   
     //an kai exo dei methodous se alla tutorial gia na vazeis eikona piso.tha ta alaksoume ola 
     g.setColor(Color.black);
     g.fillRect(1,1, 692, 592);
     //na thimaste o tipos tis fillRect ine:g.fillRect(x, y, WIDTH, HEIGHT) kai emis gemizoume tis times
     
     //dimiourgia ton bricks apo kato
     map.draw((Graphics2D)g); //draws the bricks
     // malakizete ligo sto lepto 36miso 
     
     
     //ta giro giro
     //an thimaste kai apo html pai aristera pano dexia kato
     //opote an kano 3 den tha dimiourgithei to kato gia na pefti i bala
     g.setColor(Color.yellow);
     g.fillRect(1, 0, 3, 592);
     g.fillRect(0,1,692,3);
     g.fillRect(691,0,3 ,592);
     
     // gia tin emfanisi tou score
     g.setColor(Color.white);
     g.setFont(new Font("serif",Font.BOLD, 25));
     g.drawString(""+score, 590, 30);
     
     
     //gia tin platforma
     g.setColor(Color.green);
     g.fillRect(playerX,550,100,8);
     
     //gia tin bala
     g.setColor(Color.yellow);
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
     if(ballposY> 570){ //an pesei kato dld
         play=false; //stamatei to paixnidi
         ballXdir=0; //stamata na kineitai i bala
         ballYdir=0;
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
            if( new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){ // αν τα ορια της μπαλας χτιπισουν τα ορια της πλατφορμας αλλαξε πορια
                ballYdir= -ballYdir;
            }    
            //oi parakato edelos prostethikan argotera kai ine gia na anixnevei i bala ta bricks
            A:  for(int i=0; i<map.map.length; i++){
                for(int j = 0;j<map.map[0].length;j++){//to ti ine to map.map to eksigi kala sto 41:20
                    if(map.map[i][j]>0){
                        //δημιοργουμε τα ορια για καθε brick
                        int brickX= j * map.brickWidth + 80;
                        int brickY= i * map.brickHeight + 50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;  
                        //pali dimiourgoume ena Rectangle apo giro
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        //kai giro apo tin bala tora
                        Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect = rect;
                        if(ballRect.intersects(brickRect)){ // αν τα ορια της μπαλας χτιπισουν τα ορια τον brick αλλαξε πορια
                            map.setBrickValue(0,i,j); // θετουμε 0 ετσι οστε να μην το ξανα ζωγραφισει η repaint
                            totalBricks--; // μιονουμε τον αριθμο των Bricks
                            score +=5; //ενημερονουμε το score
                            // afto ine gia ta dexia kai ta aristera meroi ton bricks
                            if(ballposX + 19 <=brickRect.x || ballposX + 1 >=brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            //pano kato tora tou les an akoubisei kapio border na pari tin aditheti poria apo aftin pou ixe
            //to proto if ine gia to aristero border  to deutero gia to pano to trito gia to dexi
            if(ballposX <0){
                ballXdir = -ballXdir;
            }
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            if(ballposX >670){
                ballXdir = -ballXdir;
            }
        }
        repaint(); //afto tha ksanazigrafisi otidipote vriskete stin puclib void paint
    }
    //gia tis kinisis tis platformas
    @Override
    public void keyPressed(KeyEvent e) { //μεθοδος για το οταν πατιθει ενα πληκτρο
    //an patao to deksi velaki kai den exi figi i platforma ekso apo ta borders kounisou dexia
        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX >=600){
                playerX=600;
            } else{
                moveRight();
            }
        }  //ta idia gia aristera tora
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX <10){
                playerX=10;
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
                totalBricks=21;
                map= new Bricks(3,7); //prosoxi to MapGenetor emeis to leme Bricks
                
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