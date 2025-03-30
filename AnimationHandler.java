import greenfoot.*;

/**
 * Handles animations for the Worker character.
 * 
 * @author Alex Watts
 * @version 1.3
 */

public class AnimationHandler {
    private GreenfootImage[] walkingImagesUpLeft;
    private GreenfootImage[] walkingImagesDownRight;
    private GreenfootImage[] effectImages;
    private GreenfootImage[] talkingImages;
    private GreenfootImage idleImage;
    private Worker worker;
    private int animationFrame = 0;
    private int animationDelay = 10;
    private int animationCounter = 0;
    private boolean isEffectActive = false;
    private boolean isTalking = false;

    /**
     * Initializes animation images and sets idle state.
     */
    public AnimationHandler(Worker worker, String idle, 
            String[] walkingUpLeft, String[] walkingDownRight, 
            String[] effect, String[] talking, int scaleWidth, int scaleHeight) {
        this.worker = worker;

        idleImage = new GreenfootImage(idle);
        idleImage.scale(scaleWidth, scaleHeight);

        walkingImagesUpLeft = loadScaledImages(walkingUpLeft, scaleWidth, scaleHeight);
        walkingImagesDownRight = loadScaledImages(walkingDownRight, scaleWidth, scaleHeight);
        effectImages = loadScaledImages(effect, scaleWidth, scaleHeight);
        talkingImages = loadScaledImages(talking, scaleWidth, scaleHeight);

        worker.setImage(idleImage);
    }

    /**
     * Animates walking based on movement direction.
     */
    public void animateWalking(int moveDirectionX, int moveDirectionY) {
        if (!isEffectActive) {
            animationCounter++;
            if (animationCounter >= animationDelay) {
                animationCounter = 0;
                if (moveDirectionX < 0 || moveDirectionY < 0) {
                    animationFrame = (animationFrame + 1) % walkingImagesUpLeft.length;
                    worker.setImage(walkingImagesUpLeft[animationFrame]);
                } else if (moveDirectionX > 0 || moveDirectionY > 0) {
                    animationFrame = (animationFrame + 1) % walkingImagesDownRight.length;
                    worker.setImage(walkingImagesDownRight[animationFrame]);
                } else {
                    resetToIdle();
                }
            }
        }
    }

    /**
     * Plays an effect animation.
     */
    public void animateEffect() {
        isEffectActive = true;
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            animationFrame = (animationFrame + 1) % effectImages.length;
            worker.setImage(effectImages[animationFrame]);
        }
    }

    /**
     * Animates talking motion.
     */
    public void animateTalking() {
        isTalking = true;
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            animationFrame = (animationFrame + 1) % talkingImages.length;
            worker.setImage(talkingImages[animationFrame]);
        }
    }

    /**
     * Resets animation to idle state.
     */
    public void resetToIdle() {
        isEffectActive = false;
        isTalking = false;
        worker.setImage(idleImage);
    }

    /**
     * Loads and scales an array of images.
     */
    private GreenfootImage[] loadScaledImages(String[] filenames, int width, int height) {
        GreenfootImage[] images = new GreenfootImage[filenames.length];
        for (int i = 0; i < filenames.length; i++) {
            images[i] = new GreenfootImage(filenames[i]);
            images[i].scale(width, height);
        }
        return images;
    }
}
