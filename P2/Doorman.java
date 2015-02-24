import java.nio.BufferOverflowException;

import static java.lang.Thread.sleep;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
    private Gui gui;
    private CustomerQueue queue;
    public Doorman(CustomerQueue queue, Gui gui) {
        this.gui = gui;
        this.queue = queue;
	}

    @Override
    public void run() {
        super.run();
        while(true){
            Customer customer = new Customer();
            gui.println("Doorman is waiting for free chairs...");
            queue.addToBuffer(customer);
            gui.println("Doorman was notified of a free chair");


            try {
                sleep(Globals.doormanSleep);
            }

            catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		// Incomplete
        start();
	}
	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		// Incomplete
	}

	// Add more methods as needed
}
