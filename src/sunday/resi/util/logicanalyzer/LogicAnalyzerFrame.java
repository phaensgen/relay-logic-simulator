package sunday.resi.util.logicanalyzer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import sunday.resi.common.Monitor;
import sunday.resi.common.Simulator;
import sunday.resi.util.SimulatorFrame;

/**
 * A simple window showing a logic analyzer.
 * 
 * @author Peter H&auml;nsgen
 */
public class LogicAnalyzerFrame extends SimulatorFrame implements Monitor
{
    private static final long serialVersionUID = 1L;

    private LogicAnalyzerCanvas canvas;

    /**
     * The constructor.
     */
    public LogicAnalyzerFrame(Simulator simulator, LogicAnalyzer logicAnalyzer)
    {
        super(simulator);

        setTitle("Logic Analyzer");
        setSize(600, 400);

        // position the frame in the center of the screen
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((ss.width - getWidth()) / 2, (ss.height - getHeight()) / 2);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // logic analyzer display
        canvas = new LogicAnalyzerCanvas(logicAnalyzer);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void monitor()
    {
        canvas.repaint();
    }
}
