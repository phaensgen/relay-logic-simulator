package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a flip flop made out of relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class FlipFlop extends Component
{
    private Input powerIn;

    private Input _clock;

    private Input clock;

    private Output _out;

    private Output out;

    /**
     * The constructor.
     */
    public FlipFlop(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        _clock = new Input();
        clock = new Input();

        _out = new Output();
        out = new Output();

        Relay ra = new Relay(circuit, name + "_RA");
        Relay rb = new Relay(circuit, name + "_RB");
        Relay rc = new Relay(circuit, name + "_RC");
        Relay rd = new Relay(circuit, name + "_RD");

        Joint ja = new Joint(circuit, name + "_JA");
        Joint jb = new Joint(circuit, name + "_JB");

        // internal wirings
        new Signal(circuit).from(powerIn).to(ra.getMiddleIn(0), rb.getMiddleIn(0));
        new Signal(circuit).from(_clock).to(ra.getMiddleIn(1));
        new Signal(circuit).from(clock).to(rb.getMiddleIn(1));

        new Signal(circuit).from(ja.getOut()).to(ra.getCoilIn());
        // new Signal(circuit).from(ra.get_Out(0)).to(_out);
        new Signal(circuit).from(ra.get_Out(1)).to(rd.getCoilIn());
        new Signal(circuit).from(ra.getOut(1)).to(jb.getIn(0));
        new Signal(circuit).from(ra.getOut(0)).to(out).to(rc.getMiddleIn(0));

        new Signal(circuit).from(jb.getOut()).to(rb.getCoilIn());
        new Signal(circuit).from(rb.get_Out(0)).to(_out);
        new Signal(circuit).from(rb.getOut(0)).to(rd.getMiddleIn(0)).to(out);
        new Signal(circuit).from(rb.get_Out(1)).to(ja.getIn(0));
        new Signal(circuit).from(rb.getOut(1)).to(rc.getCoilIn());

        new Signal(circuit).from(rc.get_Out(0)).to(ja.getIn(1));

        new Signal(circuit).from(rd.get_Out(0)).to(jb.getIn(1));
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input get_Clock()
    {
        return _clock;
    }

    public Input getClock()
    {
        return clock;
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
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[FlipFlop ");
        sb.append(getName());
        sb.append(": _clock=");
        sb.append(String.valueOf(_clock));
        sb.append(", clock=");
        sb.append(String.valueOf(clock));
        sb.append(", _out=");
        sb.append(String.valueOf(_out));
        sb.append(", out=");
        sb.append(String.valueOf(out));
        sb.append("]");

        return sb.toString();
    }
}
