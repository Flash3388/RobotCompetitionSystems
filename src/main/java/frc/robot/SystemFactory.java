package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import subSystem.Arm;
import subSystem.Gripper;

public class SystemFactory {

    public static Arm createArmSystem(){
        CANSparkMax sparkMax = new CANSparkMax(RobotMap.ARM, CANSparkMaxLowLevel.MotorType.kBrushed);
        return new Arm(sparkMax);
    }
}
