package yuki.resource.extended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public   class  ConsoleSimulator  implements  Runnable {
    private volatile boolean isStop = false;

    public static final int INFO = 0;

    public static final int ERROR = 1;

    private InputStream is;

    private int type;

    /**
     * Creates a new instance of StreamInterceptor
     */
    public ConsoleSimulator(InputStream is, int type) {
        this.is = is;
        this.type = type;
    }

    public void run() {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String s="";
        String o="";
        String e="";
        try {
            while ((!isStop) && (s = reader.readLine()) != null) {
                if (s.length() != 0) {
                    if (type == INFO) {
                        System.out.println(" INFO> " + s);
                        o+=s+"\n";
                    } else {
                        System.err.println(" ERROR> " + s);
                        e+=s+"\n";
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            this.s=s;
            this.o=o;
            this.e=e;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String o="";
    public String s="";
    public String e="";

    public void stop() {
        isStop = true;
    }
}