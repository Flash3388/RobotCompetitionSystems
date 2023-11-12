package frc.robot;

import actions.ConeIn;
import actions.ConeOut;
import actions.Extension1;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxButton;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import subSystem.Elevator;
import subSystem.Gripper;
import subSystem.Arm;
import subSystem.Swerve;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {
    private Gripper gripper;
    private Arm arm;
    private XboxController xbox;
    private Swerve swerve;
    private Elevator elevator;

    public Robot(FrcRobotControl robotControl) {
        super(robotControl);
        swerve = SystemFactory.createSwerveSystem();
        gripper = SystemFactory.createGripperSystem();
        this.xbox = getHidInterface().newXboxController(RobotMap.XBOX);

        xbox.getButton(XboxButton.A).whileActive(new ConeIn(this.gripper));
        xbox.getButton(XboxButton.Y).whileActive(new ConeOut(this.gripper));

        arm = SystemFactory.createArmSystem();
        elevator = SystemFactory.createElevatorSystem();

        xbox.getButton(XboxButton.X).whenActive(new Extension1(arm));
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {

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
