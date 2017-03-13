package sunday.resi.example.relayclock;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;

/**
 * Simulates the relay clock.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockMain
{
    /**
     * The constructor.
     */
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        RelayClock clock = new RelayClock(circuit, "Digital Clock");

        new Signal(circuit).from(power.getOut()).to(clock.getPowerIn());

        Simulator sim = new Simulator(10);
        sim.simulate(circuit);
    }
}
