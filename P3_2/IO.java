/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Norwegian Defence Research Establishment / NTNU
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * Created by Fredrik on 15/04/15.
 */
public class IO {
    private Queue ioQueue;
    private Statistics statistics;
    private long avgIOTime;
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

    public void insert(Process p) {
        ioQueue.insert(p);
    }

    public Process start() {

        if (ioQueue.isEmpty()) {
            curActProcess = null;
            gui.setIoActive(null);
            return null;
        }
        curActProcess = (Process) ioQueue.removeNext();
        gui.setIoActive(curActProcess);
        return curActProcess;
    }

    public boolean isIoQueueEmpty(){
        if(ioQueue.isEmpty()){
            return true;
        }
        else return false;
    }

    public Process stop() {
        Process p = curActProcess;
        curActProcess = null;
        gui.setIoActive(null);
        return p;
    }

}
