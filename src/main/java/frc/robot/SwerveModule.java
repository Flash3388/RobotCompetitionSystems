package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {

    private CANSparkMax drive;
    private CANSparkMax steer;
    private CANCoder canCoder;
    private RelativeEncoder driveEncoder;
    private RelativeEncoder steerEncoder;
    private SparkMaxPIDController pidDrive;
    private SparkMaxPIDController pidSteer;
    private static final double DRIVE_P = 0.0003;
    private static final double DRIVE_I = 5e-7;
    private static final double DRIVE_D = 5e-7;
    private static final double DRIVE_F = 0.0001;
    private static final double STEER_P = 0.05;
    private static final double STEER_I = 0;
    private static final double STEER_D = 0.01;
    private static final double STEER_F = 0;
    private static final double DRIVE_GEAR_RATIO = 1 / 6.75;
    private static final double WHEEL_RADIUS = 0.5005;
    private static final double STEER_GEAR_RATIO = 1 / 12.8;


    public SwerveModule(CANSparkMax drive, CANSparkMax steer, CANCoder canCoder){
        this.drive=drive;
        this.steer=steer;
        this.canCoder = canCoder;
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
        double ANGLE = this.canCoder.getAbsolutePosition() / 360 / STEER_GEAR_RATIO;
        this.canCoder.setPosition(ANGLE);
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
    public double getAbsEncoder() {
        return canCoder.getAbsolutePosition();
    }

    public void setDesiredState(SwerveModuleState desiredState){
        double velocityRPM = ((desiredState.speedMetersPerSecond * 60) / (WHEEL_RADIUS * 2 * Math.PI)) / DRIVE_GEAR_RATIO;
        this.pidDrive.setReference(velocityRPM, CANSparkMax.ControlType.kVelocity);
        double steeringValue = desiredState.angle.getDegrees() / 360 / STEER_GEAR_RATIO;
        this.pidSteer.setReference(steeringValue, CANSparkMax.ControlType.kPosition);
    }

    private static double placeInAppropriate0To360Scope(double scopeReference, double newAngle) {
        double lowerBound;
        double upperBound;
        double lowerOffset = scopeReference % 360;
        if (lowerOffset >= 0) {
            lowerBound = scopeReference - lowerOffset;
            upperBound = scopeReference + (360 - lowerOffset);
        } else {
            upperBound = scopeReference - lowerOffset;
            lowerBound = scopeReference - (360 + lowerOffset);
        }
        while (newAngle < lowerBound) {
            newAngle += 360;
        }
        while (newAngle > upperBound) {
            newAngle -= 360;
        }
        if (newAngle - scopeReference > 180) {
            newAngle -= 360;
        } else if (newAngle - scopeReference < -180) {
            newAngle += 360;
        }
        return newAngle;
    }

    public static SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle) {
        double targetAngle = placeInAppropriate0To360Scope(currentAngle.getDegrees(), desiredState.angle.getDegrees());
        double targetSpeed = desiredState.speedMetersPerSecond;
        double delta = targetAngle - currentAngle.getDegrees();
        if (Math.abs(delta) > 90) {
            targetSpeed = -targetSpeed;
            targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
        }
        return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
    }
}
