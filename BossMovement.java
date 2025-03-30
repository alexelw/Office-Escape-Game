import greenfoot.*;

/**
 * Handles the movement logic for the Boss character.
 * The Boss follows the TiredOfficeWorker while avoiding walls.
 * 
 * @author Alex Watts
 * @version 1.2
 */

public class BossMovement implements IMovement {
    private Boss boss;
    private TiredOfficeWorker target;
    private boolean stopped = false;

    /**
     * Initializes the movement handler for the Boss.
     */
    public BossMovement(Boss boss, TiredOfficeWorker target) {
        this.boss = boss;
        this.target = target;
    }

    /**
     * Moves the Boss towards the target while avoiding obstacles.
     */
    @Override
    public void move() {
        if (stopped || boss == null || target == null) return;

        int bossX = boss.getX(), bossY = boss.getY();
        int targetX = target.getX(), targetY = target.getY();
        int dx = targetX - bossX, dy = targetY - bossY;
        double distance = Math.hypot(dx, dy);

        if (distance == 0) return; // Already at the target position

        // Normalize movement direction
        int moveX = (int) Math.signum(dx) * boss.getSpeed();
        int moveY = (int) Math.signum(dy) * boss.getSpeed();

        boolean canMoveX = !isWallAt(bossX + moveX, bossY);
        boolean canMoveY = !isWallAt(bossX, bossY + moveY);
        boolean canMoveDiagonal = !isWallAt(bossX + moveX, bossY + moveY);

        // Move priority: diagonal > vertical > horizontal > random
        if (canMoveDiagonal) {
            boss.setLocation(bossX + moveX, bossY + moveY);
        } else if (!canMoveX && canMoveY) {
            boss.setLocation(bossX, bossY + moveY);
        } else if (!canMoveX) {
            if (!isWallAt(bossX, bossY + boss.getSpeed())) {
                boss.setLocation(bossX, bossY + boss.getSpeed());
            } else if (!isWallAt(bossX, bossY - boss.getSpeed())) {
                boss.setLocation(bossX, bossY - boss.getSpeed());
            }
        } else if (canMoveX) {
            boss.setLocation(bossX + moveX, bossY);
        } else {
            // Random movement to break out of stuck state
            int randomMove = (Greenfoot.getRandomNumber(2) == 0 ? -boss.getSpeed() : boss.getSpeed());
            if (!isWallAt(bossX, bossY + randomMove)) {
                boss.setLocation(bossX, bossY + randomMove);
            } else if (!isWallAt(bossX + randomMove, bossY)) {
                boss.setLocation(bossX + randomMove, bossY);
            }
        }
    }

    /**
     * Checks if a wall is present at the given coordinates.
     */
    private boolean isWallAt(int x, int y) {
        World world = boss.getWorld();
        return world != null && !world.getObjectsAt(x, y, wall.class).isEmpty();
    }

    /**
     * Stops the Boss from moving.
     */
    public void stopMoving() {
        stopped = true;
    }

    /**
     * Resumes the Boss's movement.
     */
    public void resumeMoving() {
        stopped = false;
    }
}
