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
 * Tests the NAND gate.
 * 
 * @author Peter H&auml;nsgen
 */
public class NandTest
{
    /**
     * Tests a NAND with 1 input. The output value simply inverts the input value.
     */
    @Test
    public void testNand1()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nand nand = new Nand(circuit, "Nand", 1);

        Output out0 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nand.getPowerIn());
        new Signal(circuit).from(out0).to(nand.getIn(0));
        new Signal(circuit).from(nand.get_Out()).to(in);

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
     * Tests a NAND with 2 inputs.
     */
    @Test
    public void testNand2()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nand nand = new Nand(circuit, "Nand", 2);

        Output out0 = new Output();
        Output out1 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nand.getPowerIn());
        new Signal(circuit).from(out0).to(nand.getIn(0));
        new Signal(circuit).from(out1).to(nand.getIn(1));
        new Signal(circuit).from(nand.get_Out()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

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
        assertNull(in.getValue());
    }

    /**
     * Tests a NAND with 3 inputs.
     */
    @Test
    public void testNand3()
    {
        Circuit circuit = new Circuit();
        Power power = new Power(circuit, "VCC");

        Nand nand = new Nand(circuit, "Nand", 3);

        Output out0 = new Output();
        Output out1 = new Output();
        Output out2 = new Output();
        Input in = new Input();

        new Signal(circuit).from(power.getOut()).to(nand.getPowerIn());
        new Signal(circuit).from(out0).to(nand.getIn(0));
        new Signal(circuit).from(out1).to(nand.getIn(1));
        new Signal(circuit).from(out2).to(nand.getIn(2));
        new Signal(circuit).from(nand.get_Out()).to(in);

        out0.setValue(null);
        out1.setValue(null);
        out2.setValue(null);
        circuit.simulate();
        assertTrue(in.getValue());

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
        assertNull(in.getValue());
    }
}
