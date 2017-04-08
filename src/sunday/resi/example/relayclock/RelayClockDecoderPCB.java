package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDecoder10;

/**
 * Represents a PCB for the relay clock containing the 7-segment-decoder. The clock needs six such PCBs.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockDecoderPCB extends Component
{
    private Input powerIn;

    private Input in0;

    private Input in1;

    private Input in2;

    private Input in3;

    private Input in4;

    private Input in5;

    private Input in6;

    private Input in7;

    private Input in8;

    private Input in9;

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
    public RelayClockDecoderPCB(Circuit circuit, String name)
    {
        super(circuit, name);

        SevenSegmentDecoder10 decoder = new SevenSegmentDecoder10(circuit, name + "_Decoder");

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

        new Signal(circuit).from(powerIn).to(decoder.getPowerIn());

        new Signal(circuit).from(in0).to(decoder.getIn0());
        new Signal(circuit).from(in1).to(decoder.getIn1());
        new Signal(circuit).from(in2).to(decoder.getIn2());
        new Signal(circuit).from(in3).to(decoder.getIn3());
        new Signal(circuit).from(in4).to(decoder.getIn4());
        new Signal(circuit).from(in5).to(decoder.getIn5());
        new Signal(circuit).from(in6).to(decoder.getIn6());
        new Signal(circuit).from(in7).to(decoder.getIn7());
        new Signal(circuit).from(in8).to(decoder.getIn8());
        new Signal(circuit).from(in9).to(decoder.getIn9());

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
