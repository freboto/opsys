/**
 * Created by kradalby on 14/04/15.
 */
public class CPU {
    private Queue cpuQueue;
    private Statistics statistics;
    private long maxCpuTime;
    private Process currentProcess;
    private boolean idle = true;


    public CPU(Statistics stats, long maxCpuTime) {
        this.cpuQueue = new Queue("cpu", 10, Constants.WEST);
        this.statistics = stats;
        this.maxCpuTime = maxCpuTime;

    }

    public long getMaxCpuTime() {
        return maxCpuTime;
    }

    public void insert(Process p) {
        cpuQueue.insert(p);
    }


}
