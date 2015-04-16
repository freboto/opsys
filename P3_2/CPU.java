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
public class CPU {
    private Queue cpuQueue;
    private Statistics statistics;
    private long maxCpuTime;
    private Process currentProcess = null;
    public boolean idle = true;
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

    public Process doStart(long clock) {

        if (cpuQueue.isEmpty()) {
            currentProcess = null;
            idle = true;
            return null;
        }

        currentProcess = (Process) cpuQueue.removeNext();
        gui.setCpuActive(currentProcess);
        return currentProcess;

    }

    public Process doEnd(long clock) {
        Process p = currentProcess;
        gui.setCpuActive(null);
        currentProcess = null;
        return p;
    }






}
