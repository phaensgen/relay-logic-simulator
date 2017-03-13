package sunday.resi.common;

/**
 * Represents a signal in a circuit. Signals connect inputs and outputs of parts. A signal has a state which represents
 * the current value of an output:
 * <ul>
 * <li>true stands for "HIGH", e.g. the signal is connected to POWER</li>
 * <li>false stands for "LOW", e.g. the signal is connected to GROUND</li>
 * <li>null stands for undefined, e.g. the signal is not connected to any potential</li>
 * </ul>
 * A signal can be connected to one output which determines its value and multiple inputs which consume the value. For
 * components, a signal can also be used to forward values from the output of an inner element to one or more public
 * outputs of the component. In the opposite direction, the value of one public input of a component can be forwarded to
 * multiple inputs of internal elements.
 * 
 * @author Peter H&auml;nsgen
 */
public class Signal extends Element
{
    /**
     * A counter for generating signal names automatically.
     */
    private static int instanceCount;

    /**
     * The current value of the signal.
     */
    private Boolean value;

    private Output[] externalOutputs;

    private Input externalInput;

    /**
     * The constructor.
     */
    public Signal(Circuit circuit)
    {
        this(circuit, generateName());
    }

    /**
     * The constructor.
     */
    public Signal(Circuit circuit, String name)
    {
        super(circuit, name);

        circuit.addSignal(this);
    }

    public Signal from(Output output)
    {
        output.setSignal(this);
        return this;
    }

    public Signal from(Input input)
    {
        this.externalInput = input;
        return this;
    }

    public Signal to(Output... outputs)
    {
        this.externalOutputs = outputs;
        return this;
    }

    public Signal to(Input... inputs)
    {
        for (Input input : inputs)
        {
            input.setSignal(this);
        }

        return this;
    }

    public void setValue(Boolean value)
    {
        this.value = value;

        if (externalOutputs != null)
        {
            for (Output forward : externalOutputs)
            {
                forward.setValue(value);
            }
        }
    }

    public Boolean getValue()
    {
        if (externalInput != null)
        {
            return externalInput.getValue();
        }

        return value;
    }

    @Override
    public String toString()
    {
        return "[Signal " + getName() + "=" + String.valueOf(value) + "]";
    }

    private static String generateName()
    {
        return "s" + String.valueOf(instanceCount++);
    }
}
