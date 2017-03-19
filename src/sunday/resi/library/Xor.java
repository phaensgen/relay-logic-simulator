package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * A logical XOR gate built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class Xor extends Component
{
    private final Input powerIn;

    private final Input in0;

    private final Input in1;

    private final Output out;

    /**
     * The constructor.
     */
    public Xor(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        in0 = new Input();
        in1 = new Input();
        out = new Output();

        Relay r0 = new Relay(circuit, name + "_R0");
        Switch s0 = r0.getSwitch(0);

        Relay r1 = new Relay(circuit, name + "_R1");
        Swotch s1 = r1.getSwotch(0);

        // internal wiring
        new Signal(circuit).from(powerIn).to(s0.getMiddleIn());

        new Signal(circuit).from(in0).to(r0.getCoilIn());
        new Signal(circuit).from(in1).to(r1.getCoilIn());

        new Signal(circuit).from(s0.get_Out()).to(s1.getIn());
        new Signal(circuit).from(s0.getOut()).to(s1.get_In());

        new Signal(circuit).from(s1.getMiddleOut()).to(out);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getIn0()
    {
        return in0;
    }

    public Input getIn1()
    {
        return in1;
    }

    public Output getOut()
    {
        return out;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[XOR ");
        sb.append(getName());
        sb.append(": ");

        sb.append("in0=");
        sb.append(String.valueOf(in0));
        sb.append(", ");

        sb.append("in1=");
        sb.append(String.valueOf(in1));
        sb.append(", ");

        sb.append("out=");
        sb.append(String.valueOf(out));
        sb.append("]");

        return sb.toString();
    }
}
