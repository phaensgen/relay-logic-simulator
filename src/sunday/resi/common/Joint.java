package sunday.resi.common;

import java.util.HashMap;
import java.util.Map;

/**
 * A joint is a part that connects multiple input signals to one output signal. All input signals must have the same
 * value, or they must be disconnected (e.g. have null as value). If input signals have different values, an exception
 * is thrown.
 * 
 * @author Peter H&auml;nsgen
 */
public class Joint extends Part
{
    private Output out;

    private Map<Integer, Input> inputs;

    /**
     * The constructor.
     */
    public Joint(Circuit circuit)
    {
        super(circuit);

        out = new Output();
        inputs = new HashMap<>();
    }

    /**
     * The constructor.
     */
    public Joint(Circuit circuit, String name)
    {
        super(circuit, name);

        out = new Output();
        inputs = new HashMap<>();
    }

    public Output getOut()
    {
        return out;
    }

    public Input getIn(int i)
    {
        Input in = inputs.get(i);
        if (in == null)
        {
            in = new Input();
            inputs.put(i, in);
        }
        return in;
    }

    @Override
    public void simulate()
    {
        Boolean state = null;

        for (Input in : inputs.values())
        {
            Boolean value = in.getValue();

            if (value != null)
            {
                if (state == null)
                {
                    state = value;
                }
                else if (state != value)
                {
                    throw new SimulatorException("Ambigous values of input signals (" + toString() + ")");
                }
            }
        }

        out.setValue(state);
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
