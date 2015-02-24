import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Arrays;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
    /**
     * Creates a new customer queue.
     *
     * @param queueLength    The maximum length of the queue.
     * @param gui            A reference to the GUI interface.
     */
    private int pointer;
    private Gui gui;
    private int unconsumedElements;

    private Customer[] buffer;

    public CustomerQueue(int queueLength, Gui gui) {
        buffer = new Customer[queueLength];
        this.gui = gui;
        pointer = 0;
    }


    public synchronized void addToBuffer(Customer customer) {
        while (unconsumedElements == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer[pointer] = customer;
        gui.fillLoungeChair(pointer, customer);
        pointer = (pointer + 1) % buffer.length;

        ++unconsumedElements;
        notifyAll();
    }

    public int capacity() {
        return buffer.length;
    }


    public synchronized Customer removeFromBuffer() {
        Customer result = null;
        try {
            result = peek();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        --unconsumedElements;
        gui.emptyLoungeChair(pointer);
        notifyAll();

        return result;
    }


    public synchronized Customer peek() throws InterruptedException {
        /* Wait for data to become available. */
        while (unconsumedElements == 0)
            wait();

        return buffer[(pointer + (capacity() - unconsumedElements)) % capacity()];
    }

    public synchronized int size() {
        return unconsumedElements;
    }


	// Add more methods as needed
}
