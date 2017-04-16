package sunday.resi.library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;

/**
 * Tests the 7-segment decoder with input range 0..5.
 * 
 * @author Peter H&auml;nsgen
 */
public class SevenSegmentDecoder6Test
{
    private SevenSegmentDecoder6 decoder;

    private Output in0;

    private Output in1;

    private Output in2;

    private Output in3;

    private Output in4;

    private Output in5;

    private Input outA;

    private Input outB;

    private Input outC;

    private Input outD;

    private Input outE;

    private Input outF;

    private Input outG;

    @Before
    public void before()
    {
        Circuit circuit = new Circuit();
        Power p = new Power(circuit, "VCC");

        decoder = new SevenSegmentDecoder6(circuit, "Decoder");

        // test signals
        in0 = new Output();
        in1 = new Output();
        in2 = new Output();
        in3 = new Output();
        in4 = new Output();
        in5 = new Output();
        outA = new Input();
        outB = new Input();
        outC = new Input();
        outD = new Input();
        outE = new Input();
        outF = new Input();
        outG = new Input();

        // connect test signals
        new Signal(circuit).from(p.getOut()).to(decoder.getPowerIn());

        new Signal(circuit).from(in0).to(decoder.getIn0());
        new Signal(circuit).from(in1).to(decoder.getIn1());
        new Signal(circuit).from(in2).to(decoder.getIn2());
        new Signal(circuit).from(in3).to(decoder.getIn3());
        new Signal(circuit).from(in4).to(decoder.getIn4());
        new Signal(circuit).from(in5).to(decoder.getIn5());
        new Signal(circuit).from(decoder.getOutA()).to(outA);
        new Signal(circuit).from(decoder.getOutB()).to(outB);
        new Signal(circuit).from(decoder.getOutC()).to(outC);
        new Signal(circuit).from(decoder.getOutD()).to(outD);
        new Signal(circuit).from(decoder.getOutE()).to(outE);
        new Signal(circuit).from(decoder.getOutF()).to(outF);
        new Signal(circuit).from(decoder.getOutG()).to(outG);
    }

    @Test
    public void testOff()
    {
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertFalse(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertFalse(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test0()
    {
        in0.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertFalse(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test1()
    {
        in1.setValue(true);
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertFalse(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test2()
    {
        in2.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertFalse(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test3()
    {
        in3.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test4()
    {
        in4.setValue(true);
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test5()
    {
        in5.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }
}
