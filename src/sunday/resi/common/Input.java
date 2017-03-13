package sunday.resi.common;

/**
 * Represents a connector for incoming signals. Each part can define zero or more inputs. An input can be connected to a
 * signal from which it reads its value during simulation.
 * 
 * @author Peter H&auml;nsgen
 */
public class Input extends Connector
{
    public Boolean getValue()
    {
        Signal signal = getSignal();
        return (signal != null) ? signal.getValue() : null;
    }
}
