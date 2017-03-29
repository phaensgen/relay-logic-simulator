package sunday.resi.example.counter;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.library.BCDToDecimalDecoder;
import sunday.resi.library.Clock;
import sunday.resi.library.Counter;
import sunday.resi.library.DecimalToSevenSegmentDecoder;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * Simulates a 4-bit counter and shows the result on a 7-segment display.
 * 
 * @author Peter H&auml;nsgen
 */
public class CounterMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Clock clock = new Clock(circuit, "Clock", 500, TimeUnit.MILLISECONDS);

        Counter counter = new Counter(circuit, "Counter");

        BCDToDecimalDecoder bcd = new BCDToDecimalDecoder(circuit, "BCD");

        DecimalToSevenSegmentDecoder decimal = new DecimalToSevenSegmentDecoder(circuit, "Decoder");

        SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, "Display");
        circuit.addMonitor(display);

        // internal wiring
        new Signal(circuit).from(power.getOut()).to(counter.getPowerIn(), decimal.getPowerIn(), bcd.getPowerIn());

        new Signal(circuit).from(clock.get_Out()).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(counter.getClock());

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());
        new Signal(circuit).from(counter.getOut3()).to(bcd.getIn3());

        new Signal(circuit).from(bcd.getOut0()).to(decimal.getIn0());
        new Signal(circuit).from(bcd.getOut1()).to(decimal.getIn1());
        new Signal(circuit).from(bcd.getOut2()).to(decimal.getIn2());
        new Signal(circuit).from(bcd.getOut3()).to(decimal.getIn3());
        new Signal(circuit).from(bcd.getOut4()).to(decimal.getIn4());
        new Signal(circuit).from(bcd.getOut5()).to(decimal.getIn5());
        new Signal(circuit).from(bcd.getOut6()).to(decimal.getIn6());
        new Signal(circuit).from(bcd.getOut7()).to(decimal.getIn7());
        new Signal(circuit).from(bcd.getOut8()).to(decimal.getIn8());
        new Signal(circuit).from(bcd.getOut9()).to(decimal.getIn9());
        new Signal(circuit).from(bcd.getOutA()).to(decimal.getInA());
        new Signal(circuit).from(bcd.getOutB()).to(decimal.getInB());
        new Signal(circuit).from(bcd.getOutC()).to(decimal.getInC());
        new Signal(circuit).from(bcd.getOutD()).to(decimal.getInD());
        new Signal(circuit).from(bcd.getOutE()).to(decimal.getInE());
        new Signal(circuit).from(bcd.getOutF()).to(decimal.getInF());

        new Signal(circuit).from(decimal.getOutA()).to(display.getInA());
        new Signal(circuit).from(decimal.getOutB()).to(display.getInB());
        new Signal(circuit).from(decimal.getOutC()).to(display.getInC());
        new Signal(circuit).from(decimal.getOutD()).to(display.getInD());
        new Signal(circuit).from(decimal.getOutE()).to(display.getInE());
        new Signal(circuit).from(decimal.getOutF()).to(display.getInF());
        new Signal(circuit).from(decimal.getOutG()).to(display.getInG());

        Simulator sim = new Simulator(circuit, 10);
        sim.start();
    }
}
