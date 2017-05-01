package sunday.resi.example.relaytimer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import sunday.resi.library.SevenSegmentDisplay;

/**
 * A canvas which draws the relay timer display.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerCanvas extends Canvas
{
    private static final long serialVersionUID = 1L;

    private RelayTimerDigitPCB digitS0;

    private RelayTimerDigitPCB digitS1;

    private RelayTimerDigitPCB digitM0;

    private RelayTimerDigitPCB digitM1;

    /**
     * The constructor.
     */
    public RelayTimerCanvas(RelayTimer timer)
    {
        this.digitS0 = timer.getDisplayS0();
        this.digitS1 = timer.getDisplayS1();
        this.digitM0 = timer.getDisplayM0();
        this.digitM1 = timer.getDisplayM1();
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
        int x = (w - 230) / 2;
        int y = (h - 120) / 2;

        g.setColor(Color.GREEN);
        paint(digitM1.getDisplay(), g, x + 20, y + 20);
        paint(digitM0.getDisplay(), g, x + 70, y + 20);

        g.setColor(Color.RED);
        paint(digitS1.getDisplay(), g, x + 120, y + 20);
        paint(digitS0.getDisplay(), g, x + 170, y + 20);
    }

    private void paint(SevenSegmentDisplay digit, Graphics g, int xOffset, int yOffset)
    {
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
