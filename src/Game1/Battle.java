package Game1;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Battle extends BasicGameState{
	private float bossPositionX;
    private float bossPositionY;
	private Input input;
	private int fightCount = 0;
	Image[] fightImg;
	Image worldMap;

	public Battle(int state) {
		
	}

	public void init(GameContainer gameCon, StateBasedGame staBasG)
			throws SlickException {
		
		worldMap = new Image("res/map/TaiwanWorld.png");
		
		Image[] fightGUI = {
			new Image("res/fight/fight.png"),
			new Image("res/fight/fight01.png"),
			new Image("res/fight/fight02.png"),
			new Image("res/fight/fight03.png"),
			new Image("res/fight/fight04.png"),
			
		};
		fightImg = fightGUI;
	}

	public void render(GameContainer gameCon, StateBasedGame staBasG, Graphics g)
			throws SlickException {
		worldMap.draw(bossPositionX,bossPositionY);
        fightImg[fightCount].draw(0,0);
		
	}

	public void update(GameContainer gameCon, StateBasedGame staBasG, int delta)
			throws SlickException {
        changePoint();
	}
	
	public void changePoint(){
		if(input.isKeyDown(Input.KEY_Z)){
        	fightCount = 1;
        	
            if(input.isKeyDown(Input.KEY_UP) && fightCount == 2){
                fightCount = 1;
            }
            
            if(input.isKeyDown(Input.KEY_UP) && fightCount == 4){
                fightCount = 3;
            }
        
            if(input.isKeyDown(Input.KEY_DOWN) && fightCount == 1){
                fightCount = 2;
            }
            
            if(input.isKeyDown(Input.KEY_DOWN) && fightCount == 3){
                fightCount = 4;
            }
                    
            if(input.isKeyDown(Input.KEY_LEFT) && fightCount == 3){
                fightCount = 1;
            }
            
            if(input.isKeyDown(Input.KEY_LEFT) && fightCount == 4){
                fightCount = 2;
            }

            if(input.isKeyDown(Input.KEY_RIGHT) && fightCount == 1){
                fightCount = 3;
            }
            
            if(input.isKeyDown(Input.KEY_RIGHT) && fightCount == 2){
                fightCount = 4;
            }
		}
		
	}

	public int getID() {
		return 2;
	}

}
