package sunday.resi.common;

/**
 * Represents a power source. The output is always true.
 * 
 * @author Peter H&auml;nsgen
 */
public class Power extends Part
{
    private Output out;

    /**
     * The constructor.
     */
    public Power(Circuit circuit, String name)
    {
        super(circuit, name);

        out = new Output();
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public void simulate()
    {
        out.setValue(true);
    }

    @Override
    public String toString()
    {
        return "[Power " + getName() + "=true]";
    }
}
