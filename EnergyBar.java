import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergyBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class EnergyBar extends Actor {
    private TiredOfficeWorker worker;
    private int width;
    private int height;
    
    public EnergyBar(TiredOfficeWorker worker, int width, int height) {
        this.worker = worker;
        this.width = width;
        this.height = height;
        updateBar();
    }
    
    public void act() {
        updateBar();
    }
    
    private void updateBar() {
        GreenfootImage bar = new GreenfootImage(width, height);
        
        int currentEnergy = worker.getEnergy();
        int maxEnergy = worker.getEnergyObject().getMaxEnergy();
        int filledWidth = (int)((double)currentEnergy / maxEnergy * width);
        
        bar.setColor(Color.GRAY); // background
        bar.fillRect(0, 0, width, height);
        
        bar.setColor(Color.GREEN); // filled part
        bar.fillRect(0, 0, filledWidth, height);
        
        bar.setColor(Color.BLACK); // outline
        bar.drawRect(0, 0, width - 1, height - 1);
        
        setImage(bar);
    }
}

