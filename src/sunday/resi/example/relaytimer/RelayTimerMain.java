package sunday.resi.example.relaytimer;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.util.PartCountPrinter;
import sunday.resi.util.PartTreePrinter;
import sunday.resi.util.PeakPowerMonitor;

/**
 * Simulates the relay timer.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerMain
{
    /**
     * The constructor.
     */
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        RelayTimer timer = new RelayTimer(circuit, "RelayTimer");

        new Signal(circuit).from(power.getOut()).to(timer.getPowerIn());

        Simulator sim = new Simulator(circuit, 10);

        RelayTimerFrame frame = new RelayTimerFrame(timer, sim);
        circuit.addMonitor(frame);
        circuit.addMonitor(new PeakPowerMonitor(circuit, 2, frame.getPeakPowerConsole()));

        new PartCountPrinter(frame.getConsole(), 2).printParts(circuit);
        new PartTreePrinter(frame.getConsole()).printParts(circuit);

        sim.start();
    }
}
