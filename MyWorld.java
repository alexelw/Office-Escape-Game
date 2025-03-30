import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld class for the office simulation game. a description of class MyWorld here.
 * 
 * @Alex and Olivia 
 * @version 1.2
 */
public class MyWorld extends World {
    
    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;
    
    private ChatHandler chatHandler;
    private EnergyBar bar;
    private score scoreDisplay;
    private boolean gameStarted = false;
    private GreenfootImage messageImage;
    
    public MyWorld() {
        // Initialize the world with a specific width and height
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 

        scoreDisplay = new score();
        addObject(scoreDisplay, 80, 30);  // Position the score at the top-left
        
        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 20, 250);
        
        Point pointA = new Point(25, 110);
        Point pointB = new Point(125, 110);
        CoWorker coworker = new CoWorker(pointA, pointB);
        addObject(coworker, pointA.x, pointA.y);
        
        Point pointA2 = new Point(350, 210);
        Point pointB2 = new Point(500, 210);
        CoWorker coworker2 = new CoWorker(pointA2, pointB2);
        addObject(coworker2, pointA2.x, pointA2.y);
        
        Point pointA3 = new Point(240, 40);
        Point pointB3 = new Point(240, 150);
        CoWorker coworker3 = new CoWorker(pointA3, pointB3);
        addObject(coworker3, pointA3.x, pointA3.y);
        
        Boss boss = new Boss(worker);
        addObject(boss, 500, 300);
        
        chatHandler = new ChatHandler();
        layout();
        
        placeCollectibles(); // Call method to place collectibles

        bar = new EnergyBar(worker, 100, 10);
        addObject(bar, getWidth() - 60, getHeight() - 20);
        
        messageImage = new GreenfootImage(getWidth() - 220, getHeight());
    }

    private void showIntroMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(20, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.drawString("Drink coffee to survive the workday avoiding extra work", 10, 25);  // Customize the position as needed
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
    }

    public void addPoints(int points) {
        scoreDisplay.addScore(points);
    }
    
    public void increaseScore(int points) {
        scoreDisplay.addScore(points);
    }
    
    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
    private void checkScore() {
        if (scoreDisplay.getScore() >= 100) {
            showSurvivalMessage();  // Show the message if score is 100 or more
        }
    }

    private void showSurvivalMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(0, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.drawString("You survived the work day!", 10, 25);  // Customize the position as needed
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
    }
    
    private void layout() {
    // Create office-like maze walls (desks, partitions, etc.)

    // Top horizontal wall with gaps
    for (int i = 0; i < 25; i++) {
        if (i != 7 && i != 15 && i != 20 && i != 22) { // Added one more gap
            addObject(new wall(), 10 + i * WALL_WIDTH, 10);
        }
    }

    // 2nd top horizontal wall with gaps
    for (int i = 0; i < 15; i++) {
        if (i != 5 && i != 10 && i != 13 && i != 12) { // Added another gap
            addObject(new wall(), 100 + i * WALL_WIDTH, 80);
        }
    }

    // 3rd top horizontal wall with gaps
    for (int i = 0; i < 15; i++) {
        if (i != 3 && i != 11 && i != 14 && i != 8) { // Added a gap here
            addObject(new wall(), 100 + i * WALL_WIDTH, 270);
        }
    }

    // 4th horizontal wall with gaps
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

    // 3rd vertical wall with a gap
    for (int i = 0; i < 5; i++) {
        if (i != 2 && i != 4) { // Create gaps here
            addObject(new wall(), 100, 160 + i * WALL_HEIGHT);
        }
    }

    // 4th vertical wall with a corner gap
    for (int i = 0; i < 2; i++) {
        if (i == 2) {
            addObject(new wall(), 300, 170 + i * WALL_HEIGHT); // Leave a gap at the corner
        } else {
            addObject(new wall(), 300, 170 + i * WALL_HEIGHT);
        }
    }

    // 5th vertical wall near the bottom left
    for (int i = 0; i < 2; i++) {
        addObject(new wall(), 80, 340 + i * WALL_HEIGHT);
    }

    // 6th left vertical wall near the bottom with additional gaps
    for (int i = 0; i < 5; i++) {
        if (i == 3 || i == 4) { // Create gaps here
            addObject(new wall(), 10, 290 + i * WALL_HEIGHT);
        } else {
            addObject(new wall(), 10, 290 + i * WALL_HEIGHT);
        }
    }

    // Create more gaps around the corner (bottom-left)
    // Adding gaps to the corner area to allow for better movement
    addObject(new wall(), 10, 350);   // Close to corner
    addObject(new wall(), 40, 350);   // Another corner gap
    addObject(new wall(), 10, 380);   // Corner area
    addObject(new wall(), 40, 380);   // Another corner gap

    // Right vertical wall with a gap
    for (int i = 0; i < 15; i++) {
        if (i != 7 && i != 12) { // Create gaps here
            addObject(new wall(), 590, 30 + i * WALL_HEIGHT);
        }
    }

    // Bottom horizontal wall with gaps
    for (int i = 0; i < 22; i++) {
        if (i != 5 && i != 15 && i != 18 && i != 20) { // Create gaps at these positions
            addObject(new wall(), 10 + i * WALL_WIDTH, 390);
        }
    }

    // Additional small desk areas for office space
    addObject(new wall(), 178, 100);
    addObject(new wall(), 178, 126);
    addObject(new wall(), 178, 152);
    addObject(new wall(), 207, 100);
    addObject(new wall(), 207, 126);
    addObject(new wall(), 207, 152);
    addObject(new deskchair(), 178, 178);
    addObject(new deskchair(), 207, 178);
    
    // Additional small desk areas for office space
    addObject(new wall(), 178, 300);
    addObject(new wall(), 178, 326);
    addObject(new wall(), 207, 300);
    addObject(new wall(), 207, 326);
    addObject(new wall(), 40, 160);
    addObject(new wall(), 70, 160);

    // Continue with additional small desk areas
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
    
    // Additional small desk areas
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
    // Existing coffee boosts
    addObject(new CoffeeBoost(), 250, 150);
    addObject(new CoffeeBoost(), 450, 300);
    addObject(new CoffeeBoost(), 50, 350);
    addObject(new CoffeeBoost(), 50, 200);
    addObject(new CoffeeBoost(), 560, 40);

    // Additional coffee boosts
    addObject(new CoffeeBoost(), 320, 120);
    addObject(new CoffeeBoost(), 150, 250);
    addObject(new CoffeeBoost(), 500, 100);
    addObject(new CoffeeBoost(), 350, 300);
    addObject(new CoffeeBoost(), 100, 50);
}
        
    public ChatHandler getChatHandler() {
        return chatHandler;
    }

    public void act() {
        chatHandler.update();
        showIntroMessage();
    }
    
}

    



