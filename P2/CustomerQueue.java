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
    private int head;
    private Gui gui;
    private int unconsumedElements;

    private Customer[] buffer;

    public CustomerQueue(int queueLength, Gui gui) {
        buffer = new Customer[queueLength];
        this.gui = gui;
        head = 0;
    }


    public synchronized void addToBuffer(Customer customer) {
        while (unconsumedElements == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer[head] = customer;
        gui.fillLoungeChair(head, customer);
        head = (head + 1) % buffer.length;

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
        gui.emptyLoungeChair(head);
        notifyAll();

        return result;
    }


    public synchronized Customer peek() throws InterruptedException {
        /* Wait for data to become available. */
        while (unconsumedElements == 0)
            wait();

        return buffer[(head + (capacity() - unconsumedElements)) % capacity()];
    }

    public synchronized int size() {
        return unconsumedElements;
    }


	// Add more methods as needed
}
