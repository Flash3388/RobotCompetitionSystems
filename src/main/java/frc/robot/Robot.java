package frc.robot;

import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxAxis;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import frc.robot.subSystems.Gripper;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {
    private Gripper gripper;
    private XboxController xbox;
    public Robot(FrcRobotControl robotControl) {
        super(robotControl);
        this.gripper = new Gripper();
        this.xbox = getHidInterface().newXboxController(RobotMap.XBOX);
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

    }

    @Override
    public void teleopPeriodic() {
        gripper.move(xbox.getAxis(XboxAxis.LeftStickY).getAsDouble());
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
