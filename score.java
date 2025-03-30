import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class score extends Actor
{
    /**
     * Act - do whatever the score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int score = 0;  // The score variable

    public score() {
        updateImage();
    }

    // Method to increase score
    public void addScore(int points) {
        score += points;
        updateImage();
    }

    // Updates the displayed score
    private void updateImage() {
        setImage(new GreenfootImage("Score: " + score, 24, Color.WHITE, Color.BLACK));
    }
    
    public int getScore() {
    return score;
    }    
        
}

