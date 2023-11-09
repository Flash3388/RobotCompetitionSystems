package subSystem;

import com.ctre.phoenix.sensors.CANCoder;
import com.flash3388.flashlib.scheduling.Subsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.SwerveModule;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Rotation2d;

public class Swerve extends Subsystem {

    private SwerveModule[] modules;
    private SwerveDriveKinematics swerveDriveKinematics;
    private static final double OFFSET = 0.37;
    private static final double MAX_SPEED = 4.4196;
    private CANCoder canCoder;
    private static final double STEER_GEAR_RATIO = 1 / 12.8;

    public Swerve(SwerveModule[] modules, CANCoder canCoder){
        this.modules = modules;
        this.canCoder = canCoder;
        Translation2d front_left = new Translation2d(OFFSET, OFFSET);
        Translation2d front_right = new Translation2d(OFFSET, -OFFSET);
        Translation2d back_left = new Translation2d(-OFFSET, OFFSET);
        Translation2d back_right = new Translation2d(-OFFSET, -OFFSET);
        swerveDriveKinematics = new SwerveDriveKinematics(front_left, front_right, back_left, back_right);
        double ANGLE = this.canCoder.getAbsolutePosition() / 360 / STEER_GEAR_RATIO;
        this.canCoder.setPosition(ANGLE);
    }

    public void stop(){
        for(int i = 0; i < 4; i++){
            this.modules[i].stop();
        }
    }

    public void move(double drive, double rotation){
        for (int i = 0; i < 4; i++) {
            this.modules[i].move(drive,rotation);
        }
    }

    public void setDesiredStates(SwerveModuleState[] states){
        for (int i = 0; i < 4; i++) {
            this.modules[i].setDesiredState(states[i]);
        }
    }

    public void drive(double speedY, double speedX, double rotation){
        SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(new ChassisSpeeds(speedY, speedX, rotation));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, MAX_SPEED);
        setDesiredStates(swerveModuleStates);
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
