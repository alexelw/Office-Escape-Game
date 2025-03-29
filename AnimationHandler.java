import greenfoot.*;

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

    // Constructor now expects two sets for walking animations:
    public AnimationHandler(Worker worker, String idle, 
            String[] walkingUpLeft, String[] walkingDownRight, 
            String[] effect, String[] talking, int scaleWidth, int scaleHeight) {
        this.worker = worker;

        idleImage = new GreenfootImage(idle);
        idleImage.scale(scaleWidth, scaleHeight);

        // Load and scale walking images for up-left movement
        walkingImagesUpLeft = new GreenfootImage[walkingUpLeft.length];
        for (int i = 0; i < walkingUpLeft.length; i++) {
            walkingImagesUpLeft[i] = new GreenfootImage(walkingUpLeft[i]);
            walkingImagesUpLeft[i].scale(scaleWidth, scaleHeight);
        }

        // Load and scale walking images for down-right movement
        walkingImagesDownRight = new GreenfootImage[walkingDownRight.length];
        for (int i = 0; i < walkingDownRight.length; i++) {
            walkingImagesDownRight[i] = new GreenfootImage(walkingDownRight[i]);
            walkingImagesDownRight[i].scale(scaleWidth, scaleHeight);
        }

        // Load and scale effect images
        effectImages = new GreenfootImage[effect.length];
        for (int i = 0; i < effect.length; i++) {
            effectImages[i] = new GreenfootImage(effect[i]);
            effectImages[i].scale(scaleWidth, scaleHeight);
        }

        // Load and scale talking images
        talkingImages = new GreenfootImage[talking.length];
        for (int i = 0; i < talking.length; i++) {
            talkingImages[i] = new GreenfootImage(talking[i]);
            talkingImages[i].scale(scaleWidth, scaleHeight);
        }
        
        // Set initial image
        worker.setImage(idleImage);
    }

    // Animate walking using the two sets based on movement direction:
    public void animateWalking(int moveDirectionX, int moveDirectionY) {
        if (!isEffectActive) {
            animationCounter++;
            if (animationCounter >= animationDelay) {
                animationCounter = 0;
                // If any key in the negative direction is pressed, use up-left set:
                if (moveDirectionX < 0 || moveDirectionY < 0) {
                    animationFrame = (animationFrame + 1) % walkingImagesUpLeft.length;
                    worker.setImage(walkingImagesUpLeft[animationFrame]);
                } 
                // If any key in the positive direction is pressed, use down-right set:
                else if (moveDirectionX > 0 || moveDirectionY > 0) {
                    animationFrame = (animationFrame + 1) % walkingImagesDownRight.length;
                    worker.setImage(walkingImagesDownRight[animationFrame]);
                } 
                else {
                    resetToIdle();
                }
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

    public void animateTalking() {
        isTalking = true;
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            animationFrame = (animationFrame + 1) % talkingImages.length;
            worker.setImage(talkingImages[animationFrame]);
        }
    }

    public void resetToIdle() {
        isEffectActive = false;
        worker.setImage(idleImage);
    }
}
