import greenfoot.*;
/**
 * Write a description of class AnimationHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class AnimationHandler {
    private GreenfootImage[] walkingImages;
    private GreenfootImage[] effectImages;
    private GreenfootImage idleImage;
    private Worker worker;
    private int animationFrame = 0;
    private int animationDelay = 5;
    private int animationCounter = 0;
    private boolean isEffectActive = false;

    public AnimationHandler(Worker worker, String idle, String[] walking, String[] effect) {
        this.worker = worker;

        idleImage = new GreenfootImage(idle);

        // Load walking animation images
        walkingImages = new GreenfootImage[walking.length];
        for (int i = 0; i < walking.length; i++) {
            walkingImages[i] = new GreenfootImage(walking[i]);
        }

        // Load effect animation images
        effectImages = new GreenfootImage[effect.length];
        for (int i = 0; i < effect.length; i++) {
            effectImages[i] = new GreenfootImage(effect[i]);
        }

        // Set initial image
        worker.setImage(idleImage);
    }

    public void animateWalking() {
        if (!isEffectActive) {
            animationCounter++;
            if (animationCounter >= animationDelay) {
                animationCounter = 0;
                animationFrame = (animationFrame + 1) % walkingImages.length;
                worker.setImage(walkingImages[animationFrame]);
            }
        }
    }

    public void animateEffect() {
        isEffectActive = true;
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            animationFrame = (animationFrame + 1) % effectImages.length;
            worker.setImage(effectImages[animationFrame]);
        }
    }

    public void resetToIdle() {
        isEffectActive = false;
        worker.setImage(idleImage);
    }
}
