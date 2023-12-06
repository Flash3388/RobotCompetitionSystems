package frc.robot.subSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.robot.RunningRobot;
import com.flash3388.flashlib.robot.control.PidController;
import com.flash3388.flashlib.scheduling.Subsystem;
import com.flash3388.flashlib.time.Time;
import com.jmath.ExtendedMath;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmSystem extends Subsystem {
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    private PidController pid;
    private static final double KP = 0;
    private static final double KI = 0;
    private static final double KD = 0;
    private static final double KF = 0;

    private static final double SPEED_LIMIT = 0.4;
    private static final double STAGE_1 = 30;
    private static final double STAGE_2 = 70;
    private static final double STAGE_3 = 120;
    private static final double CLOSE = 0.0;
    private static final double ERROR = 0.05;

    public ArmSystem(){
        this.motor = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.encoder = motor.getEncoder();

        this.pid = new PidController(RunningRobot.getControl().getClock(),
                ()-> SmartDashboard.getNumber("KP_A", KP),
                ()-> SmartDashboard.getNumber("KI_A", KI),
                ()-> SmartDashboard.getNumber("KD_A", KD),
                ()-> SmartDashboard.getNumber("KF_A", KF));
        pid.setOutputLimit(-1, 1);
        pid.setTolerance(ERROR, Time.milliseconds(500));

        SmartDashboard.putNumber("KP_A", KP);
        SmartDashboard.putNumber("KI_A", KI);
        SmartDashboard.putNumber("KD_A", KD);
        SmartDashboard.putNumber("KF_A", KF);
        SmartDashboard.putNumber("SET_POINT_A", 0);
        resetEncoder();
    }

    public void resetPID(){
        pid.reset();
    }

    public void resetEncoder(){
        this.encoder.setPosition(0.0);
    }

    public void setPID(){
        double speed = pid.applyAsDouble(getPosition(), SmartDashboard.getNumber("SET_POINT_A", 0));
        move(speed);
    }

    public double getPosition(){
        return this.encoder.getPosition();
    }

    public void move(double speed) {
        speed = ExtendedMath.constrain(speed, -SPEED_LIMIT, SPEED_LIMIT);
       if(getPosition()>12) //*********!!!!
        this.motor.set(speed);
    }

    public void moveToStage1(){
        double speed = pid.applyAsDouble(getPosition(), STAGE_1);
        move(speed);
    }

    public void moveToStage2(){
        double speed = pid.applyAsDouble(getPosition(), STAGE_2);
        move(speed);
    }

    public void moveToStage3(){
        double speed = pid.applyAsDouble(getPosition(), STAGE_3);
        move(speed);
    }

    public boolean isAtStage1(){
        return pid.isInTolerance(getPosition(), STAGE_1);
    }

    public boolean isAtStage2(){
        return pid.isInTolerance(getPosition(), STAGE_2);
    }

    public boolean isAtStage3(){
        return pid.isInTolerance(getPosition(), STAGE_3);
    }

    public void stop(){
        this.motor.stopMotor();
    }

    public void printPosition(){
        SmartDashboard.putNumber("Arm Position: " , getPosition());
    }
}
