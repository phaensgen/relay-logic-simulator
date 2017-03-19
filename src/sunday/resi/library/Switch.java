package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;

/**
 * This is a switch where the middle contact represents an input whose value is forwarded to one of the two outputs.
 * 
 * @author Peter H&auml;nsgen
 */
public class Switch extends AbstractSwitch
{
    private final Input middleIn;

    private final Output _out;

    private final Output out;

    /**
     * The constructor.
     */
    public Switch(Circuit circuit, String name)
    {
        super(circuit, name);

        middleIn = new Input();
        _out = new Output();
        out = new Output();
    }

    public Input getMiddleIn()
    {
        return middleIn;
    }

    public Output get_Out()
    {
        return _out;
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public void simulate()
    {
        if (isPushed())
        {
            _out.setValue(null);
            out.setValue(middleIn.getValue());
        }
        else
        {
            _out.setValue(middleIn.getValue());
            out.setValue(null);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Switch ");
        sb.append(getName());
        sb.append(": ");
        sb.append("pushed=");
        sb.append(String.valueOf(isPushed()));
        sb.append(", middleIn=");
        sb.append(String.valueOf(middleIn));
        sb.append(", _out=");
        sb.append(String.valueOf(_out));
        sb.append(", out=");
        sb.append(String.valueOf(out));
        sb.append("]");
        return sb.toString();
    }
}
