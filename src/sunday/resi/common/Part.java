package sunday.resi.common;

/**
 * This is the common superclass for all parts. A part belongs to a circuit and has a unique name. It can be simulated
 * by a simulator.
 * 
 * @author Peter H&auml;nsgen
 */
public abstract class Part extends Element
{
    private static int instanceCount;

    /**
     * The constructor for an automatically generated name.
     */
    public Part(Circuit circuit)
    {
        this(circuit, generateName());
    }

    /**
     * The constructor.
     */
    public Part(Circuit circuit, String name)
    {
        super(circuit, name);

        circuit.addPart(this);
    }

    public abstract void simulate();

    private static String generateName()
    {
        return "p" + String.valueOf(instanceCount++);
    }
}
