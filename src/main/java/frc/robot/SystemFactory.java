package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.subSystems.ArmSystem;
import frc.robot.subSystems.ElevatorSystem;
import frc.robot.subSystems.Gripper;

public class SystemFactory {
    public static ArmSystem createArmSystem(){
        return new ArmSystem(new CANSparkMax(RobotMap.ARM, CANSparkMaxLowLevel.MotorType.kBrushless));
    }

    public static ElevatorSystem createElevatorSystem(){
        WPI_TalonSRX master = new WPI_TalonSRX(RobotMap.ELEVATOR_MASTER);
        WPI_TalonSRX follower = new WPI_TalonSRX(RobotMap.ELEVATOR_MASTER);
        return new ElevatorSystem(master, follower);
    }

    public static Gripper createGripperSystem(){
        return new Gripper(new WPI_TalonSRX(RobotMap.GRIPPER));
    }
}
