package Game1;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.*;  

public class Play extends BasicGameState{
    
    Image worldMap;
    Random rand;
    Timer timer;
    
    public String mouse,bossPosition,alert;
    
    Animation boss,moveUp,moveDown,moveLeft,moveRight;
    Animation standDown,standUp,standLeft,standRight;
    Animation lastOne,event,showNo,showGet,showGetLast;
    Animation fishingUp,fishingDown,fishingLeft,fishingRight;
    Animation handsUp;
    int[] duration = {200};
    private int fishingTime;
    private int countEventTime;
    private SpriteSheet moveDownSheet,moveUpSheet,moveLeftSheet,moveRightSheet;
    private float bossPositionX = 0;
    private float bossPositionY = 0;
    private float shiftX = bossPositionX + 375;
    private float shiftY = bossPositionY + 246;
    
    boolean upKeyDownF = false;
    boolean downKeyDownF = false;
    boolean leftKeyDownF = false;
    boolean rightKeyDownF = false;
    boolean escape = false;
    boolean startFishing = false;
    boolean endFishing = false;
    boolean eventShow = false;
    
    public Play(int state){
        
    }

    public void init(GameContainer gameCon, StateBasedGame staBasG) throws SlickException {
        Timer timer = new Timer();
        
        
        worldMap = new Image("res/bigWorldMap001.png");
        
        Image[] sUp = {new Image("res/upStand50x107.png")};
        Image[] sDown = {new Image("res/downStand50x107.png")};
        Image[] sLeft = {new Image("res/leftStand50x107.png")};
        Image[] sRight = {new Image("res/rightStand50x107.png")};
        Image[] fUp = {new Image("res/upFishing/upFishing50x107.png")};
        Image[] fDown = {new Image("res/downFishing/downFishing50x133.png")};
        Image[] fLeft = {new Image("res/leftFishing/leftFishing(05)134x107.png")};
        Image[] fRight = {new Image("res/rightFishing/rightFishing134x107.png")};
        Image[] fHandsUp = {new Image("res/handsUp50x107.png")};
        Image[] eEmpty = {new Image("res/event/event-empty.png")};
        
        Image[] eGetLast = {new Image("res/Get/20001.png")};
        Image[] eGet = {new Image("res/Get/20015.png"),new Image("res/Get/20014.png"),
                new Image("res/Get/20013.png"),new Image("res/Get/20012.png"),
                new Image("res/Get/20011.png"),new Image("res/Get/20010.png"),
                new Image("res/Get/20009.png"),new Image("res/Get/20008.png"),
                new Image("res/Get/20007.png"),new Image("res/Get/20006.png"),
                new Image("res/Get/20005.png"),new Image("res/Get/20004.png"),
                new Image("res/Get/20003.png"),new Image("res/Get/20002.png"),
                new Image("res/Get/20001.png")};
        
        moveUpSheet = new SpriteSheet("res/upMove200x107.png",50,107);
        moveDownSheet = new SpriteSheet("res/downMove200x107.png",50,107);
        moveLeftSheet = new SpriteSheet("res/leftMove200x107.png",50,107);
        moveRightSheet = new SpriteSheet("res/rightMove200x107.png",50,107);
        
        moveUp = new Animation(moveUpSheet,200);
        moveDown = new Animation(moveDownSheet,200);
        moveLeft = new Animation(moveLeftSheet,200);
        moveRight = new Animation(moveRightSheet,200);
        
        standUp = new Animation(sUp,100,false);
        standDown = new Animation(sDown,100,false);
        standLeft = new Animation(sLeft,100,false);
        standRight = new Animation(sRight,100,false);
        
        fishingUp = new Animation(fUp,200);
        fishingDown = new Animation(fDown,200);
        fishingLeft = new Animation(fLeft,200);
        fishingRight = new Animation(fRight,200);
        handsUp = new Animation(fHandsUp,1000);
        
        showNo = new Animation(eEmpty,500);
        showGet = new Animation(eGet,50,true);
        showGetLast = new Animation(eGetLast,100,false);
        
        event = new Animation(eEmpty,500,false);
        
        boss = new Animation(sDown,100,false);
        lastOne = new Animation(sDown,100,false);
        
    }

    public void render(GameContainer gameCon, StateBasedGame staBasG, Graphics g) throws SlickException {
        worldMap.draw(bossPositionX,bossPositionY);
        g.drawString(mouse,50,50); //print the words
        g.drawString(bossPosition,50,100);
        g.drawString(alert,50,150);
        boss.draw(shiftX, shiftY);
        event.draw(0,0);

        
        
        
        //g.drawImage(exImage, exImageX, exImageY); //set the image location (image name, X pos , Y pos)
    }

    public void update(GameContainer gameCon, StateBasedGame staBasG, int delta) throws SlickException {
        
        Input input = gameCon.getInput(); //set the input
        Random rand = new Random();
        
        int mouseNowX = Mouse.getX();
        int mouseNowY = Mouse.getY();
        mouse = "Mouse Position x: " +mouseNowX+ " y: " +mouseNowY; //print the location of the mouse
        bossPosition = "Boss Position x: " +bossPositionX+ " y: " +bossPositionY;
        alert = "counting: "+fishingTime;
        
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            escape = true;
            if(escape == true){
                 
            }
        }
        
        if(input.isKeyDown(Input.KEY_UP)){
            startFishing = false;
            fishingTime = rand.nextInt(50)+100;
            checkKeyDownF();
            boss = moveUp;
            lastOne = standUp;
            bossPositionY += 0.3f * delta;
        }
        
        if(input.isKeyDown(Input.KEY_DOWN)){
            startFishing = false;
            fishingTime = rand.nextInt(50)+100;
            checkKeyDownF();
            boss = moveDown;
            lastOne = standDown;
            bossPositionY -= 0.3f * delta;
            if(bossPositionY<-5202){
                bossPositionY += 0.3f * delta;
            }
        }

        
        if(input.isKeyDown(Input.KEY_LEFT)){
            startFishing = false;
            fishingTime = rand.nextInt(50)+100;
            checkKeyDownF();
            boss = moveLeft;
            lastOne = standLeft;
            bossPositionX += 0.3f * delta;
        }

        
        if(input.isKeyDown(Input.KEY_RIGHT)){
            startFishing = false;
            fishingTime = rand.nextInt(50)+100;
            checkKeyDownF();
            boss = moveRight;
            lastOne = standRight;
            bossPositionX -= 0.3f * delta;
        }
        
        if(lastOne == standUp && input.isKeyDown(Input.KEY_F)){
            upKeyDownF = true;
            startFishing = true;
            boss = fishingUp;
            lastOne = fishingUp;
        }
        
        if(lastOne == standDown && input.isKeyDown(Input.KEY_F)){
            startFishing = true;
            downKeyDownF = true;
            boss = fishingDown;
            lastOne = fishingDown;
        }
        
        if(lastOne == standLeft && input.isKeyDown(Input.KEY_F)){
            startFishing = true;
            leftKeyDownF = true;
            if(leftKeyDownF){
                shiftX = shiftX-84;
            }
            boss = fishingLeft;
            lastOne = fishingLeft;
        }
        
        if(lastOne == standRight && input.isKeyDown(Input.KEY_F)){
            startFishing = true;
            rightKeyDownF = true;
            boss = fishingRight;
            lastOne = fishingRight;
        }
        
        if(!(input.isKeyDown(Input.KEY_UP)) && !(input.isKeyDown(Input.KEY_DOWN)) &&
                !(input.isKeyDown(Input.KEY_LEFT)) && !(input.isKeyDown(Input.KEY_RIGHT))){
            boss = lastOne;
        }
        
        if(startFishing == true){
            
            if(fishingTime <= 200){
                fishingTime += 1;
            }
            if(fishingTime > 200){
                fishingTime = 0;
                startFishing = false;
                if(leftKeyDownF){
                    shiftX = shiftX+84;
                    leftKeyDownF = false;
                }
                lastOne = handsUp;
                event = showGet;
                eventShow = true;
            }
            
        }
        
        if(eventShow){
            if(countEventTime<=20){
                countEventTime += 1;
            }
            else {
                event = showNo;
                countEventTime = 0;
                eventShow = false;
            }
        }
    }
    


    public void checkKeyDownF(){
        if(leftKeyDownF){
            shiftX = shiftX+84;
            leftKeyDownF = false;
        }
    }
    
    public void getFish(){
        alert = "get!!!!!!!!!!!!!";
    }

    public int getID() {
        return 1;
    }
    
}