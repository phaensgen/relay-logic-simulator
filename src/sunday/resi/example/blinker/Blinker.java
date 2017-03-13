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
    private Lamp l1;

    /**
     * The constructor.
     */
    public Blinker(Circuit circuit, String name)
    {
        super(circuit, name);

        Clock c1 = new Clock(circuit, name + "_Clock", 1, TimeUnit.SECONDS);
        l1 = new Lamp(circuit, name + "_L1");

        new Signal(circuit).from(c1.getOut()).to(l1.getIn());

        circuit.addMonitor(this);
    }

    @Override
    public String toString()
    {
        return l1.getOn();
    }
}
