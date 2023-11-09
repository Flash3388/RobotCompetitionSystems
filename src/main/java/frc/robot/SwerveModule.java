package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {

    private CANSparkMax drive;
    private CANSparkMax steer;
    private RelativeEncoder driveEncoder;
    private RelativeEncoder steerEncoder;
    private SparkMaxPIDController pidDrive;
    private SparkMaxPIDController pidSteer;
    private static final double DRIVE_P = 0;
    private static final double DRIVE_I = 0;
    private static final double DRIVE_D = 0;
    private static final double DRIVE_F = 0;
    private static final double STEER_P = 0;
    private static final double STEER_I = 0;
    private static final double STEER_D = 0;
    private static final double STEER_F = 0;
    private static final double DRIVE_GEAR_RATIO = 1 / 6.75;
    private static final double WHEEL_RADIUS = 0.5005;
    private static final double STEER_GEAR_RATIO = 1 / 12.8;


    public SwerveModule(CANSparkMax drive, CANSparkMax steer){
        this.drive=drive;
        this.steer=steer;
        driveEncoder = this.drive.getEncoder();
        steerEncoder = this.steer.getEncoder();
        pidDrive= this.drive.getPIDController();
        pidSteer= this.steer.getPIDController();
        this.pidDrive.setOutputRange(-1,1);
        this.pidSteer.setOutputRange(-1,1);
        driveEncoder.setPosition(0);
        steerEncoder.setPosition(0);
        pidDrive.setP(DRIVE_P);
        pidDrive.setI(DRIVE_I);
        pidDrive.setD(DRIVE_D);
        pidDrive.setFF(DRIVE_F);
        pidSteer.setP(STEER_P);
        pidSteer.setI(STEER_I);
        pidSteer.setD(STEER_D);
        pidSteer.setFF(STEER_F);
    }

    public void stop(){
        drive.stopMotor();
        steer.stopMotor();
    }

    public void move(double drive, double rotation){
        this.drive.set(drive);
        this.steer.set(rotation);
    }

    public double getHeadingDegrees(){
        return this.steerEncoder.getPosition() * STEER_GEAR_RATIO * 360;
    }

    public double getVelocityRpm(){
        return driveEncoder.getVelocity() * DRIVE_GEAR_RATIO;
    }

    public void setDesiredState(SwerveModuleState desiredState){
        double velocityRPM = ((desiredState.speedMetersPerSecond * 60) / (WHEEL_RADIUS * 2 * Math.PI)) / DRIVE_GEAR_RATIO;
        this.pidDrive.setReference(velocityRPM, CANSparkMax.ControlType.kVelocity);
        double steeringValue = desiredState.angle.getDegrees() / 360 / STEER_GEAR_RATIO;
        this.pidSteer.setReference(steeringValue, CANSparkMax.ControlType.kPosition);
    }
}
