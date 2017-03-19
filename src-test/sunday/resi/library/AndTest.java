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
 * Tests the AND gate component.
 * 
 * @author Peter H&auml;nsgen
 */
public class AndTest
{
    /**
     * Tests an AND with 1 input. The output value simply follows the input value.
     */
    @Test
    public void testAnd1()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        And and = new And(circuit, "And", 1);

        Output out0 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(and.getPowerIn());
        new Signal(circuit).from(out0).to(and.getIn(0));
        new Signal(circuit).from(and.getOut()).to(in);

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
     * Tests an AND with 2 inputs.
     */
    @Test
    public void testAnd2()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        And and = new And(circuit, "And", 2);

        Output out0 = new Output();
        Output out1 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(and.getPowerIn());
        new Signal(circuit).from(out0).to(and.getIn(0));
        new Signal(circuit).from(out1).to(and.getIn(1));
        new Signal(circuit).from(and.getOut()).to(in);

        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());
    }

    /**
     * Tests an AND with 3 inputs.
     */
    @Test
    public void testAnd3()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        And and = new And(circuit, "And", 3);

        Output out0 = new Output();
        Output out1 = new Output();
        Output out2 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(and.getPowerIn());
        new Signal(circuit).from(out0).to(and.getIn(0));
        new Signal(circuit).from(out1).to(and.getIn(1));
        new Signal(circuit).from(out2).to(and.getIn(2));
        new Signal(circuit).from(and.getOut()).to(in);

        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        out2.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        out2.setValue(null);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(null);
        out2.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        out1.setValue(true);
        out2.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(true);
        out1.setValue(true);
        out2.setValue(true);
        circuit.simulate();
        assertTrue(in.getValue());
    }
}
