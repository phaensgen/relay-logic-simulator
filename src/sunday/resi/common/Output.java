package sunday.resi.common;

/**
 * Represents a connector for outgoing signals. Each part may define zero or more outputs to which it sends its outgoing
 * values during simulation. The outputs can be connected to inputs of other parts via signals.
 * 
 * @author Peter H&auml;nsgen
 */
public class Output extends Connector
{
    public void setValue(Boolean value)
    {
        Signal signal = getSignal();
        if (signal != null)
        {
            signal.setValue(value);
        }
    }
}
