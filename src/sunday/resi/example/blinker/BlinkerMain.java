package sunday.resi.example.blinker;

import sunday.resi.common.Circuit;
import sunday.resi.common.Signal;
import sunday.resi.common.Simulator;
import sunday.resi.util.PartConsoleMonitor;
import sunday.resi.util.console.SystemConsole;
import sunday.resi.util.logicanalyzer.LogicAnalyzer;
import sunday.resi.util.logicanalyzer.LogicAnalyzer.Track;
import sunday.resi.util.logicanalyzer.LogicAnalyzerFrame;

/**
 * Simulates the blinker.
 * 
 * @author Peter H&auml;nsgen
 */
public class BlinkerMain
{
    public static void main(String[] args)
    {
        Circuit circuit = new Circuit();

        Blinker blinker = new Blinker(circuit, "Blinker");

        circuit.addMonitor(new PartConsoleMonitor(blinker.getLamp(), new SystemConsole()));

        Simulator sim = new Simulator(circuit, 10);

        LogicAnalyzer logicAnalyzer = new LogicAnalyzer(500);
        circuit.addMonitor(logicAnalyzer);

        Signal out = blinker.getLamp().getIn().getSignal();
        Track t = logicAnalyzer.addTrack(out);
        t.setLabel("Lamp");

        LogicAnalyzerFrame logicAnalyzerFrame = new LogicAnalyzerFrame(sim, logicAnalyzer);
        circuit.addMonitor(logicAnalyzerFrame);

        sim.start();
    }
}
