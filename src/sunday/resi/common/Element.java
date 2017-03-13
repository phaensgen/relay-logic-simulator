package sunday.resi.common;

/**
 * This is the common superclass for all elements of a circuit.
 * 
 * @author Peter H&auml;nsgen
 */
public abstract class Element implements Monitor
{
    private Circuit circuit;

    private String name;

    private String lastDebugMessage;

    /**
     * The constructor.
     */
    public Element(Circuit circuit, String name)
    {
        this.circuit = circuit;
        this.name = name;
    }

    public Circuit getCircuit()
    {
        return circuit;
    }

    public String getName()
    {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <T extends Element> T watch()
    {
        circuit.addMonitor(this);
        return (T) this;
    }

    @Override
    public void monitor()
    {
        String debugMessage = toString();
        if (!debugMessage.equals(lastDebugMessage))
        {
            System.out.println(debugMessage);
            lastDebugMessage = debugMessage;
        }
    }
}
