package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a flip flop made out of relays. The outputs are connected differently to the other Flip Flop, for easier use
 * in a countdown.
 * 
 * @author Peter H&auml;nsgen
 */
public class FlipFlopR extends Component
{
    private Input powerIn;

    private Input _clock;

    private Input clock;

    private Output _out;

    private Output out;

    /**
     * The constructor.
     */
    public FlipFlopR(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        _clock = new Input();
        clock = new Input();

        _out = new Output();
        out = new Output();

        // create elements in an order that the simulation takes as less cycles as possible
        Relay ra = new Relay(circuit, name + "_RA");
        Switch sa0 = ra.getSwitch(0);
        Switch sa1 = ra.getSwitch(1);

        Relay rb = new Relay(circuit, name + "_RB");
        Switch sb0 = rb.getSwitch(0);
        Switch sb1 = rb.getSwitch(1);

        Relay rc = new Relay(circuit, name + "_RC");
        Switch sc0 = rc.getSwitch(0);

        Relay rd = new Relay(circuit, name + "_RD");
        Switch sd0 = rd.getSwitch(0);

        Joint ja = new Joint(circuit, name + "_JA");
        Joint jb = new Joint(circuit, name + "_JB");

        // internal wirings
        new Signal(circuit).from(powerIn).to(sa0.getMiddleIn(), sb0.getMiddleIn());
        new Signal(circuit).from(_clock).to(sa1.getMiddleIn());
        new Signal(circuit).from(clock).to(sb1.getMiddleIn());
        new Signal(circuit).from(sa0.getOut()).to(sc0.getMiddleIn()).to(out);
        new Signal(circuit).from(sa0.get_Out()).to(_out);
        new Signal(circuit).from(sb0.getOut()).to(sd0.getMiddleIn());

        new Signal(circuit).from(sb1.get_Out()).to(ja.getIn(0));
        new Signal(circuit).from(sc0.get_Out()).to(ja.getIn(1));

        new Signal(circuit).from(ja.getOut()).to(ra.getCoilIn());
        new Signal(circuit).from(sa1.get_Out()).to(rd.getCoilIn());

        new Signal(circuit).from(sd0.get_Out()).to(jb.getIn(1));
        new Signal(circuit).from(sa1.getOut()).to(jb.getIn(0));

        new Signal(circuit).from(jb.getOut()).to(rb.getCoilIn());
        new Signal(circuit).from(sb1.getOut()).to(rc.getCoilIn());
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
        sb.append("[FlipFlopR ");
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
