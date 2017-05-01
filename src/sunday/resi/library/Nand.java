package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * A logical NAND gate built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class Nand extends Component
{
    private final Input powerIn;

    private final Input[] ins;

    private final Output _out;

    /**
     * The constructor.
     * 
     * @param n number of inputs
     */
    public Nand(Circuit parent, String name, int n)
    {
        super(parent, name);

        powerIn = new Input();

        ins = new Input[n];

        Circuit local = getLocalCircuit();

        // first create relays, then the joint to keep them in the right processing order
        Relay[] relays = new Relay[n];
        for (int i = 0; i < n; i++)
        {
            Input in = ins[i] = new Input();
            Relay r = relays[i] = new Relay(local, name + "_R" + String.valueOf(i));

            new Signal(local).from(in).to(r.getCoilIn());
            new Signal(local).from(powerIn).to(r.getMiddleIn(0));
        }

        Joint joint = new Joint(local, name + "_J");

        _out = new Output();

        // internal wiring
        for (int i = 0; i < n; i++)
        {
            Relay r = relays[i];

            new Signal(local).from(r.get_Out(0)).to(joint.getIn(i));
        }

        new Signal(local).from(joint.getOut()).to(_out);
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
        sb.append("[NAND ");
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
