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
 * Tests the NOR gate.
 * 
 * @author Peter H&auml;nsgen
 */
public class NorTest
{
    /**
     * Tests a NOR with 1 input. The output value simply follows the input value.
     */
    @Test
    public void testNor1()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nor nor = new Nor(circuit, "Nor", 1);

        Output out0 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nor.getPowerIn());
        new Signal(circuit).from(out0).to(nor.getIn(0));
        new Signal(circuit).from(nor.get_Out()).to(in);

        out0.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

        out0.setValue(true);
        circuit.simulate();
        assertNull(in.getValue());

        out0.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());
    }

    /**
     * Tests a NOR with 2 inputs.
     */
    @Test
    public void testNor2()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nor nor = new Nor(circuit, "Nor", 2);

        Output out0 = new Output();
        Output out1 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nor.getPowerIn());
        new Signal(circuit).from(out0).to(nor.getIn(0));
        new Signal(circuit).from(out1).to(nor.getIn(1));
        new Signal(circuit).from(nor.get_Out()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

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
        assertNull(in.getValue());
    }

    /**
     * Tests a NOR with 3 inputs.
     */
    @Test
    public void testNor3()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nor nor = new Nor(circuit, "Nor", 3);

        Output out0 = new Output();
        Output out1 = new Output();
        Output out2 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nor.getPowerIn());
        new Signal(circuit).from(out0).to(nor.getIn(0));
        new Signal(circuit).from(out1).to(nor.getIn(1));
        new Signal(circuit).from(out2).to(nor.getIn(2));
        new Signal(circuit).from(nor.get_Out()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

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
        assertNull(in.getValue());
    }
}
