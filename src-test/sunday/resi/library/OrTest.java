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
 * Tests the OR gate.
 * 
 * @author Peter H&auml;nsgen
 */
public class OrTest
{
    /**
     * Tests an OR with 1 input. The output value simply follows the input value.
     */
    @Test
    public void testOr1()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Or or = new Or(circuit, "Or", 1).watch();

        Output out0 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(or.getPowerIn());
        new Signal(circuit).from(out0).to(or.getIn(0));
        new Signal(circuit).from(or.getOut()).to(in);

        out0.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());
    }

    /**
     * Tests an OR with 2 inputs.
     */
    @Test
    public void testOr2()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Or or = new Or(circuit, "Or", 2);

        Output out0 = new Output();
        Output out1 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(or.getPowerIn());
        new Signal(circuit).from(out0).to(or.getIn(0));
        new Signal(circuit).from(out1).to(or.getIn(1));
        new Signal(circuit).from(or.getOut()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());
    }

    /**
     * Tests an OR with 3 inputs.
     */
    @Test
    public void testOr3()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Or or = new Or(circuit, "Or", 3);

        Output out0 = new Output();
        Output out1 = new Output();
        Output out2 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(or.getPowerIn());
        new Signal(circuit).from(out0).to(or.getIn(0));
        new Signal(circuit).from(out1).to(or.getIn(1));
        new Signal(circuit).from(out2).to(or.getIn(2));
        new Signal(circuit).from(or.getOut()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        out2.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        out2.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        out2.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        out2.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        out2.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());
    }
}
