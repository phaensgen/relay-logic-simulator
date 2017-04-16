package sunday.resi.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents an electronic circuit. A circuit consists of parts and signals that connects parts with each other. Parts
 * and signals can be monitored.
 * 
 * @author Peter H&auml;nsgen
 */
public class Circuit
{
    private Map<String, Part> parts;

    private Map<String, Signal> signals;

    private List<Monitor> monitors;

    /**
     * The constructor.
     */
    public Circuit()
    {
        parts = new LinkedHashMap<>();
        signals = new LinkedHashMap<>();
        monitors = new ArrayList<>();
    }

    public void addSignal(Signal signal)
    {
        String name = signal.getName();
        if (signals.containsKey(name))
        {
            throw new IllegalArgumentException("Signal name '" + name + "' is not unique.");
        }

        signals.put(name, signal);
    }

    public Signal getSignal(String name)
    {
        return signals.get(name);
    }

    public Collection<Signal> getSignals()
    {
        return signals.values();
    }

    public void addPart(Part part)
    {
        String name = part.getName();
        if (parts.containsKey(name))
        {
            throw new IllegalArgumentException("Part name '" + name + "' is not unique.");
        }

        parts.put(name, part);
    }

    public Part getPart(String name)
    {
        return parts.get(name);
    }

    public Collection<Part> getParts()
    {
        return parts.values();
    }

    @SuppressWarnings("unchecked")
    public <P extends Part> Collection<P> getParts(Class<P> type)
    {
        return (Collection<P>) parts.values().stream().filter(p -> type.isInstance(p)).collect(Collectors.toList());
    }

    /**
     * Returns the number of parts with the given type in the circuit.
     */
    public int getPartCount(Class<? extends Part> type)
    {
        int count = 0;

        for (Part p : parts.values())
        {
            if (type.isInstance(p))
            {
                count++;
            }
        }

        return count;
    }

    public void addMonitor(Monitor monitor)
    {
        monitors.add(monitor);
    }

    public Collection<Monitor> getMonitors()
    {
        return monitors;
    }

    public void simulate()
    {
        for (Part part : getParts())
        {
            part.simulate();
        }

        for (Monitor monitor : getMonitors())
        {
            monitor.monitor();
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Part part : getParts())
        {
            if (sb.length() > 0)
            {
                sb.append(", ");
            }

            sb.append(part.toString());
        }

        for (Signal signal : getSignals())
        {
            if (sb.length() > 0)
            {
                sb.append(", ");
            }

            sb.append(signal.toString());
        }

        return sb.toString();
    }
}
