package sunday.resi.common;

/**
 * Represents a ground potential. The output is always false.
 * 
 * @author Peter H&auml;nsgen
 */
public class Ground extends Part
{
    private Output out;

    /**
     * The constructor.
     */
    public Ground(Circuit circuit, String name)
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
        out.setValue(false);
    }

    @Override
    public String toString()
    {
        return "[Ground " + getName() + "=false]";
    }
}
