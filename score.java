import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Score Class - Displays and updates the player's score.
 * 
 * @author Olivia-Melody Adenyin
 * @version 1.2
 */
public class Score extends Actor {
    // Score tracking variable
    private int score = 0;  

    /**
     * Constructor - Initializes the score display.
     */
    public Score() {
        updateImage();
    }

    /**
     * Adds points to the score and updates the display.
     * 
     * @param points The number of points to add.
     */
    public void addScore(int points) {
        score += points;
        updateImage();
    }

    /**
     * Returns the current score.
     * 
     * @return The current score value.
     */
    public int getScore() {
        return score;
    }    

    /**
     * Updates the displayed score.
     */
    private void updateImage() {
        setImage(new GreenfootImage("Score: " + score, 24, Color.WHITE, Color.BLACK));
    }
}
