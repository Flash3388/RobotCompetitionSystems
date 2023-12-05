package subSystem;

import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.flash3388.flashlib.scheduling.Subsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.robot.SwerveModule;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Swerve extends Subsystem {

    private SwerveModule[] modules;
    private SwerveDriveKinematics swerveDriveKinematics;
    private WPI_Pigeon2 gyro;
    private static final double OFFSET = 0.37;
    private static final double MAX_SPEED = 4.4196;

    public Swerve(SwerveModule[] modules, WPI_Pigeon2 gyro){
        this.modules = modules;
        this.gyro = gyro;
        Translation2d front_left = new Translation2d(OFFSET, OFFSET);
        Translation2d front_right = new Translation2d(OFFSET, -OFFSET);
        Translation2d back_left = new Translation2d(-OFFSET, OFFSET);
        Translation2d back_right = new Translation2d(-OFFSET, -OFFSET);
        swerveDriveKinematics = new SwerveDriveKinematics(front_left, front_right, back_left, back_right);
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

    public void drive(double speedX, double speedY, double rotation){
        SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(new ChassisSpeeds(speedY, speedX, rotation));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, MAX_SPEED);
        setDesiredStates(swerveModuleStates);
    }


}
