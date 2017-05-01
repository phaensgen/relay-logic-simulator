package sunday.resi.example.relaytimer;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.library.Relay;

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
        RelayTimer timer = new RelayTimer(circuit, "Relay Timer");

        new Signal(circuit).from(power.getOut()).to(timer.getPowerIn());

        Simulator sim = new Simulator(circuit, 10);

        RelayTimerFrame frame = new RelayTimerFrame(timer, sim);
        circuit.addMonitor(frame);

        printRelayCount(circuit, 2);

        sim.start();
    }

    /**
     * Prints the number of required relays for the circuit.
     */
    static void printRelayCount(Circuit circuit, int maxSwitchCount)
    {
        // we assume that every relay has up to maxSwitchCount switches
        int count = 0;
        for (Relay r : circuit.getAllParts(Relay.class))
        {
            count += (r.getSwitchCount() + 1) / maxSwitchCount;
        }

        System.out.println(String.valueOf(count + " relays with " + maxSwitchCount + " switches required."));
    }
}
