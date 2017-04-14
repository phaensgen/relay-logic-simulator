package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.BCDToDecimalDecoder;
import sunday.resi.library.Counter;
import sunday.resi.library.Relay;

/**
 * Represents a PCB for the relay clock that contains a counter, a BCD decoder and the set / reset logic for a digit.
 * The counter counts up to 5.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockCounter5PCB extends Component
{
    private Input powerIn;

    private Input clockIn;

    private Input setIn;

    private Input reset24In;

    private Output out0;

    private Output out1;

    private Output out2;

    private Output out3;

    private Output out4;

    private Output out5;

    private Output carryOut;

    /**
     * The constructor.
     */
    public RelayClockCounter5PCB(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();
        clockIn = new Input();
        setIn = new Input();
        reset24In = new Input();

        out0 = new Output();
        out1 = new Output();
        out2 = new Output();
        out3 = new Output();
        out4 = new Output();
        out5 = new Output();
        carryOut = new Output();

        Counter counter = new Counter(circuit, name + "_Counter");

        BCDToDecimalDecoder bcd = new BCDToDecimalDecoder(circuit, name + "_BCD");

        Relay clock = new Relay(circuit, name + "_Clock");
        Relay reset24 = new Relay(circuit, name + "_Reset24");
        Relay carry = new Relay(circuit, name + "_Carry");

        Joint j = new Joint(circuit, name + "_JC");

        new Signal(circuit).from(powerIn).to(bcd.getPowerIn(), clock.getMiddleIn(0), reset24.getMiddleIn(0),
            carry.getMiddleIn(1));

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());
        new Signal(circuit).from(counter.getOut3()).to(bcd.getIn3());

        new Signal(circuit).from(bcd.getOut0()).to(out0);
        new Signal(circuit).from(bcd.getOut1()).to(out1);
        new Signal(circuit).from(bcd.getOut2()).to(out2);
        new Signal(circuit).from(bcd.getOut3()).to(out3);
        new Signal(circuit).from(bcd.getOut4()).to(out4);
        new Signal(circuit).from(bcd.getOut5()).to(out5);
        new Signal(circuit).from(bcd.getOut6()).to(carry.getCoilIn());

        new Signal(circuit).from(clockIn).to(j.getIn(0));
        new Signal(circuit).from(setIn).to(j.getIn(1));
        new Signal(circuit).from(j.getOut()).to(clock.getCoilIn());
        new Signal(circuit).from(clock.get_Out(0)).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut(0)).to(counter.getClock());

        new Signal(circuit).from(reset24In).to(reset24.getCoilIn());
        new Signal(circuit).from(reset24.get_Out(0)).to(carry.getMiddleIn(0));

        new Signal(circuit).from(carry.get_Out(0)).to(counter.getPowerIn());
        new Signal(circuit).from(carry.getOut(1)).to(carryOut);
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

    public Output getOut3()
    {
        return out3;
    }

    public Output getOut4()
    {
        return out4;
    }

    public Output getOut5()
    {
        return out5;
    }

    public Output getCarryOut()
    {
        return carryOut;
    }
}
