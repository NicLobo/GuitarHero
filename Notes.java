///FOR GUITAR HERO
import java.awt.Rectangle;
public class Notes{
  int xpos,ypos,size=50,speed=1,value,speedbooster=0;
  boolean alive=true;
  public Rectangle getBounds(){
    return new Rectangle(xpos,ypos,size,size); 
  }
  public void updatepostion(){
    if (alive==true){
    ypos+=speed+speedbooster;
    }
    if (alive==false){
     ypos=-2000;
    }
  }   
  public void setpostion(int x, int y,int check){
    if (check==0){
    alive=false;
    }
    xpos=x;
    ypos=y;
  } 
 }
  
  
  