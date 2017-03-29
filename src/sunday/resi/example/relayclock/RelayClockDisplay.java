package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * This is a display consisting of lamps forming a HH:MM:SS structure. Each digit consist of 7 segments.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockDisplay extends Component
{
    // seconds
    private SevenSegmentDisplay s0;

    private SevenSegmentDisplay s1;

    // minutes
    private SevenSegmentDisplay m0;

    private SevenSegmentDisplay m1;

    // hours
    private SevenSegmentDisplay h0;

    private SevenSegmentDisplay h1;

    /**
     * The constructor.
     */
    public RelayClockDisplay(Circuit circuit, String name)
    {
        super(circuit, name);

        s0 = new SevenSegmentDisplay(circuit, name + "_S0");
        s1 = new SevenSegmentDisplay(circuit, name + "_S1");
        m0 = new SevenSegmentDisplay(circuit, name + "_M0");
        m1 = new SevenSegmentDisplay(circuit, name + "_M1");
        h0 = new SevenSegmentDisplay(circuit, name + "_H0");
        h1 = new SevenSegmentDisplay(circuit, name + "_H1");
    }

    public SevenSegmentDisplay getSeconds0()
    {
        return s0;
    }

    public SevenSegmentDisplay getSeconds1()
    {
        return s1;
    }

    public SevenSegmentDisplay getMinutes0()
    {
        return m0;
    }

    public SevenSegmentDisplay getMinutes1()
    {
        return m1;
    }

    public SevenSegmentDisplay getHours0()
    {
        return h0;
    }

    public SevenSegmentDisplay getHours1()
    {
        return h1;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(" ");
        sb.append(h1.getA().getOn());
        sb.append(h1.getA().getOn());
        sb.append("    ");
        sb.append(h0.getA().getOn());
        sb.append(h0.getA().getOn());
        sb.append("     ");
        sb.append(m1.getA().getOn());
        sb.append(m1.getA().getOn());
        sb.append("    ");
        sb.append(m0.getA().getOn());
        sb.append(m0.getA().getOn());
        sb.append("     ");
        sb.append(s1.getA().getOn());
        sb.append(s1.getA().getOn());
        sb.append("    ");
        sb.append(s0.getA().getOn());
        sb.append(s0.getA().getOn());
        sb.append("\n");

        sb.append(h1.getF().getOn());
        sb.append("  ");
        sb.append(h1.getB().getOn());
        sb.append("  ");
        sb.append(h0.getF().getOn());
        sb.append("  ");
        sb.append(h0.getB().getOn());
        sb.append("   ");
        sb.append(m1.getF().getOn());
        sb.append("  ");
        sb.append(m1.getB().getOn());
        sb.append("  ");
        sb.append(m0.getF().getOn());
        sb.append("  ");
        sb.append(m0.getB().getOn());
        sb.append("   ");
        sb.append(s1.getF().getOn());
        sb.append("  ");
        sb.append(s1.getB().getOn());
        sb.append("  ");
        sb.append(s0.getF().getOn());
        sb.append("  ");
        sb.append(s0.getB().getOn());
        sb.append("\n");

        sb.append(h1.getF().getOn());
        sb.append("  ");
        sb.append(h1.getB().getOn());
        sb.append("  ");
        sb.append(h0.getF().getOn());
        sb.append("  ");
        sb.append(h0.getB().getOn());
        sb.append("   ");
        sb.append(m1.getF().getOn());
        sb.append("  ");
        sb.append(m1.getB().getOn());
        sb.append("  ");
        sb.append(m0.getF().getOn());
        sb.append("  ");
        sb.append(m0.getB().getOn());
        sb.append("   ");
        sb.append(s1.getF().getOn());
        sb.append("  ");
        sb.append(s1.getB().getOn());
        sb.append("  ");
        sb.append(s0.getF().getOn());
        sb.append("  ");
        sb.append(s0.getB().getOn());
        sb.append("\n");

        sb.append(" ");
        sb.append(h1.getG().getOn());
        sb.append(h1.getG().getOn());
        sb.append("    ");
        sb.append(h0.getG().getOn());
        sb.append(h0.getG().getOn());
        sb.append("     ");
        sb.append(m1.getG().getOn());
        sb.append(m1.getG().getOn());
        sb.append("    ");
        sb.append(m0.getG().getOn());
        sb.append(m0.getG().getOn());
        sb.append("     ");
        sb.append(s1.getG().getOn());
        sb.append(s1.getG().getOn());
        sb.append("    ");
        sb.append(s0.getG().getOn());
        sb.append(s0.getG().getOn());
        sb.append("\n");

        sb.append(h1.getE().getOn());
        sb.append("  ");
        sb.append(h1.getC().getOn());
        sb.append("  ");
        sb.append(h0.getE().getOn());
        sb.append("  ");
        sb.append(h0.getC().getOn());
        sb.append("   ");
        sb.append(m1.getE().getOn());
        sb.append("  ");
        sb.append(m1.getC().getOn());
        sb.append("  ");
        sb.append(m0.getE().getOn());
        sb.append("  ");
        sb.append(m0.getC().getOn());
        sb.append("   ");
        sb.append(s1.getE().getOn());
        sb.append("  ");
        sb.append(s1.getC().getOn());
        sb.append("  ");
        sb.append(s0.getE().getOn());
        sb.append("  ");
        sb.append(s0.getC().getOn());
        sb.append("\n");

        sb.append(h1.getE().getOn());
        sb.append("  ");
        sb.append(h1.getC().getOn());
        sb.append("  ");
        sb.append(h0.getE().getOn());
        sb.append("  ");
        sb.append(h0.getC().getOn());
        sb.append("   ");
        sb.append(m1.getE().getOn());
        sb.append("  ");
        sb.append(m1.getC().getOn());
        sb.append("  ");
        sb.append(m0.getE().getOn());
        sb.append("  ");
        sb.append(m0.getC().getOn());
        sb.append("   ");
        sb.append(s1.getE().getOn());
        sb.append("  ");
        sb.append(s1.getC().getOn());
        sb.append("  ");
        sb.append(s0.getE().getOn());
        sb.append("  ");
        sb.append(s0.getC().getOn());
        sb.append("\n");

        sb.append(" ");
        sb.append(h1.getD().getOn());
        sb.append(h1.getD().getOn());
        sb.append("    ");
        sb.append(h0.getD().getOn());
        sb.append(h0.getD().getOn());
        sb.append("     ");
        sb.append(m1.getD().getOn());
        sb.append(m1.getD().getOn());
        sb.append("    ");
        sb.append(m0.getD().getOn());
        sb.append(m0.getD().getOn());
        sb.append("     ");
        sb.append(s1.getD().getOn());
        sb.append(s1.getD().getOn());
        sb.append("    ");
        sb.append(s0.getD().getOn());
        sb.append(s0.getD().getOn());
        sb.append("\n");

        return sb.toString();
    }
}
