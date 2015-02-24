import java.nio.BufferUnderflowException;

import static java.lang.Thread.sleep;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */

    private CustomerQueue queue;
    private Gui gui;
    private int pos;
    private boolean running;

	public Barber(CustomerQueue queue, Gui gui, int pos) {
        this.queue = queue;
        this.gui = gui;
        this.pos = pos;
	}

    @Override
    public void run() {
        running = true;
        gui.barberIsSleeping(pos);
        super.run();
        while(running) {
            Customer customer = queue.removeFromBuffer();
            gui.fillBarberChair(pos, customer);
            System.out.println("work");

            try {
                gui.barberIsAwake(pos);
                sleep(Globals.barberSleep);
                gui.emptyBarberChair(pos);
                gui.barberIsSleeping(pos);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
        start();
    }

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
        running = false;
	}

	// Add more methods as needed
}

