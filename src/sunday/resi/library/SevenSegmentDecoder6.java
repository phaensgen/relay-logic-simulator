package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a decoder which converts decimal input signals from 0..5 into 7-segment output signals. This decoder is
 * basically equivalent with the other 7-segment-decoders, but has only a reduced number of inputs and needs fewer
 * parts. It is useful for clocks.
 * 
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDecoder6 extends Component
{
    private Input powerIn;

    private Input in0;

    private Input in1;

    private Input in2;

    private Input in3;

    private Input in4;

    private Input in5;

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
    public SevenSegmentDecoder6(Circuit circuit, String name)
    {
        super(circuit, name);

        // external connectors
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

        // internal elements
        Relay r0 = new Relay(circuit, name + "_R0");
        Relay r1 = new Relay(circuit, name + "_R1");
        Relay r2 = new Relay(circuit, name + "_R2");
        Relay r3 = new Relay(circuit, name + "_R3");
        Relay r4 = new Relay(circuit, name + "_R4");
        Relay r5 = new Relay(circuit, name + "_R5");

        // internal wirings
        // connect the middle contact for all used relay switches with power
        new Signal(circuit).from(powerIn)
            .to(r0.getMiddleIn(0), r0.getMiddleIn(1), r0.getMiddleIn(2), r0.getMiddleIn(3), r0.getMiddleIn(4),
                r0.getMiddleIn(5))
            .to(r1.getMiddleIn(0), r1.getMiddleIn(1))
            .to(r2.getMiddleIn(0), r2.getMiddleIn(1), r2.getMiddleIn(2), r2.getMiddleIn(3), r2.getMiddleIn(4))
            .to(r3.getMiddleIn(0), r3.getMiddleIn(1), r3.getMiddleIn(2), r3.getMiddleIn(3), r3.getMiddleIn(4))
            .to(r4.getMiddleIn(0), r4.getMiddleIn(1), r4.getMiddleIn(2), r4.getMiddleIn(3))
            .to(r5.getMiddleIn(0), r5.getMiddleIn(1), r5.getMiddleIn(2), r5.getMiddleIn(3), r5.getMiddleIn(4));

        // connect inputs with their coils
        new Signal(circuit).from(in0).to(r0.getCoilIn());
        new Signal(circuit).from(in1).to(r1.getCoilIn());
        new Signal(circuit).from(in2).to(r2.getCoilIn());
        new Signal(circuit).from(in3).to(r3.getCoilIn());
        new Signal(circuit).from(in4).to(r4.getCoilIn());
        new Signal(circuit).from(in5).to(r5.getCoilIn());

        // A
        Joint ja = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(0)).to(ja.getIn(0));
        new Signal(circuit).from(r2.getOut(0)).to(ja.getIn(1));
        new Signal(circuit).from(r3.getOut(0)).to(ja.getIn(2));
        new Signal(circuit).from(r5.getOut(0)).to(ja.getIn(3));
        new Signal(circuit).from(ja.getOut()).to(outA);

        // B
        Joint jb = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(1)).to(jb.getIn(0));
        new Signal(circuit).from(r1.getOut(0)).to(jb.getIn(1));
        new Signal(circuit).from(r2.getOut(1)).to(jb.getIn(2));
        new Signal(circuit).from(r3.getOut(1)).to(jb.getIn(3));
        new Signal(circuit).from(r4.getOut(0)).to(jb.getIn(4));
        new Signal(circuit).from(jb.getOut()).to(outB);

        // C
        Joint jc = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(2)).to(jc.getIn(0));
        new Signal(circuit).from(r1.getOut(1)).to(jc.getIn(1));
        new Signal(circuit).from(r3.getOut(2)).to(jc.getIn(2));
        new Signal(circuit).from(r4.getOut(1)).to(jc.getIn(3));
        new Signal(circuit).from(r5.getOut(1)).to(jc.getIn(4));
        new Signal(circuit).from(jc.getOut()).to(outC);

        // D
        Joint jd = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(3)).to(jd.getIn(0));
        new Signal(circuit).from(r2.getOut(2)).to(jd.getIn(1));
        new Signal(circuit).from(r3.getOut(3)).to(jd.getIn(2));
        new Signal(circuit).from(r5.getOut(2)).to(jd.getIn(3));
        new Signal(circuit).from(jd.getOut()).to(outD);

        // E
        Joint je = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(4)).to(je.getIn(0));
        new Signal(circuit).from(r2.getOut(3)).to(je.getIn(1));
        new Signal(circuit).from(je.getOut()).to(outE);

        // F
        Joint jf = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(5)).to(jf.getIn(0));
        new Signal(circuit).from(r4.getOut(2)).to(jf.getIn(1));
        new Signal(circuit).from(r5.getOut(3)).to(jf.getIn(2));
        new Signal(circuit).from(jf.getOut()).to(outF);

        // G
        Joint jg = new Joint(circuit);
        new Signal(circuit).from(r2.getOut(4)).to(jg.getIn(0));
        new Signal(circuit).from(r3.getOut(4)).to(jg.getIn(1));
        new Signal(circuit).from(r4.getOut(3)).to(jg.getIn(2));
        new Signal(circuit).from(r5.getOut(4)).to(jg.getIn(3));
        new Signal(circuit).from(jg.getOut()).to(outG);
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
