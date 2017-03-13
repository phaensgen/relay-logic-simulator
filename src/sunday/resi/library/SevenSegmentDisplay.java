package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Signal;

/**
 * This is a 7-segment-display made of lamps.
 * 
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDisplay extends Component
{
    private Lamp a;

    private Lamp b;

    private Lamp c;

    private Lamp d;

    private Lamp e;

    private Lamp f;

    private Lamp g;

    private Input inA;

    private Input inB;

    private Input inC;

    private Input inD;

    private Input inE;

    private Input inF;

    private Input inG;

    /**
     * The constructor.
     */
    public SevenSegmentDisplay(Circuit circuit, String name)
    {
        super(circuit, name);

        inA = new Input();
        inB = new Input();
        inC = new Input();
        inD = new Input();
        inE = new Input();
        inF = new Input();
        inG = new Input();

        a = new Lamp(circuit, name + "_A");
        b = new Lamp(circuit, name + "_B");
        c = new Lamp(circuit, name + "_C");
        d = new Lamp(circuit, name + "_D");
        e = new Lamp(circuit, name + "_E");
        f = new Lamp(circuit, name + "_F");
        g = new Lamp(circuit, name + "_G");

        // internal wirings
        new Signal(circuit).from(inA).to(a.getIn());
        new Signal(circuit).from(inB).to(b.getIn());
        new Signal(circuit).from(inC).to(c.getIn());
        new Signal(circuit).from(inD).to(d.getIn());
        new Signal(circuit).from(inE).to(e.getIn());
        new Signal(circuit).from(inF).to(f.getIn());
        new Signal(circuit).from(inG).to(g.getIn());
    }

    public Lamp getA()
    {
        return a;
    }

    public Lamp getB()
    {
        return b;
    }

    public Lamp getC()
    {
        return c;
    }

    public Lamp getD()
    {
        return d;
    }

    public Lamp getE()
    {
        return e;
    }

    public Lamp getF()
    {
        return f;
    }

    public Lamp getG()
    {
        return g;
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

    public Input getInG()
    {
        return inG;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(" ");
        sb.append(a.getOn());
        sb.append(a.getOn());
        sb.append(" \n");

        sb.append(f.getOn());
        sb.append("  ");
        sb.append(b.getOn());
        sb.append(" \n");
        sb.append(f.getOn());
        sb.append("  ");
        sb.append(b.getOn());
        sb.append(" \n");

        sb.append(" ");
        sb.append(g.getOn());
        sb.append(g.getOn());
        sb.append(" \n");

        sb.append(e.getOn());
        sb.append("  ");
        sb.append(c.getOn());
        sb.append(" \n");
        sb.append(e.getOn());
        sb.append("  ");
        sb.append(c.getOn());
        sb.append(" \n");

        sb.append(" ");
        sb.append(d.getOn());
        sb.append(d.getOn());
        sb.append(" \n");

        return sb.toString();
    }
}
