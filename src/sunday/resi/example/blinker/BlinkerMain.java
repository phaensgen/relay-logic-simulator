package sunday.resi.example.blinker;

import sunday.resi.common.Circuit;
import sunday.resi.common.Simulator;
import sunday.resi.util.PartConsoleMonitor;
import sunday.resi.util.SystemConsole;

/**
 * Simulates the blinker.
 * 
 * @author Peter H&auml;nsgen
 */
public class BlinkerMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Blinker blinker = new Blinker(circuit, "Blinker");

        circuit.addMonitor(new PartConsoleMonitor(blinker.getLamp(), new SystemConsole()));

        Simulator sim = new Simulator(circuit, 10);
        sim.start();
    }
}
