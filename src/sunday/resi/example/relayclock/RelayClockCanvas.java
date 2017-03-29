package sunday.resi.example.relayclock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import sunday.resi.library.SevenSegmentDisplay;

/**
 * A canvas which draws the relay clock display.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockCanvas extends Canvas
{
    private static final long serialVersionUID = 1L;

    private RelayClockDisplay display;

    /**
     * The constructor.
     */
    public RelayClockCanvas(RelayClockDisplay display)
    {
        this.display = display;
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

        // paint it in the middle of the canvas
        int x = (w - 330) / 2;
        int y = (h - 120) / 2;

        paint(display.getHours1(), g, x + 20, y + 20);
        paint(display.getHours0(), g, x + 70, y + 20);
        paint(display.getMinutes1(), g, x + 120, y + 20);
        paint(display.getMinutes0(), g, x + 170, y + 20);
        paint(display.getSeconds1(), g, x + 220, y + 20);
        paint(display.getSeconds0(), g, x + 270, y + 20);
    }

    private void paint(SevenSegmentDisplay digit, Graphics g, int xOffset, int yOffset)
    {
        g.setColor(Color.GREEN);

        if (digit.getA().isOn())
        {
            g.drawLine(xOffset, yOffset, xOffset + 40, yOffset);
        }
        if (digit.getB().isOn())
        {
            g.drawLine(xOffset + 40, yOffset, xOffset + 40, yOffset + 40);
        }
        if (digit.getC().isOn())
        {
            g.drawLine(xOffset + 40, yOffset + 40, xOffset + 40, yOffset + 80);
        }
        if (digit.getD().isOn())
        {
            g.drawLine(xOffset, yOffset + 80, xOffset + 40, yOffset + 80);
        }
        if (digit.getE().isOn())
        {
            g.drawLine(xOffset, yOffset + 80, xOffset, yOffset + 40);
        }
        if (digit.getF().isOn())
        {
            g.drawLine(xOffset, yOffset + 40, xOffset, yOffset);
        }
        if (digit.getG().isOn())
        {
            g.drawLine(xOffset, yOffset + 40, xOffset + 40, yOffset + 40);
        }
    }
}
