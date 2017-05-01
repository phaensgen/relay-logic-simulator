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
    private final Input powerIn;

    private final Input _clock;

    private final Input clock;

    private final Output _out;

    private final Output out;

    /**
     * The constructor.
     */
    public FlipFlop(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();
        _clock = new Input();
        clock = new Input();

        _out = new Output();
        out = new Output();

        Circuit local = getLocalCircuit();

        // create elements in an order that the simulation takes as less cycles as possible
        Relay ra = new Relay(local, name + "_RA");
        Switch sa0 = ra.getSwitch(0);
        Switch sa1 = ra.getSwitch(1);

        Relay rb = new Relay(local, name + "_RB");
        Switch sb0 = rb.getSwitch(0);
        Switch sb1 = rb.getSwitch(1);

        Relay rc = new Relay(local, name + "_RC");
        Switch sc0 = rc.getSwitch(0);

        Relay rd = new Relay(local, name + "_RD");
        Switch sd0 = rd.getSwitch(0);

        Joint ja = new Joint(local, name + "_JA");
        Joint jb = new Joint(local, name + "_JB");

        // internal wirings
        new Signal(local).from(powerIn).to(sa0.getMiddleIn(), sb0.getMiddleIn());
        new Signal(local).from(_clock).to(sa1.getMiddleIn());
        new Signal(local).from(clock).to(sb1.getMiddleIn());
        new Signal(local).from(sa0.getOut()).to(sc0.getMiddleIn());
        new Signal(local).from(sb0.getOut()).to(sd0.getMiddleIn()).to(out);

        new Signal(local).from(sb1.get_Out()).to(ja.getIn(0));
        new Signal(local).from(sc0.get_Out()).to(ja.getIn(1));

        new Signal(local).from(ja.getOut()).to(ra.getCoilIn());
        new Signal(local).from(sa1.get_Out()).to(rd.getCoilIn());

        new Signal(local).from(sd0.get_Out()).to(jb.getIn(1));
        new Signal(local).from(sa1.getOut()).to(jb.getIn(0));

        new Signal(local).from(jb.getOut()).to(rb.getCoilIn());
        new Signal(local).from(sb0.get_Out()).to(_out);
        new Signal(local).from(sb1.getOut()).to(rc.getCoilIn());
    }

    @Override
    public void simulate()
    {
        // use multiple cycles for consistent results
        for (int i = 0; i < 3; i++)
        {
            super.simulate();
        }
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
