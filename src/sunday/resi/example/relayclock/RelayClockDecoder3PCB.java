package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDecoder3;

/**
 * Represents a PCB for the relay clock containing the 7-segment-decoder for the reduced value range 0..2.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockDecoder3PCB extends Component
{
    private Input powerIn;

    private Input in0;

    private Input in1;

    private Input in2;

    private Output outA;

    private Output outB;

    private Output outC;

    private Output outD;

    private Output outE;

    private Output outF;

    private Output outG;

    /**
     * The constructor.
     */
    public RelayClockDecoder3PCB(Circuit circuit, String name)
    {
        super(circuit, name);

        SevenSegmentDecoder3 decoder = new SevenSegmentDecoder3(circuit, name + "_Decoder");

        powerIn = new Input();

        in0 = new Input();
        in1 = new Input();
        in2 = new Input();

        outA = new Output();
        outB = new Output();
        outC = new Output();
        outD = new Output();
        outE = new Output();
        outF = new Output();
        outG = new Output();

        new Signal(circuit).from(powerIn).to(decoder.getPowerIn());

        new Signal(circuit).from(in0).to(decoder.getIn0());
        new Signal(circuit).from(in1).to(decoder.getIn1());
        new Signal(circuit).from(in2).to(decoder.getIn2());

        new Signal(circuit).from(decoder.getOutA()).to(outA);
        new Signal(circuit).from(decoder.getOutB()).to(outB);
        new Signal(circuit).from(decoder.getOutC()).to(outC);
        new Signal(circuit).from(decoder.getOutD()).to(outD);
        new Signal(circuit).from(decoder.getOutE()).to(outE);
        new Signal(circuit).from(decoder.getOutF()).to(outF);
        new Signal(circuit).from(decoder.getOutG()).to(outG);
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
