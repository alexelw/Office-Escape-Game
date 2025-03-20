import greenfoot.*;
/**
 * Write a description of class Energy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Energy {
    private int currentEnergy;
    private int maxEnergy;

    public Energy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
    }

    public void changeEnergy(int amount) {
        currentEnergy = Math.max(0, Math.min(maxEnergy, currentEnergy + amount));
    }

    public void restoreEnergy(int amount) {
        currentEnergy = Math.min(maxEnergy, currentEnergy + amount);
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }
    
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public boolean isEnergyDepleted() {
        return currentEnergy == 0;
    }
}


