package frc.robot;

import actions.ConeIn;
import actions.ConeOut;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxButton;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import subSystem.Gripper;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {
    private Gripper gripper;
    private XboxController xbox;

    public Robot(FrcRobotControl robotControl) {
        super(robotControl);
        this.gripper = new Gripper(new WPI_TalonSRX(RobotMap.GRIPPER));
        this.xbox = getHidInterface().newXboxController(RobotMap.XBOX);

        xbox.getButton(XboxButton.A).whileActive(new ConeIn(this.gripper));
        xbox.getButton(XboxButton.Y).whileActive(new ConeOut(this.gripper));
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
