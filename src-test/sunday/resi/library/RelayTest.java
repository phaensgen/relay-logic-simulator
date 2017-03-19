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
    /**
     * Test relay with a switch with one middle input and two outputs.
     */
    @Test
    public void testRelaySwitch()
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
        circuit.simulate();
        assertFalse(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(null);
        middle.setValue(true);
        circuit.simulate();
        assertTrue(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(null);
        circuit.simulate();
        assertNull(_out.getValue());
        assertNull(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(false);
        circuit.simulate();
        assertFalse(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(false);
        middle.setValue(true);
        circuit.simulate();
        assertTrue(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(true);
        middle.setValue(null);
        circuit.simulate();
        assertNull(_out.getValue());
        assertNull(out.getValue());

        coil.setValue(true);
        middle.setValue(false);
        circuit.simulate();
        assertNull(_out.getValue());
        assertFalse(out.getValue());

        coil.setValue(true);
        middle.setValue(true);
        circuit.simulate();
        assertNull(_out.getValue());
        assertTrue(out.getValue());
    }

    /**
     * Test relay with a switch with two inputs and one middle output.
     */
    @Test
    public void testRelaySwotch()
    {
        Circuit circuit = new Circuit();
        Relay r = new Relay(circuit, "Relay");

        // define test signal connectors
        Output coil = new Output();
        Input middle = new Input();
        Output _out = new Output();
        Output out = new Output();

        // connect with relay
        new Signal(circuit).from(coil).to(r.getCoilIn());
        new Signal(circuit).from(r.getMiddleOut(0)).to(middle);
        new Signal(circuit).from(_out).to(r.get_In(0));
        new Signal(circuit).from(out).to(r.getIn(0));

        // perform tests
        coil.setValue(null);
        _out.setValue(null);
        out.setValue(null);
        circuit.simulate();
        assertNull(middle.getValue());

        coil.setValue(null);
        _out.setValue(false);
        out.setValue(null);
        circuit.simulate();
        assertFalse(middle.getValue());

        coil.setValue(null);
        _out.setValue(true);
        out.setValue(null);
        circuit.simulate();
        assertTrue(middle.getValue());

        coil.setValue(false);
        _out.setValue(null);
        out.setValue(null);
        circuit.simulate();
        assertNull(middle.getValue());

        coil.setValue(false);
        _out.setValue(false);
        out.setValue(null);
        circuit.simulate();
        assertFalse(middle.getValue());

        coil.setValue(false);
        _out.setValue(true);
        out.setValue(null);
        circuit.simulate();
        assertTrue(middle.getValue());

        coil.setValue(true);
        _out.setValue(null);
        out.setValue(null);
        circuit.simulate();
        assertNull(middle.getValue());

        coil.setValue(true);
        _out.setValue(null);
        out.setValue(false);
        circuit.simulate();
        assertFalse(middle.getValue());

        coil.setValue(true);
        _out.setValue(null);
        out.setValue(true);
        circuit.simulate();
        assertTrue(middle.getValue());
    }
}
