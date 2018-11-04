package sunday.resi.util.logicanalyzer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import sunday.resi.common.Monitor;
import sunday.resi.common.Signal;

/**
 * Records the values of signals.
 * 
 * @author Peter H&auml;nsgen
 */
public class LogicAnalyzer implements Monitor
{
    private List<Track> tracks;

    private int maxValues;

    /**
     * The constructor.
     */
    public LogicAnalyzer(int maxValues)
    {
        this.maxValues = maxValues;

        tracks = new ArrayList<>();
    }

    public int getMaxValues()
    {
        return maxValues;
    }

    public Track addTrack(Signal signal)
    {
        Track t = new Track(signal);
        tracks.add(t);
        return t;
    }

    public Collection<Track> getTracks()
    {
        return tracks;
    }

    @Override
    public void monitor()
    {
        for (Track track : tracks)
        {
            track.monitor();
        }
    }

    public class Track implements Monitor
    {
        private Signal signal;

        private String label;

        private Color color;

        private LinkedList<Boolean> values;

        private Track(Signal signal)
        {
            this.signal = signal;

            this.label = signal.getName();
            this.color = Color.GREEN;

            values = new LinkedList<>();
        }

        @Override
        public void monitor()
        {
            addValue(signal.getValue());
        }

        public void setLabel(String label)
        {
            this.label = label;
        }

        public String getLabel()
        {
            return label;
        }

        public void setColor(Color color)
        {
            this.color = color;
        }

        public Color getColor()
        {
            return color;
        }

        private void addValue(Boolean value)
        {
            if (values.size() >= maxValues)
            {
                values.removeFirst();
            }
            values.addLast(value);
        }

        public List<Boolean> getValues()
        {
            return new ArrayList<>(values);
        }
    }
}
