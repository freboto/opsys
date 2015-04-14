/**
 * Created by kradalby on 14/04/15.
 */
public class IO {
    private Queue ioQueue;
    private Statistics statistics;
    private long avgIOTime;
    /**
     * A reference to the gui, so we can animate stuff
     */
    private Gui gui;


    private Process curActProcess;

    public IO(Queue ioQueue, Statistics stats, long avgIOTime, Gui gui) {
        this.ioQueue = ioQueue;
        this.statistics = stats;
        this.avgIOTime = avgIOTime;
        this.gui = gui;
    }

    public long getAvgIOTime() {
        return avgIOTime;
    }

    public 

}