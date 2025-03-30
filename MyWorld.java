import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld class for the office simulation game. a description of class MyWorld here.
 * 
 * @Alex and Olivia 
 * @version 1.2
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld class for the office simulation game.
 * 
 * @author Alex and Olivia
 * @version 1.2
 */
public class MyWorld extends World {
    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;
    
    private ChatHandler chatHandler;
    private EnergyBar energyBar;
    private score scoreDisplay;
    private GreenfootImage messageImage;
    
    public MyWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        initializeGameObjects();
        layoutWalls();
        placeCollectibles();
        
        messageImage = new GreenfootImage(getWidth() - 220, getHeight());
    }
    
    private void initializeGameObjects() {
        scoreDisplay = new score();
        addObject(scoreDisplay, 80, 30);
        
        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 20, 250);
        
        addCoWorkers();
        
        Boss boss = new Boss(worker);
        addObject(boss, 500, 300);
        
        chatHandler = new ChatHandler();
        
        energyBar = new EnergyBar(worker, 100, 10);
        addObject(energyBar, getWidth() - 60, getHeight() - 20);
    }
    
    private void addCoWorkers() {
        Point[] points = {
            new Point(25, 110), new Point(125, 110),
            new Point(350, 210), new Point(500, 210),
            new Point(240, 40), new Point(240, 150)
        };
        
        for (int i = 0; i < points.length; i += 2) {
            CoWorker coworker = new CoWorker(points[i], points[i + 1]);
            addObject(coworker, points[i].x, points[i].y);
        }
    }
    
    private void layoutWalls() {
        // Walls with gaps for movement
        createHorizontalWall(10, 10, 25, new int[]{7, 15, 20, 22});
        createHorizontalWall(100, 80, 15, new int[]{5, 10, 12, 13});
        createHorizontalWall(100, 270, 15, new int[]{3, 8, 11, 14});
        createVerticalWall(10, 30, 3);
        createVerticalWall(10, 160, 3);
        createVerticalWall(100, 160, 5, new int[]{2, 4});
        createVerticalWall(300, 170, 2);
        createVerticalWall(80, 340, 2);
        createVerticalWall(10, 290, 5, new int[]{3, 4});
        createVerticalWall(590, 30, 15, new int[]{7, 12});
        createHorizontalWall(10, 390, 22, new int[]{5, 15, 18, 20});
        
        // Office desk areas
        int[][] deskWalls = {
            {178, 100}, {178, 126}, {178, 152}, {207, 100}, {207, 126}, {207, 152},
            {428, 100}, {428, 126}, {428, 152}, {457, 100}, {457, 126}, {457, 152},
            {178, 300}, {178, 326}, {207, 300}, {207, 326}, {40, 160}, {70, 160},
            {560, 170}, {530, 170}, {500, 330}, {470, 330}, {440, 330},
            {410, 330}, {380, 330}, {350, 330}, {320, 330}, {290, 330}
        };
        
        for (int[] position : deskWalls) {
            addObject(new wall(), position[0], position[1]);
        }
    }
    
    private void createHorizontalWall(int startX, int startY, int count, int... gaps) {
        for (int i = 0; i < count; i++) {
            if (!contains(gaps, i)) {
                addObject(new wall(), startX + i * WALL_WIDTH, startY);
            }
        }
    }
    
    private void createVerticalWall(int startX, int startY, int count, int... gaps) {
        for (int i = 0; i < count; i++) {
            if (!contains(gaps, i)) {
                addObject(new wall(), startX, startY + i * WALL_HEIGHT);
            }
        }
    }
    
    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
    
    private void placeCollectibles() {
        int[][] collectiblePositions = {
            {250, 150}, {450, 300}, {50, 350}, {50, 200}, {560, 40},
            {320, 120}, {150, 250}, {500, 100}, {350, 300}, {100, 50}
        };
        
        for (int[] position : collectiblePositions) {
            addObject(new CoffeeBoost(), position[0], position[1]);
        }
    }
    
    private void showIntroMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(20, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.drawString("Drink coffee to survive the workday avoiding extra work", 10, 25);
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
    }
    
    public void addPoints(int points) {
        scoreDisplay.addScore(points);
    }
    
    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
    
    private void checkScore() {
        if (scoreDisplay.getScore() >= 100) {
            showSurvivalMessage();
        }
    }
    
    private void showSurvivalMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(0, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.drawString("You survived the work day!", 10, 25);
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
    }
    
    public ChatHandler getChatHandler() {
        return chatHandler;
    }
    
    public void act() {
        chatHandler.update();
        showIntroMessage();
    }
}


    



