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
public class DecimalToSevenSegmentDecoder extends Component
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

    private Input inA;

    private Input inB;

    private Input inC;

    private Input inD;

    private Input inE;

    private Input inF;

    private Output a;

    private Output b;

    private Output c;

    private Output d;

    private Output e;

    private Output f;

    private Output g;

    /**
     * The constructor.
     */
    public DecimalToSevenSegmentDecoder(Circuit circuit, String name)
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
        in6 = new Input();
        in7 = new Input();
        in8 = new Input();
        in9 = new Input();
        inA = new Input();
        inB = new Input();
        inC = new Input();
        inD = new Input();
        inE = new Input();
        inF = new Input();

        a = new Output();
        b = new Output();
        c = new Output();
        d = new Output();
        e = new Output();
        f = new Output();
        g = new Output();

        // internal elements
        Relay r0 = new Relay(circuit, name + "_R0");
        Relay r1 = new Relay(circuit, name + "_R1");
        Relay r2 = new Relay(circuit, name + "_R2");
        Relay r3 = new Relay(circuit, name + "_R3");
        Relay r4 = new Relay(circuit, name + "_R4");
        Relay r5 = new Relay(circuit, name + "_R5");
        Relay r6 = new Relay(circuit, name + "_R6");
        Relay r7 = new Relay(circuit, name + "_R7");
        Relay r8 = new Relay(circuit, name + "_R8");
        Relay r9 = new Relay(circuit, name + "_R9");
        Relay ra = new Relay(circuit, name + "_RA");
        Relay rb = new Relay(circuit, name + "_RB");
        Relay rc = new Relay(circuit, name + "_RC");
        Relay rd = new Relay(circuit, name + "_RD");
        Relay re = new Relay(circuit, name + "_RE");
        Relay rf = new Relay(circuit, name + "_RF");

        // internal wirings
        // each relay needs up to 13 switches
        // connect the middle contact for all of them with power
        for (int i = 0; i < 13; i++)
        {
            new Signal(circuit).from(powerIn).to(r0.getMiddleIn(i), r1.getMiddleIn(i), r2.getMiddleIn(i),
                r3.getMiddleIn(i), r4.getMiddleIn(i), r5.getMiddleIn(i), r6.getMiddleIn(i), r7.getMiddleIn(i),
                r8.getMiddleIn(i), r9.getMiddleIn(i), ra.getMiddleIn(i), rb.getMiddleIn(i), rc.getMiddleIn(i),
                rd.getMiddleIn(i), re.getMiddleIn(i), rf.getMiddleIn(i));
        }

        // connect inputs with their coils
        new Signal(circuit).from(in0).to(r0.getCoilIn());
        new Signal(circuit).from(in1).to(r1.getCoilIn());
        new Signal(circuit).from(in2).to(r2.getCoilIn());
        new Signal(circuit).from(in3).to(r3.getCoilIn());
        new Signal(circuit).from(in4).to(r4.getCoilIn());
        new Signal(circuit).from(in5).to(r5.getCoilIn());
        new Signal(circuit).from(in6).to(r6.getCoilIn());
        new Signal(circuit).from(in7).to(r7.getCoilIn());
        new Signal(circuit).from(in8).to(r8.getCoilIn());
        new Signal(circuit).from(in9).to(r9.getCoilIn());
        new Signal(circuit).from(inA).to(ra.getCoilIn());
        new Signal(circuit).from(inB).to(rb.getCoilIn());
        new Signal(circuit).from(inC).to(rc.getCoilIn());
        new Signal(circuit).from(inD).to(rd.getCoilIn());
        new Signal(circuit).from(inE).to(re.getCoilIn());
        new Signal(circuit).from(inF).to(rf.getCoilIn());

        // A
        Joint ja = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(0)).to(ja.getIn(0));
        new Signal(circuit).from(r2.getOut(0)).to(ja.getIn(1));
        new Signal(circuit).from(r3.getOut(0)).to(ja.getIn(2));
        new Signal(circuit).from(r5.getOut(0)).to(ja.getIn(3));
        new Signal(circuit).from(r6.getOut(0)).to(ja.getIn(4));
        new Signal(circuit).from(r7.getOut(0)).to(ja.getIn(5));
        new Signal(circuit).from(r8.getOut(0)).to(ja.getIn(6));
        new Signal(circuit).from(r9.getOut(0)).to(ja.getIn(7));
        new Signal(circuit).from(ra.getOut(0)).to(ja.getIn(8));
        new Signal(circuit).from(re.getOut(0)).to(ja.getIn(9));
        new Signal(circuit).from(rf.getOut(0)).to(ja.getIn(10));
        new Signal(circuit).from(ja.getOut()).to(a);

        // B
        Joint jb = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(1)).to(jb.getIn(0));
        new Signal(circuit).from(r1.getOut(1)).to(jb.getIn(1));
        new Signal(circuit).from(r2.getOut(1)).to(jb.getIn(2));
        new Signal(circuit).from(r3.getOut(1)).to(jb.getIn(3));
        new Signal(circuit).from(r4.getOut(1)).to(jb.getIn(4));
        new Signal(circuit).from(r7.getOut(1)).to(jb.getIn(5));
        new Signal(circuit).from(r8.getOut(1)).to(jb.getIn(6));
        new Signal(circuit).from(r9.getOut(1)).to(jb.getIn(7));
        new Signal(circuit).from(ra.getOut(1)).to(jb.getIn(8));
        new Signal(circuit).from(rd.getOut(1)).to(jb.getIn(9));
        new Signal(circuit).from(jb.getOut()).to(b);

        // C
        Joint jc = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(2)).to(jc.getIn(0));
        new Signal(circuit).from(r1.getOut(2)).to(jc.getIn(1));
        new Signal(circuit).from(r3.getOut(2)).to(jc.getIn(2));
        new Signal(circuit).from(r4.getOut(2)).to(jc.getIn(3));
        new Signal(circuit).from(r5.getOut(2)).to(jc.getIn(4));
        new Signal(circuit).from(r6.getOut(2)).to(jc.getIn(5));
        new Signal(circuit).from(r7.getOut(2)).to(jc.getIn(6));
        new Signal(circuit).from(r8.getOut(2)).to(jc.getIn(7));
        new Signal(circuit).from(r9.getOut(2)).to(jc.getIn(8));
        new Signal(circuit).from(ra.getOut(2)).to(jc.getIn(9));
        new Signal(circuit).from(rb.getOut(2)).to(jc.getIn(10));
        new Signal(circuit).from(rd.getOut(2)).to(jc.getIn(11));
        new Signal(circuit).from(jc.getOut()).to(c);

        // D
        Joint jd = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(3)).to(jd.getIn(0));
        new Signal(circuit).from(r2.getOut(3)).to(jd.getIn(1));
        new Signal(circuit).from(r3.getOut(3)).to(jd.getIn(2));
        new Signal(circuit).from(r5.getOut(3)).to(jd.getIn(3));
        new Signal(circuit).from(r6.getOut(3)).to(jd.getIn(4));
        new Signal(circuit).from(r8.getOut(3)).to(jd.getIn(5));
        new Signal(circuit).from(r9.getOut(3)).to(jd.getIn(6));
        new Signal(circuit).from(rb.getOut(3)).to(jd.getIn(7));
        new Signal(circuit).from(rc.getOut(3)).to(jd.getIn(8));
        new Signal(circuit).from(rd.getOut(3)).to(jd.getIn(9));
        new Signal(circuit).from(re.getOut(3)).to(jd.getIn(10));
        new Signal(circuit).from(jd.getOut()).to(d);

        // E
        Joint je = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(4)).to(je.getIn(0));
        new Signal(circuit).from(r2.getOut(4)).to(je.getIn(1));
        new Signal(circuit).from(r6.getOut(4)).to(je.getIn(2));
        new Signal(circuit).from(r8.getOut(4)).to(je.getIn(3));
        new Signal(circuit).from(ra.getOut(4)).to(je.getIn(4));
        new Signal(circuit).from(rb.getOut(4)).to(je.getIn(5));
        new Signal(circuit).from(rc.getOut(4)).to(je.getIn(6));
        new Signal(circuit).from(rd.getOut(4)).to(je.getIn(7));
        new Signal(circuit).from(re.getOut(4)).to(je.getIn(8));
        new Signal(circuit).from(rf.getOut(4)).to(je.getIn(9));
        new Signal(circuit).from(je.getOut()).to(e);

        // F
        Joint jf = new Joint(circuit);
        new Signal(circuit).from(r0.getOut(5)).to(jf.getIn(0));
        new Signal(circuit).from(r4.getOut(5)).to(jf.getIn(1));
        new Signal(circuit).from(r5.getOut(5)).to(jf.getIn(2));
        new Signal(circuit).from(r6.getOut(5)).to(jf.getIn(3));
        new Signal(circuit).from(r8.getOut(5)).to(jf.getIn(4));
        new Signal(circuit).from(r9.getOut(5)).to(jf.getIn(5));
        new Signal(circuit).from(ra.getOut(5)).to(jf.getIn(6));
        new Signal(circuit).from(rb.getOut(5)).to(jf.getIn(7));
        new Signal(circuit).from(re.getOut(5)).to(jf.getIn(8));
        new Signal(circuit).from(rf.getOut(5)).to(jf.getIn(9));
        new Signal(circuit).from(jf.getOut()).to(f);

        // G
        Joint jg = new Joint(circuit);
        new Signal(circuit).from(r2.getOut(6)).to(jg.getIn(0));
        new Signal(circuit).from(r3.getOut(6)).to(jg.getIn(1));
        new Signal(circuit).from(r4.getOut(6)).to(jg.getIn(2));
        new Signal(circuit).from(r5.getOut(6)).to(jg.getIn(3));
        new Signal(circuit).from(r6.getOut(6)).to(jg.getIn(4));
        new Signal(circuit).from(r8.getOut(6)).to(jg.getIn(5));
        new Signal(circuit).from(r9.getOut(6)).to(jg.getIn(6));
        new Signal(circuit).from(ra.getOut(6)).to(jg.getIn(7));
        new Signal(circuit).from(rb.getOut(6)).to(jg.getIn(8));
        new Signal(circuit).from(rc.getOut(6)).to(jg.getIn(9));
        new Signal(circuit).from(rd.getOut(6)).to(jg.getIn(10));
        new Signal(circuit).from(re.getOut(6)).to(jg.getIn(11));
        new Signal(circuit).from(rf.getOut(6)).to(jg.getIn(12));
        new Signal(circuit).from(jg.getOut()).to(g);
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

    public Input getInA()
    {
        return inA;
    }

    public Input getInB()
    {
        return inB;
    }

    public Input getInC()
    {
        return inC;
    }

    public Input getInD()
    {
        return inD;
    }

    public Input getInE()
    {
        return inE;
    }

    public Input getInF()
    {
        return inF;
    }

    public Output getOutA()
    {
        return a;
    }

    public Output getOutB()
    {
        return b;
    }

    public Output getOutC()
    {
        return c;
    }

    public Output getOutD()
    {
        return d;
    }

    public Output getOutE()
    {
        return e;
    }

    public Output getOutF()
    {
        return f;
    }

    public Output getOutG()
    {
        return g;
    }
}
