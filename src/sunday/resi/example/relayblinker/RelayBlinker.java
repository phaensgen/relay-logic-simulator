package sunday.resi.example.relayblinker;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Signal;
import sunday.resi.library.Clock;
import sunday.resi.library.Lamp;
import sunday.resi.library.Relay;

/**
 * This is a blinker with a relay which alternates between two lamps.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayBlinker extends Component
{
    private Input powerIn;

    private Lamp l1;

    private Lamp l2;

    private Relay r1;

    /**
     * The constructor.
     */
    public RelayBlinker(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();

        Clock c1 = new Clock(circuit, name + "_Clock", 1, TimeUnit.SECONDS);
        r1 = new Relay(circuit, name + "_Relay");
        l1 = new Lamp(circuit, name + "_L1");
        l2 = new Lamp(circuit, name + "_L2");

        // connect signals
        new Signal(circuit).from(powerIn).to(r1.getMiddleIn(0));
        new Signal(circuit).from(c1.getOut()).to(r1.getCoilIn());
        new Signal(circuit).from(r1.getOut(0)).to(l1.getIn());
        new Signal(circuit).from(r1.get_Out(0)).to(l2.getIn());

        circuit.addMonitor(this);
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    @Override
    public String toString()
    {
        return l1.getOn() + " " + l2.getOn();
    }
}
