package sunday.resi.example.countdown;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.library.BCDToDecimalDecoder;
import sunday.resi.library.Clock;
import sunday.resi.library.Counter;
import sunday.resi.library.SevenSegmentDecoder16;
import sunday.resi.library.SevenSegmentDisplay;

/**
 * Simulates a 4-bit countdown and shows the result on a 7-segment display.
 * 
 * @author Peter H&auml;nsgen
 */
public class CountdownMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Clock clock = new Clock(circuit, "Clock", 500, TimeUnit.MILLISECONDS);

        Counter counter = new Counter(circuit, "Counter");

        BCDToDecimalDecoder bcd = new BCDToDecimalDecoder(circuit, "BCD");

        SevenSegmentDecoder16 decoder = new SevenSegmentDecoder16(circuit, "Decoder");

        SevenSegmentDisplay display = new SevenSegmentDisplay(circuit, "Display");
        circuit.addMonitor(display);

        // internal wiring
        new Signal(circuit).from(power.getOut()).to(counter.getPowerIn(), decoder.getPowerIn(), bcd.getPowerIn());

        new Signal(circuit).from(clock.get_Out()).to(counter.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(counter.getClock());

        new Signal(circuit).from(counter.getOut0()).to(bcd.getIn0());
        new Signal(circuit).from(counter.getOut1()).to(bcd.getIn1());
        new Signal(circuit).from(counter.getOut2()).to(bcd.getIn2());
        new Signal(circuit).from(counter.getOut3()).to(bcd.getIn3());

        // connect counter and decoder cross-wise for counting backwards
        new Signal(circuit).from(bcd.getOut0()).to(decoder.getInF());
        new Signal(circuit).from(bcd.getOut1()).to(decoder.getInE());
        new Signal(circuit).from(bcd.getOut2()).to(decoder.getInD());
        new Signal(circuit).from(bcd.getOut3()).to(decoder.getInC());
        new Signal(circuit).from(bcd.getOut4()).to(decoder.getInB());
        new Signal(circuit).from(bcd.getOut5()).to(decoder.getInA());
        new Signal(circuit).from(bcd.getOut6()).to(decoder.getIn9());
        new Signal(circuit).from(bcd.getOut7()).to(decoder.getIn8());
        new Signal(circuit).from(bcd.getOut8()).to(decoder.getIn7());
        new Signal(circuit).from(bcd.getOut9()).to(decoder.getIn6());
        new Signal(circuit).from(bcd.getOutA()).to(decoder.getIn5());
        new Signal(circuit).from(bcd.getOutB()).to(decoder.getIn4());
        new Signal(circuit).from(bcd.getOutC()).to(decoder.getIn3());
        new Signal(circuit).from(bcd.getOutD()).to(decoder.getIn2());
        new Signal(circuit).from(bcd.getOutE()).to(decoder.getIn1());
        new Signal(circuit).from(bcd.getOutF()).to(decoder.getIn0());

        new Signal(circuit).from(decoder.getOutA()).to(display.getInA());
        new Signal(circuit).from(decoder.getOutB()).to(display.getInB());
        new Signal(circuit).from(decoder.getOutC()).to(display.getInC());
        new Signal(circuit).from(decoder.getOutD()).to(display.getInD());
        new Signal(circuit).from(decoder.getOutE()).to(display.getInE());
        new Signal(circuit).from(decoder.getOutF()).to(display.getInF());
        new Signal(circuit).from(decoder.getOutG()).to(display.getInG());

        Simulator sim = new Simulator(circuit, 10);
        sim.start();
    }
}
