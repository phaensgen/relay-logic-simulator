package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a decoder which converts decimal input signals from 0..2 into 7-segment output signals. This decoder is
 * basically equivalent with the other 7-segment-decoders, but has only a reduced number of inputs and needs fewer
 * parts. It is useful for clocks.
 * 
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDecoder3 extends Component
{
    private final Input powerIn;

    private final Input in0;

    private final Input in1;

    private final Input in2;

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
    public SevenSegmentDecoder3(Circuit parent, String name)
    {
        super(parent, name);

        // external connectors
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

        Circuit local = getLocalCircuit();

        // internal elements
        Relay r0 = new Relay(local, name + "_R0");
        Relay r1 = new Relay(local, name + "_R1");
        Relay r2 = new Relay(local, name + "_R2");

        // internal wirings
        // connect the middle contact for all used relay switches with power
        new Signal(local).from(powerIn)
            .to(r0.getMiddleIn(0), r0.getMiddleIn(1), r0.getMiddleIn(2), r0.getMiddleIn(3), r0.getMiddleIn(4),
                r0.getMiddleIn(5))
            .to(r1.getMiddleIn(0), r1.getMiddleIn(1))
            .to(r2.getMiddleIn(0), r2.getMiddleIn(1), r2.getMiddleIn(2), r2.getMiddleIn(3), r2.getMiddleIn(4));

        // connect inputs with their coils
        new Signal(local).from(in0).to(r0.getCoilIn());
        new Signal(local).from(in1).to(r1.getCoilIn());
        new Signal(local).from(in2).to(r2.getCoilIn());

        // A
        Joint ja = new Joint(local);
        new Signal(local).from(r0.getOut(0)).to(ja.getIn(0));
        new Signal(local).from(r2.getOut(0)).to(ja.getIn(1));
        new Signal(local).from(ja.getOut()).to(outA);

        // B
        Joint jb = new Joint(local);
        new Signal(local).from(r0.getOut(1)).to(jb.getIn(0));
        new Signal(local).from(r1.getOut(0)).to(jb.getIn(1));
        new Signal(local).from(r2.getOut(1)).to(jb.getIn(2));
        new Signal(local).from(jb.getOut()).to(outB);

        // C
        Joint jc = new Joint(local);
        new Signal(local).from(r0.getOut(2)).to(jc.getIn(0));
        new Signal(local).from(r1.getOut(1)).to(jc.getIn(1));
        new Signal(local).from(jc.getOut()).to(outC);

        // D
        Joint jd = new Joint(local);
        new Signal(local).from(r0.getOut(3)).to(jd.getIn(0));
        new Signal(local).from(r2.getOut(2)).to(jd.getIn(1));
        new Signal(local).from(jd.getOut()).to(outD);

        // E
        Joint je = new Joint(local);
        new Signal(local).from(r0.getOut(4)).to(je.getIn(0));
        new Signal(local).from(r2.getOut(3)).to(je.getIn(1));
        new Signal(local).from(je.getOut()).to(outE);

        // F
        Joint jf = new Joint(local);
        new Signal(local).from(r0.getOut(5)).to(jf.getIn(0));
        new Signal(local).from(jf.getOut()).to(outF);

        // G
        Joint jg = new Joint(local);
        new Signal(local).from(r2.getOut(4)).to(jg.getIn(0));
        new Signal(local).from(jg.getOut()).to(outG);
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
