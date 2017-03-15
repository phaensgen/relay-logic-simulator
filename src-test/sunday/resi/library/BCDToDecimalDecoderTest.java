package sunday.resi.library;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sunday.resi.common.Circuit;
import sunday.resi.common.Input;
import sunday.resi.common.Output;
import sunday.resi.common.Power;
import sunday.resi.common.Signal;

/**
 * Tests the BCD-to-decimal decoder.
 * 
 * @author Peter H&auml;nsgen
 */
public class BCDToDecimalDecoderTest
{
    private BCDToDecimalDecoder decoder;

    private Output in0;

    private Output in1;

    private Output in2;

    private Output in3;

    private Input out0;

    private Input out1;

    private Input out2;

    private Input out3;

    private Input out4;

    private Input out5;

    private Input out6;

    private Input out7;

    private Input out8;

    private Input out9;

    private Input outA;

    private Input outB;

    private Input outC;

    private Input outD;

    private Input outE;

    private Input outF;

    @Before
    public void before()
    {
        Circuit circuit = new Circuit();

        Power p = new Power(circuit, "VCC");
        decoder = new BCDToDecimalDecoder(circuit, "Decoder");

        new Signal(circuit).from(p.getOut()).to(decoder.getPowerIn());

        in0 = new Output();
        in1 = new Output();
        in2 = new Output();
        in3 = new Output();

        out0 = new Input();
        out1 = new Input();
        out2 = new Input();
        out3 = new Input();
        out4 = new Input();
        out5 = new Input();
        out6 = new Input();
        out7 = new Input();
        out8 = new Input();
        out9 = new Input();
        outA = new Input();
        outB = new Input();
        outC = new Input();
        outD = new Input();
        outE = new Input();
        outF = new Input();

        // connect test wirings
        new Signal(circuit).from(in0).to(decoder.getIn0());
        new Signal(circuit).from(in1).to(decoder.getIn1());
        new Signal(circuit).from(in2).to(decoder.getIn2());
        new Signal(circuit).from(in3).to(decoder.getIn3());

        new Signal(circuit).from(decoder.getOut0()).to(out0);
        new Signal(circuit).from(decoder.getOut1()).to(out1);
        new Signal(circuit).from(decoder.getOut2()).to(out2);
        new Signal(circuit).from(decoder.getOut3()).to(out3);
        new Signal(circuit).from(decoder.getOut4()).to(out4);
        new Signal(circuit).from(decoder.getOut5()).to(out5);
        new Signal(circuit).from(decoder.getOut6()).to(out6);
        new Signal(circuit).from(decoder.getOut7()).to(out7);
        new Signal(circuit).from(decoder.getOut8()).to(out8);
        new Signal(circuit).from(decoder.getOut9()).to(out9);
        new Signal(circuit).from(decoder.getOutA()).to(outA);
        new Signal(circuit).from(decoder.getOutB()).to(outB);
        new Signal(circuit).from(decoder.getOutC()).to(outC);
        new Signal(circuit).from(decoder.getOutD()).to(outD);
        new Signal(circuit).from(decoder.getOutE()).to(outE);
        new Signal(circuit).from(decoder.getOutF()).to(outF);
    }

    @Test
    public void test0()
    {
        in0.setValue(false);
        in1.setValue(false);
        in2.setValue(false);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertTrue(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test1()
    {
        in0.setValue(true);
        in1.setValue(false);
        in2.setValue(false);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertTrue(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test2()
    {
        in0.setValue(false);
        in1.setValue(true);
        in2.setValue(false);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertTrue(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test3()
    {
        in0.setValue(true);
        in1.setValue(true);
        in2.setValue(false);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertTrue(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test4()
    {
        in0.setValue(false);
        in1.setValue(false);
        in2.setValue(true);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertTrue(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test5()
    {
        in0.setValue(true);
        in1.setValue(false);
        in2.setValue(true);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertTrue(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test6()
    {
        in0.setValue(false);
        in1.setValue(true);
        in2.setValue(true);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertTrue(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test7()
    {
        in0.setValue(true);
        in1.setValue(true);
        in2.setValue(true);
        in3.setValue(false);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertTrue(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test8()
    {
        in0.setValue(false);
        in1.setValue(false);
        in2.setValue(false);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertTrue(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void test9()
    {
        in0.setValue(true);
        in1.setValue(false);
        in2.setValue(false);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertTrue(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testA()
    {
        in0.setValue(false);
        in1.setValue(true);
        in2.setValue(false);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertTrue(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testB()
    {
        in0.setValue(true);
        in1.setValue(true);
        in2.setValue(false);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertTrue(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testC()
    {
        in0.setValue(false);
        in1.setValue(false);
        in2.setValue(true);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertTrue(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testD()
    {
        in0.setValue(true);
        in1.setValue(false);
        in2.setValue(true);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertTrue(outD.getValue());
        assertNull(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testE()
    {
        in0.setValue(false);
        in1.setValue(true);
        in2.setValue(true);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertTrue(outE.getValue());
        assertNull(outF.getValue());
    }

    @Test
    public void testF()
    {
        in0.setValue(true);
        in1.setValue(true);
        in2.setValue(true);
        in3.setValue(true);

        decoder.getCircuit().simulate();

        assertNull(out0.getValue());
        assertNull(out1.getValue());
        assertNull(out2.getValue());
        assertNull(out3.getValue());
        assertNull(out4.getValue());
        assertNull(out5.getValue());
        assertNull(out6.getValue());
        assertNull(out7.getValue());
        assertNull(out8.getValue());
        assertNull(out9.getValue());
        assertNull(outA.getValue());
        assertNull(outB.getValue());
        assertNull(outC.getValue());
        assertNull(outD.getValue());
        assertNull(outE.getValue());
        assertTrue(outF.getValue());
    }
}
