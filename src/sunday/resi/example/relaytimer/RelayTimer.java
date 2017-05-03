package sunday.resi.example.relaytimer;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Signal;
import sunday.resi.library.And;
import sunday.resi.library.BCDDecoder10;
import sunday.resi.library.Bell;
import sunday.resi.library.Clock;
import sunday.resi.library.Counter16;
import sunday.resi.library.Counter8;
import sunday.resi.library.FlipFlop;
import sunday.resi.library.Relay;
import sunday.resi.library.Switch;

/**
 * This is a simulator for a digital timer built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayTimer extends Component
{
    private final Input powerIn;

    private final RelayTimerDigitPCB digitS0;

    private final RelayTimerDigitPCB digitS1;

    private final RelayTimerDigitPCB digitM0;

    private final RelayTimerDigitPCB digitM1;

    private final Switch setM0Switch;

    private final Switch setM1Switch;

    private final Switch startSwitch;

    private final Switch resetSwitch;

    /**
     * The constructor.
     */
    public RelayTimer(Circuit parent, String name)
    {
        super(parent, name);

        powerIn = new Input();

        Circuit local = getLocalCircuit();

        Clock clock = new Clock(local, name + "Clock", 500, TimeUnit.MILLISECONDS);

        Counter16 counterS0 = new Counter16(local, name + "_CounterS0");
        Counter8 counterS1 = new Counter8(local, name + "_CounterS1");
        Counter16 counterM0 = new Counter16(local, name + "_CounterM0");
        Counter8 counterM1 = new Counter8(local, name + "_CounterM1");

        BCDDecoder10 bcdS0 = new BCDDecoder10(local, name + "_BCDS0");
        BCDDecoder10 bcdS1 = new BCDDecoder10(local, name + "_BCDS1");
        BCDDecoder10 bcdM0 = new BCDDecoder10(local, name + "_BCDM0");
        BCDDecoder10 bcdM1 = new BCDDecoder10(local, name + "_BCDM1");

        RelayTimerDecoder10PCB decoderS0 = new RelayTimerDecoder10PCB(local, name + "_DecoderS0");
        RelayTimerDecoder6PCB decoderS1 = new RelayTimerDecoder6PCB(local, name + "_DecoderS1");
        RelayTimerDecoder10PCB decoderM0 = new RelayTimerDecoder10PCB(local, name + "_DecoderM0");
        RelayTimerDecoder6PCB decoderM1 = new RelayTimerDecoder6PCB(local, name + "_DecoderM1");

        digitS0 = new RelayTimerDigitPCB(local, name + "_DigitS0");
        digitS1 = new RelayTimerDigitPCB(local, name + "_DigitS1");
        digitM0 = new RelayTimerDigitPCB(local, name + "_DigitM0");
        digitM1 = new RelayTimerDigitPCB(local, name + "_DigitM1");

        setM0Switch = new Switch(local, name + "_SetM0Switch");
        setM1Switch = new Switch(local, name + "_SetM1Switch");
        startSwitch = new Switch(local, name + "_StartSwitch");
        resetSwitch = new Switch(local, name + "_ResetSwitch");

        Relay resetS0 = new Relay(local, name + "_ResetS0");
        Relay resetS1 = new Relay(local, name + "_ResetS1");
        Relay resetM0 = new Relay(local, name + "_ResetM0");
        Relay resetM1 = new Relay(local, name + "_ResetM1");
        Relay reset = new Relay(local, name + "_Reset");

        Relay clockS0 = new Relay(local, name + "_ClockS0");
        Relay clockM0 = new Relay(local, name + "_ClockM0");
        Relay clockM1 = new Relay(local, name + "_ClockM1");

        Joint jointM0 = new Joint(local, name + "_JointM0");
        Joint jointM1 = new Joint(local, name + "_JointM1");

        FlipFlop startStop = new FlipFlop(local, name + "_StartStop");

        Bell bell = new Bell(local, name + "_Bell");
        And alarm = new And(local, name + "_Alarm", 5);

        // TODO alarm when 00 and seconds1=5 (for 10 seconds alarm)
        // TODO pre alarm when 01:59 (for 1 seconds alarm)
        // TODO stop clock when overflow to 0000
        // TODO connect reset switch (or remove it)
        // TODO restructure to meet final PCB layout and move parts

        // internal wirings
        // connect power
        new Signal(local).from(powerIn).to(resetS0.getMiddleIn(1), resetS1.getMiddleIn(1), resetM0.getMiddleIn(1),
            resetM1.getMiddleIn(1), bcdS0.getPowerIn(), bcdS1.getPowerIn(), bcdM0.getPowerIn(), bcdM1.getPowerIn(),
            decoderS0.getPowerIn(), decoderS1.getPowerIn(), decoderM0.getPowerIn(), decoderM1.getPowerIn(),
            setM0Switch.getMiddleIn(), setM1Switch.getMiddleIn(), startSwitch.getMiddleIn(), resetSwitch.getMiddleIn(),
            clockM0.getMiddleIn(0), clockM1.getMiddleIn(0), startStop.getPowerIn(), alarm.getPowerIn(),
            reset.getMiddleIn(0));

        new Signal(local).from(resetS0.get_Out(0)).to(counterS0.getPowerIn());
        new Signal(local).from(resetS1.get_Out(0)).to(counterS1.getPowerIn());
        new Signal(local).from(resetM0.get_Out(0)).to(counterM0.getPowerIn());
        new Signal(local).from(resetM1.get_Out(0)).to(counterM1.getPowerIn());

        new Signal(local).from(resetSwitch.getOut()).to(reset.getCoilIn());
        new Signal(local).from(reset.get_Out(0)).to(resetS0.getMiddleIn(0), resetS1.getMiddleIn(0),
            resetM0.getMiddleIn(0), resetM1.getMiddleIn(0));

        new Signal(local).from(alarm.getOut()).to(bell.getIn());

        // connect start switch with clock generator
        new Signal(local).from(startSwitch.get_Out()).to(startStop.get_Clock());
        new Signal(local).from(startSwitch.getOut()).to(startStop.getClock());
        new Signal(local).from(startStop.getOut()).to(clockS0.getCoilIn(), alarm.getIn(4));
        new Signal(local).from(clock.get_Out()).to(clockS0.getMiddleIn(0));
        new Signal(local).from(clock.getOut()).to(clockS0.getMiddleIn(1));
        new Signal(local).from(clockS0.getOut(0)).to(counterS0.get_Clock());
        new Signal(local).from(clockS0.getOut(1)).to(counterS0.getClock());

        // connect clock and overflows with counters
        // new Signal(local).from(clock.get_Out()).to(counterS0.get_Clock());
        // new Signal(local).from(clock.getOut()).to(counterS0.getClock());
        new Signal(local).from(resetS0.get_Out(1)).to(counterS1.get_Clock());
        new Signal(local).from(resetS0.getOut(1)).to(counterS1.getClock());
        new Signal(local).from(resetS1.getOut(1)).to(jointM0.getIn(0));
        new Signal(local).from(resetM0.getOut(1)).to(jointM1.getIn(0));
        // TODO new Signal(local).from(resetM1.getOut(1)).to(globalStop);

        new Signal(local).from(setM0Switch.getOut()).to(jointM0.getIn(1));
        new Signal(local).from(jointM0.getOut()).to(clockM0.getCoilIn());
        new Signal(local).from(clockM0.get_Out(0)).to(counterM0.get_Clock());
        new Signal(local).from(clockM0.getOut(0)).to(counterM0.getClock());

        new Signal(local).from(setM1Switch.getOut()).to(jointM1.getIn(1));
        new Signal(local).from(jointM1.getOut()).to(clockM1.getCoilIn());
        new Signal(local).from(clockM1.get_Out(0)).to(counterM1.get_Clock());
        new Signal(local).from(clockM1.getOut(0)).to(counterM1.getClock());

        // decimal decoders
        new Signal(local).from(counterS0.get_Out0()).to(alarm.getIn(0));
        new Signal(local).from(counterS0.getOut0()).to(bcdS0.getIn0());
        new Signal(local).from(counterS0.getOut1()).to(bcdS0.getIn1());
        new Signal(local).from(counterS0.getOut2()).to(bcdS0.getIn2());
        new Signal(local).from(counterS0.getOut3()).to(bcdS0.getIn3());

        new Signal(local).from(counterS1.getOut0()).to(bcdS1.getIn0());
        new Signal(local).from(counterS1.getOut1()).to(bcdS1.getIn1());
        new Signal(local).from(counterS1.getOut2()).to(bcdS1.getIn2());

        new Signal(local).from(counterM0.getOut0()).to(bcdM0.getIn0());
        new Signal(local).from(counterM0.getOut1()).to(bcdM0.getIn1());
        new Signal(local).from(counterM0.getOut2()).to(bcdM0.getIn2());
        new Signal(local).from(counterM0.getOut3()).to(bcdM0.getIn3());

        new Signal(local).from(counterM1.getOut0()).to(bcdM1.getIn0());
        new Signal(local).from(counterM1.getOut1()).to(bcdM1.getIn1());
        new Signal(local).from(counterM1.getOut2()).to(bcdM1.getIn2());

        // connect decimal decoders with 7-segment decoders
        // connect crosswise for counting down
        new Signal(local).from(bcdS0.getOut0()).to(decoderS0.getIn9());
        new Signal(local).from(bcdS0.getOut1()).to(decoderS0.getIn8());
        new Signal(local).from(bcdS0.getOut2()).to(decoderS0.getIn7());
        new Signal(local).from(bcdS0.getOut3()).to(decoderS0.getIn6());
        new Signal(local).from(bcdS0.getOut4()).to(decoderS0.getIn5());
        new Signal(local).from(bcdS0.getOut5()).to(decoderS0.getIn4());
        new Signal(local).from(bcdS0.getOut6()).to(decoderS0.getIn3());
        new Signal(local).from(bcdS0.getOut7()).to(decoderS0.getIn2());
        new Signal(local).from(bcdS0.getOut8()).to(decoderS0.getIn1());
        new Signal(local).from(bcdS0.getOut9()).to(decoderS0.getIn0());
        new Signal(local).from(bcdS0.getOutA()).to(resetS0.getCoilIn());

        new Signal(local).from(bcdS1.getOut0()).to(decoderS1.getIn5(), alarm.getIn(1));
        new Signal(local).from(bcdS1.getOut1()).to(decoderS1.getIn4());
        new Signal(local).from(bcdS1.getOut2()).to(decoderS1.getIn3());
        new Signal(local).from(bcdS1.getOut3()).to(decoderS1.getIn2());
        new Signal(local).from(bcdS1.getOut4()).to(decoderS1.getIn1());
        new Signal(local).from(bcdS1.getOut5()).to(decoderS1.getIn0());
        new Signal(local).from(bcdS1.getOut6()).to(resetS1.getCoilIn());

        new Signal(local).from(bcdM0.getOut0()).to(decoderM0.getIn9());
        new Signal(local).from(bcdM0.getOut1()).to(decoderM0.getIn8());
        new Signal(local).from(bcdM0.getOut2()).to(decoderM0.getIn7());
        new Signal(local).from(bcdM0.getOut3()).to(decoderM0.getIn6());
        new Signal(local).from(bcdM0.getOut4()).to(decoderM0.getIn5());
        new Signal(local).from(bcdM0.getOut5()).to(decoderM0.getIn4());
        new Signal(local).from(bcdM0.getOut6()).to(decoderM0.getIn3());
        new Signal(local).from(bcdM0.getOut7()).to(decoderM0.getIn2());
        new Signal(local).from(bcdM0.getOut8()).to(decoderM0.getIn1());
        new Signal(local).from(bcdM0.getOut9()).to(decoderM0.getIn0(), alarm.getIn(2));
        new Signal(local).from(bcdM0.getOutA()).to(resetM0.getCoilIn());

        new Signal(local).from(bcdM1.getOut0()).to(decoderM1.getIn5());
        new Signal(local).from(bcdM1.getOut1()).to(decoderM1.getIn4());
        new Signal(local).from(bcdM1.getOut2()).to(decoderM1.getIn3());
        new Signal(local).from(bcdM1.getOut3()).to(decoderM1.getIn2());
        new Signal(local).from(bcdM1.getOut4()).to(decoderM1.getIn1());
        new Signal(local).from(bcdM1.getOut5()).to(decoderM1.getIn0(), alarm.getIn(3));
        new Signal(local).from(bcdM1.getOut6()).to(resetM1.getCoilIn());

        // connect decoders with display
        new Signal(local).from(decoderS0.getOutA()).to(digitS0.getInA());
        new Signal(local).from(decoderS0.getOutB()).to(digitS0.getInB());
        new Signal(local).from(decoderS0.getOutC()).to(digitS0.getInC());
        new Signal(local).from(decoderS0.getOutD()).to(digitS0.getInD());
        new Signal(local).from(decoderS0.getOutE()).to(digitS0.getInE());
        new Signal(local).from(decoderS0.getOutF()).to(digitS0.getInF());
        new Signal(local).from(decoderS0.getOutG()).to(digitS0.getInG());

        new Signal(local).from(decoderS1.getOutA()).to(digitS1.getInA());
        new Signal(local).from(decoderS1.getOutB()).to(digitS1.getInB());
        new Signal(local).from(decoderS1.getOutC()).to(digitS1.getInC());
        new Signal(local).from(decoderS1.getOutD()).to(digitS1.getInD());
        new Signal(local).from(decoderS1.getOutE()).to(digitS1.getInE());
        new Signal(local).from(decoderS1.getOutF()).to(digitS1.getInF());
        new Signal(local).from(decoderS1.getOutG()).to(digitS1.getInG());

        new Signal(local).from(decoderM0.getOutA()).to(digitM0.getInA());
        new Signal(local).from(decoderM0.getOutB()).to(digitM0.getInB());
        new Signal(local).from(decoderM0.getOutC()).to(digitM0.getInC());
        new Signal(local).from(decoderM0.getOutD()).to(digitM0.getInD());
        new Signal(local).from(decoderM0.getOutE()).to(digitM0.getInE());
        new Signal(local).from(decoderM0.getOutF()).to(digitM0.getInF());
        new Signal(local).from(decoderM0.getOutG()).to(digitM0.getInG());

        new Signal(local).from(decoderM1.getOutA()).to(digitM1.getInA());
        new Signal(local).from(decoderM1.getOutB()).to(digitM1.getInB());
        new Signal(local).from(decoderM1.getOutC()).to(digitM1.getInC());
        new Signal(local).from(decoderM1.getOutD()).to(digitM1.getInD());
        new Signal(local).from(decoderM1.getOutE()).to(digitM1.getInE());
        new Signal(local).from(decoderM1.getOutF()).to(digitM1.getInF());
        new Signal(local).from(decoderM1.getOutG()).to(digitM1.getInG());
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public RelayTimerDigitPCB getDisplayS0()
    {
        return digitS0;
    }

    public RelayTimerDigitPCB getDisplayS1()
    {
        return digitS1;
    }

    public RelayTimerDigitPCB getDisplayM0()
    {
        return digitM0;
    }

    public RelayTimerDigitPCB getDisplayM1()
    {
        return digitM1;
    }

    public Switch getM0Switch()
    {
        return setM0Switch;
    }

    public Switch getM1Switch()
    {
        return setM1Switch;
    }

    public Switch getStartSwitch()
    {
        return startSwitch;
    }

    public Switch getResetSwitch()
    {
        return resetSwitch;
    }
}
