package sunday.resi.example.relaytimer;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Signal;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * This component represents a PCB for one digit of the relay timer. The timer consists of two such PCBs.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerDigitPCB extends Component
{
    private final SevenSegmentDisplay display;

    private final Input inA;

    private final Input inB;

    private final Input inC;

    private final Input inD;

    private final Input inE;

    private final Input inF;

    private final Input inG;

    /**
     * The constructor.
     */
    public RelayTimerDigitPCB(Circuit parent, String name)
    {
        super(parent, name);

        inA = new Input();
        inB = new Input();
        inC = new Input();
        inD = new Input();
        inE = new Input();
        inF = new Input();
        inG = new Input();

        Circuit local = getLocalCircuit();

        display = new SevenSegmentDisplay(local, name + "_7S");

        new Signal(local).from(inA).to(display.getInA());
        new Signal(local).from(inB).to(display.getInB());
        new Signal(local).from(inC).to(display.getInC());
        new Signal(local).from(inD).to(display.getInD());
        new Signal(local).from(inE).to(display.getInE());
        new Signal(local).from(inF).to(display.getInF());
        new Signal(local).from(inG).to(display.getInG());
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
