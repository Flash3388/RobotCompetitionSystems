package frc.robot;

import com.flash3388.flashlib.frc.robot.RoboRio;
import com.flash3388.flashlib.hid.HidChannel;

public class RobotMap {
    public static final int GRIPPER = 4;
    public static final int ELEVATOR_FOLLOWER = 7;
    public static final int ELEVATOR_MASTER = 8;
    public static final int ARM = 11;
    public static final HidChannel XBOX = RoboRio.newHidChannel(0);
}
