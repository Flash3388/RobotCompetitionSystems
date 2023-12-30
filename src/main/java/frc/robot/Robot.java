package frc.robot;

import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxAxis;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import frc.robot.actions.armActions.ArmWithXbox;
import frc.robot.actions.armActions.InitializeArm;
import frc.robot.subSystems.ArmSystem;
import frc.robot.subSystems.ElevatorSystem;
import frc.robot.subSystems.Gripper;
import frc.robot.subsystems.ShooterSystem;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {

    private Gripper gripper;
    private ElevatorSystem elevatorSystem;
    private ArmSystem armSystem;
    private final ShooterSystem shooter;
    private final XboxController xboxController;

    public Robot(FrcRobotControl robotControl) {
        super(robotControl);
        this.gripper = SystemFactory.createGripperSystem();
        this.armSystem = SystemFactory.createArmSystem();
        this.elevatorSystem = SystemFactory.createElevatorSystem();
        shooter = new ShooterSystem();

        xboxController = getHidInterface().newXboxController(RobotMap.XBOX);

        armSystem.setDefaultAction(new ArmWithXbox(armSystem, xboxController));
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
        new InitializeArm(armSystem).start();
    }

    @Override
    public void teleopPeriodic() {
        gripper.move(xboxController.getAxis(XboxAxis.LeftStickY).getAsDouble());

        double elevatorSpeed = xboxController.getAxis(XboxAxis.RightStickY).getAsDouble();
        elevatorSpeed = Math.abs(elevatorSpeed) > 0.2 ? elevatorSpeed : 0;
        elevatorSystem.move(elevatorSpeed);

        armSystem.printPosition();
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
