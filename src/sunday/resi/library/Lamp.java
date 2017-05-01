package sunday.resi.library;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Part;

/**
 * Represents an indicator lamp.
 * 
 * @author Peter H&auml;nsgen
 */
public class Lamp extends Part
{
    private final Input in;

    private Boolean state;

    /**
     * The constructor.
     */
    public Lamp(Circuit circuit, String name)
    {
        super(circuit, name);

        in = new Input();
    }

    public Input getIn()
    {
        return in;
    }

    public boolean isOn()
    {
        return Boolean.TRUE.equals(state);
    }

    public String getOn()
    {
        return isOn() ? "O" : ".";
    }

    @Override
    public void simulate()
    {
        state = in.getValue();
    }

    @Override
    public String toString()
    {
        return getName() + ": " + (isOn() ? "on" : "off");
    }
}
