import greenfoot.*;

/**
 * Manages energy levels, including depletion and restoration.
 * 
 * @author Alex Watts
 * @version 1.2
 */
public class Energy {
    private int currentEnergy;
    private int maxEnergy;

    /**
     * Initializes the energy system with a maximum energy value.
     */
    public Energy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
    }

    /**
     * Changes the current energy level within valid bounds.
     */
    public void changeEnergy(int amount) {
        currentEnergy = Math.max(0, Math.min(maxEnergy, currentEnergy + amount));
    }

    /**
     * Restores energy, ensuring it does not exceed the maximum.
     */
    public void restoreEnergy(int amount) {
        currentEnergy = Math.min(maxEnergy, currentEnergy + amount);
    }

    /**
     * Returns the current energy level.
     */
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    
    /**
     * Returns the maximum energy capacity.
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * Checks if energy is fully depleted.
     */
    public boolean isEnergyDepleted() {
        return currentEnergy == 0;
    }
}
