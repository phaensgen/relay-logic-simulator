package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * This is a decoder which converts decimal input signals into 7-segment output signals.
 * 
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDecoder10 extends Component
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
    public SevenSegmentDecoder10(Circuit parent, String name)
    {
        super(parent, name);

        // external connectors
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

        // internal elements
        Relay r0 = new Relay(local, name + "_R0");
        Relay r1 = new Relay(local, name + "_R1");
        Relay r2 = new Relay(local, name + "_R2");
        Relay r3 = new Relay(local, name + "_R3");
        Relay r4 = new Relay(local, name + "_R4");
        Relay r5 = new Relay(local, name + "_R5");
        Relay r6 = new Relay(local, name + "_R6");
        Relay r7 = new Relay(local, name + "_R7");
        Relay r8 = new Relay(local, name + "_R8");
        Relay r9 = new Relay(local, name + "_R9");

        // internal wirings
        // connect the middle contact for all used relay switches with power
        new Signal(local).from(powerIn)
            .to(r0.getMiddleIn(0), r0.getMiddleIn(1), r0.getMiddleIn(2), r0.getMiddleIn(3), r0.getMiddleIn(4),
                r0.getMiddleIn(5))
            .to(r1.getMiddleIn(0), r1.getMiddleIn(1))
            .to(r2.getMiddleIn(0), r2.getMiddleIn(1), r2.getMiddleIn(2), r2.getMiddleIn(3), r2.getMiddleIn(4))
            .to(r3.getMiddleIn(0), r3.getMiddleIn(1), r3.getMiddleIn(2), r3.getMiddleIn(3), r3.getMiddleIn(4))
            .to(r4.getMiddleIn(0), r4.getMiddleIn(1), r4.getMiddleIn(2), r4.getMiddleIn(3))
            .to(r5.getMiddleIn(0), r5.getMiddleIn(1), r5.getMiddleIn(2), r5.getMiddleIn(3), r5.getMiddleIn(4))
            .to(r6.getMiddleIn(0), r6.getMiddleIn(1), r6.getMiddleIn(2), r6.getMiddleIn(3), r6.getMiddleIn(4),
                r6.getMiddleIn(5))
            .to(r7.getMiddleIn(0), r7.getMiddleIn(1), r7.getMiddleIn(2))
            .to(r8.getMiddleIn(0), r8.getMiddleIn(1), r8.getMiddleIn(2), r8.getMiddleIn(3), r8.getMiddleIn(4),
                r8.getMiddleIn(5), r8.getMiddleIn(6))
            .to(r9.getMiddleIn(0), r9.getMiddleIn(1), r9.getMiddleIn(2), r9.getMiddleIn(3), r9.getMiddleIn(4),
                r9.getMiddleIn(5));

        // connect inputs with their coils
        new Signal(local).from(in0).to(r0.getCoilIn());
        new Signal(local).from(in1).to(r1.getCoilIn());
        new Signal(local).from(in2).to(r2.getCoilIn());
        new Signal(local).from(in3).to(r3.getCoilIn());
        new Signal(local).from(in4).to(r4.getCoilIn());
        new Signal(local).from(in5).to(r5.getCoilIn());
        new Signal(local).from(in6).to(r6.getCoilIn());
        new Signal(local).from(in7).to(r7.getCoilIn());
        new Signal(local).from(in8).to(r8.getCoilIn());
        new Signal(local).from(in9).to(r9.getCoilIn());

        // A
        Joint ja = new Joint(local);
        new Signal(local).from(r0.getOut(0)).to(ja.getIn(0));
        new Signal(local).from(r2.getOut(0)).to(ja.getIn(1));
        new Signal(local).from(r3.getOut(0)).to(ja.getIn(2));
        new Signal(local).from(r5.getOut(0)).to(ja.getIn(3));
        new Signal(local).from(r6.getOut(0)).to(ja.getIn(4));
        new Signal(local).from(r7.getOut(0)).to(ja.getIn(5));
        new Signal(local).from(r8.getOut(0)).to(ja.getIn(6));
        new Signal(local).from(r9.getOut(0)).to(ja.getIn(7));
        new Signal(local).from(ja.getOut()).to(outA);

        // B
        Joint jb = new Joint(local);
        new Signal(local).from(r0.getOut(1)).to(jb.getIn(0));
        new Signal(local).from(r1.getOut(0)).to(jb.getIn(1));
        new Signal(local).from(r2.getOut(1)).to(jb.getIn(2));
        new Signal(local).from(r3.getOut(1)).to(jb.getIn(3));
        new Signal(local).from(r4.getOut(0)).to(jb.getIn(4));
        new Signal(local).from(r7.getOut(1)).to(jb.getIn(5));
        new Signal(local).from(r8.getOut(1)).to(jb.getIn(6));
        new Signal(local).from(r9.getOut(1)).to(jb.getIn(7));
        new Signal(local).from(jb.getOut()).to(outB);

        // C
        Joint jc = new Joint(local);
        new Signal(local).from(r0.getOut(2)).to(jc.getIn(0));
        new Signal(local).from(r1.getOut(1)).to(jc.getIn(1));
        new Signal(local).from(r3.getOut(2)).to(jc.getIn(2));
        new Signal(local).from(r4.getOut(1)).to(jc.getIn(3));
        new Signal(local).from(r5.getOut(1)).to(jc.getIn(4));
        new Signal(local).from(r6.getOut(1)).to(jc.getIn(5));
        new Signal(local).from(r7.getOut(2)).to(jc.getIn(6));
        new Signal(local).from(r8.getOut(2)).to(jc.getIn(7));
        new Signal(local).from(r9.getOut(2)).to(jc.getIn(8));
        new Signal(local).from(jc.getOut()).to(outC);

        // D
        Joint jd = new Joint(local);
        new Signal(local).from(r0.getOut(3)).to(jd.getIn(0));
        new Signal(local).from(r2.getOut(2)).to(jd.getIn(1));
        new Signal(local).from(r3.getOut(3)).to(jd.getIn(2));
        new Signal(local).from(r5.getOut(2)).to(jd.getIn(3));
        new Signal(local).from(r6.getOut(2)).to(jd.getIn(4));
        new Signal(local).from(r8.getOut(3)).to(jd.getIn(5));
        new Signal(local).from(r9.getOut(3)).to(jd.getIn(6));
        new Signal(local).from(jd.getOut()).to(outD);

        // E
        Joint je = new Joint(local);
        new Signal(local).from(r0.getOut(4)).to(je.getIn(0));
        new Signal(local).from(r2.getOut(3)).to(je.getIn(1));
        new Signal(local).from(r6.getOut(3)).to(je.getIn(2));
        new Signal(local).from(r8.getOut(4)).to(je.getIn(3));
        new Signal(local).from(je.getOut()).to(outE);

        // F
        Joint jf = new Joint(local);
        new Signal(local).from(r0.getOut(5)).to(jf.getIn(0));
        new Signal(local).from(r4.getOut(2)).to(jf.getIn(1));
        new Signal(local).from(r5.getOut(3)).to(jf.getIn(2));
        new Signal(local).from(r6.getOut(4)).to(jf.getIn(3));
        new Signal(local).from(r8.getOut(5)).to(jf.getIn(4));
        new Signal(local).from(r9.getOut(4)).to(jf.getIn(5));
        new Signal(local).from(jf.getOut()).to(outF);

        // G
        Joint jg = new Joint(local);
        new Signal(local).from(r2.getOut(4)).to(jg.getIn(0));
        new Signal(local).from(r3.getOut(4)).to(jg.getIn(1));
        new Signal(local).from(r4.getOut(3)).to(jg.getIn(2));
        new Signal(local).from(r5.getOut(4)).to(jg.getIn(3));
        new Signal(local).from(r6.getOut(5)).to(jg.getIn(4));
        new Signal(local).from(r8.getOut(6)).to(jg.getIn(5));
        new Signal(local).from(r9.getOut(5)).to(jg.getIn(6));
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
