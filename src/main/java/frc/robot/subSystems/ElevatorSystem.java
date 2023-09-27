package frc.robot.subSystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.io.devices.SpeedController;
import com.flash3388.flashlib.robot.RunningRobot;
import com.flash3388.flashlib.robot.control.PidController;
import com.flash3388.flashlib.scheduling.Subsystem;
import com.flash3388.flashlib.time.Time;
import com.jmath.ExtendedMath;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ElevatorSystem extends Subsystem {
    private WPI_TalonSRX master;
    private WPI_TalonSRX follower;

    private PidController pid;
    private static final double kp = 2.0;
    private static final double ki = 0.13;
    private static final double kd = 0.13;
    private static final double kf = 0.0;

    private static final double SPEED_LIMIT = 0.4;
    private static final double UPPER_CONE_POSITION = 2;
    private static final double MIDDLE_CONE_POSITION = 1;
    private static final double LOWER_CONE_POSITION = 0;
    private static final double ERROR = 0.03;

    private double setPoint;

    public ElevatorSystem(){
        this.master = new WPI_TalonSRX(RobotMap.ELEVATOR_MASTER);
        this.follower = new WPI_TalonSRX(RobotMap.ELEVATOR_FOLLOWER);
        this.follower.set(ControlMode.Follower, master.getDeviceID());
        this.pid = new PidController(RunningRobot.getControl().getClock(),
                ()-> SmartDashboard.getNumber("KP", kp),
                ()-> SmartDashboard.getNumber("KI", ki),
                ()-> SmartDashboard.getNumber("KD", kd),
                ()-> SmartDashboard.getNumber("KF", kf));
        pid.setOutputLimit(-1, 1);
        pid.setTolerance(ERROR, Time.milliseconds(500));
        resetEncoders();
        SmartDashboard.putNumber("KP", kp);
        SmartDashboard.putNumber("KI", ki);
        SmartDashboard.putNumber("KD", kd);
        SmartDashboard.putNumber("KF", kf);
        SmartDashboard.putNumber("SET_POINT", 0);
    }

    public void setPID(){
        double speed = pid.applyAsDouble(getPosition(), SmartDashboard.getNumber("SET_POINT", 0));
        move(speed);
    }

    public void resetPID(){
        pid.reset();
    }

    public void resetEncoders(){
        this.follower.setSelectedSensorPosition(0);
    }

    public void moveToUpperCone(){
        double speed = pid.applyAsDouble(getPosition(), UPPER_CONE_POSITION);
        move(speed);
    }

    public void moveToMiddleCone(){
        double speed = pid.applyAsDouble(getPosition(), MIDDLE_CONE_POSITION);
        move(speed);
    }

    public void moveToLowerCone(){
        double speed = pid.applyAsDouble(getPosition(), LOWER_CONE_POSITION);
        move(speed);
    }

    public double getPosition(){ //meters
        return -this.follower.getSelectedSensorPosition() / 4096.0 * 0.04 * Math.PI;
    }

    public void move(double speed){
        speed = ExtendedMath.constrain(speed, -SPEED_LIMIT, SPEED_LIMIT);
        this.master.set(speed);
    }

    public boolean isAtUpperCone(){
        return pid.isInTolerance(getPosition(), UPPER_CONE_POSITION);
    }

    public boolean isAtMiddleCone(){
        return pid.isInTolerance(getPosition(), MIDDLE_CONE_POSITION);
    }

    public boolean isAtLowerCone(){
        return pid.isInTolerance(getPosition(), LOWER_CONE_POSITION);
    }

    public void stop(){
        this.master.stopMotor();
    }


}
