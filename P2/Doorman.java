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

    private boolean running;

    //private Gui gui;
    private CustomerQueue queue;
    public Doorman(CustomerQueue queue, Gui gui) {
        //this.gui = gui;
        this.queue = queue;
	}

    @Override
    public void run() {
        super.run();
        running = true;
        while(running){
            Customer customer = new Customer();
            queue.addToBuffer(customer);

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
        running = false;
	}

	// Add more methods as needed
}
