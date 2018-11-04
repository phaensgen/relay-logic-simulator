package sunday.resi.example.relayblinker;

import sunday.resi.common.Circuit;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.util.PartConsoleMonitor;
import sunday.resi.util.console.SystemConsole;
import sunday.resi.util.logicanalyzer.LogicAnalyzer;
import sunday.resi.util.logicanalyzer.LogicAnalyzerFrame;

/**
 * Simulates the relay blinker.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayBlinkerMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Power vcc = new Power(circuit, "VCC");
        RelayBlinker blinker = new RelayBlinker(circuit, "RelayBlinker");

        new Signal(circuit).from(vcc.getOut()).to(blinker.getPowerIn());

        // debug output to system out
        circuit.addMonitor(new PartConsoleMonitor(blinker, new SystemConsole()));

        // add logic analyzer
        LogicAnalyzer logicAnalyzer = new LogicAnalyzer(500);
        logicAnalyzer.addTrack(blinker.getLamp1().getIn().getSignal()).setLabel("Lamp1");
        logicAnalyzer.addTrack(blinker.getLamp2().getIn().getSignal()).setLabel("Lamp2");
        circuit.addMonitor(logicAnalyzer);

        Simulator sim = new Simulator(circuit, 10);

        // visualize behavior in window
        LogicAnalyzerFrame frame = new LogicAnalyzerFrame(sim, logicAnalyzer);
        circuit.addMonitor(frame);

        sim.start();
    }
}
