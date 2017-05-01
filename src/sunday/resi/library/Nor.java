package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * A logical NOR gate built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class Nor extends Component
{
    private final Input powerIn;

    private final Input[] ins;

    private final Output _out;

    /**
     * The constructor.
     * 
     * @param n number of inputs
     */
    public Nor(Circuit parent, String name, int n)
    {
        super(parent, name);

        powerIn = new Input();

        ins = new Input[n];

        Circuit local = getLocalCircuit();

        Relay[] relays = new Relay[n];
        for (int i = 0; i < n; i++)
        {
            ins[i] = new Input();

            Relay r = new Relay(local, name + "_R" + String.valueOf(i));
            relays[i] = r;

            // internal wiring
            new Signal(local).from(ins[i]).to(r.getCoilIn());

            if (i == 0)
            {
                new Signal(local).from(powerIn).to(r.getMiddleIn(0));
            }
            else
            {
                new Signal(local).from(relays[i - 1].get_Out(0)).to(r.getMiddleIn(0));
            }
        }

        _out = new Output();

        new Signal(local).from(relays[n - 1].get_Out(0)).to(_out);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getIn(int index)
    {
        return ins[index];
    }

    public Output get_Out()
    {
        return _out;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[NOR ");
        sb.append(getName());
        sb.append(": ");

        for (int i = 0; i < ins.length; i++)
        {
            Input in = ins[i];

            sb.append("in");
            sb.append(String.valueOf(i));
            sb.append("=");
            sb.append(String.valueOf(in));
            sb.append(", ");
        }

        sb.append("_out=");
        sb.append(String.valueOf(_out));
        sb.append("]");

        return sb.toString();
    }
}
