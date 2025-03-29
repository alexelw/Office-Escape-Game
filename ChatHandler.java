import greenfoot.*; 
/**
 * Write a description of class ChatInteraction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ChatHandler {
    private static final int CHAT_DURATION = 100;
    private static final int DISABLE_DURATION = 200;

    private int chatTimer = 0;
    private int disableTimer = 0;
    private boolean chatActive = false;
    private boolean chatCooldown = false;

    private TiredOfficeWorker worker;
    private CoWorker coworker;

    public void startChat(TiredOfficeWorker worker, CoWorker coworker) {
        if (!chatActive && !chatCooldown) {
            this.worker = worker;
            this.coworker = coworker;
            chatTimer = CHAT_DURATION;
            disableTimer = DISABLE_DURATION;
            chatActive = true;
            chatCooldown = true;

            worker.stopMovement();
            coworker.stopMovement();
        }
    }

    public void update() {
        if (chatActive) {
            if (chatTimer > 0) {
                chatTimer--;
                if (worker != null) {
                    worker.animateTalking();
                }
            } else {
                endChat();
            }
        } else if (chatCooldown) {
            if (disableTimer > 0) {
                disableTimer--;
            } else {
                chatCooldown = false;
            }
        }
    }

    private void endChat() {
        chatActive = false;
        if (worker != null) worker.resumeMovement();
        if (coworker != null) coworker.resumeMovement();
        worker = null;
        coworker = null;
    }

    public boolean isChatActive() {
        return chatActive;
    }

    public boolean isChatOnCooldown() {
        return chatCooldown;
    }
}

