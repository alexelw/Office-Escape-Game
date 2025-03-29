import greenfoot.*;

public class AnimationHandler {
    private GreenfootImage[] walkingImages;
    private GreenfootImage[] effectImages;
    private GreenfootImage[] talkingImages;
    private GreenfootImage idleImage;
    private Worker worker;
    private int animationFrame = 0;
    private int animationDelay = 10;
    private int animationCounter = 0;
    private boolean isEffectActive = false;
    private boolean isTalking = false; 

    
    public AnimationHandler(Worker worker, String idle, String[] walking, String[] effect,String[] talking, int scaleWidth, int scaleHeight) {
        this.worker = worker;

        idleImage = new GreenfootImage(idle);
        idleImage.scale(scaleWidth, scaleHeight);

        // Load and scale walking images
        walkingImages = new GreenfootImage[walking.length];
        for (int i = 0; i < walking.length; i++) {
            walkingImages[i] = new GreenfootImage(walking[i]);
            walkingImages[i].scale(scaleWidth, scaleHeight);
        }

        // Load and scale effect images
        effectImages = new GreenfootImage[effect.length];
        for (int i = 0; i < effect.length; i++) {
            effectImages[i] = new GreenfootImage(effect[i]);
            effectImages[i].scale(scaleWidth, scaleHeight);
        }

        talkingImages = new GreenfootImage[talking.length];
        for (int i = 0; i < talking.length; i++) {
            talkingImages[i] = new GreenfootImage(talking[i]);
            talkingImages[i].scale(scaleWidth, scaleHeight);
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
