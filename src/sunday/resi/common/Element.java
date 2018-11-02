package sunday.resi.common;

/**
 * This is the common superclass for all elements of a circuit.
 * 
 * @author Peter H&auml;nsgen
 */
public abstract class Element
{
    private Circuit circuit;

    private String name;

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
}
