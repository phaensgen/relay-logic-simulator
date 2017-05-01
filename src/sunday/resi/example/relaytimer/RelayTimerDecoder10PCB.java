package sunday.resi.example.relaytimer;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDecoder10;

/**
 * Represents a PCB for the relay timer containing the 7-segment-decoder for the full value range 0..9. The timer needs
 * one such PCBs.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerDecoder10PCB extends Component
{
    private final Input powerIn;

    private final Input in0;

    private final Input in1;

    private final Input in2;

    private final Input in3;

    private final Input in4;

    private final Input in5;

    private final Input in6;

    private final Input in7;

    private final Input in8;

    private final Input in9;

    private final Output outA;

    private final Output outB;

    private final Output outC;

    private final Output outD;

    private final Output outE;

    private final Output outF;

    private final Output outG;

    /**
     * The constructor.
     */
    public RelayTimerDecoder10PCB(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();

        in0 = new Input();
        in1 = new Input();
        in2 = new Input();
        in3 = new Input();
        in4 = new Input();
        in5 = new Input();
        in6 = new Input();
        in7 = new Input();
        in8 = new Input();
        in9 = new Input();

        outA = new Output();
        outB = new Output();
        outC = new Output();
        outD = new Output();
        outE = new Output();
        outF = new Output();
        outG = new Output();

        Circuit local = getLocalCircuit();

        SevenSegmentDecoder10 decoder = new SevenSegmentDecoder10(local, name + "_Decoder");

        new Signal(local).from(powerIn).to(decoder.getPowerIn());

        new Signal(local).from(in0).to(decoder.getIn0());
        new Signal(local).from(in1).to(decoder.getIn1());
        new Signal(local).from(in2).to(decoder.getIn2());
        new Signal(local).from(in3).to(decoder.getIn3());
        new Signal(local).from(in4).to(decoder.getIn4());
        new Signal(local).from(in5).to(decoder.getIn5());
        new Signal(local).from(in6).to(decoder.getIn6());
        new Signal(local).from(in7).to(decoder.getIn7());
        new Signal(local).from(in8).to(decoder.getIn8());
        new Signal(local).from(in9).to(decoder.getIn9());

        new Signal(local).from(decoder.getOutA()).to(outA);
        new Signal(local).from(decoder.getOutB()).to(outB);
        new Signal(local).from(decoder.getOutC()).to(outC);
        new Signal(local).from(decoder.getOutD()).to(outD);
        new Signal(local).from(decoder.getOutE()).to(outE);
        new Signal(local).from(decoder.getOutF()).to(outF);
        new Signal(local).from(decoder.getOutG()).to(outG);
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

    public Input getIn2()
    {
        return in2;
    }

    public Input getIn3()
    {
        return in3;
    }

    public Input getIn4()
    {
        return in4;
    }

    public Input getIn5()
    {
        return in5;
    }

    public Input getIn6()
    {
        return in6;
    }

    public Input getIn7()
    {
        return in7;
    }

    public Input getIn8()
    {
        return in8;
    }

    public Input getIn9()
    {
        return in9;
    }

    public Output getOutA()
    {
        return outA;
    }

    public Output getOutB()
    {
        return outB;
    }

    public Output getOutC()
    {
        return outC;
    }

    public Output getOutD()
    {
        return outD;
    }

    public Output getOutE()
    {
        return outE;
    }

    public Output getOutF()
    {
        return outF;
    }

    public Output getOutG()
    {
        return outG;
    }
}
