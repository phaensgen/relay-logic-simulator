package sunday.resi.example.relayclock;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sunday.resi.common.Monitor;
import sunday.resi.common.Simulator;
import sunday.resi.library.Switch;

/**
 * A window for a graphical visualization of the relay clock.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClockFrame extends Frame implements Monitor
{
    private static final long serialVersionUID = 1L;

    private RelayClockCanvas canvas;

    /**
     * The constructor.
     */
    public RelayClockFrame(RelayClock clock, Simulator simulator)
    {
        setTitle("Relay Clock");
        setSize(600, 400);

        // position the frame in the center of the screen
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((ss.width - getWidth()) / 2, (ss.height - getHeight()) / 2);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // clock display
        canvas = new RelayClockCanvas(clock.getDisplay());
        add(canvas, BorderLayout.CENTER);

        // buttons to set the clock
        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(Color.BLACK);
        FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.CENTER);
        buttonPanel.setLayout(buttonPanelLayout);
        add(buttonPanel, BorderLayout.SOUTH);

        // button for setting the hours
        Button hoursButton = new Button("Hours");
        hoursButton.setEnabled(true);
        hoursButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch setHours = clock.getHoursSwitch();
                setHours.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                setHours.release();
            }
        });
        buttonPanel.add(hoursButton);

        // button for setting the minutes
        Button minutesButton = new Button("Minutes");
        minutesButton.setEnabled(true);
        minutesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch setMinutes = clock.getMinutesSwitch();
                setMinutes.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                setMinutes.release();
            }
        });
        buttonPanel.add(minutesButton);

        // handle window events
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                simulator.stop();
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e)
            {
                simulator.stop();
            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {
                simulator.start();
            }
        });

        setVisible(true);
    }

    @Override
    public void monitor()
    {
        canvas.repaint();
    }
}
