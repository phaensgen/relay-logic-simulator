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

    private RelayClockDigitPCB digitS0;

    private RelayClockDigitPCB digitS1;

    private RelayClockDigitPCB digitM0;

    private RelayClockDigitPCB digitM1;

    private RelayClockDigitPCB digitH0;

    private RelayClockDigitPCB digitH1;

    /**
     * The constructor.
     */
    public RelayClockCanvas(RelayClock clock)
    {
        this.digitS0 = clock.getDisplayS0();
        this.digitS1 = clock.getDisplayS1();
        this.digitM0 = clock.getDisplayM0();
        this.digitM1 = clock.getDisplayM1();
        this.digitH0 = clock.getDisplayH0();
        this.digitH1 = clock.getDisplayH1();
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

        paint(digitH1.getDisplay(), g, x + 20, y + 20);
        paint(digitH0.getDisplay(), g, x + 70, y + 20);
        paint(digitM1.getDisplay(), g, x + 120, y + 20);
        paint(digitM0.getDisplay(), g, x + 170, y + 20);
        paint(digitS1.getDisplay(), g, x + 220, y + 20);
        paint(digitS0.getDisplay(), g, x + 270, y + 20);
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
