/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brick_breaker;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author User
 */
class Gameplay extends JPanel implements KeyListener,ActionListener{
    //genika to Timer ine enas etimos tipos tis java kai mia metavliti aftou tou tipou xrisimopiite gia na pirodotei gegonota
    private Timer timer;//orizoume mia metavliti timer tipou Timer
    
    private Bricks map;//metavliti map tipou bricks
    
    //domitis gia na valo tis parapano times otan dimiourgithi adikimeno apo aftin tin klasi
    public Gameplay(){
        map=new Bricks(5,6); // dimiourgi grammes kai stiles gia ta bricks
        addKeyListener(this);
        setFocusable(true);// indicates whether a component can gain the focus if it is requested to do so
        setFocusTraversalKeysEnabled(false);//decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus
        timer = new Timer(0,this); //vazoume ena timer gia na pirodoti gegonota ana 6 delay 
        timer.start();
    }
    
    private boolean play = false; //gia na min ksekinai mono tou to pexnidi
    
    private int platformΧ=300;//arxiki thesi tis platformas
    
    private Color ballColor=Color.WHITE;
    //ta epomena 2 gia tin arxiki thesi tis balas        
    private int ballX=340; 
    private int ballY=530;
    //ta epomena 2 gia tin kateuthisni tis balas
    private int ballVelocityX=1;
    private int ballVelocityY=2;
    
    private int score=0; //arxiko score
    
    private int totalBricks=30;//total bricks
    
    //dimiourgo 3 metavlites typou Color gia kathena apo 3 border tou paixnidiou. Arxiko xroma prasino
    private Color rightColor=Color.GREEN;
    private Color topColor=Color.GREEN;
    private Color leftColor=Color.GREEN;

    //i parakato methodos ine gia ton sxediasmo adikimenon (bala,platforma klp)
    @Override
    public void paint(Graphics g){
        //backgroung   
        g.setColor(Color.black);
        g.fillRect(1,1,1000,650);
        
        //Bricks
        map.draw((Graphics2D)g);
        
        //platform
        g.setColor(Color.green);
        g.fillRect(platformΧ,550,100,15);
     
        //ball
        g.setColor(ballColor);
        g.fillOval(ballX, ballY, 20, 20);

        Graphics2D g2 = (Graphics2D) g;
        
        //perigrama
        g.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(10));
        g.drawLine(700,100,1000,100);
        g.drawLine(700,200,1000,200);
        g.drawRect(700,6,289,610);
        
        
        //perigrama me xromata
        g.setColor(rightColor);
        g.drawLine(700,6,700,616);
        
        g.setColor(topColor);
        g2.setStroke(new BasicStroke(10));
        g.drawLine(6,6,690,6);
        
        g.setColor(leftColor);
        g.drawLine(6,6,6,616);

        //score - totalBricks
        g.setColor(Color.white);
        g.setFont(new Font("Courier",Font.BOLD, 25));
        g.drawString("Score:"+score, 800,65);
        g.drawString("Remaining Bricks:"+totalBricks, 723,160);
        
        //made by
        g.setColor(new Color(179, 0, 0));
        g.setFont(new Font("Courier",Font.BOLD, 25));
        g.drawString("Created by: ", 780, 250);
        g.drawString("Karachristos Georgios", 710, 300);
        g.drawString("Tsantilis Vasileios", 740,350);
        g.drawString("Fotopoulou Nikoletta", 726,400);

        //how to start
        if (!play && totalBricks >0 && ballY< 650){
            g.setColor(Color.magenta);
            g.setFont(new Font("Courier",Font.BOLD, 25));
            g.drawString("Press space to start!", 230,300); 
        }

        //Minima an xasis i kerdisis
        if(totalBricks <=0 || ballY> 650){ 
            play=false;
            ballVelocityX=0; 
            ballVelocityY=0;
            g.setColor(Color.red);
            g.setFont(new Font("Courier",Font.BOLD, 30)); 
            if (totalBricks <=0)
                g.drawString("You Won! Score:"+score, 235, 300);
            if (ballY> 650)
                g.drawString("Game Over! Score:"+score, 220, 300);
            g.drawString("Press Enter to Restart", 205, 330);
        }
        g.dispose(); //katastrefei to jframe    
    }
    //override apo KeyListen interface pou kaname implement
    //to apache mou evgaze mono tou throws profanos kai ta evgala
    //ta dio prota den tha xrisimopoiithoun alla an ta vgaloume tha petaksi error
    //πρεπει να τα βαλουμε ομος γιατι κανουμε implements την κλαση KeyListener
    int i,j,brickX,brickY,brickWidth,brickHeight;
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //gia tin kinisi tis balas
        if(play){
            if( new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(platformΧ,550,100,15))){ // αν τα ορια της μπαλας χτιπισουν τα ορια της πλατφορμας αλλαξε πορια
                //an i bala xtipisei kato apo tin platforma, dld plagia, na allaksei x, ara na pesei kato
                if(ballY>535)
                    ballVelocityX= -ballVelocityX;
                else
                    ballVelocityY= -ballVelocityY;
            }    
            //oi parakato edelos prostethikan argotera kai ine gia na anixnevei i bala ta bricks
            LOOP:  for (i=0; i<map.map.length; i++) {
                for (j = 0;j<map.map[0].length;j++) {//to ti ine to map.map to eksigi kala sto 41:00
                    if (map.map[i][j]>0) {
                        //δημιοργουμε τα ορια για καθε brick
                        brickX= j * map.brickWidth + 80;
                        brickY= i * map.brickHeight + 50;
                        brickWidth=map.brickWidth;
                        brickHeight=map.brickHeight;  
                        //pali dimiourgoume ena Rectangle apo giro
                        Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        //kai giro apo tin bala tora
                        Rectangle ballRect=new Rectangle(ballX,ballY,20,20);
                        if (ballRect.intersects(brickRect)) { // αν τα ορια της μπαλας χτιπισουν τα ορια τον brick αλλαξε πορια
                            map.setBrickValue(0,i,j); // θετουμε 0 ετσι οστε να μην το ξανα ζωγραφισει η repaint
                            totalBricks--; // μιονουμε τον αριθμο των Bricks
                            score +=5; //ενημερονουμε το score
                            
                            if (i==j || i+2==j || i+4==j || i-2==j || i-4==j)
                                ballColor=Color.orange;
                            else
                                ballColor=Color.cyan;
                            
                            // afto ine gia ta dexia kai ta aristera meroi ton bricks
                            //ta x gekina einai i aristeri pleura tou sximatos. diametros ballas 20, ara to x einai to proto pixel kai meta +19 i ipolypi bala
                            //stin aristeri pleura tou || les an i dexia pleura tis balas akoubisei tin aristeri pleura tou brick
                            //stin dexia pleura tou || les an i aristeri pleura tis balas akoubisei tin dexia tou brick, pou einai to x tou brick+to platos tou
                            if (ballX + 19 <=brickRect.x || ballX+1 >=brickRect.x + brickRect.width) 
                                ballVelocityX = -ballVelocityX;
                            else  //an diladi xtipisei apo pano i kato to brick
                                ballVelocityY = -ballVelocityY;
                            break LOOP;//ama akoubisei 2 toubla mazi stamataei to loop stin mia allagi foras 
                        }
                    }
                }
            }
            //analoga me tin timi toy direction allazoun ta x,y tis balas
            ballX += ballVelocityX;
            ballY += ballVelocityY;
            //pano kato tora tou les an akoubisei kapio border na pari tin aditheti poria apo aftin pou ixe
            //to proto if ine gia to aristero border  to deutero gia to pano to trito gia to dexi
            
            
            if(ballX <10){
                ballVelocityX = -ballVelocityX;
                //kodikas pou epanalambanetai alles 2 fores apo kato, an einai to ena xroma allakse to se allo
                if(leftColor==Color.green)
                    leftColor=Color.yellow;
                else if(leftColor==Color.yellow)
                    leftColor=Color.cyan;
                else if(leftColor==Color.cyan)
                    leftColor=Color.green;
                
            }
            if(ballY < 10){
                ballVelocityY = -ballVelocityY;
                if(topColor==Color.green)
                    topColor=Color.yellow;
                else if(topColor==Color.yellow)
                    topColor=Color.cyan;
                else if(topColor==Color.cyan)
                    topColor=Color.green;
            }
            if(ballX >675){
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
    
    
    @Override
    public void keyTyped(KeyEvent e){}//δεν τα χρισιμοποιουμε
    @Override
    public void keyReleased(KeyEvent e){}//δεν τα χρισιμοποιουμε
    //gia tis kinisis tis platformas
    @Override
    public void keyPressed(KeyEvent e) { //μεθοδος για το οταν πατιθει ενα πληκτρο
    //an patao to deksi velaki kai den exi figi i platforma ekso apo ta borders kounisou dexia
        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(platformΧ+20>595) //το εκανα για να ειναι στα ορια
                platformΧ=595;
            else
                platformΧ+=20;
            if (!play && totalBricks >0)
                ballX=platformΧ+40;
        }  //ta idia gia aristera tora
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(platformΧ-20<11) //το εκανα για να ειναι στα ορια
                platformΧ=11;
            else
                platformΧ-=20;
            if (!play && totalBricks >0)
                ballX=platformΧ+40;   
        }
        //για να ξεκινιση ημπαλα να κινιτε
        if(e.getKeyCode()==KeyEvent.VK_SPACE ){
            play=true;
        }
        
        // gia na boris na kaneis retry patontas ENTER
        //ξανα αρχικοποιουμε της μεταβλητες
        if(e.getKeyCode()==KeyEvent.VK_ENTER ){
            if(!play){
                ballColor=Color.WHITE;
                ballX=340;
                ballY=530;
                ballVelocityX=1;
                ballVelocityY=2;
                platformΧ=300;
                score=0;
                totalBricks=30;
                map= new Bricks(5,6);
                rightColor=Color.GREEN;
                topColor=Color.GREEN;
                leftColor=Color.GREEN;
                
                repaint(); //καλουμε την repaint ετσι οστε να ξανα ζωγραφισουμε το "παιχνιδι" με της μεταβλιτες που θεσαμε απο πανω
            }
        }
    }  
}