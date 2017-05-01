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
     * The inner circuit, formed of the parts of the component.
     */
    private final Circuit local;

    /**
     * The constructor.
     */
    public Component(Circuit parent, String name)
    {
        super(parent, name);

        local = new Circuit();
    }

    protected Circuit getLocalCircuit()
    {
        return local;
    }

    @Override
    public void simulate()
    {
        // parts are contained by local circuit
        local.simulate();
    }
}
