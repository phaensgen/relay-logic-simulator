package sunday.resi.common;

/**
 * A component is a group of parts that are wired together. It represents a reusable circuit of a bigger granularity
 * than single parts. A component can have external outputs and inputs which are connected to its internal parts.
 * 
 * @author Peter H&auml;nsgen
 */
public abstract class Component extends Part
{
    /**
     * The constructor.
     */
    public Component(Circuit circuit, String name)
    {
        super(circuit, name);
    }

    @Override
    public void simulate()
    {
        // contained parts are simulated individually, nothing to do here
    }
}
