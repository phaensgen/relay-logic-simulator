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
 * Tests the XNOR gate.
 * 
 * @author Peter H&auml;nsgen
 */
public class XnorTest
{
    @Test
    public void testXnor()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Xnor xnor = new Xnor(circuit, "Xnor");

        Output out0 = new Output();
        Output out1 = new Output();
        Input _in = new Input();

        new Signal(circuit).from(power.getOut()).to(xnor.getPowerIn());
        new Signal(circuit).from(out0).to(xnor.getIn0());
        new Signal(circuit).from(out1).to(xnor.getIn1());
        new Signal(circuit).from(xnor.get_Out()).to(_in);

        out0.setValue(null);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(_in.getValue());

        out0.setValue(false);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(_in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        circuit.simulate();
        assertNull(_in.getValue());

        out0.setValue(null);
        out1.setValue(false);
        circuit.simulate();
        assertTrue(_in.getValue());

        out0.setValue(false);
        out1.setValue(false);
        circuit.simulate();
        assertTrue(_in.getValue());

        out0.setValue(true);
        out1.setValue(false);
        circuit.simulate();
        assertNull(_in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        circuit.simulate();
        assertNull(_in.getValue());

        out0.setValue(false);
        out1.setValue(true);
        circuit.simulate();
        assertNull(_in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(_in.getValue());
    }
}
