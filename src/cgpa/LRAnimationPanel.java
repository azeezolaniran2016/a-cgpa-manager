/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.Dimension;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AZYSS
 */
public class LRAnimationPanel extends JPanel{
    
    private JLabel advertLabel ;  //advert label
    static final int MY_WIDTH = 340 ;  // constant width for advertPanel and label 
    static final int MY_HEIGHT = 240 ;  // constant height for advertPanel and label
    private static final Dimension FRAME_DIMENSION = new Dimension(MY_WIDTH, MY_HEIGHT); // constant dimension for this panel
    private int hPosition ; //advert label horizontal position tracker
    private int sleep ;  // thread sleep time tracker
    private static final int SLOW_SLEEP = 10000;  // slow animation by slowing the thread sleep time
    private static final int FAST_SLEEP = 10;  // slower animation by slowing the thread sleep time
    private boolean fastSleep ;  // To keep track of the sleep time and increase it back after the first long show
    //private int manipulatePosition ;
    private boolean moveLeft ;  // direction tracker
    private Icon[] advertsIcon ;  //Array of icons / adverts for the advert label
    private Thread thread ;  //thread to run
    private int numAdverts ;   // keep the presently displayed advert indice of adverts
    
    /**
     * Constructor for this class
     */
    LRAnimationPanel (){
        super();
        
        //hPosition stuff
        this.hPosition = -MY_WIDTH  ; //initial position of the advert panel
        
        //advertsIcon stuff
        this.advertsIcon = new Icon[]{ 
            PNGGetter.getAdvert2Icon(),PNGGetter.getAdvert3Icon(),PNGGetter.getAdvert4Icon(),PNGGetter.getAdvert5Icon()
            ,PNGGetter.getAdvert6Icon(),PNGGetter.getAdvert7Icon(),PNGGetter.getAdvert8Icon(),PNGGetter.getAdvert9Icon()  
                ,PNGGetter.getAdvert10Icon(),PNGGetter.getAdvert11Icon(),PNGGetter.getAdvert12Icon(),PNGGetter.getAdvert13Icon()
            ,PNGGetter.getAdvert14Icon(),PNGGetter.getAdvert15Icon(),PNGGetter.getAdvert16Icon(),PNGGetter.getAdvert17Icon() 
                ,PNGGetter.getAdvert18Icon(),PNGGetter.getAdvert19Icon(),PNGGetter.getAdvert20Icon(),PNGGetter.getAdvert21Icon()
            ,PNGGetter.getAdvert22Icon(),PNGGetter.getAdvert23Icon(),PNGGetter.getAdvert24Icon(),PNGGetter.getAdvert25Icon() 
                ,PNGGetter.getAdvert26Icon(),PNGGetter.getAdvert27Icon()
        };
        
        //numadverts stuff
        this.numAdverts = 0;
        
        //actions buttons 
        JButton nextButton = new JButton(">> ");;
        JButton prevButton = new JButton("<< ");;
        JButton pauseButton = new JButton(" || ");;
        
        //advert label
        this.advertLabel = new JLabel();
        this.advertLabel.setHorizontalAlignment(JLabel.CENTER);
        this.advertLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.advertLabel.setVerticalAlignment(JLabel.CENTER);
        this.advertLabel.setVerticalTextPosition(JLabel.CENTER);
        //this.advertLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        //this panel stuff
        this.setOpaque(false);
        this.setLayout(null);
        this.setMaximumSize(FRAME_DIMENSION);
        this.setMinimumSize(FRAME_DIMENSION);
        this.setPreferredSize(FRAME_DIMENSION);
        this.setVisible(true);
        //this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        this.add(this.advertLabel).setBounds(hPosition, 0, MY_WIDTH, MY_HEIGHT);
        //this.add(new JButton("advert oh")).setBounds(0, 0, MY_WIDTH, MY_HEIGHT);
        
        //thread stuff
        this.sleep = FAST_SLEEP ;  // first scroll
        this.thread = new Thread(){
            @Override
            public void run(){
                while(true){
                    LRAnimationPanel.this.doAdvertMove() ;
                    try {
                        Thread.sleep(sleep);
                        if(!fastSleep)
                            sleep = FAST_SLEEP ;  // if its not a fast sleep, make it a fast sleep after the long sleep i.e previous code
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LRAnimationPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        this.thread.setPriority(Thread.MIN_PRIORITY);
        this.thread.setDaemon(true);
        this.thread.start();
        
         
    }
    
    /**
     * Method to calculate and do the advert animation/ movement
     */
    private void doAdvertMove(){
        if(this.numAdverts == this.advertsIcon.length)
            numAdverts = 0;
        //if advert is at the middle, slow movement
        if(hPosition == (hPosition/2) || hPosition == -(hPosition/2)){
            sleep = SLOW_SLEEP ;
            //fastSleep = false ; // reset fastSleep here
        }
        //if advert is at 3/4, speed up movement
        //if(hPosition == (hPosition/4) || hPosition == -(hPosition/4))
        //    sleep = FAST_SLEEP;
        
        //if advert is out of the window(right) change movement to opposite  direction
        if(hPosition == MY_WIDTH){
            this.moveLeft = false ;
            sleep = FAST_SLEEP;
            //change icon here
            this.advertLabel.setIcon(this.advertsIcon[new Random().nextInt(this.advertsIcon.length)]);
            ++this.numAdverts ;
        }
        
        //if advert is out of the window(left), change movement ot opposite direction
        if(hPosition == -MY_WIDTH){
            this.moveLeft = true ;
            sleep = FAST_SLEEP;
            //change icon here
            this.advertLabel.setIcon(this.advertsIcon[new Random().nextInt(this.advertsIcon.length)]);
            ++this.numAdverts ;
        }
        
        //if it just finished left movement, go right ??
        if(moveLeft){
            goRight();
        }
        else{ //if it just finished right movement, go left ??
            goLeft();
        }
        
    }
    
    /**
     * Method to move the advert right
     */
    private void goRight(){
        this.advertLabel.setBounds(++this.hPosition, 0, MY_WIDTH, MY_HEIGHT);
        //this.repaint();
    }
    
    /**
     * Method to move the advert left
     */
    private void goLeft(){
        this.advertLabel.setBounds(--this.hPosition, 0, MY_WIDTH, MY_HEIGHT);
        //this.repaint();
    }
    
    /*
    public static void main(String[] azeez){
        JFrame frame = new JFrame("test LRAnimation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LRAnimationPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    */
    
}
