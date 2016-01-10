/**
 * Created by lintaocui on 1/3/16.
 */
public class ReadWriteLock {
    int reader;
    int writer;
    int writeRequest;

    public synchronized void lockRead() throws InterruptedException {
        while(writer > 0 || writeRequest > 0)
        {
            wait();
        }

        reader++;
    }

    public synchronized void unlockRead() {
        reader--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequest++;

        while(reader > 0)
            wait();

        writeRequest--;
        writer++;
    }


    public synchronized void unlockWrite() {
        writer--;
        notifyAll();
    }
}
