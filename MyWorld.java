import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld class for the office simulation game.
 * This class manages the game environment, including walls, collectibles, and worker interactions.
 * It also handles the display of score and chat interactions.
 * 
 * @Olivia-Melody Adenyin
 * @version 1.2
 */
public class MyWorld extends World {
    
    // Constants defining the world and wall sizes
    private static final int WORLD_WIDTH = 600;
    private static final int WORLD_HEIGHT = 400;
    private static final int WALL_WIDTH = 28;
    private static final int WALL_HEIGHT = 26;
    
    // Game object instances
    private boolean introShown = false;
    private int introTimer = 0;  // Timer to control the duration of the intro message
    
    private ChatHandler chatHandler;
    private EnergyBar energyBar;
    private Score scoreDisplay;
    private GreenfootImage messageImage;
    
    /**
     * Constructor for the MyWorld class.
     * Initializes the world, game objects, walls, and collectibles.
     */
    public MyWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        // Initialize game objects and layout
        layoutWalls();
        initializeGameObjects();
        placeCollectibles();
        
        // Initialize the message image for intro and game messages
        messageImage = new GreenfootImage(getWidth() - 220, getHeight());
    }
    
    /**
     * Initializes game objects like the worker, boss, co-workers, chat handler, and energy bar.
     */
    private void initializeGameObjects() {
        // Create and add score display
        scoreDisplay = new Score();
        addObject(scoreDisplay, 80, 30);
        
        // Create and add the main worker object
        TiredOfficeWorker worker = new TiredOfficeWorker();
        addObject(worker, 20, 250);
        
        // Add co-workers to the world
        addCoWorkers();
        
        // Create and add the boss
        Boss boss = new Boss(worker);
        addObject(boss, 500, 300);
        
        // Create and add chat handler
        chatHandler = new ChatHandler();
        
        // Create and add energy bar
        energyBar = new EnergyBar(worker, 100, 10);
        addObject(energyBar, getWidth() - 60, getHeight() - 20);
    }
    
    /**
     * Adds co-workers to the world at specified positions.
     */
    private void addCoWorkers() {
        // List of co-worker positions
        Point[] points = {
            new Point(25, 110), new Point(125, 110),
            new Point(350, 210), new Point(500, 210),
            new Point(240, 40), new Point(240, 150)
        };
        
        // Add co-workers to the world
        for (int i = 0; i < points.length; i += 2) {
            CoWorker coworker = new CoWorker(points[i], points[i + 1]);
            addObject(coworker, points[i].x, points[i].y);
        }
    }
    
    /**
     * Layouts the walls of the world, creating horizontal and vertical walls with gaps for movement.
     */
    private void layoutWalls() {
        // Creating horizontal walls with specified gaps
        createHorizontalWall(10, 10, 25, new int[]{7, 15, 20, 22});
        createHorizontalWall(100, 80, 15, new int[]{5, 10, 12, 13});
        createHorizontalWall(100, 270, 15, new int[]{3, 8, 11, 14});
        
        // Creating vertical walls with specified gaps
        createVerticalWall(10, 30, 3);
        createVerticalWall(10, 160, 3);
        createVerticalWall(100, 160, 5, new int[]{2, 4});
        createVerticalWall(300, 170, 2);
        createVerticalWall(80, 340, 2);
        createVerticalWall(10, 290, 5, new int[]{3, 4});
        createVerticalWall(590, 30, 15, new int[]{7, 12});
        
        // Creating additional horizontal walls for boundaries
        createHorizontalWall(10, 390, 22, new int[]{5, 15, 18, 20});
        
        // Adding office desk walls
        int[][] deskWalls = {
            {178, 100}, {178, 126}, {178, 152}, {207, 100}, {207, 126}, {207, 152},
            {428, 100}, {428, 126}, {428, 152}, {457, 100}, {457, 126}, {457, 152},
            {178, 300}, {178, 326}, {207, 300}, {207, 326}, {40, 160}, {70, 160},
            {560, 170}, {530, 170}, {500, 330}, {470, 330}, {440, 330},
            {410, 330}, {380, 330}, {350, 330}, {320, 330}, {290, 330}
        };
        
        // Adding wall objects at the desk positions
        for (int[] position : deskWalls) {
            addObject(new wall(), position[0], position[1]);
        }
    }
    
    /**
     * Creates a horizontal wall with gaps at specified positions.
     */
    private void createHorizontalWall(int startX, int startY, int count, int... gaps) {
        for (int i = 0; i < count; i++) {
            if (!contains(gaps, i)) {
                addObject(new wall(), startX + i * WALL_WIDTH, startY);
            }
        }
    }
    
    /**
     * Creates a vertical wall with gaps at specified positions.
     */
    private void createVerticalWall(int startX, int startY, int count, int... gaps) {
        for (int i = 0; i < count; i++) {
            if (!contains(gaps, i)) {
                addObject(new wall(), startX, startY + i * WALL_HEIGHT);
            }
        }
    }
    
    /**
     * Helper method to check if a value exists in an array.
     */
    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
    
    /**
     * Places collectibles (coffee boosts) at predefined positions in the world.
     */
    private void placeCollectibles() {
        int[][] collectiblePositions = {
            {250, 150}, {450, 300}, {50, 350}, {50, 200}, {560, 40},{450,230},{250,350},
            {320, 120}, {150, 250}, {500, 100}, {350, 300}, {100, 50}, {200,200}, {400, 200}
        };
        
        // Add collectible items (coffee boosts) to the world
        for (int[] position : collectiblePositions) {
            addObject(new CoffeeBoost(), position[0], position[1]);
        }
    }
    
    /**
     * Displays an introductory message to the player.
     */
    private void showIntroMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(20, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.drawString("Drink coffee to survive the workday avoiding extra work", 10, 25);
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
    }

    
    /**
     * Adds points to the score display.
     */
    public void addPoints(int points) {
        scoreDisplay.addScore(points);
    }
    
    /**
     * Removes a collectible from the world.
     */
    public void removeCollectibleFromWorld(Collectible collectible) {
        removeObject(collectible);
    }
    
    /**
     * Checks if the score has reached 100 and displays the survival message.
     */
    private void checkScore() {
        if (scoreDisplay.getScore() >= 10) {
            showSurvivalMessage();
        }
    }
    
    /**
     * Displays a message indicating the player has survived the workday.
     */
    private void showSurvivalMessage() {
        messageImage.setColor(Color.WHITE);
        messageImage.fillRect(0, 0, messageImage.getWidth(), messageImage.getHeight());
        messageImage.setColor(Color.BLACK);
        messageImage.setFont(new Font("Arial", 36));  // Set large and bold font
        messageImage.drawString("You survived work!", 25, 50);  // Draw at a higher Y position for emphasis
    
        getBackground().drawImage(messageImage, (getWidth() - messageImage.getWidth()) / 2, getHeight() / 2);
        Greenfoot.stop();  // Stop the game when survival message is displayed
    }

    
    /**
     * Returns the chat handler for managing interactions between characters.
     */
    public ChatHandler getChatHandler() {
        return chatHandler;
    }
    
    /**
     * Act method is called in every game cycle to update chat and display intro message.
     */
    public void act() {
        chatHandler.update();
       // Show intro message briefly
        if (!introShown) {
            showIntroMessage();  // Show the intro message once
            introTimer++;  // Increase the timer on each act cycle
            if (introTimer > 1) {  // Wait for a certain number of cycles (approx. 3 seconds)
                introShown = true;
            }
        }

    checkScore();  // Check score and show survival message if neededaw
}
}
