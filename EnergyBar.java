import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EnergyBar Class - Displays and updates the energy level of the TiredOfficeWorker.
 * 
 * @author Olivia-Melody Adenyin
 * @version 1.0
 */
public class EnergyBar extends Actor {
    private TiredOfficeWorker worker;
    private int width;
    private int height;

    /**
     * Constructor - Initializes the energy bar.
     * 
     * @param worker The TiredOfficeWorker whose energy is being tracked.
     * @param width  The width of the energy bar.
     * @param height The height of the energy bar.
     */
    public EnergyBar(TiredOfficeWorker worker, int width, int height) {
        this.worker = worker;
        this.width = width;
        this.height = height;
        updateBar();
    }

    /**
     * Continuously updates the energy bar each frame.
     */
    public void act() {
        updateBar();
    }

    /**
     * Updates the visual representation of the energy bar based on the worker's current energy.
     */
    private void updateBar() {
        GreenfootImage bar = new GreenfootImage(width, height);

        int currentEnergy = worker.getEnergy();
        int maxEnergy = worker.getEnergyObject().getMaxEnergy();
        int filledWidth = (int) ((double) currentEnergy / maxEnergy * width);

        // Draw background
        bar.setColor(Color.GRAY);
        bar.fillRect(0, 0, width, height);

        // Draw energy fill
        bar.setColor(Color.GREEN);
        bar.fillRect(0, 0, filledWidth, height);

        // Draw outline
        bar.setColor(Color.BLACK);
        bar.drawRect(0, 0, width - 1, height - 1);

        setImage(bar);
    }
}
