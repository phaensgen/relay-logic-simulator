package sunday.resi.common;

/**
 * This is a common exception for the simulator.
 * 
 * @author Peter H&auml;nsgen
 */
public class SimulatorException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * The constructor for a message.
     */
    public SimulatorException(String message)
    {
        super(message);
    }

    /**
     * The constructor for a message and a cause.
     */
    public SimulatorException(String message, Exception cause)
    {
        super(message, cause);
    }
}
