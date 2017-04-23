package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a 3-bit backward counter based on flip flops.
 * 
 * @author Peter H&auml;nsgen
 */
public class Countdown8 extends Component
{
    private Input powerIn;

    private Input _clock;

    private Input clock;

    private Output _out0;

    private Output out0;

    private Output _out1;

    private Output out1;

    private Output _out2;

    private Output out2;

    /**
     * The constructor.
     */
    public Countdown8(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        _clock = new Input();
        clock = new Input();

        _out0 = new Output();
        out0 = new Output();
        _out1 = new Output();
        out1 = new Output();
        _out2 = new Output();
        out2 = new Output();

        FlipFlopR f0 = new FlipFlopR(circuit, name + "_FF0");
        FlipFlopR f1 = new FlipFlopR(circuit, name + "_FF1");
        FlipFlopR f2 = new FlipFlopR(circuit, name + "_FF2");

        // internal wiring
        new Signal(circuit).from(powerIn).to(f0.getPowerIn(), f1.getPowerIn(), f2.getPowerIn());

        // connect the flip flops with each other for form a 4-bit counter
        new Signal(circuit).from(_clock).to(f0.get_Clock());
        new Signal(circuit).from(clock).to(f0.getClock());

        // inverse external outputs, otherwise it would count backwards
        new Signal(circuit).from(f0.getOut()).to(out0).to(f1.getClock());
        new Signal(circuit).from(f1.getOut()).to(out1).to(f2.getClock());
        new Signal(circuit).from(f2.getOut()).to(out2);

        new Signal(circuit).from(f0.get_Out()).to(_out0).to(f1.get_Clock());
        new Signal(circuit).from(f1.get_Out()).to(_out1).to(f2.get_Clock());
        new Signal(circuit).from(f2.get_Out()).to(_out2);
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
        sb.append("]");

        return sb.toString();
    }
}
