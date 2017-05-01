package sunday.resi.example.relaytimer;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDecoder6;

/**
 * Represents a PCB for the relay timer containing the 7-segment-decoder for the reduced value range 0..5.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerDecoder6PCB extends Component
{
    private final Input powerIn;

    private final Input in0;

    private final Input in1;

    private final Input in2;

    private final Input in3;

    private final Input in4;

    private final Input in5;

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
    public RelayTimerDecoder6PCB(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();

        in0 = new Input();
        in1 = new Input();
        in2 = new Input();
        in3 = new Input();
        in4 = new Input();
        in5 = new Input();

        outA = new Output();
        outB = new Output();
        outC = new Output();
        outD = new Output();
        outE = new Output();
        outF = new Output();
        outG = new Output();

        Circuit local = getLocalCircuit();

        SevenSegmentDecoder6 decoder = new SevenSegmentDecoder6(local, name + "_Decoder");

        // TODO there is still space left on this PCB

        new Signal(local).from(powerIn).to(decoder.getPowerIn());

        new Signal(local).from(in0).to(decoder.getIn0());
        new Signal(local).from(in1).to(decoder.getIn1());
        new Signal(local).from(in2).to(decoder.getIn2());
        new Signal(local).from(in3).to(decoder.getIn3());
        new Signal(local).from(in4).to(decoder.getIn4());
        new Signal(local).from(in5).to(decoder.getIn5());

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
