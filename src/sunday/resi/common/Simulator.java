package sunday.resi.common;

/**
 * Simulates a circuit. The actual simulation is done in an extra thread which can be stopped and restarted.
 * 
 * @author Peter H&auml;nsgen
 */
public class Simulator
{
    private final long delay;

    private SimulatorThread workerThread;

    private Circuit circuit;

    /**
     * The constructor.
     */
    public Simulator(Circuit circuit, long delay)
    {
        this.circuit = circuit;
        this.delay = delay;
    }

    public void start()
    {
        if (workerThread == null)
        {
            workerThread = new SimulatorThread();
            workerThread.start();
        }
    }

    public void stop()
    {
        if (workerThread != null)
        {
            workerThread.terminate();
            workerThread = null;
        }
    }

    class SimulatorThread extends Thread
    {
        private volatile boolean stop;

        @Override
        public void run()
        {
            while (!stop)
            {
                circuit.simulate();

                try
                {
                    Thread.sleep(delay);
                }
                catch (InterruptedException e)
                {
                }
            }
        }

        public void terminate()
        {
            stop = true;
            try
            {
                join();
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
