/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Shape;
import com.sun.javafx.geom.Rectangle;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.audio.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;


public class Road extends JPanel implements ActionListener,KeyListener{
          private int score=0;
	  private int space;
	  private int width=80;
	  private int height=70;
	    private int speed;
	    private int move=20;
            private int count=1;
	    private int WIDTH=500;
	    private int HEIGHT=700;
	    private Rectangle chr;
	    private ArrayList<Rectangle> ochr;
            private ArrayList<Rectangle> line;
	    private Random rand;
            BufferedImage user;
            BufferedImage road;
            BufferedImage background;
            BufferedImage samurai;
            BufferedImage samurai1;
            BufferedImage dust;
            public Menu m;
            Boolean liner=true;
            public boolean con=true;
	    Timer t;
            File sound=new File("movesound.WAV");
            File bgm=new File("jaja.WAV");
            public static Clip clip;
            
         
            
            
            public void PlaySound(File Sound)
            {
                try{
                Clip clip=AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Sound));
                clip.start();
                } catch (Exception ex) {
              {
            ex.printStackTrace( );
        }

              }
            }
            
            public void playsound(String filepath){
    try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            // NOTICE: I am only initializing and NOT declaring (no capital Clip)
            clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );    
            }

        catch(Exception e)  {
            e.printStackTrace( );
        }
}

 // call stop method to stop clip form playing
 public void dummyMethod(){
    clip.stop();
 }
        
            

    public boolean isCon() {
        return con;
    }

    public void setCon(boolean con) {
        this.con = con;
    }
            
            
           
          
   

	public Road() {
             try {
                  background=ImageIO.read(new File("../Images/background.png"));
                  user=ImageIO.read(new File("../Images/user.png"));
                  road=ImageIO.read(new File("../Images/ROAD.png"));
                  samurai=ImageIO.read(new File("../Images/samurai.png"));
                  samurai1=ImageIO.read(new File("../Images/samurai1.png"));
                  dust=ImageIO.read(new File("../Images/dust.png"));
              } catch (IOException ex) {
                  Logger.getLogger(Road.class.getName()).log(Level.SEVERE, null, ex);
              }
		t=new Timer(20,this);
		rand=new Random();
		ochr=new ArrayList<Rectangle>();
                line=new ArrayList<Rectangle>();
		chr=new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
	    space=1000;
	    speed=10;
            playsound("../Sounds/jaja.WAV");
            m=new Menu();
	    addKeyListener(this);
	    setFocusable(true);
	    addochrs(true);
            addochrs(true);
            addochrs(true);
            addline(true);
            addline(true);
            addline(true);
            addline(true);
            
	    t.start();
            
	}
        
       
//         public void music(){
//                AudioPlayer MGP=AudioPlayer.player;
//                AudioStream BGM;
//                AudioData MD;
//                ContinuousAudioDataStream loop=null;
//                try{
//                BGM=new AudioStream(new FileInputStream("C:\\Users\\msi\\Documents\\NetBeansProjects\\FA\\jaja.mp3"));
//                MD=BGM.getData();
//                loop=new ContinuousAudioDataStream(MD);
//                }catch(IOException error){}
//                MGP.start(loop);
//        }
        public void addline(Boolean first){
            int x=WIDTH/2-2;
            int y=700;
            int width=4;
            int height=100;
            if(first){
                line.add(new Rectangle(x,y-(line.size()*20),width,height));
            }else
            {
                line.add(new Rectangle(x,line.get(line.size()-1).y-space,width,height));
            }
        }
	public void addochrs(boolean first){
		int positionx=rand.nextInt()%2;
		int x=0;
		int y=0;
		int Width=width;
		int Height=height;
		if(positionx==0)
		{
			x=WIDTH/2-90;
		}else
		{
			x=WIDTH/2+10;
		}
		if(first)
		{
			ochr.add(new Rectangle(x,y-100-(ochr.size()*space),Width,Height));
		}else
		{
			ochr.add(new Rectangle(x,ochr.get(ochr.size()-1).y-300,Width,Height));
		}
	}
        
     
       
        
        
	public void paintComponent(Graphics g){
	super.paintComponents(g);
        //Graphics2D gg=(Graphics2D)g;
//	g.setColor(Color.cyan);
//	g.fillRect(0,0, WIDTH, HEIGHT);
       g.drawImage(background,0,0, null);
       g.drawImage(road,WIDTH/2-265,-15, null);
       //gg.setStroke(new BasicStroke(10));
	//g.setColor(Color.orange);
	//g.fillRect(WIDTH/2-100,0,200,HEIGHT);
        g.drawImage(user,chr.x,chr.y,null);
//	g.setColor(Color.red);
//	g.fillRect(chr.x,chr.y,chr.width,chr.height);
        g.setColor(Color.WHITE);
        for(Rectangle rect:line){
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
	}
	//g.setColor(Color.WHITE);
	//g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
        
	//g.setColor(Color.MAGENTA);
	for(Rectangle rect:ochr){
            if(rand.nextInt()%2==0)
            {
                g.drawImage(samurai,rect.x, rect.y,null);
            }else
            {
                g.drawImage(samurai1,rect.x,rect.y,null);
            }
		//g.fillRect(rect.x,rect.y,rect.width,rect.height);
	}
	}

	public void actionPerformed(ActionEvent e){
		Rectangle rect;
                count++;
		for(int i=0;i<ochr.size();i++)
		{
			rect=ochr.get(i);
                        if(count%1000==0)
                        {
                           if(move<10)
                           {
                               speed++;
                               move+=10;
                           }
                           
                        }
			rect.y+=speed;
		}
		//ochrs crashing with main chr

		for(int i=0;i<ochr.size();i++)
                    {
			if(chr.y==ochr.get(i).y)
                    {         
                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){
                                dummyMethod();
                                  Menu m=new Menu();
                                 condition c=new condition();
                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
                                this.setVisible(false);
                                m.jf.setSize(0,0);
                                m.setVisible(true);
                                m.jf.dispose(); 
                                c.destroy();
                                 
                                
                                 
                                 }
                              
			} 
//                        
//                        	if(chr.y-3==ochr.get(i).y+3)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                              }
//			} 
//                        
//                        	if(chr.y-5==ochr.get(i).y+5)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                 dummyMethod();
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                              }
//			} 
//                                       	if(chr.y-8==ochr.get(i).y+8)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                 
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                
//                                	if(chr.y-10==ochr.get(i).y+10)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){      
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                               	if(chr.y-13==ochr.get(i).y+13)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                        
//                                        	if(chr.y-15==ochr.get(i).y+15)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                 
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                       	if(chr.y-18==ochr.get(i).y+18)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                 
//                                 dummyMethod();
//                                 Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                
//                                                	if(chr.y-20==ochr.get(i).y+20)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){        
//                                 
//                                dummyMethod(); 
//                              Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                               	if(chr.y-23==ochr.get(i).y+23)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                dummyMethod(); 
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                        
//                                                        	if(chr.y-25==ochr.get(i).y+25)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){   
//                                 
//                                dummyMethod(); 
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                
//                                c.destroy();
//                                }
//			} 
//                                                                
//                                                                
//                                                                       	if(chr.y+3==ochr.get(i).y-3)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){    
//                                 
//                                 dummyMethod();
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                                
//                  
//                                                                        
//                                                                        	if(chr.y+5==ochr.get(i).y-5)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                 dummyMethod();
//                                  Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			} 
//                                                                                                                                      
//                                                                        	if(chr.y+8==ochr.get(i).y-8)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                 dummyMethod();
//                                 Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                c.destroy();
//                                 }
//			} 
//                                                                                
//                                                                                
//                                                                                
//                                                                                	if(chr.y+10==ochr.get(i).y-10)
//                    {         
//                           if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){       
//                                 
//                                 dummyMethod();
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                 }
//			}
//                                                                                                                                              
//                                                                        	if(chr.y+13==ochr.get(i).y-13)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 dummyMethod();
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  }
//			} 
//                                                                                        
//                   
//                                                                                                                                                   
//                                                                                	if(chr.y+15==ochr.get(i).y-15)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                 dummyMethod();
//                               Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  }
//			}
//                                                                                                                                              
//                                                                        	if(chr.y+18==ochr.get(i).y-18)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                 dummyMethod(); 
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  }
//			} 
//                                                                                                                                                   
//                                                                                	if(chr.y+20==ochr.get(i).y-20)
//                    {         
//                              if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){       
//                                 dummyMethod();
//                                  Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  }
//			}
//                                                                                        
//                                                                                                                                              
//                                                                        	if(chr.y+23==ochr.get(i).y-23)
//                    {         
//                                if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){     
//                                 
//                                 dummyMethod();
//                                   Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  }
//			} 
//                                                                                        
//                                                                                                                                                                                                                        
//                                                                                	if(chr.y+25==ochr.get(i).y-25)
//                    {         
//                             if(ochr.get(i).x>chr.x-65 && ochr.get(i).x<chr.x+65){      
//                                 
//                                  dummyMethod();
//                                Menu m=new Menu();
//                                 condition c=new condition();
//                                 JOptionPane.showMessageDialog(null,"Game Over!!You Survived From "+"'"+score+"'"+" Enemies");
//                                this.setVisible(false);
//                                this.setSize(0,0);
//                                m.setVisible(true);
//                                m.jf.dispose();
//                                   
//                                c.destroy();
//                                  
//                              }
//			}
//                                                                                                                                                   
//            
//                  
                   }
                for(int i=0;i<ochr.size();i++)
                {
                    rect=ochr.get(i);
                    if(rect.y+rect.height>HEIGHT)
                    {
                        ochr.remove(rect);
                        addochrs(false);
                        score=score+1;
                    }
                }
                
                for(int i=0;i<line.size();i++)
		{
			rect=line.get(i);
                        if(count%1000==0)
                        {
                           speed++;
                        }
			rect.y+=speed;
		}
                
                 for(int i=0;i<line.size();i++)
                { 
                    rect=line.get(i);
                    if(rect.y>HEIGHT)
                    {
                        line.remove(rect);
                        addline(false);
                    }
                }

		repaint();
	}
	//moving functions

//	public void moveup(){
//		if(chr.y-move<0){
//			System.out.println("\b");
//		}else
//		{
//			chr.y-=move;
//		}
//	}
//	public void exit(){
//               Menu r=new Menu();
//               r.jf.;
//               
//	}
        
       
	public void moveleft(){
		if(chr.x-move<WIDTH/2-90){
			System.out.println("\b");
		}else
		{
			chr.x-=move+20;
		}
                PlaySound(sound);
	}
	public void moveright(){
		if(chr.x+move>WIDTH/2+10){
			System.out.println("\b");
		}else
		{
			chr.x+=move+20;
		}
                PlaySound(sound);
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){

//		case KeyEvent.VK_UP:
//		moveup();
//		break;
//
//		case KeyEvent.VK_SPACE:
//			exit();
//			break;

		case KeyEvent.VK_LEFT:
			moveleft();
			break;

		case KeyEvent.VK_RIGHT:
			moveright();
			break;
                        
                default:
                    break;

		}

	}
	@Override
	public void keyTyped(KeyEvent e) {


	}
}
