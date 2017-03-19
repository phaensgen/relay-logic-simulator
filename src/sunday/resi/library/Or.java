package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * A logical OR gate built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class Or extends Component
{
    private Input powerIn;

    private Input[] ins;

    private Output out;

    /**
     * The constructor.
     * 
     * @param n number of inputs
     */
    public Or(Circuit circuit, String name, int n)
    {
        super(circuit, name);

        powerIn = new Input();

        ins = new Input[n];

        // first create relays, then the joint to keep them in the right processing order
        Relay[] relays = new Relay[n];
        for (int i = 0; i < n; i++)
        {
            Input in = ins[i] = new Input();
            Relay r = relays[i] = new Relay(circuit, name + "_R" + String.valueOf(i));

            new Signal(circuit).from(in).to(r.getCoilIn());
            new Signal(circuit).from(powerIn).to(r.getMiddleIn(0));
        }

        Joint joint = new Joint(circuit, name + "_J");

        out = new Output();

        // internal wiring
        for (int i = 0; i < n; i++)
        {
            Relay r = relays[i];

            new Signal(circuit).from(r.getOut(0)).to(joint.getIn(i));
        }

        new Signal(circuit).from(joint.getOut()).to(out);
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
        sb.append("[OR ");
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
