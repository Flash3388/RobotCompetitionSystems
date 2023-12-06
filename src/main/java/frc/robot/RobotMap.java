package frc.robot;

import com.flash3388.flashlib.frc.robot.RoboRio;
import com.flash3388.flashlib.hid.HidChannel;

public class RobotMap {
    public static final int GRIPPER = 4;
    public static final HidChannel XBOX = RoboRio.newHidChannel(0);
    public static final int ARM = 3;

    public static final int ELEVATOR1 = 5;

    public static final int ELEVATOR2 = 6;

    public static final int PIGEON = 9;

    public static final int SWERVE_DRIVE_FL = 51;
    public static final int SWERVE_DRIVE_FR = 41;
    public static final int SWERVE_DRIVE_RL = 61;
    public static final int SWERVE_DRIVE_RR = 31;
    public static final int SWERVE_STEER_FL = 52;
    public static final int SWERVE_STEER_FR = 42;
    public static final int SWERVE_STEER_RL = 62;
    public static final int SWERVE_STEER_RR = 32;

    public static final int CAN_CODER_FL = 0;
    public static final int CAN_CODER_FR = 0;
    public static final int CAN_CODER_RL = 0;
    public static final int CAN_CODER_RR = 0;

}
