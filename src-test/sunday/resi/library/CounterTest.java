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
 * Tests the 4-bit counter.
 * 
 * @author Peter H&auml;nsgen
 */
public class CounterTest
{
    @Test
    public void testCounter()
    {
        Circuit circuit = new Circuit();

        Power power = new Power(circuit, "VCC");
        Counter counter = new Counter(circuit, "Counter");

        new Signal(circuit).from(power.getOut()).to(counter.getPowerIn());

        Output _clock = new Output();
        Output clock = new Output();
        Input out0 = new Input();
        Input out1 = new Input();
        Input out2 = new Input();
        Input out3 = new Input();

        // test wirings
        new Signal(circuit).from(_clock).to(counter.get_Clock());
        new Signal(circuit).from(clock).to(counter.getClock());
        new Signal(circuit).from(counter.getOut0()).to(out0);
        new Signal(circuit).from(counter.getOut1()).to(out1);
        new Signal(circuit).from(counter.getOut2()).to(out2);
        new Signal(circuit).from(counter.getOut3()).to(out3);

        // test counter
        // initially, output is 0
        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 1
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 2
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 3
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        // 4
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 5
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 6
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 7
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        // 8
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 9
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 10
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 11
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        // 12
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 13
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 14
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 15
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        // 0
        _clock.setValue(null);
        clock.setValue(true);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertTrue(out0.getValue());
        assertTrue(out1.getValue());
        assertTrue(out2.getValue());
        assertTrue(out3.getValue());

        _clock.setValue(true);
        clock.setValue(null);
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        circuit.simulate();
        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
    }
}
