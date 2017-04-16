package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.BCDDecoder10;
import sunday.resi.library.Counter4;
import sunday.resi.library.Relay;

/**
 * Represents a PCB for the relay clock that contains a counter, a BCD decoder and the set / reset logic for a digit.
 * The counter counts up to 2.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockCounter2PCB extends Component
{
    private Input powerIn;

    private Input clockIn;

    private Input setIn;

    private Input reset24In;

    private Output out0;

    private Output out1;

    private Output out2;

    /**
     * The constructor.
     */
    public RelayClockCounter2PCB(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        clockIn = new Input();
        setIn = new Input();
        reset24In = new Input();

        out0 = new Output();
        out1 = new Output();
        out2 = new Output();

        Counter4 counter = new Counter4(circuit, name + "_Counter");

        BCDDecoder10 bcd = new BCDDecoder10(circuit, name + "_BCD");

        Relay clock = new Relay(circuit, name + "_Clock");
        Relay reset24 = new Relay(circuit, name + "_Reset24");

        Joint j = new Joint(circuit, name + "_JC");

        new Signal(circuit).from(powerIn).to(bcd.getPowerIn(), clock.getMiddleIn(0), reset24.getMiddleIn(0));

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());

        new Signal(circuit).from(bcd.getOut0()).to(out0);
        new Signal(circuit).from(bcd.getOut1()).to(out1);
        new Signal(circuit).from(bcd.getOut2()).to(out2);

        new Signal(circuit).from(clockIn).to(j.getIn(0));
        new Signal(circuit).from(setIn).to(j.getIn(1));
        new Signal(circuit).from(j.getOut()).to(clock.getCoilIn());
        new Signal(circuit).from(clock.get_Out(0)).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut(0)).to(counter.getClock());

        new Signal(circuit).from(reset24In).to(reset24.getCoilIn());
        new Signal(circuit).from(reset24.get_Out(0)).to(counter.getPowerIn());
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public Input getClockIn()
    {
        return clockIn;
    }

    public Input getSetIn()
    {
        return setIn;
    }

    public Input getReset24In()
    {
        return reset24In;
    }

    public Output getOut0()
    {
        return out0;
    }

    public Output getOut1()
    {
        return out1;
    }

    public Output getOut2()
    {
        return out2;
    }
}
