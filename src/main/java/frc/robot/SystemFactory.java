package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import subSystem.Arm;
import subSystem.Elevator;
import subSystem.Gripper;
import subSystem.Swerve;


public class SystemFactory {

    public static Gripper createGripperSystem(){
        WPI_TalonSRX talonSRX = new WPI_TalonSRX(RobotMap.ARM);
        return new Gripper(talonSRX);
    }
    public static Arm createArmSystem(){
        CANSparkMax sparkMax = new CANSparkMax(RobotMap.ARM, CANSparkMaxLowLevel.MotorType.kBrushed);
        return new Arm(sparkMax);
    }

    public static Elevator createElevatorSystem(){
        WPI_TalonSRX talonSRX1 = new WPI_TalonSRX(RobotMap.ELEVATOR1);
        WPI_TalonSRX talonSRX2 = new WPI_TalonSRX(RobotMap.ELEVATOR2);
        return new Elevator(talonSRX1, talonSRX2);
    }

    public static Swerve createSwerveSystem(){
        SwerveModule[] swerveModules = new SwerveModule[4];

        CANSparkMax drive = new CANSparkMax(RobotMap.SWERVE_DRIVE_FL, CANSparkMaxLowLevel.MotorType.kBrushless);
        CANSparkMax steer = new CANSparkMax(RobotMap.SWERVE_STEER_FL, CANSparkMaxLowLevel.MotorType.kBrushless);
        CANCoder can = new CANCoder(RobotMap.CAN_CODER_FL);
        swerveModules[0] = new SwerveModule(drive,steer,can);

        drive = new CANSparkMax(RobotMap.SWERVE_DRIVE_FR, CANSparkMaxLowLevel.MotorType.kBrushless);
        steer = new CANSparkMax(RobotMap.SWERVE_STEER_FR, CANSparkMaxLowLevel.MotorType.kBrushless);
        can = new CANCoder(RobotMap.CAN_CODER_FR);
        swerveModules[1] = new SwerveModule(drive,steer,can);

        drive = new CANSparkMax(RobotMap.SWERVE_DRIVE_RL, CANSparkMaxLowLevel.MotorType.kBrushless);
        steer = new CANSparkMax(RobotMap.SWERVE_STEER_RL, CANSparkMaxLowLevel.MotorType.kBrushless);
        can = new CANCoder(RobotMap.CAN_CODER_RL);
        swerveModules[2] = new SwerveModule(drive,steer,can);

        drive = new CANSparkMax(RobotMap.SWERVE_DRIVE_RR, CANSparkMaxLowLevel.MotorType.kBrushless);
        steer = new CANSparkMax(RobotMap.SWERVE_STEER_RR, CANSparkMaxLowLevel.MotorType.kBrushless);
        can = new CANCoder(RobotMap.CAN_CODER_RR);
        swerveModules[3] = new SwerveModule(drive,steer,can);

        WPI_Pigeon2 gyro = new WPI_Pigeon2(RobotMap.PIGEON);

        return new Swerve(swerveModules, gyro);
    }
}
