package sunday.resi.util.logicanalyzer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import sunday.resi.util.logicanalyzer.LogicAnalyzer.Track;

/**
 * Visualizes the signals that are collected by a logic analyzer.
 * 
 * @author Peter H&auml;nsgen
 */
public class LogicAnalyzerCanvas extends Canvas
{
    private static final long serialVersionUID = 1L;

    private LogicAnalyzer logicAnalyzer;

    /**
     * The constructor.
     */
    public LogicAnalyzerCanvas(LogicAnalyzer logicAnalyzer)
    {
        this.logicAnalyzer = logicAnalyzer;
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public void paint(Graphics g)
    {
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, w, h);

        int xText = 10;
        int xGraph = 80;
        int yText = 30;
        int yGraph = 40;
        int yOffset = 0;

        for (Track track : logicAnalyzer.getTracks())
        {
            // paint a label for the signal
            g.setColor(track.getColor());
            g.drawString(track.getLabel(), xText, yText + yOffset);

            // paint the values
            List<Boolean> values = track.getValues();

            for (int i = 1; i < values.size(); i++)
            {
                Boolean v0 = values.get(i - 1);
                Boolean v1 = values.get(i);

                int x0 = xGraph + i - 1;
                int x1 = xGraph + i;

                int yBase = yGraph + yOffset;

                int y0 = Boolean.TRUE.equals(v0) ? yBase - 30 : yBase;
                int y1 = Boolean.TRUE.equals(v1) ? yBase - 30 : yBase;
                g.drawLine(x0, y0, x1, y1);
            }

            yOffset += 50;
        }
    }
}
