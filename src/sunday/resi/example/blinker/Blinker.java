package sunday.resi.example.blinker;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Signal;
import sunday.resi.library.Clock;
import sunday.resi.library.Lamp;

/**
 * This is a simple blinker which periodically switches a light on and off.
 * 
 * @author Peter H&auml;nsgen
 */
public class Blinker extends Component
{
    private Lamp lamp;

    /**
     * The constructor.
     */
    public Blinker(Circuit circuit, String name)
    {
        super(circuit, name);

        Circuit local = getLocalCircuit();

        Clock clock = new Clock(local, name + "_Clock", 1, TimeUnit.SECONDS);
        lamp = new Lamp(local, name + "_Lamp");

        new Signal(local).from(clock.getOut()).to(lamp.getIn());
    }

    public Lamp getLamp()
    {
        return lamp;
    }
}
