package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * This component represents a PCB for one digit of the relay clock. The clock consists of six such PCBs.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockDigitPCB extends Component
{
    private SevenSegmentDisplay display;

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
    public RelayClockDigitPCB(Circuit circuit, String name)
    {
        super(circuit, name);

        display = new SevenSegmentDisplay(circuit, name + "_7S");

        inA = new Input();
        inB = new Input();
        inC = new Input();
        inD = new Input();
        inE = new Input();
        inF = new Input();
        inG = new Input();

        new Signal(circuit).from(inA).to(display.getInA());
        new Signal(circuit).from(inB).to(display.getInB());
        new Signal(circuit).from(inC).to(display.getInC());
        new Signal(circuit).from(inD).to(display.getInD());
        new Signal(circuit).from(inE).to(display.getInE());
        new Signal(circuit).from(inF).to(display.getInF());
        new Signal(circuit).from(inG).to(display.getInG());
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

    public SevenSegmentDisplay getDisplay()
    {
        return display;
    }

    @Override
    public String toString()
    {
        return display.toString();
    }
}
