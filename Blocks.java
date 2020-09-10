///FOR GUITAR HERO
import java.awt.Rectangle;
public class Blocks{
  int xpos=0,ypos=0,size=50; 
  public Rectangle getBounds(){
    return new Rectangle(xpos,ypos,size,size); 
  }
  public void setpos(int x,int y){
  xpos=x;
  ypos=y;
  }
}