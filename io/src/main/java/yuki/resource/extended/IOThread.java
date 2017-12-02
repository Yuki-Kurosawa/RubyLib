package yuki.resource.extended;

/**
 * Created by Akeno on 2016/10/24.
 */
public class IOThread extends Thread {
    public ConsoleSimulator simulator;
    public IOThread(ConsoleSimulator simulator){
        this.simulator=simulator;
    }

    @Override
    public void run() {
        this.simulator.run();
    }
}
