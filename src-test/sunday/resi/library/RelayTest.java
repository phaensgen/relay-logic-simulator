package sunday.resi.library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Signal;

/**
 * Tests the relay.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTest
{
    @Test
    public void testRelay()
    {
        Circuit circuit = new Circuit();
        Relay r = new Relay(circuit, "Relay");

        // define test signal connectors
        Output coil = new Output();
        Output middle = new Output();
        Input _out = new Input();
        Input out = new Input();

        // connect with relay
        new Signal(circuit).from(coil).to(r.getCoilIn());
        new Signal(circuit).from(middle).to(r.getMiddleIn(0));
        new Signal(circuit).from(r.get_Out(0)).to(_out);
        new Signal(circuit).from(r.getOut(0)).to(out);

        // perform tests
        coil.setValue(null);
        middle.setValue(null);
        r.simulate();
        assertNull(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(null);
        middle.setValue(false);
        r.simulate();
        assertFalse(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(null);
        middle.setValue(true);
        r.simulate();
        assertTrue(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(null);
        r.simulate();
        assertNull(_out.getValue());
        assertNull(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(false);
        r.simulate();
        assertFalse(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(true);
        r.simulate();
        assertTrue(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(true);
        middle.setValue(null);
        r.simulate();
        assertNull(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(true);
        middle.setValue(false);
        r.simulate();
        assertNull(_out.getValue());
        assertFalse(out.getValue());

        coil.setValue(true);
        middle.setValue(true);
        r.simulate();
        assertNull(_out.getValue());
        assertTrue(out.getValue());
    }
}
