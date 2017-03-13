package sunday.resi.common;

/**
 * Simulates a circuit.
 * 
 * @author Peter H&auml;nsgen
 */
public class Simulator
{
    private long delay;

    private long duration;

    /**
     * The constructor.
     */
    public Simulator(long delay)
    {
        this(delay, Long.MAX_VALUE);
    }

    /**
     * The constructor.
     */
    public Simulator(long delay, long duration)
    {
        this.delay = delay;
        this.duration = duration;
    }

    public void simulate(Circuit circuit)
    {
        long begin = System.currentTimeMillis();

        while ((System.currentTimeMillis() - begin) < duration)
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
}
