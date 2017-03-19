package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * A logical AND gate built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class And extends Component
{
    private Input powerIn;

    private Input[] ins;

    private Output out;

    /**
     * The constructor.
     * 
     * @param n number of inputs
     */
    public And(Circuit circuit, String name, int n)
    {
        super(circuit, name);

        powerIn = new Input();

        ins = new Input[n];

        Relay[] relays = new Relay[n];
        for (int i = 0; i < n; i++)
        {
            ins[i] = new Input();

            Relay r = new Relay(circuit, name + "_R" + String.valueOf(i));
            relays[i] = r;

            // internal wiring
            new Signal(circuit).from(ins[i]).to(r.getCoilIn());

            if (i == 0)
            {
                new Signal(circuit).from(powerIn).to(r.getMiddleIn(0));
            }
            else
            {
                new Signal(circuit).from(relays[i - 1].getOut(0)).to(r.getMiddleIn(0));
            }
        }

        out = new Output();

        new Signal(circuit).from(relays[n - 1].getOut(0)).to(out);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getIn(int index)
    {
        return ins[index];
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[AND ");
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

        sb.append("out=");
        sb.append(String.valueOf(out));
        sb.append("]");

        return sb.toString();
    }
}
