//RUNN THIS
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class GuitarHero extends JFrame implements ActionListener, Runnable, KeyListener{ 
  PrintWriter out=null;
  BufferedReader in=null;
  String [] name={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","W","X","Y","Z"};
  String [] soundeffects;
  String [] fields;
  String line="",hscore1="",hscore2="",hscore3="",user1="",user2="",user3="",user="NIC",a=name[0],b=name[0],c=name[0],spacer="";
  int [] speed3;
  int [] notes1;
  int [] speed1;  
  int [] notes2;
  int [] speed2;   
  int [] notes3;
  int[] NOTE_NAMES = {0,1,2,3,4,5,6,7,8,9,10,11};
  int NOTE_ON = 0x90;
  int NOTE_OFF = 0x80;
  int lightred=0,lightblue=0,lightgreen=0,lightorange=0,start=0,popop=0,combo=0,trackk=0,pass=0,aa=0,rectx=60,recty=460,gameselect=0,cc=1,bigcounter=0,jj=0,kk=0,ll=0,flagflag=0,score=0,hold=0,pointy=700,highscore=0;
  int showred=0,showblue=0,showorange=0,showgreen=0,redcounter=0,bluecounter=0,greencounter=0,orangecounter=0;
  File f1=new File("original.txt");
  File f2=new File("temp.txt");
  long clipTime;
  Blocks red,green,blue,orange;
  Notes [] note;
  Pointer point;
  TrackList trackchose;
  Clip clip0,clip1,clip2,clip3,clip4,soundclip0,soundclip1,soundclip2,soundclip3,themer;
  BufferedImage backimage=null,redred=null,greengreen=null,blueblue=null,orangeorange=null,red1=null,green1=null,blue1=null,orange1=null,titleimage=null,startimage=null,selector=null,endimage=null,scoreboard=null,username=null,pcombo=null,menumenu=null;
  Image  pointer = Toolkit.getDefaultToolkit().getImage("pointpoint2.gif"), pointer2 = Toolkit.getDefaultToolkit().getImage("pointpoint.gif"), fire = Toolkit.getDefaultToolkit().getImage("fire.gif");
  MyDrawPanel drawpanel1;    
  Thread th = new Thread (this); 
  public static void main(String[ ] args)   
  {
    new GuitarHero();
  }    
  ////////////////////////////////////////////STARTUP//////////////////////////////// 
  public GuitarHero(){
    trackchose= new TrackList();
    trackchose.createimage();
    trackchose.createfile();
    red= new Blocks (); 
    green= new Blocks ();
    orange= new Blocks ();
    blue= new Blocks ();
    red.setpos(37,735);
    green.setpos(149,735);
    blue.setpos(261,735);
    orange.setpos(373,735);
    point= new Pointer();
    notes1= new int [7000];
    speed1= new int [7000];
    notes2= new int [7000];
    speed2= new int [7000];
    notes3= new int [7000];
    speed3= new int [7000];
    note=new Notes [7000];
    soundeffects= new String[4];
    soundeffects[0]="select.wav";
    soundeffects[1]="click.wav";
    soundeffects[2]="yourock.wav";
    soundeffects[3]="score.wav";
    try { 
      menumenu=ImageIO.read(new File("menumenu.png"));
      username=ImageIO.read(new File("USERNAME.png"));
      endimage=ImageIO.read(new File("ENDIMAGE.png"));
      scoreboard=ImageIO.read(new File("SCOREBOARD.png"));
      pcombo=ImageIO.read(new File("pcombo.png"));
      backimage=ImageIO.read(new File("BACKIMAGE2.png"));
      startimage=ImageIO.read(new File("STARTIMAGE.png"));
      titleimage=ImageIO.read(new File("TITLEIMAGE.png"));
      selector=ImageIO.read(new File("SONGIMAGE.png"));
      redred=ImageIO.read(new File("red.png"));
      greengreen=ImageIO.read(new File("green.png"));
      blueblue=ImageIO.read(new File("blue.png"));
      orange1=ImageIO.read(new File("orange1.png"));
      orangeorange=ImageIO.read(new File("orange.png"));
      red1=ImageIO.read(new File("red1.png"));
      green1=ImageIO.read(new File("green1.png"));
      blue1=ImageIO.read(new File("blue1.png"));  
    }catch(IOException e){
      System.out.println("no work");} 
    opensongs(); 
    addKeyListener(this);    
    drawpanel1=new MyDrawPanel();
    th.start (); 
    this.add (drawpanel1);     
    this.setSize(800,820);
    this.setVisible(true);
    this.setResizable(false);  
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
        System.exit(0);
      }
    }
    );
  } 
  ///////////////////////////////////////////////////////////////////
  
  
  ///////////////////////////DRAWPANEL///////////////////////////////
  class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      Font f1= new Font("BolsterBold", Font.BOLD,42);
      Font f2= new Font("BolsterBold", Font.BOLD,52);
      Font f3= new Font("BolsterBold", Font.BOLD,190);
      Font f4= new Font("BolsterBold", Font.BOLD,24);
      if (gameselect==0){
        g2.drawImage(startimage,0,0,800,800,null); 
        g2.drawImage(pointer,650,pointy,40,40,null);
        g2.drawImage(pointer2,110,pointy,40,40,null);
      }       
      if (gameselect==1){
        g2.setFont(f4);
        g2.setColor(Color.white);
        g2.drawImage(titleimage,0,0,800,800,null); 
        g2.drawImage(pointer,530,pointy,40,40,null);
        g2.drawString(""+user,680,29); 
      }      
      if(gameselect==2){
        g2.drawImage(selector,0,0,800,800,null); 
        g2.drawImage(pointer,650,pointy,40,40,null);      
      }     
      if (gameselect==3){
        g2.drawImage(backimage,0,0,800,800,null);
        g2.setFont(f1);
        g2.setColor(Color.white);
        g2.fillRect(point.xpos,point.ypos,point.lenght,point.width);
        if (popop==0){
        g2.drawImage(trackchose.image[cc],30,20,400,600,null);
        }
        g2.drawImage(trackchose.imagename[cc],460,65,343,100,null);
        g2.setColor(Color.white);
        g2.drawString(""+score,480,370);
        g2.drawString("x"+combo,522,680);
        g2.drawString(""+hscore1,480,250);
        if (lightred==1){          
          g2.drawImage(red1,37,735,50,50,null);
        }
        if (lightgreen==1){
          
          g2.drawImage(green1,149,735,50,50,null);
        }
        if (lightblue==1){
          
          g2.drawImage(blue1,261,735,50,50,null);
        }
        if (lightorange==1){
          
          g2.drawImage(orange1,373,735,50,50,null);
        }        
        if (start==1){
          for (int i=0+pass; i <trackchose.trackkval[cc]; i++) { 
            if(note[i].xpos==37){
              g2.drawImage(redred,note[i].xpos,note[i].ypos,note[i].size,note[i].size,null);    
            }
            if(note[i].xpos==149){
              g2.drawImage(greengreen,note[i].xpos,note[i].ypos,note[i].size,note[i].size,null);    
            }
            if(note[i].xpos==261){
              g2.drawImage(blueblue,note[i].xpos,note[i].ypos,note[i].size,note[i].size,null);    
            }
            if(note[i].xpos==373){
              g2.drawImage(orangeorange,note[i].xpos,note[i].ypos,note[i].size,note[i].size,null);    
            }            
          }  
          if (combo>=50){
            g2.drawImage(pcombo,470,400,200,200,null);
            if (flagflag==0){
              flagflag=1;
            }
          }
          if (combo==0 && flagflag==1){
            flagflag=0;
          }
        }
        if (showred==1 && redcounter!=10){
          g2.drawImage(fire,37,715,50,70,null);     
          redcounter+=1;
        }
        else if (redcounter==10){
        showred=0;
        redcounter=0;
        }
        if (showblue==1 && bluecounter!=10){
          g2.drawImage(fire,373,715,50,70,null);     
          bluecounter+=1;
        }
        else if (bluecounter==10){
        showblue=0;
        bluecounter=0;
        }
        if (showgreen==1 && greencounter!=10){
          g2.drawImage(fire,149,715,50,70,null);     
          greencounter+=1;
        }
        else if (greencounter==10){
        showgreen=0;
        greencounter=0;
        }
        if (showorange==1 && orangecounter!=10){
          g2.drawImage(fire,261,715,50,70,null);     
          orangecounter+=1;
        }
        else if (orangecounter==10){
        showorange=0;
        orangecounter=0;
        }
        
        
        
        if (popop==1){
          g2.drawImage(menumenu,50,200,700,400,null);
        }        
      }
      if (gameselect==4){
        g2.setFont(f1);
        g2.setColor(Color.white);
        g2.drawImage(endimage,0,0,800,800,null);
        g2.drawImage(pointer,550,pointy,40,40,null);
        g2.drawString(""+score,320,480);
        
        if(highscore==0){
          g2.setColor(Color.black);
          g2.fillRect(35,510,300,30);
        }       
      }
      if (gameselect==5){
        
        g2.drawImage(scoreboard,0,0,800,800,null);
        g2.drawImage(trackchose.imagename[cc],250,130,343,100,null);
        g2.setFont(f2);
        g2.setColor(Color.white);
        g2.drawString(""+hscore1,170,285);
        g2.drawString(""+hscore2,170,435);
        g2.drawString(""+hscore3,170,585);
        g2.drawString(""+user1,560,285);
        g2.drawString(""+user2,560,435);
        g2.drawString(""+user3,560,585);
      }
      if (gameselect==6){
        g2.drawImage(username,0,0,800,800,null);
        g2.setFont(f3);
        g2.setColor(Color.white);
        g2.drawString(spacer+""+name[jj],90,360);
        g2.drawString(spacer+""+name[kk],320,360);
        g2.drawString(spacer+""+name[ll],550,360);
        g2.fillRect(rectx,recty,220,20);
      }   
    }
  }
  ///////////////////////////////////////////////////////
  
  
  /////////////////////THREAD///////////////////////////////
  public void run () 
  {  
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);    
    while (true) 
    { 
      if (gameselect==3){
        if (popop==0){
          if (start==1){
            point.checkout();
            checkout();
            checkend();
            for (int i=0; i < trackk; i++) { 
              note[i].updatepostion();                     
            }
          }
        }
      } 
      repaint();  
      try 
      {  
        Thread.sleep (3-aa); 
      } 
      catch (InterruptedException ex) 
      {  
      } 
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
    }
  }
/////////////////////////////////////////////////////////////////  
  
/////////////////////KEYBOARDCOMMANDS/////////////////////////////
  public void actionPerformed(ActionEvent e){} 
  public void keyReleased (KeyEvent e){
    int code =e.getKeyCode();
    if (code == KeyEvent.VK_F1 || code == KeyEvent.VK_Q )   
    { 
      if(gameselect==1){
        if (pointy==530){  
          playsoundeffect(soundeffects[1]);
          gameselect=2;
          cc=0;
          stopstop();
          
          pointy=200;
        }
        if (pointy==595){ 
          playsoundeffect(soundeffects[1]);
          gameselect=5;
          cc=0;
          load();
        } 
        if (pointy==660){
          jj=0;
          kk=0;
          ll=0;
          gameselect=6;
        }    
      }
      else if (gameselect==2){
        if(pointy==200){
          stopstop();
          playsoundeffect(soundeffects[1]);
          cc=0;
          aa=0;
          load();
          MakeSong();
          gameselect=3;
          pointy=-1000;
        }
        if(pointy==309){
          stopstop();
          playsoundeffect(soundeffects[1]);
          cc=1;
          aa=1;
          load();
          MakeSong();
          gameselect=3;
          pointy=-1000;
        }
        if(pointy==418){
          stopstop();
          playsoundeffect(soundeffects[1]);
          cc=2;
          load();
          aa=1;
          MakeSong();
          gameselect=3;
          pointy=-1000;
        }
        if(pointy==527){
          stopstop();
          playsoundeffect(soundeffects[1]);
          cc=3;
          load();
          aa=1;
          MakeSong();
          gameselect=3;
          pointy=-1000;
        }
        if(pointy==636){
          stopstop();
          playsoundeffect(soundeffects[1]);
          cc=4;
          load();
          aa=2;
          MakeSong();
          gameselect=3;
          pointy=-1000;
        }
      }
      else if(gameselect==4){
        if (pointy==555){
          playsoundeffect(soundeffects[1]);
          score=0;
          bigcounter=0;
          highscore=0;
          combo=0;
          gameselect=3;
          MakeSong();
        } 
        else if (pointy==609){
          playsoundeffect(soundeffects[1]);
          score=0;
          highscore=0;
          bigcounter=0;
          combo=0;
          gameselect=1;
          pointy=530;
          playtitle();
        } 
        else if (pointy==663){
          playsoundeffect(soundeffects[1]);
          score=0;
          highscore=0;
          bigcounter=0;
          combo=0;
          pointy=200;
          gameselect=2;
          cc=0;
          play();
        }   
      }
      else if (gameselect==6){
        rectx+=230;
        if (rectx==750){
          rectx=300;
          recty+=150;
        }
        else if(recty==610){
          recty=460;
          rectx=60;
          user=name[jj]+name[kk]+name[ll];
          gameselect=1;
        }      
        playsoundeffect(soundeffects[1]);
      }
      if (gameselect==3 && popop==0){
        lightred=0;
      }
    }   
    if (code == KeyEvent.VK_F2 || code == KeyEvent.VK_W)   
    { 
      if (gameselect==2){
        gameselect=1;
        stopstop();
        playtitle();
        pointy=530;
      }
      if (gameselect==3 && popop==0){
        lightgreen=0;
      }
      if (gameselect==5){
        gameselect=1;
        cc=0;
      }
      if (gameselect==6){
        if(rectx==60)
        {
          gameselect=1;
        }
        else if(rectx==290)
        {
          rectx=60;
        }
        else if(rectx==520)
        {
          rectx=290;
        }
        else{
          rectx=520;
          recty=460;
        } 
      }
    }
    if (code == KeyEvent.VK_F3 || code == KeyEvent.VK_E)   
    { 
      if (gameselect==3 && popop==0){
        lightblue=0;
      }
    }
    if (code == KeyEvent.VK_F4 || code == KeyEvent.VK_R)   
    { 
      if (gameselect==3 && popop==0){
        lightorange=0;
      }  
    }
    if (code == KeyEvent.VK_F5)   
    { 
      if (gameselect==3 && popop==1){
        score=0;
        combo=0;
        stopstop();
        point.ypos=620;
        MakeSong();
        popop=0;
      }      
    }
    if (code == KeyEvent.VK_F6)   
    { 
      if (gameselect==3 && popop==1){
        score=0;
        combo=0;
        stopstop();
        point.ypos=620;
        popop=0;
        playtitle();
        gameselect=1;
        pointy=530;
      }      
    }
    if (code == KeyEvent.VK_ENTER)   
    { 
      if (gameselect==3 && popop==0){
        hold=0;
      }
      
    }    
  }
  public void keyTyped(KeyEvent e){}  
  public void keyPressed (KeyEvent e)
  {
    int code =e.getKeyCode(); 
    if (code == KeyEvent.VK_F1 || code == KeyEvent.VK_Q)   
    { 
      if (gameselect==3 && popop==0){
        lightred=1;
        if (cc==4){
          checkhits();
        }
      }
    }
    if (code == KeyEvent.VK_F2 || code == KeyEvent.VK_W)   
    { 
      if (gameselect==3 && popop==0){
        lightgreen=1;  
        if (cc==4){
          checkhits();
        }
      }
      
    }
    if (code == KeyEvent.VK_F3 || code == KeyEvent.VK_E)   
    { 
      if (gameselect==3 && popop==0){
        lightblue=1;  
        if (cc==4){
          checkhits();
        }
      }
    }
    if (code == KeyEvent.VK_F4|| code == KeyEvent.VK_R)   
    { 
      if (gameselect==3 && popop==0){
        lightorange=1;   
        if (cc==4){
          checkhits();
        }
      }
    }
    
    if (code == KeyEvent.VK_ENTER)   
    {  
      if (gameselect==0 ){
        gameselect=1;
        pointy=530;       
        playsoundeffect(soundeffects[0]);
        playtitle();
      }
      else if (gameselect==1){
        pointy+=65;
        if (pointy>700){
          pointy=530;
        }
      }
      else if (gameselect==2){
        if (pointy!=636){
          pointy+=109;
          cc+=1;
          load();
        }
        else {
          pointy=200;
          cc=0;
          load();
        }
        stopstop();
        play();
        if (pointy>700){
          pointy=200;
        }
      }
      else if (gameselect==3 && popop==0){
        if (hold==0){
          checkhits();
          hold=1;
          
        }
      }
      else if (gameselect==4){
        pointy+=54;
        if (pointy>700){
          pointy=555;
        }
      }
      else if (gameselect==5){
        cc+=1;
        
        if (cc==5){
          cc=0;
          load();
        }
        playsoundeffect(soundeffects[1]);
        load();       
      }
      else if (gameselect==6){
        if(rectx==60){
          jj+=1;
          if (jj==25){
            jj=0;
          }
        }
        if(rectx==290){
          kk+=1;
          if (kk==25){
            kk=0;
          }
        }
        if(rectx==520){
          ll+=1;
          if (ll==25){
            ll=0;
          }
        } 
      }  
    }
    if (code == KeyEvent.VK_ESCAPE)   
    {  
      if (gameselect==3 && popop==0){
        popop=1;       
        if (cc==0){
        clip0.stop();   
        }
        if (cc==1){
          clip1.stop();
        }
        if (cc==2){ 
          clip2.stop();
        }
        if (cc==3){
          clip3.stop();
        }
        if (cc==4){      
          clip4.stop();
        }
      }
      else if (gameselect==3 && popop==1){
        popop=0;
        if (cc==0){
          clip0.start();
        }
        if (cc==1){
          clip1.start();
        }
        if (cc==2){
          clip2.start();
        }
        if (cc==3){
          clip3.start();
        }
        if (cc==4){
          clip4.start();
        }
      }
    }
  }
/////////////////////////////////////////////////////////  
  
///////////////////////DEF COMMANDS///////////////////////
  public void showhit(int show){
    if (show==1){
     showred=1;
     redcounter=0;
    }
    if (show==2){
     showgreen=1;
     greencounter=0;
    }
    if (show==3){
     showorange=1;
     orangecounter=0;
    }
    if (show==4){
     showblue=1;
     bluecounter=0;
    }
  }
  public void stopstop(){
    clip0.stop();
    clip1.stop();
    clip2.stop();
    clip3.stop();
    clip4.stop();
    soundclip0.stop();
    soundclip1.stop();
    soundclip2.stop();
    soundclip3.stop();;
    soundclip0.setMicrosecondPosition(0);
    soundclip1.setMicrosecondPosition(0);
    soundclip2.setMicrosecondPosition(0);    
    soundclip3.setMicrosecondPosition(0); 
    clip0.setMicrosecondPosition(0);
    clip1.setMicrosecondPosition(0);
    clip2.setMicrosecondPosition(0);    
    clip3.setMicrosecondPosition(0);    
    clip4.setMicrosecondPosition(0);   
  }
  public void opensongs(){
    try
    {    
      AudioInputStream stream1,stream2,stream3,stream4,stream5,stream6,stream7,stream8,stream9,stream10;
      AudioFormat format;
      DataLine.Info info1,info2,info3,info4,info5,info6,info7,info8,info9,info10;
      
      stream1 = AudioSystem.getAudioInputStream(trackchose.wavfiles[0]);
      format = stream1.getFormat();
      info1 = new DataLine.Info(Clip.class, format);
      clip0 = (Clip) AudioSystem.getLine(info1);
      clip0.open(stream1);
      clip0.start();
      clip0.stop();
      
      stream2 = AudioSystem.getAudioInputStream(trackchose.wavfiles[1]);
      info2 = new DataLine.Info(Clip.class, format);
      clip1 = (Clip) AudioSystem.getLine(info2);
      clip1.open(stream2);
      clip1.start();
      clip1.stop();
      
      stream3 = AudioSystem.getAudioInputStream(trackchose.wavfiles[2]);
      info3 = new DataLine.Info(Clip.class, format);
      clip2 = (Clip) AudioSystem.getLine(info3);
      clip2.open(stream3);
      clip2.start();
      clip2.stop();
      
      stream4 = AudioSystem.getAudioInputStream(trackchose.wavfiles[3]);
      format = stream4.getFormat();
      info4 = new DataLine.Info(Clip.class, format);
      clip3 = (Clip) AudioSystem.getLine(info4);
      clip3.open(stream4);
      clip3.start();
      clip3.stop();
      
      stream5 = AudioSystem.getAudioInputStream(trackchose.wavfiles[4]);   
      info5 = new DataLine.Info(Clip.class, format);
      clip4 = (Clip) AudioSystem.getLine(info5);
      clip4.open(stream5);
      clip4.start();
      clip4.stop();
      
      stream7 = AudioSystem.getAudioInputStream(new File (soundeffects[0]));
      info7 = new DataLine.Info(Clip.class, format);
      soundclip0 = (Clip) AudioSystem.getLine(info7);
      soundclip0.open(stream7);
      
      stream8 = AudioSystem.getAudioInputStream(new File (soundeffects[1]));
      info8 = new DataLine.Info(Clip.class, format);
      soundclip1 = (Clip) AudioSystem.getLine(info8);
      soundclip1.open(stream8);
      
      stream9 = AudioSystem.getAudioInputStream(new File (soundeffects[2]));
      info9 = new DataLine.Info(Clip.class, format);
      soundclip2 = (Clip) AudioSystem.getLine(info9);
      soundclip2.open(stream9);
      
      stream10 = AudioSystem.getAudioInputStream(new File (soundeffects[3]));
      info10 = new DataLine.Info(Clip.class, format);
      soundclip3 = (Clip) AudioSystem.getLine(info10);
      soundclip3.open(stream10);          
    }
    catch (Exception exc)
    {
      exc.printStackTrace(System.out);
    } 
  }  
  public void checkend(){
    int counter=0;
    for (int i=0; i < trackk; i++) { 
      if (note[i].alive==false){
        counter+=1;
      }
      if (counter==trackk){    
        bigcounter+=1;      
      }
      if (bigcounter==trackchose.bigcounter[cc]){
        stopstop();
        if (point.ypos<620){
          playsoundeffect(soundeffects[2]);
        }
        gameselect=4;
        change();
        pointy=555;
        point.ypos=620;  
      }     
    }    
  }
  public void checkmove(){
    int x=37;
    int y=0;
    int alive=0;
    int prevnote=0;
    int prevx=0;
    if(trackchose.trackval[cc]==0){
      for (int i=0; i < trackk; i++) { 
        if(notes1[i]==0){
          alive=0;
        }
        if (notes1[i]>0){
          alive=1;
        }
        if (notes1[i]<prevnote){
          x+=112;
          y-=100;
        }
        if (notes1[i]==prevnote){
          x=prevx;
          y-=100;
        }
        if (notes1[i]>prevnote ){
          x-=112;
          y-=100;
        }
        if (x>373){
          x=373;
          
        }
        if (x<37){
          x=37;
        }
        note[i]= new Notes();
        note[i].setpostion(x,y,alive);
        prevnote=notes1[i];
        prevx=x;
        start=1;      
      }
    }   
    if(trackchose.trackval[cc]==1){
      for (int i=0; i < trackk; i++) { 
        if(notes1[i]==0){
          alive=0;
        }
        if (notes2[i]>0){
          alive=1;
        }
        if (notes2[i]<prevnote){
          x+=112;
          y-=100;
        }
        if (notes2[i]==prevnote){
          x=prevx;
          y-=100;
        }
        if (notes2[i]>prevnote ){
          x-=112;
          y-=100;
        }
        if (x>373){
          x=373;  
        }
        if (x<37){
          x=37;
        }    
        note[i]= new Notes();
        note[i].setpostion(x,y,alive);
        prevnote=notes2[i];
        prevx=x;  
        start=1;
      }
    }    
    if(trackchose.trackval[cc]==2){
      for (int i=0; i < trackk; i++) { 
        if(notes3[i]==0){
          alive=0;
        }
        if (notes3[i]>0){
          alive=1;
        }
        if (notes3[i]<prevnote){
          x+=112;
          y-=100;
        }
        if (notes3[i]==prevnote){
          x=prevx;
          y-=100;
        }
        if (notes3[i]>prevnote ){
          x-=112;
          y-=100;
        }
        if (x>373){
          x=373;        
        }
        if (x<37){
          x=37;
        }
        note[i]= new Notes();
        note[i].setpostion(x,y,alive);
        prevnote=notes3[i];
        prevx=x;       
        start=1;       
      }
    } 
    play(); 
  } 
  public void checkout(){
    for(int z=0;z<trackk;z++){ 
      if (note[z].ypos>800){
        note[z].alive=false;  
        combo=0;
        point.ypos+=5;
      }
    }
  }
  public void checkhits(){
    int rholder=0,gholder=0,bholder=0,oholder=0;
    for(int z=0;z<trackk;z++){ 
      if (lightred==1){      
        if (red.getBounds().intersects(note[z].getBounds())){
          note[z].alive=false;
          combo+=1;
          score+=(10*combo);
          rholder=1;
          showhit(1);
          point.ypos-=5;
        }
        else if(z==trackk-1 && rholder==0){
          combo=0;
          point.ypos+=5;
        }
        
      }
      if (lightgreen==1){      
        if (green.getBounds().intersects(note[z].getBounds())){
          note[z].alive=false;
          combo+=1;
          score+=(10*combo);      
          gholder=1;
          point.ypos-=5;
          showhit(2);
        }
        else if(z==trackk-1 && gholder==0){
          combo=0;
          point.ypos+=5;
        }
      }
      if (lightblue==1){      
        if (blue.getBounds().intersects(note[z].getBounds())){
          note[z].alive=false;
          combo+=1;
          score+=(10*combo);      
          bholder=1;
          point.ypos-=5;
          showhit(3);
        }
        else if(z==trackk-1 && bholder==0){
          combo=0;
          point.ypos+=5;
        }
      }
      if (lightorange==1){      
        if (orange.getBounds().intersects(note[z].getBounds())){
          note[z].alive=false;
          combo+=1;
          score+=(10*combo);      
          oholder=1;
          point.ypos-=5;
          showhit(4);
        }       
        else if(z==trackk-1 && oholder==0){
          combo=0;
          point.ypos+=5;
        }
      }   
    }   
  }
/////////////////////////////////////////////////////////////////
  
/////////////////////////MIDI FILIE LOADER///////////////////////
  public void MakeSong(){
    try{
      File myMidiFile = new File(trackchose.midinames[cc]);
      Sequence sequence = MidiSystem.getSequence(myMidiFile);
      int trackNumber = 0;
      for (Track track :  sequence.getTracks()) {
        trackNumber++;
        for (int i=0; i < track.size(); i++) { 
          MidiEvent event = track.get(i);
          MidiMessage message = event.getMessage();
          if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            if (sm.getCommand() == NOTE_ON) {
              int key = sm.getData1();
              int octave = (key / 12)-1;
              int note = key % 12;
              int noteName = NOTE_NAMES[note];
              int velocity = sm.getData2();          
              if(trackNumber==1){
                notes1[i]=(octave*100)+note;
                speed1[i]=velocity;
              }         
              if(trackNumber==2){
                notes2[i]=(octave*100)+note;
                speed2[i]=velocity;
              }        
              if(trackNumber==3){
                notes3[i]=(octave*100)+note;
                speed3[i]=velocity;
              }
            }     
            else if (sm.getCommand() == NOTE_OFF) {
              int key = sm.getData1();
              int octave = (key / 12)-1;
              int note = key % 12;
              int noteName = NOTE_NAMES[note];
              int velocity = sm.getData2();   
              if(trackNumber==1){
                notes1[i]=(octave*100)+note;
                speed1[i]=velocity;
              }     
              if(trackNumber==2){
                notes2[i]=(octave*100)+note;
                speed2[i]=velocity;
              }
              if(trackNumber==3){
                notes3[i]=(octave*100)+note;
                speed3[i]=velocity;
              }
            }    
          } 
        } 
        trackk=trackchose.trackkval[cc];    
      }  
    } catch(Exception e){
    }
    checkmove();
  }
////////////////////////////////////////////////////////////////////////  
  
  
/////////////////////// PLAYING SOUNDS//////////////////////////////////
  public  void play()
  {
    try
    {
      if (cc==0){
        clip0.start();
      }
      if (cc==1){
        clip1.start();
      }
      if (cc==2){
        clip2.start();
      }
      if (cc==3){
        clip3.start();
      }
      if (cc==4){
        clip4.start();
      }
    }
    catch (Exception exc)
    {
      exc.printStackTrace(System.out);
    }
  }
  public  void playtitle()
  {  
    int a=(int)(Math.random() * (4 - 0) + 0);
    if(a==4){
      clip4.setMicrosecondPosition(0);  
      clip4.start();        
    }    
    if(a==3){
      clip3.start();      
    }
    if(a==2){
      clip2.start();     
    }
    if(a==1){
      clip1.start();   
    }
    if(a==0){
      clip0.start();  
    }
  }
  public  void playsoundeffect(String a)
  {
    try
    {
      if (a.equals("select.wav")){
        soundclip0.start();
      }
      else if (a.equals("click.wav")){
        soundclip1.start();
      }
      else if (a.equals("yourock.wav")){
        soundclip2.start();
      }
      else if (a.equals("score.wav")){
        soundclip3.start();
      }     
    }
    catch (Exception exc)
    {
      exc.printStackTrace(System.out);
    }
  }
  ///////////////////////////////////////////////////////////////////////////
  
  
  /////////////////////////DATAFILES FOR HIGHSCORES//////////////////////////
  public void openfiles(){
    try
    {
      in=new BufferedReader(new FileReader(f1));
    }
    catch (FileNotFoundException e)
    {
    }
    try
    {
      out=new PrintWriter(new BufferedWriter(new FileWriter(f2, false)),true); 
    }
    catch (IOException e)
    { 
    } 
  }
  public void closefiles(){
    try
    {
      out.close();
      in.close();
    }
    catch (IOException e)
    {
    }
    f1.delete();
    f2.renameTo(f1);
  }
  public void load(){
    openfiles();
    int count=0;
    do
    {
      try{
        line=in.readLine();  
      }
      catch (IOException e)
      { 
      }
      if (line!=null){
        out.println(line);  
        if (count==cc){
          fields=line.split("/");
          hscore1=fields[0];
          hscore2=fields[2];
          hscore3=fields[4];
          user1=fields[1];
          user2=fields[3];
          user3=fields[5];      
        }
        count++;
      }
    }while (line!=null);
    closefiles();
  }
 
  public void change(){
    openfiles();
    int count=0;
    String  placeholder="";
    String placeholder2="";
    String  placeholder3="";
    String placeholder4="";
    do
    {
      try{
        line=in.readLine(); 
      }
      catch (IOException e)
      {  
      }
      if (line!=null){
        if (count==cc){
          fields=line.split("/");
          int a1 = Integer.parseInt(fields[0]);
          int a2 = Integer.parseInt(fields[2]);
          int a3 = Integer.parseInt(fields[4]);
          if(score>a1){
            placeholder=fields[0];
            placeholder2=fields[1];
            placeholder3=fields[2];
            placeholder4=fields[3];
            fields[0]=""+score;
            fields[1]=""+user;
            fields[2]=placeholder;
            fields[3]=placeholder2;
            fields[4]=placeholder3;
            fields[5]=placeholder4;
            highscore=1; 
            playsoundeffect(soundeffects[3]);
          }
          else if(score>a2){
            placeholder=fields[2];
            placeholder2=fields[3];
            fields[2]=""+score;
            fields[3]=""+user;
            fields[4]=placeholder;
            fields[5]=placeholder2;
          }
          else if(score>a3){
            fields[4]=""+score;
            fields[5]=""+user;
          }  
          line=fields[0]+"/"+fields[1]+"/"+fields[2]+"/"+fields[3]+"/"+fields[4]+"/"+fields[5];
        }
        out.println(line); 
        count++;
      }
    }while (line!=null);
    closefiles();
  }
  ////////////////////////////////////////////////////////////
}



