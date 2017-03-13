package sunday.resi.example.dice;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Signal;
import sunday.resi.library.BCDToDecimalDecoder;
import sunday.resi.library.Clock;
import sunday.resi.library.Counter;
import sunday.resi.library.DecimalToSevenSegmentDecoder;
import sunday.resi.library.Relay;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * This is a dice which prints out random values.
 * 
 * @author Peter H&auml;nsgen
 */
public class Dice extends Component
{
    private Input powerIn;

    public Dice(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();

        Clock clock = new Clock(circuit, name + "_Clock", 10, TimeUnit.MILLISECONDS);

        Counter counter = new Counter(circuit, name + "_Counter");

        BCDToDecimalDecoder bcd = new BCDToDecimalDecoder(circuit, name + "_BCD");

        DecimalToSevenSegmentDecoder decimal = new DecimalToSevenSegmentDecoder(circuit, name + "_Decoder");

        SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, name + "_Display");
        circuit.addMonitor(display);

        Relay reset = new Relay(circuit, name + "_Reset");

        // internal wiring
        new Signal(circuit).from(powerIn).to(decimal.getPowerIn(), bcd.getPowerIn(), reset.getMiddleIn(0));

        new Signal(circuit).from(clock.get_Out()).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(counter.getClock());

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());
        new Signal(circuit).from(counter.getOut3()).to(bcd.getIn3());

        new Signal(circuit).from(bcd.getOut0()).to(decimal.getIn1());
        new Signal(circuit).from(bcd.getOut1()).to(decimal.getIn2());
        new Signal(circuit).from(bcd.getOut2()).to(decimal.getIn3());
        new Signal(circuit).from(bcd.getOut3()).to(decimal.getIn4());
        new Signal(circuit).from(bcd.getOut4()).to(decimal.getIn5());
        new Signal(circuit).from(bcd.getOut5()).to(decimal.getIn6());
        new Signal(circuit).from(bcd.getOut6()).to(reset.getCoilIn());

        new Signal(circuit).from(reset.get_Out(0)).to(counter.getPowerIn());

        new Signal(circuit).from(decimal.getOutA()).to(display.getInA());
        new Signal(circuit).from(decimal.getOutB()).to(display.getInB());
        new Signal(circuit).from(decimal.getOutC()).to(display.getInC());
        new Signal(circuit).from(decimal.getOutD()).to(display.getInD());
        new Signal(circuit).from(decimal.getOutE()).to(display.getInE());
        new Signal(circuit).from(decimal.getOutF()).to(display.getInF());
        new Signal(circuit).from(decimal.getOutG()).to(display.getInG());
    }

    public Input getPowerIn()
    {
        return powerIn;
    }
}
