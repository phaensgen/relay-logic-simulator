package sunday.resi.library;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;

/**
 * Tests the flip flop component.
 * 
 * @author Peter H&auml;nsgen
 */
public class FlipFlopRTest
{
    @Test
    public void testFlipFlop()
    {
        Circuit circuit = new Circuit();

        Power p = new Power(circuit, "VCC");
        FlipFlopR ff = new FlipFlopR(circuit, "FlipFlopR");

        new Signal(circuit).from(p.getOut()).to(ff.getPowerIn());

        Output _cl = new Output();
        Output cl = new Output();
        Input _q = new Input();
        Input q = new Input();

        new Signal(circuit).from(_cl).to(ff.get_Clock());
        new Signal(circuit).from(cl).to(ff.getClock());
        new Signal(circuit).from(ff.get_Out()).to(_q);
        new Signal(circuit).from(ff.getOut()).to(q);

        // start
        _cl.setValue(true);
        cl.setValue(null);
        circuit.simulate();
        assertTrue(_q.getValue());
        assertNull(q.getValue());

        // clock 1 down
        _cl.setValue(null);
        cl.setValue(true);
        circuit.simulate();
        assertNull(_q.getValue());
        assertTrue(q.getValue());

        // clock 1 up
        _cl.setValue(true);
        cl.setValue(null);
        circuit.simulate();
        assertNull(_q.getValue());
        assertTrue(q.getValue());

        // clock 2 down
        _cl.setValue(null);
        cl.setValue(true);
        circuit.simulate();
        assertTrue(_q.getValue());
        assertNull(q.getValue());

        // clock 2 up
        _cl.setValue(true);
        cl.setValue(null);
        circuit.simulate();
        assertTrue(_q.getValue());
        assertNull(q.getValue());

        // clock 1 down
        _cl.setValue(null);
        cl.setValue(true);
        circuit.simulate();
        assertNull(_q.getValue());
        assertTrue(q.getValue());

        // clock 1 up
        _cl.setValue(true);
        cl.setValue(null);
        circuit.simulate();
        assertNull(_q.getValue());
        assertTrue(q.getValue());
    }
}
