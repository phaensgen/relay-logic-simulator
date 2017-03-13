package sunday.resi.example.blinker;

import sunday.resi.common.Circuit;
import sunday.resi.common.Simulator;

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

        new Blinker(circuit, "Blinker");

        Simulator sim = new Simulator(10);
        sim.simulate(circuit);
    }
}
