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
 * Tests the decimal-to-7-segment decoder.
 * 
 * @author Peter H&auml;nsgen
 */
public class DecimalToSevenSegmentDecoderTest
{
    private DecimalToSevenSegmentDecoder decoder;

    private Output in0;

    private Output in1;

    private Output in2;

    private Output in3;

    private Output in4;

    private Output in5;

    private Output in6;

    private Output in7;

    private Output in8;

    private Output in9;

    private Output inA;

    private Output inB;

    private Output inC;

    private Output inD;

    private Output inE;

    private Output inF;

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

        decoder = new DecimalToSevenSegmentDecoder(circuit, "Decoder");

        // test signals
        in0 = new Output();
        in1 = new Output();
        in2 = new Output();
        in3 = new Output();
        in4 = new Output();
        in5 = new Output();
        in6 = new Output();
        in7 = new Output();
        in8 = new Output();
        in9 = new Output();
        inA = new Output();
        inB = new Output();
        inC = new Output();
        inD = new Output();
        inE = new Output();
        inF = new Output();
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
        new Signal(circuit).from(in6).to(decoder.getIn6());
        new Signal(circuit).from(in7).to(decoder.getIn7());
        new Signal(circuit).from(in8).to(decoder.getIn8());
        new Signal(circuit).from(in9).to(decoder.getIn9());
        new Signal(circuit).from(inA).to(decoder.getInA());
        new Signal(circuit).from(inB).to(decoder.getInB());
        new Signal(circuit).from(inC).to(decoder.getInC());
        new Signal(circuit).from(inD).to(decoder.getInD());
        new Signal(circuit).from(inE).to(decoder.getInE());
        new Signal(circuit).from(inF).to(decoder.getInF());
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

    @Test
    public void test6()
    {
        in6.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test7()
    {
        in7.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertFalse(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test8()
    {
        in8.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void test9()
    {
        in9.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertFalse(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testA()
    {
        inA.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testB()
    {
        inB.setValue(true);
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testC()
    {
        inC.setValue(true);
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertFalse(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testD()
    {
        inD.setValue(true);
        decoder.getCircuit().simulate();
        assertFalse(Boolean.TRUE.equals(outA.getValue()));
        assertTrue(Boolean.TRUE.equals(outB.getValue()));
        assertTrue(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertFalse(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testE()
    {
        inE.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertFalse(Boolean.TRUE.equals(outC.getValue()));
        assertTrue(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }

    @Test
    public void testF()
    {
        inF.setValue(true);
        decoder.getCircuit().simulate();
        assertTrue(Boolean.TRUE.equals(outA.getValue()));
        assertFalse(Boolean.TRUE.equals(outB.getValue()));
        assertFalse(Boolean.TRUE.equals(outC.getValue()));
        assertFalse(Boolean.TRUE.equals(outD.getValue()));
        assertTrue(Boolean.TRUE.equals(outE.getValue()));
        assertTrue(Boolean.TRUE.equals(outF.getValue()));
        assertTrue(Boolean.TRUE.equals(outG.getValue()));
    }
}
