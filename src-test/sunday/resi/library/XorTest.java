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
 * Tests the XOR gate.
 * 
 * @author Peter H&auml;nsgen
 */
public class XorTest
{
    @Test
    public void testXor()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Xor xor = new Xor(circuit, "Xor");

        Output out0 = new Output();
        Output out1 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(xor.getPowerIn());
        new Signal(circuit).from(out0).to(xor.getIn0());
        new Signal(circuit).from(out1).to(xor.getIn1());
        new Signal(circuit).from(xor.getOut()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(false);
        out1.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(false);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(false);
        out1.setValue(false);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(false);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(false);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());
    }
}
