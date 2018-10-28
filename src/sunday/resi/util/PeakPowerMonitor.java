package sunday.resi.util;

import sunday.resi.common.Circuit;
import sunday.resi.common.Monitor;
import sunday.resi.library.Lamp;
import sunday.resi.library.Relay;

/**
 * This is a monitor implementation that estimates the maximum used power by a circuit. It counts the number of parts
 * that are active / switched on. The absolute maximum values are printed to the console.
 * 
 * @author Peter H&auml;nsgen
 */
public class PeakPowerMonitor implements Monitor
{
    private Circuit circuit;

    private int maxActiveLamps;

    private int maxActiveRelays;

    private int maxRelaySwitchCount;

    private Console console;

    /**
     * The constructor.
     */
    public PeakPowerMonitor(Circuit circuit, int maxRelaySwitchCount, Console console)
    {
        this.circuit = circuit;
        this.maxRelaySwitchCount = maxRelaySwitchCount;
        this.console = console;
    }

    @Override
    public void monitor()
    {
        int activeLamps = 0;
        int activeRelays = 0;

        for (Lamp l : circuit.getAllParts(Lamp.class))
        {
            if (l.isOn())
            {
                activeLamps++;
            }
        }

        for (Relay r : circuit.getAllParts(Relay.class))
        {
            if (Boolean.TRUE.equals(r.getCoilIn().getValue()))
            {
                // multiple real relays may be needed
                activeRelays += (r.getSwitchCount() + 1) / maxRelaySwitchCount;
            }
        }

        if (maxActiveLamps < activeLamps)
        {
            maxActiveLamps = activeLamps;
            console.println("Maximum active lamps: " + String.valueOf(maxActiveLamps));
        }

        if (maxActiveRelays < activeRelays)
        {
            maxActiveRelays = activeRelays;
            console.println("Maximum active relays: " + String.valueOf(maxActiveRelays));
        }
    }
}
