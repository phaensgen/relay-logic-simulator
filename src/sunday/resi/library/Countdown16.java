package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a 4-bit backward counter based on flip flops.
 * 
 * @author Peter H&auml;nsgen
 */
public class Countdown16 extends Component
{
    private final Input powerIn;

    private final Input _clock;

    private final Input clock;

    private final Output _out0;

    private final Output out0;

    private final Output _out1;

    private final Output out1;

    private final Output _out2;

    private final Output out2;

    private final Output _out3;

    private final Output out3;

    /**
     * The constructor.
     */
    public Countdown16(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();
        _clock = new Input();
        clock = new Input();

        _out0 = new Output();
        out0 = new Output();
        _out1 = new Output();
        out1 = new Output();
        _out2 = new Output();
        out2 = new Output();
        _out3 = new Output();
        out3 = new Output();

        Circuit local = getLocalCircuit();

        FlipFlopR f0 = new FlipFlopR(local, name + "_FF0");
        FlipFlopR f1 = new FlipFlopR(local, name + "_FF1");
        FlipFlopR f2 = new FlipFlopR(local, name + "_FF2");
        FlipFlopR f3 = new FlipFlopR(local, name + "_FF3");

        // internal wiring
        new Signal(local).from(powerIn).to(f0.getPowerIn(), f1.getPowerIn(), f2.getPowerIn(), f3.getPowerIn());

        // connect the flip flops with each other for form a 4-bit counter
        new Signal(local).from(_clock).to(f0.get_Clock());
        new Signal(local).from(clock).to(f0.getClock());

        // inverse external outputs, otherwise it would count backwards
        new Signal(local).from(f0.getOut()).to(out0).to(f1.getClock());
        new Signal(local).from(f1.getOut()).to(out1).to(f2.getClock());
        new Signal(local).from(f2.getOut()).to(out2).to(f3.getClock());
        new Signal(local).from(f3.getOut()).to(out3);

        new Signal(local).from(f0.get_Out()).to(_out0).to(f1.get_Clock());
        new Signal(local).from(f1.get_Out()).to(_out1).to(f2.get_Clock());
        new Signal(local).from(f2.get_Out()).to(_out2).to(f3.get_Clock());
        new Signal(local).from(f3.get_Out()).to(_out3);
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

    public Output get_Out0()
    {
        return _out0;
    }

    public Output getOut0()
    {
        return out0;
    }

    public Output get_Out1()
    {
        return _out1;
    }

    public Output getOut1()
    {
        return out1;
    }

    public Output get_Out2()
    {
        return _out2;
    }

    public Output getOut2()
    {
        return out2;
    }

    public Output get_Out3()
    {
        return _out3;
    }

    public Output getOut3()
    {
        return out3;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[Countdown ");
        sb.append(getName());
        sb.append(": _clock=");
        sb.append(String.valueOf(_clock));
        sb.append(", clock=");
        sb.append(String.valueOf(clock));
        sb.append(", _out0=");
        sb.append(String.valueOf(_out0));
        sb.append(", out0=");
        sb.append(String.valueOf(out0));
        sb.append(", _out1=");
        sb.append(String.valueOf(_out1));
        sb.append(", out1=");
        sb.append(String.valueOf(out1));
        sb.append(", _out2=");
        sb.append(String.valueOf(_out2));
        sb.append(", out2=");
        sb.append(String.valueOf(out2));
        sb.append(", _out3=");
        sb.append(String.valueOf(_out3));
        sb.append(", out3=");
        sb.append(String.valueOf(out3));
        sb.append("]");

        return sb.toString();
    }
}
