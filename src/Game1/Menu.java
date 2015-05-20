package Game1;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
    
    public String mouse = "no mouse";
    
    public Menu(int state){
        
    }

    public void init(GameContainer gameCon, StateBasedGame staBasG) throws SlickException {
    }

    public void render(GameContainer gameCon, StateBasedGame staBasG, Graphics g) throws SlickException {
        g.drawString(mouse,50,50); //print the words
        g.fillRect(300, 250, 200, 80);
        g.drawString("Play Now!",350,300);
    }

    public void update(GameContainer gameCon, StateBasedGame staBasG, int delta) throws SlickException {
        Input input = gameCon.getInput();
        int mouseNowX = Mouse.getX();
        int mouseNowY = Mouse.getY();
        mouse = "Mouse Position x: " +mouseNowX+ " y: " +mouseNowY; //print the location of the mouse
        
        if((mouseNowX>300 && mouseNowX<500) && (mouseNowY>250 && mouseNowY<330)){
            if(input.isMouseButtonDown(0)){
                staBasG.enterState(1);
            }
        }
    }

    public int getID() {
        return 0;
    }
    
}
