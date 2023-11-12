package subSystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.robot.RunningRobot;
import com.flash3388.flashlib.robot.control.PidController;
import com.flash3388.flashlib.scheduling.Subsystem;
import com.flash3388.flashlib.time.Time;

public class Elevator extends Subsystem {

    private WPI_TalonSRX talon1;
    private WPI_TalonSRX talon2;
    private PidController pidController;
    private final double KP = 0;
    private final double KI = 0;
    private final double KD = 0;
    private final double KF = 0;
    private final double PID_LIMIT = 0;
    private final double ERROR = 0;
    private final double LEVEL1 = 1;
    private final double LEVEL2 = 2;
    private final double LEVEL3 = 3;

    public Elevator(WPI_TalonSRX talon1, WPI_TalonSRX talon2){
        this.talon1 = talon1;
        this.talon2 = talon2;
        talon1.follow(talon2);
        this.pidController = new PidController(RunningRobot.getControl().getClock(), KP, KI, KD, KF);
        pidController.setTolerance(ERROR, Time.milliseconds(500));
        pidController.setOutputLimit(PID_LIMIT);
    }

    public void move(double speed){
        talon2.set(speed);
    }

    public void pidReset(){
        pidController.reset();
    }

    public double getSensorPosition(){
        return talon2.getSelectedSensorPosition() / 4096 * 0.04 * Math.PI;
    }

    public void firstLevel(){
        double speed = pidController.applyAsDouble(getSensorPosition(), LEVEL1);
        this.talon2.set(speed);
    }

    public boolean EndLevel1(){
        return pidController.isInTolerance(getSensorPosition(), LEVEL1);
    }

    public void secondLevel(){
        double speed = pidController.applyAsDouble(getSensorPosition(), LEVEL2);
        this.talon2.set(speed);
    }

    public boolean EndLevel2(){
        return pidController.isInTolerance(getSensorPosition(), LEVEL2);
    }

    public void thirdLevel(){
        double speed = pidController.applyAsDouble(getSensorPosition(), LEVEL3);
        this.talon2.set(speed);
    }

    public boolean EndLevel3(){
        return pidController.isInTolerance(getSensorPosition(), LEVEL3);
    }

    public void stop(){
        this.talon2.stopMotor();
    }
}
