package sunday.resi.example.relayclock;

import java.util.concurrent.TimeUnit;

import sunday.resi.common.Circuit;
import sunday.resi.common.Component;
import sunday.resi.common.Input;
import sunday.resi.common.Joint;
import sunday.resi.common.Signal;
import sunday.resi.library.BCDToDecimalDecoder;
import sunday.resi.library.Clock;
import sunday.resi.library.Counter;
import sunday.resi.library.DecimalToSevenSegmentDecoder;
import sunday.resi.library.Relay;
import sunday.resi.library.Switch;

/**
 * This is a simulator for a digital clock built with relays.
 * 
 * @author Peter H&auml;nsgen
 */
public class RelayClock extends Component
{
    private Input powerIn;

    private RelayClockDigitPCB digitS0;

    private RelayClockDigitPCB digitS1;

    private RelayClockDigitPCB digitM0;

    private RelayClockDigitPCB digitM1;

    private RelayClockDigitPCB digitH0;

    private RelayClockDigitPCB digitH1;

    private Switch minutesSwitch;

    private Switch hoursSwitch;

    /**
     * The constructor.
     */
    public RelayClock(Circuit circuit, String name)
    {
        super(circuit, name);

        powerIn = new Input();

        Clock clock = new Clock(circuit, name + "Clock", 500, TimeUnit.MILLISECONDS);

        Counter cS0 = new Counter(circuit, name + "_CounterS0");
        Counter cS1 = new Counter(circuit, name + "_CounterS1");
        Counter cM0 = new Counter(circuit, name + "_CounterM0");
        Counter cM1 = new Counter(circuit, name + "_CounterM1");
        Counter cH0 = new Counter(circuit, name + "_CounterH0");
        Counter cH1 = new Counter(circuit, name + "_CounterH1");

        BCDToDecimalDecoder bcdS0 = new BCDToDecimalDecoder(circuit, name + "_BCDS0");
        BCDToDecimalDecoder bcdS1 = new BCDToDecimalDecoder(circuit, name + "_BCDS1");
        BCDToDecimalDecoder bcdM0 = new BCDToDecimalDecoder(circuit, name + "_BCDM0");
        BCDToDecimalDecoder bcdM1 = new BCDToDecimalDecoder(circuit, name + "_BCDM1");
        BCDToDecimalDecoder bcdH0 = new BCDToDecimalDecoder(circuit, name + "_BCDH0");
        BCDToDecimalDecoder bcdH1 = new BCDToDecimalDecoder(circuit, name + "_BCDH1");

        DecimalToSevenSegmentDecoder decoderS0 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecS0");
        DecimalToSevenSegmentDecoder decoderS1 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecS1");
        DecimalToSevenSegmentDecoder decoderM0 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecM0");
        DecimalToSevenSegmentDecoder decoderM1 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecM1");
        DecimalToSevenSegmentDecoder decoderH0 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecH0");
        DecimalToSevenSegmentDecoder decoderH1 = new DecimalToSevenSegmentDecoder(circuit, name + "_DecH1");

        digitS0 = new RelayClockDigitPCB(circuit, name + "_DisplayS0");
        digitS1 = new RelayClockDigitPCB(circuit, name + "_DisplayS1");
        digitM0 = new RelayClockDigitPCB(circuit, name + "_DisplayM0");
        digitM1 = new RelayClockDigitPCB(circuit, name + "_DisplayM1");
        digitH0 = new RelayClockDigitPCB(circuit, name + "_DisplayH0");
        digitH1 = new RelayClockDigitPCB(circuit, name + "_DisplayH1");

        minutesSwitch = new Switch(circuit, name + "_SetMM");
        hoursSwitch = new Switch(circuit, name + "_SetHH");

        Relay clockM0 = new Relay(circuit, name + "_ClockM0");
        Relay clockH0 = new Relay(circuit, name + "_ClockH0");
        Joint jointM0 = new Joint(circuit, name + "_JointM0");
        Joint jointH0 = new Joint(circuit, name + "_JointH0");

        Relay resetS0 = new Relay(circuit, name + "_ResetS0");
        Relay resetS1 = new Relay(circuit, name + "_ResetS1");
        Relay resetM0 = new Relay(circuit, name + "_ResetM0");
        Relay resetM1 = new Relay(circuit, name + "_ResetM1");
        Relay resetH0 = new Relay(circuit, name + "_ResetH0");
        Relay reset24M0 = new Relay(circuit, name + "_Reset24M0");
        Relay reset24M1 = new Relay(circuit, name + "_Reset24M1");
        Relay reset24 = new Relay(circuit, name + "_Reset24");

        // internal wirings
        // connect power
        new Signal(circuit).from(powerIn).to(bcdS0.getPowerIn(), bcdS1.getPowerIn(), bcdM0.getPowerIn(),
            bcdM1.getPowerIn(), bcdH0.getPowerIn(), bcdH1.getPowerIn(), decoderS0.getPowerIn(), decoderS1.getPowerIn(),
            decoderM0.getPowerIn(), decoderM1.getPowerIn(), decoderH0.getPowerIn(), decoderH1.getPowerIn(),
            reset24M0.getMiddleIn(0), reset24.getMiddleIn(0), resetS0.getMiddleIn(1), resetS1.getMiddleIn(1),
            resetM0.getMiddleIn(1), resetM1.getMiddleIn(1), resetH0.getMiddleIn(1), hoursSwitch.getMiddleIn(),
            minutesSwitch.getMiddleIn(), clockH0.getMiddleIn(0), clockM0.getMiddleIn(0));

        // connect clock with counter
        new Signal(circuit).from(clock.get_Out()).to(cS0.get_Clock());
        new Signal(circuit).from(clock.getOut()).to(cS0.getClock());

        // connect counters with bcd decoders
        new Signal(circuit).from(cS0.getOut0()).to(bcdS0.getIn0());
        new Signal(circuit).from(cS0.getOut1()).to(bcdS0.getIn1());
        new Signal(circuit).from(cS0.getOut2()).to(bcdS0.getIn2());
        new Signal(circuit).from(cS0.getOut3()).to(bcdS0.getIn3());

        new Signal(circuit).from(cS1.getOut0()).to(bcdS1.getIn0());
        new Signal(circuit).from(cS1.getOut1()).to(bcdS1.getIn1());
        new Signal(circuit).from(cS1.getOut2()).to(bcdS1.getIn2());
        new Signal(circuit).from(cS1.getOut3()).to(bcdS1.getIn3());

        new Signal(circuit).from(cM0.getOut0()).to(bcdM0.getIn0());
        new Signal(circuit).from(cM0.getOut1()).to(bcdM0.getIn1());
        new Signal(circuit).from(cM0.getOut2()).to(bcdM0.getIn2());
        new Signal(circuit).from(cM0.getOut3()).to(bcdM0.getIn3());

        new Signal(circuit).from(cM1.getOut0()).to(bcdM1.getIn0());
        new Signal(circuit).from(cM1.getOut1()).to(bcdM1.getIn1());
        new Signal(circuit).from(cM1.getOut2()).to(bcdM1.getIn2());
        new Signal(circuit).from(cM1.getOut3()).to(bcdM1.getIn3());

        new Signal(circuit).from(cH0.getOut0()).to(bcdH0.getIn0());
        new Signal(circuit).from(cH0.getOut1()).to(bcdH0.getIn1());
        new Signal(circuit).from(cH0.getOut2()).to(bcdH0.getIn2());
        new Signal(circuit).from(cH0.getOut3()).to(bcdH0.getIn3());

        new Signal(circuit).from(cH1.getOut0()).to(bcdH1.getIn0());
        new Signal(circuit).from(cH1.getOut1()).to(bcdH1.getIn1());
        new Signal(circuit).from(cH1.getOut2()).to(bcdH1.getIn2());
        new Signal(circuit).from(cH1.getOut3()).to(bcdH1.getIn3());

        // connect overflow of bcd decoders with counter clock
        new Signal(circuit).from(bcdS0.getOutA()).to(resetS0.getCoilIn());
        new Signal(circuit).from(resetS0.get_Out(0)).to(cS0.getPowerIn());
        new Signal(circuit).from(resetS0.get_Out(1)).to(cS1.get_Clock());
        new Signal(circuit).from(resetS0.getOut(1)).to(cS1.getClock());

        new Signal(circuit).from(bcdS1.getOut6()).to(resetS1.getCoilIn());
        new Signal(circuit).from(resetS1.get_Out(0)).to(cS1.getPowerIn());
        new Signal(circuit).from(resetS1.getOut(1)).to(jointM0.getIn(0));

        new Signal(circuit).from(bcdM0.getOutA()).to(resetM0.getCoilIn());
        new Signal(circuit).from(resetM0.get_Out(0)).to(cM0.getPowerIn());
        new Signal(circuit).from(resetM0.get_Out(1)).to(cM1.get_Clock());
        new Signal(circuit).from(resetM0.getOut(1)).to(cM1.getClock());

        new Signal(circuit).from(bcdM1.getOut6()).to(resetM1.getCoilIn());
        new Signal(circuit).from(resetM1.get_Out(0)).to(cM1.getPowerIn());
        new Signal(circuit).from(resetM1.getOut(1)).to(jointH0.getIn(0));

        new Signal(circuit).from(bcdH0.getOutA()).to(resetH0.getCoilIn());
        new Signal(circuit).from(resetH0.get_Out(0)).to(cH0.getPowerIn());
        new Signal(circuit).from(resetH0.get_Out(1)).to(cH1.get_Clock());
        new Signal(circuit).from(resetH0.getOut(1)).to(cH1.getClock());

        // reset all counters when 24h is reached
        // disconnect them from power
        new Signal(circuit).from(reset24.get_Out(0)).to(resetS0.getMiddleIn(0), resetS1.getMiddleIn(0),
            resetM0.getMiddleIn(0), resetM1.getMiddleIn(0), resetH0.getMiddleIn(0), cH1.getPowerIn());
        new Signal(circuit).from(reset24M0.getOut(0)).to(reset24M1.getMiddleIn(0));
        new Signal(circuit).from(reset24M1.getOut(0)).to(reset24.getCoilIn());

        // set switch for minutes
        new Signal(circuit).from(minutesSwitch.getOut()).to(jointM0.getIn(1));
        new Signal(circuit).from(jointM0.getOut()).to(clockM0.getCoilIn());
        new Signal(circuit).from(clockM0.get_Out(0)).to(cM0.get_Clock());
        new Signal(circuit).from(clockM0.getOut(0)).to(cM0.getClock());

        // set switch for hours
        new Signal(circuit).from(hoursSwitch.getOut()).to(jointH0.getIn(1));
        new Signal(circuit).from(jointH0.getOut()).to(clockH0.getCoilIn());
        new Signal(circuit).from(clockH0.get_Out(0)).to(cH0.get_Clock());
        new Signal(circuit).from(clockH0.getOut(0)).to(cH0.getClock());

        // connect decimal decoders with 7-segment decoders
        new Signal(circuit).from(bcdS0.getOut0()).to(decoderS0.getIn0());
        new Signal(circuit).from(bcdS0.getOut1()).to(decoderS0.getIn1());
        new Signal(circuit).from(bcdS0.getOut2()).to(decoderS0.getIn2());
        new Signal(circuit).from(bcdS0.getOut3()).to(decoderS0.getIn3());
        new Signal(circuit).from(bcdS0.getOut4()).to(decoderS0.getIn4());
        new Signal(circuit).from(bcdS0.getOut5()).to(decoderS0.getIn5());
        new Signal(circuit).from(bcdS0.getOut6()).to(decoderS0.getIn6());
        new Signal(circuit).from(bcdS0.getOut7()).to(decoderS0.getIn7());
        new Signal(circuit).from(bcdS0.getOut8()).to(decoderS0.getIn8());
        new Signal(circuit).from(bcdS0.getOut9()).to(decoderS0.getIn9());

        new Signal(circuit).from(bcdS1.getOut0()).to(decoderS1.getIn0());
        new Signal(circuit).from(bcdS1.getOut1()).to(decoderS1.getIn1());
        new Signal(circuit).from(bcdS1.getOut2()).to(decoderS1.getIn2());
        new Signal(circuit).from(bcdS1.getOut3()).to(decoderS1.getIn3());
        new Signal(circuit).from(bcdS1.getOut4()).to(decoderS1.getIn4());
        new Signal(circuit).from(bcdS1.getOut5()).to(decoderS1.getIn5());
        /*
        new Signal( circuit ).from( bcdS1.getOut6() ).to( decoderS1.getIn6() );
        new Signal( circuit ).from( bcdS1.getOut7() ).to( decoderS1.getIn7() );
        new Signal( circuit ).from( bcdS1.getOut8() ).to( decoderS1.getIn8() );
        new Signal( circuit ).from( bcdS1.getOut9() ).to( decoderS1.getIn9() );
        */

        new Signal(circuit).from(bcdM0.getOut0()).to(decoderM0.getIn0());
        new Signal(circuit).from(bcdM0.getOut1()).to(decoderM0.getIn1());
        new Signal(circuit).from(bcdM0.getOut2()).to(decoderM0.getIn2());
        new Signal(circuit).from(bcdM0.getOut3()).to(decoderM0.getIn3());
        new Signal(circuit).from(bcdM0.getOut4()).to(decoderM0.getIn4());
        new Signal(circuit).from(bcdM0.getOut5()).to(decoderM0.getIn5());
        new Signal(circuit).from(bcdM0.getOut6()).to(decoderM0.getIn6());
        new Signal(circuit).from(bcdM0.getOut7()).to(decoderM0.getIn7());
        new Signal(circuit).from(bcdM0.getOut8()).to(decoderM0.getIn8());
        new Signal(circuit).from(bcdM0.getOut9()).to(decoderM0.getIn9());

        new Signal(circuit).from(bcdM1.getOut0()).to(decoderM1.getIn0());
        new Signal(circuit).from(bcdM1.getOut1()).to(decoderM1.getIn1());
        new Signal(circuit).from(bcdM1.getOut2()).to(decoderM1.getIn2());
        new Signal(circuit).from(bcdM1.getOut3()).to(decoderM1.getIn3());
        new Signal(circuit).from(bcdM1.getOut4()).to(decoderM1.getIn4());
        new Signal(circuit).from(bcdM1.getOut5()).to(decoderM1.getIn5());
        /*
        new Signal( circuit ).from( bcdM1.getOut6() ).to( decoderM1.getIn6() );
        new Signal( circuit ).from( bcdM1.getOut7() ).to( decoderM1.getIn7() );
        new Signal( circuit ).from( bcdM1.getOut8() ).to( decoderM1.getIn8() );
        new Signal( circuit ).from( bcdM1.getOut9() ).to( decoderM1.getIn9() );
        */

        new Signal(circuit).from(bcdH0.getOut0()).to(decoderH0.getIn0());
        new Signal(circuit).from(bcdH0.getOut1()).to(decoderH0.getIn1());
        new Signal(circuit).from(bcdH0.getOut2()).to(decoderH0.getIn2());
        new Signal(circuit).from(bcdH0.getOut3()).to(decoderH0.getIn3());
        new Signal(circuit).from(bcdH0.getOut4()).to(decoderH0.getIn4(), reset24M0.getCoilIn());
        new Signal(circuit).from(bcdH0.getOut5()).to(decoderH0.getIn5());
        new Signal(circuit).from(bcdH0.getOut6()).to(decoderH0.getIn6());
        new Signal(circuit).from(bcdH0.getOut7()).to(decoderH0.getIn7());
        new Signal(circuit).from(bcdH0.getOut8()).to(decoderH0.getIn8());
        new Signal(circuit).from(bcdH0.getOut9()).to(decoderH0.getIn9());

        new Signal(circuit).from(bcdH1.getOut0()).to(decoderH1.getIn0());
        new Signal(circuit).from(bcdH1.getOut1()).to(decoderH1.getIn1());
        new Signal(circuit).from(bcdH1.getOut2()).to(decoderH1.getIn2(), reset24M1.getCoilIn());
        /*
        new Signal( circuit ).from( bcdH1.getOut3() ).to( decoderH1.getIn3() );
        new Signal( circuit ).from( bcdH1.getOut4() ).to( decoderH1.getIn4() );
        new Signal( circuit ).from( bcdH1.getOut5() ).to( decoderH1.getIn5() );
        new Signal( circuit ).from( bcdH1.getOut6() ).to( decoderH1.getIn6() );
        new Signal( circuit ).from( bcdH1.getOut7() ).to( decoderH1.getIn7() );
        new Signal( circuit ).from( bcdH1.getOut8() ).to( decoderH1.getIn8() );
        new Signal( circuit ).from( bcdH1.getOut9() ).to( decoderH1.getIn9() );
        */

        // connect decoders with display
        new Signal(circuit).from(decoderS0.getOutA()).to(digitS0.getInA());
        new Signal(circuit).from(decoderS0.getOutB()).to(digitS0.getInB());
        new Signal(circuit).from(decoderS0.getOutC()).to(digitS0.getInC());
        new Signal(circuit).from(decoderS0.getOutD()).to(digitS0.getInD());
        new Signal(circuit).from(decoderS0.getOutE()).to(digitS0.getInE());
        new Signal(circuit).from(decoderS0.getOutF()).to(digitS0.getInF());
        new Signal(circuit).from(decoderS0.getOutG()).to(digitS0.getInG());

        new Signal(circuit).from(decoderS1.getOutA()).to(digitS1.getInA());
        new Signal(circuit).from(decoderS1.getOutB()).to(digitS1.getInB());
        new Signal(circuit).from(decoderS1.getOutC()).to(digitS1.getInC());
        new Signal(circuit).from(decoderS1.getOutD()).to(digitS1.getInD());
        new Signal(circuit).from(decoderS1.getOutE()).to(digitS1.getInE());
        new Signal(circuit).from(decoderS1.getOutF()).to(digitS1.getInF());
        new Signal(circuit).from(decoderS1.getOutG()).to(digitS1.getInG());

        new Signal(circuit).from(decoderM0.getOutA()).to(digitM0.getInA());
        new Signal(circuit).from(decoderM0.getOutB()).to(digitM0.getInB());
        new Signal(circuit).from(decoderM0.getOutC()).to(digitM0.getInC());
        new Signal(circuit).from(decoderM0.getOutD()).to(digitM0.getInD());
        new Signal(circuit).from(decoderM0.getOutE()).to(digitM0.getInE());
        new Signal(circuit).from(decoderM0.getOutF()).to(digitM0.getInF());
        new Signal(circuit).from(decoderM0.getOutG()).to(digitM0.getInG());

        new Signal(circuit).from(decoderM1.getOutA()).to(digitM1.getInA());
        new Signal(circuit).from(decoderM1.getOutB()).to(digitM1.getInB());
        new Signal(circuit).from(decoderM1.getOutC()).to(digitM1.getInC());
        new Signal(circuit).from(decoderM1.getOutD()).to(digitM1.getInD());
        new Signal(circuit).from(decoderM1.getOutE()).to(digitM1.getInE());
        new Signal(circuit).from(decoderM1.getOutF()).to(digitM1.getInF());
        new Signal(circuit).from(decoderM1.getOutG()).to(digitM1.getInG());

        new Signal(circuit).from(decoderH0.getOutA()).to(digitH0.getInA());
        new Signal(circuit).from(decoderH0.getOutB()).to(digitH0.getInB());
        new Signal(circuit).from(decoderH0.getOutC()).to(digitH0.getInC());
        new Signal(circuit).from(decoderH0.getOutD()).to(digitH0.getInD());
        new Signal(circuit).from(decoderH0.getOutE()).to(digitH0.getInE());
        new Signal(circuit).from(decoderH0.getOutF()).to(digitH0.getInF());
        new Signal(circuit).from(decoderH0.getOutG()).to(digitH0.getInG());

        new Signal(circuit).from(decoderH1.getOutA()).to(digitH1.getInA());
        new Signal(circuit).from(decoderH1.getOutB()).to(digitH1.getInB());
        new Signal(circuit).from(decoderH1.getOutC()).to(digitH1.getInC());
        new Signal(circuit).from(decoderH1.getOutD()).to(digitH1.getInD());
        new Signal(circuit).from(decoderH1.getOutE()).to(digitH1.getInE());
        new Signal(circuit).from(decoderH1.getOutF()).to(digitH1.getInF());
        new Signal(circuit).from(decoderH1.getOutG()).to(digitH1.getInG());
    }

    public Input getPowerIn()
    {
        return powerIn;
    }

    public RelayClockDigitPCB getDisplayS0()
    {
        return digitS0;
    }

    public RelayClockDigitPCB getDisplayS1()
    {
        return digitS1;
    }

    public RelayClockDigitPCB getDisplayM0()
    {
        return digitM0;
    }

    public RelayClockDigitPCB getDisplayM1()
    {
        return digitM1;
    }

    public RelayClockDigitPCB getDisplayH0()
    {
        return digitH0;
    }

    public RelayClockDigitPCB getDisplayH1()
    {
        return digitH1;
    }

    public Switch getMinutesSwitch()
    {
        return minutesSwitch;
    }

    public Switch getHoursSwitch()
    {
        return hoursSwitch;
    }
}
