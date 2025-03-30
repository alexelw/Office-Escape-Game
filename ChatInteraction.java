import greenfoot.*;
/**
 * Write a description of class ChatInteraction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChatInteraction {
    private Worker worker1, worker2;
    private int chatTimer, disableTimer;

    /**
     * Initializes a chat interaction with timers.
     */
    public ChatInteraction(Worker worker1, Worker worker2, int chatTime, int disableTime) {
        this.worker1 = worker1;
        this.worker2 = worker2;
        this.chatTimer = chatTime;
        this.disableTimer = disableTime;
    }

    /**
     * Updates chat timers and animations.
     */
    public void update() {
        if (chatTimer > 0) {
            chatTimer--;
            worker1.animateTalking();
            worker2.animateTalking();
        } else if (disableTimer > 0) {
            disableTimer--;
        }
    }

    /**
     * Checks if the chat has ended.
     */
    public boolean isFinished() {
        return chatTimer == 0;
    }

    /**
     * Checks if the cooldown period is over.
     */
    public boolean isCooldownOver() {
        return disableTimer == 0;
    }

    /**
     * Ends the chat and resumes movement.
     */
    public void endChat() {
        worker1.resumeMovement();
        worker2.resumeMovement();
    }

    /**
     * Checks if this interaction involves the given workers.
     */
    public boolean involves(Worker w1, Worker w2) {
        return (worker1 == w1 && worker2 == w2) || (worker1 == w2 && worker2 == w1);
    }
}