package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;

/**
 * This is a switch where the middle contact represents an output which selects its value from one of the two inputs.
 * 
 * @author Peter H&auml;nsgen
 */
public class Swotch extends AbstractSwitch
{
    private final Input _in;

    private final Input in;

    private final Output middleOut;

    /**
     * The constructor.
     */
    public Swotch(Circuit circuit, String name)
    {
        super(circuit, name);

        _in = new Input();
        in = new Input();
        middleOut = new Output();
    }

    public Input get_In()
    {
        return _in;
    }

    public Input getIn()
    {
        return in;
    }

    public Output getMiddleOut()
    {
        return middleOut;
    }

    @Override
    public void simulate()
    {
        if (isPushed())
        {
            middleOut.setValue(in.getValue());
        }
        else
        {
            middleOut.setValue(_in.getValue());
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Swotch ");
        sb.append(getName());
        sb.append(": ");
        sb.append("pushed=");
        sb.append(String.valueOf(isPushed()));
        sb.append(", _in=");
        sb.append(String.valueOf(_in));
        sb.append(", in=");
        sb.append(String.valueOf(in));
        sb.append(", middleOut=");
        sb.append(String.valueOf(middleOut));
        sb.append("]");
        return sb.toString();
    }
}
