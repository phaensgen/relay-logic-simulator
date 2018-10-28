package sunday.resi.example.relaytimer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import sunday.resi.common.Monitor;
import sunday.resi.common.Simulator;
import sunday.resi.library.Switch;
import sunday.resi.util.Console;

/**
 * A window for a graphical visualization of the relay timer.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimerFrame extends Frame implements Monitor, Console
{
    private static final long serialVersionUID = 1L;

    private RelayTimerCanvas canvas;

    private TextArea textArea;

    /**
     * The constructor.
     */
    public RelayTimerFrame(RelayTimer timer, Simulator simulator)
    {
        setTitle("Relay Timer");
        setSize(600, 400);

        // position the frame in the center of the screen
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((ss.width - getWidth()) / 2, (ss.height - getHeight()) / 2);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // timer display
        canvas = new RelayTimerCanvas(timer);
        add(canvas, BorderLayout.CENTER);

        // buttons to set the timer
        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(Color.BLACK);
        FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.CENTER);
        buttonPanel.setLayout(buttonPanelLayout);
        add(buttonPanel, BorderLayout.NORTH);

        // button for setting the 10 minutes
        Button setM1Button = new Button("Set Minutes 10");
        setM1Button.setEnabled(true);
        setM1Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch setM1Switch = timer.getM1Switch();
                setM1Switch.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                setM1Switch.release();
            }
        });
        buttonPanel.add(setM1Button);

        // button for setting the minutes
        Button setM0Button = new Button("Set Minutes");
        setM0Button.setEnabled(true);
        setM0Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch setM0Switch = timer.getM0Switch();
                setM0Switch.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                setM0Switch.release();
            }
        });
        buttonPanel.add(setM0Button);

        // button for starting the countdown
        Button startButton = new Button("Start / Stop");
        startButton.setEnabled(true);
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch startSwitch = timer.getStartSwitch();
                startSwitch.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                startSwitch.release();
            }
        });
        buttonPanel.add(startButton);

        // button for resetting the countdown
        Button resetButton = new Button("Reset");
        resetButton.setEnabled(true);
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Switch resetSwitch = timer.getResetSwitch();
                resetSwitch.push();
                try
                {
                    // we have to wait for a number of simulation cycles so that the change becomes effective
                    Thread.sleep(100);
                }
                catch (InterruptedException e1)
                {
                }
                resetSwitch.release();
            }
        });
        buttonPanel.add(resetButton);

        textArea = new TextArea();
        add(textArea, BorderLayout.SOUTH);

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

    /**
     * Prints a line of text to the console text area.
     */
    @Override
    public void println(String line)
    {
        String text = textArea.getText();
        text += '\n' + line;
        textArea.setText(text);
    }
}
