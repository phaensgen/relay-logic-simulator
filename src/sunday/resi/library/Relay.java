package sunday.resi.library;

import java.util.LinkedHashMap;
import java.util.Map;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Part;

/**
 * Represents a relay with an arbitrary number of switches. A relay has a single coil input which determines the state
 * of all switches. Each switch has a single middle input whose signal is forwarded to an active and an inactive output.
 * 
 * @author Peter H&auml;nsgen
 */
public class Relay extends Part
{
    private Input coilIn;

    private Map<Integer, Switch> switches;

    static class Switch
    {
        private Input middle = new Input();

        private Output _out = new Output();

        private Output out = new Output();
    }

    /**
     * The constructor.
     */
    public Relay(Circuit circuit, String name)
    {
        super(circuit, name);

        coilIn = new Input();
        switches = new LinkedHashMap<>();
    }

    public Input getCoilIn()
    {
        return coilIn;
    }

    public Input getMiddleIn(int index)
    {
        Switch sw = getSwitch(index);
        return sw.middle;
    }

    public Output get_Out(int index)
    {
        Switch sw = getSwitch(index);
        return sw._out;
    }

    public Output getOut(int index)
    {
        Switch sw = getSwitch(index);
        return sw.out;
    }

    @Override
    public void simulate()
    {
        Boolean state = coilIn.getValue();

        if (Boolean.TRUE.equals(state))
        {
            for (Switch sw : switches.values())
            {
                sw._out.setValue(null);
                sw.out.setValue(sw.middle.getValue());
            }
        }
        else if ((state == null) || Boolean.FALSE.equals(state))
        {
            for (Switch sw : switches.values())
            {
                sw._out.setValue(sw.middle.getValue());
                sw.out.setValue(null);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Relay ");
        sb.append(getName());
        sb.append(": ");
        sb.append("coilIn=");
        sb.append(String.valueOf(coilIn));

        for (int i = 0; i < switches.size(); i++)
        {
            sb.append(", ");
            Switch sw = switches.get(i);
            sb.append("middleIn");
            sb.append(String.valueOf(i));
            sb.append("=");
            sb.append(String.valueOf(sw.middle));
            sb.append(", _out=");
            sb.append(String.valueOf(sw._out));
            sb.append(", out=");
            sb.append(String.valueOf(sw.out));
        }
        sb.append("]");
        return sb.toString();
    }

    private Switch getSwitch(int index)
    {
        Switch sw = switches.get(index);
        if (sw == null)
        {
            sw = new Switch();
            switches.put(index, sw);
        }
        return sw;
    }
}
