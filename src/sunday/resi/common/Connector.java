package sunday.resi.common;

/**
 * This is the common superclass for connectors to a signal. Every connector can be connected with exactly one signal to
 * which it sends its output value or from which it reads its input value, depending on the type of connector.
 * 
 * @author Peter H&auml;nsgen
 */
public abstract class Connector
{
    private Signal signal;

    public void setSignal(Signal signal)
    {
        if ((this.signal != null) && (this.signal != signal))
        {
            throw new SimulatorException("Signal is already connected.");
        }

        this.signal = signal;
    }

    public Signal getSignal()
    {
        return signal;
    }

    @Override
    public String toString()
    {
        return String.valueOf(signal);
    }
}
