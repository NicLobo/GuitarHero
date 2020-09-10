///FOR GUITAR HERO
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import java.io.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import java.util.*;
public class TrackList{
  String [] midinames={"JCANIME.mid","teenspirit.mid","bib.mid","woman.mid","fireandflames.mid"};
  String [] wavnames={"jcanime.wav","teenspirit.wav","bib.wav","woman.wav","fireandflames.wav"};
  int [] trackkval={250,600,600,600,1000};
  int [] trackval={2,1,2,0,1}; 
  int [] bigcounter={400,1000,1000,1000,1000};
  File [] wavfiles;
  File [] midifiles;
  BufferedImage [] imagename;
  Image [] image = {Toolkit.getDefaultToolkit().getImage("cabotanimeopen2.gif"),Toolkit.getDefaultToolkit().getImage("breezeblocks.gif"),Toolkit.getDefaultToolkit().getImage("everydaybro.gif"),Toolkit.getDefaultToolkit().getImage("woman.gif"), Toolkit.getDefaultToolkit().getImage("dragonforce.gif")};
  public void createimage(){  
    imagename= new BufferedImage[5];
    try { imagename[0]=ImageIO.read(new File("cabototitle.png"));
      imagename[1]=ImageIO.read(new File("spirittitle.png"));
      imagename[2]=ImageIO.read(new File("blacktitle.png"));
      imagename[3]=ImageIO.read(new File("woman.png"));
      imagename[4]=ImageIO.read(new File("fireandflamestitle.png"));
    }catch(IOException e){
      System.out.println("no work");}
  }
  public void createfile(){
    wavfiles= new File[5];
    midifiles= new File[5];
     for (int i=0; i <5; i++) { 
    wavfiles[i]= new File(wavnames[i]);
    midifiles[i]= new File(midinames[i]);
    }
  }
}