package sunday.resi.library;

import java.awt.Toolkit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Part;

/**
 * Simulates a sound signal, like a bell or a buzzer.
 * 
 * @author Peter H&auml;nsgen
 */
public class Bell extends Part
{
    private final Input in;

    private long lastTime;

    /**
     * The constructor.
     */
    public Bell(Circuit circuit, String name)
    {
        super(circuit, name);

        in = new Input();
    }

    public Input getIn()
    {
        return in;
    }

    @Override
    public void simulate()
    {
        if (Boolean.TRUE.equals(in.getValue()))
        {
            long time = System.currentTimeMillis();
            long diff = time - lastTime;
            if (diff > 120)
            {
                Toolkit.getDefaultToolkit().beep();
                lastTime = time;
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Bell ");
        sb.append(getName());
        sb.append(": in=");
        sb.append(String.valueOf(in));
        sb.append("]");

        return sb.toString();
    }
}
