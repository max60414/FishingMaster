package Game1;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{
    public static final String gamename = "Fishing Boss";
    public static final int menu = 0;
    public static final int play = 1;
    
    
    public Main(String gamename) {
        super(gamename);
        this.addState(new Menu(menu)); //add new screen
        this.addState(new Play(play));
    }

    @Override
    public void initStatesList(GameContainer gameCon) throws SlickException {
        this.getState(menu).init(gameCon,this); //put the screen in the GameContainer object
        this.getState(play).init(gameCon,this);
        this.enterState(menu); //set the first screen as the "menu" screen;
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer appGameCon;
        try{
            appGameCon = new AppGameContainer(new Main(gamename));
            appGameCon.setVSync(true);  //Make FPS stable
            //appGameCon.setTargetFrameRate(60); //Set FPS
            appGameCon.setDisplayMode(800, 600, false);
            appGameCon.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}
