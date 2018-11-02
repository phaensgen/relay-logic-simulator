package sunday.resi.example.relayblinker;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.util.PartConsoleMonitor;
import sunday.resi.util.SystemConsole;

/**
 * Simulates the relay blinker.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayBlinkerMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power vcc = new Power(circuit, "VCC");
        RelayBlinker blinker = new RelayBlinker(circuit, "RelayBlinker");

        new Signal(circuit).from(vcc.getOut()).to(blinker.getPowerIn());

        circuit.addMonitor(new PartConsoleMonitor(blinker, new SystemConsole()));

        Simulator sim = new Simulator(circuit, 10);
        sim.start();
    }
}
