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
 * Tests the 2-bit counter.
 * 
 * @author Peter H&auml;nsgen
 */
public class Counter4Test
{
    @Test
    public void testCounter()
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Counter4 counter = new Counter4(circuit, "Counter");

        new Signal(circuit).from(power.getOut()).to(counter.getPowerIn());

        Output _clock = new Output();
        Output clock = new Output();
        Input out0 = new Input();
        Input out1 = new Input();

        // test wirings
        new Signal(circuit).from(_clock).to(counter.get_Clock());
        new Signal(circuit).from(clock).to(counter.getClock());
        new Signal(circuit).from(counter.getOut0()).to(out0);
        new Signal(circuit).from(counter.getOut1()).to(out1);

        // test counter
        // initially, output is 0
        _clock.setValue(true);
        clock.setValue(null);
        simulate(circuit);
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(null);
        clock.setValue(true);
        simulate(circuit);
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        // 1
        _clock.setValue(null);
        clock.setValue(true);
        simulate(circuit);
        assertNull(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        simulate(circuit);
        assertTrue(out0.getValue());
        assertNull(out1.getValue());

        // 2
        _clock.setValue(null);
        clock.setValue(true);
        simulate(circuit);
        assertTrue(out0.getValue());
        assertNull(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        simulate(circuit);
        assertNull(out0.getValue());
        assertTrue(out1.getValue());

        // 3
        _clock.setValue(null);
        clock.setValue(true);
        simulate(circuit);
        assertNull(out0.getValue());
        assertTrue(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        simulate(circuit);
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());

        // 0
        _clock.setValue(null);
        clock.setValue(true);
        simulate(circuit);
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        simulate(circuit);
        assertNull(out0.getValue());
        assertNull(out1.getValue());
    }

    /**
     * Performs a number of simulation steps.
     */
    private void simulate(Circuit circuit)
    {
        for (int i = 0; i < 5; i++)
        {
            circuit.simulate();
        }
    }
}
