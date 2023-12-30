package frc.robot;

import com.flash3388.flashlib.frc.robot.FrcRobotControl;
import com.flash3388.flashlib.frc.robot.RoboRio;
import com.flash3388.flashlib.frc.robot.base.iterative.IterativeFrcRobot;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.robot.base.DelegatingRobotControl;
import frc.robot.subsystems.ShooterSystem;

public class Robot extends DelegatingRobotControl implements IterativeFrcRobot {

    private final ShooterSystem shooter;
    private final XboxController xboxController;

    public Robot(FrcRobotControl robotControl) {
        super(robotControl);

        shooter = new ShooterSystem();
        xboxController = getHidInterface().newXboxController(RoboRio.newHidChannel(0));
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
