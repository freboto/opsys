/**
 * Created by kradalby on 14/04/15.
 */
public class CPU {
    private Queue cpuQueue;
    private Statistics statistics;
    private long maxCpuTime;
    private Process currentProcess = null;
    private boolean idle = true;
    private Gui gui;


    public CPU(Queue cpuQueue, Statistics stats, long maxCpuTime, Gui gui) {
        this.cpuQueue = cpuQueue;
        this.statistics = stats;
        this.maxCpuTime = maxCpuTime;
        this.gui = gui;

    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public long getMaxCpuTime() {
        return maxCpuTime;
    }

    public void insert(Process p) {
        cpuQueue.insert(p);
    }

    public Process doSwitch(long clock) {
        if (currentProcess != null) {
            insert(currentProcess);
        }

        currentProcess = popQ(clock);
        gui.setCpuActive(currentProcess);
        return currentProcess;
    }

    public Process doEnd(long clock) {
        if (cpuQueue.isEmpty()) {
            idle = true;
            currentProcess = null;
        }
        Process p = currentProcess;
        gui.setCpuActive(null);
        currentProcess = null;
        return p;
    }

    public void handleIO(long clock) {
        if (cpuQueue.isEmpty()) {
            idle = true;
            currentProcess = null;
        }
        currentProcess = popQ(clock);
    }

    public Process popQ(long clock) {
        if (!cpuQueue.isEmpty()) {
            Process next = (Process) cpuQueue.removeNext();
            return next;
        }
        return null;
    }

    public boolean isIdle() {
        return currentProcess == null;
    }
}
