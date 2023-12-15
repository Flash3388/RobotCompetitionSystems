package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxAxis;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actions.armActions.InitializeArm;
import frc.robot.subSystems.ArmSystem;
import frc.robot.subSystems.ElevatorSystem;
import frc.robot.subSystems.Gripper;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {
    private Gripper gripper;
    private XboxController xbox;

    private ElevatorSystem elevatorSystem;

    private ArmSystem armSystem;
    public Robot(FrcRobotControl robotControl) {
        super(robotControl);
        this.gripper = SystemFactory.createGripperSystem();
        this.xbox = getHidInterface().newXboxController(RobotMap.XBOX);
        this.armSystem = SystemFactory.createArmSystem();
        this.elevatorSystem = SystemFactory.createElevatorSystem();
    }

    @Override
    public void disabledInit() {
//
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
    //    new InitializeArm(armSystem).start();
    }

    @Override
    public void teleopPeriodic() {
     //   gripper.move(xbox.getAxis(XboxAxis.LeftStickY).getAsDouble());
        double armSpeed = xbox.getAxis(XboxAxis.LeftStickX).getAsDouble();
        armSpeed = Math.abs(armSpeed) > 0.2 ? armSpeed : 0;
        armSystem.move(armSpeed);
/*
        double elevatorSpeed = xbox.getAxis(XboxAxis.RightStickY).getAsDouble();
        elevatorSpeed = Math.abs(elevatorSpeed) > 0.2 ? elevatorSpeed : 0;
        elevatorSystem.move(elevatorSpeed);*/

        armSystem.printPosition();
        SmartDashboard.putNumber("Vel Arm", armSpeed);
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void robotStop() {

    }
}
