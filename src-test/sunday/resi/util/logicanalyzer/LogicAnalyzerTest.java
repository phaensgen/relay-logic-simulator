package sunday.resi.util.logicanalyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import sunday.resi.common.Circuit;
import sunday.resi.common.Signal;
import sunday.resi.util.logicanalyzer.LogicAnalyzer.Track;

/**
 * Some unit tests for the logic analyzer.
 * 
 * @author Peter H&auml;nsgen
 */
public class LogicAnalyzerTest
{
    @Test
    public void testLogicAnalyzer()
    {
        Circuit circuit = new Circuit();
        Signal s0 = new Signal(circuit);
        Signal s1 = new Signal(circuit);

        LogicAnalyzer l = new LogicAnalyzer(5);
        assertEquals(5, l.getMaxValues());

        Track t0 = l.addTrack(s0);
        Track t1 = l.addTrack(s1);

        assertEquals(2, l.getTracks().size());

        // record some values
        s0.setValue(false);
        s1.setValue(false);
        l.monitor();

        s0.setValue(true);
        s1.setValue(false);
        l.monitor();

        s0.setValue(false);
        s1.setValue(true);
        l.monitor();

        s0.setValue(true);
        s1.setValue(true);
        l.monitor();

        s0.setValue(false);
        s1.setValue(false);
        l.monitor();

        s0.setValue(true);
        s1.setValue(false);
        l.monitor();

        s0.setValue(false);
        s1.setValue(true);
        l.monitor();

        // order of tracks must be as they where added
        Iterator<Track> it = l.getTracks().iterator();
        Track r0 = it.next();
        assertEquals(t0, r0);

        Track r1 = it.next();
        assertEquals(t1, r1);

        // check recordings
        List<Boolean> l0 = r0.getValues();
        assertEquals(5, l0.size());
        assertFalse(l0.get(0));
        assertTrue(l0.get(1));
        assertFalse(l0.get(2));
        assertTrue(l0.get(3));
        assertFalse(l0.get(4));

        List<Boolean> l1 = r1.getValues();
        assertEquals(5, l1.size());
        assertTrue(l1.get(0));
        assertTrue(l1.get(1));
        assertFalse(l1.get(2));
        assertFalse(l1.get(3));
        assertTrue(l1.get(4));
    }
}
