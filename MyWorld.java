import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    
    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;

    public MyWorld() {
        // Initialize the world with a specific width and height
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 

        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 20, 250);
        
        Point pointA = new Point(50, 100);
        Point pointB = new Point(550, 100);
        CoWorker coworker = new CoWorker(pointA, pointB);
        addObject(coworker, pointA.x, pointA.y); 
        
        layout();
        
        placeCollectibles(); // Call method to place collectibles

    }

    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
    
    private void layout() {
        // Create office-like maze walls (desks, partitions, etc.)
        
        // Top horizontal wall
        for (int i = 0; i < 25; i++) {
            addObject(new wall(), 10 + i * WALL_WIDTH, 10);
        }
        
        // 2nd top horizontal wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 100 + i * WALL_WIDTH, 80);
        }
        
        // 3rd top horizontal wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 100 + i * WALL_WIDTH, 270);
        }
        
        // 4th horizontal wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 280 + i * WALL_WIDTH, 150);
        }

        // 1st left vertical wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 10, 30 + i * WALL_HEIGHT);
        }
        
        // 2nd left vertical wall
        for (int i = 0; i < 3; i++) {
            addObject(new wall(), 10, 160 + i * WALL_HEIGHT);
        }
        
        // 3rd vertical wall
        for (int i = 0; i < 5; i++) {
            addObject(new wall(), 100, 160 + i * WALL_HEIGHT);
        }
        
        // 4th vertical wall
        for (int i = 0; i < 4; i++) {
            addObject(new wall(), 300, 170 + i * WALL_HEIGHT);
        }
        
        // 5th vertical wall
        for (int i = 0; i < 2; i++) {
            addObject(new wall(), 80, 340 + i * WALL_HEIGHT);
        }
        
        // 6th left vertical wall
        for (int i = 0; i < 5; i++) {
            addObject(new wall(), 10, 290 + i * WALL_HEIGHT);
        }
        
        // Right vertical wall
        for (int i = 0; i < 15; i++) {
            addObject(new wall(), 590, 30 + i * WALL_HEIGHT);
        }

        // Bottom horizontal wall
        for (int i = 0; i < 22; i++) {
            addObject(new wall(), 10 + i * WALL_WIDTH, 390);
        }
        
        
        //small desk areas for office space
        addObject(new wall(), 178, 100);
        addObject(new wall(), 178, 126);
        addObject(new wall(), 178, 152);
        addObject(new wall(), 207, 100);
        addObject(new wall(), 207, 126);
        addObject(new wall(), 207, 152);
        addObject(new deskchair(), 178, 178);
        addObject(new deskchair(), 207, 178);
        
        //small desk areas for office space
        addObject(new wall(), 178, 300);
        addObject(new wall(), 178, 326);
        addObject(new wall(), 207, 300);
        addObject(new wall(), 207, 326);
        addObject(new wall(), 40, 160);
        addObject(new wall(), 70, 160);
        
        
        //small desk areas for office space
        addObject(new wall(), 428, 100);
        addObject(new wall(), 428, 126);
        addObject(new wall(), 428, 152);
        addObject(new wall(), 457, 100);
        addObject(new wall(), 457, 126);
        addObject(new wall(), 457, 152);
        addObject(new wall(), 428, 178);
        addObject(new wall(), 457, 178);
        addObject(new wall(), 560, 170);
        addObject(new wall(), 530, 170);
        addObject(new deskchair(), 530, 200);
        addObject(new deskchair(), 560, 200);
        
        //small desk areas for office space
        addObject(new wall(), 500, 330);
        addObject(new wall(), 470, 330);
        addObject(new wall(), 440, 330);
        addObject(new wall(), 410, 330);
        addObject(new wall(), 380, 330);
        addObject(new wall(), 350, 330);
        addObject(new wall(), 320, 330);
        addObject(new wall(), 290, 330);
        
    }
     //Collectible
    private void placeCollectibles() {
        addObject(new CoffeeBoost(), 250, 150);
        addObject(new CoffeeBoost(), 450, 300);
        addObject(new CoffeeBoost(), 50, 350);
        addObject(new CoffeeBoost(), 50, 200);
        addObject(new CoffeeBoost(), 560, 40);
        
        }
        
        public void act() {
        // Get the worker object in world1
        TiredOfficeWorker worker = (TiredOfficeWorker) getObjects(TiredOfficeWorker.class).get(0);
        EnergyBar bar = new EnergyBar(worker, 100, 10); // width & height of the bar
        addObject(bar, getWidth() - 60, getHeight() - 20); // bottom right
        
        
    }
}

    



