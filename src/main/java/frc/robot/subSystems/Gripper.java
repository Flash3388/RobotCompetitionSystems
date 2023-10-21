package frc.robot.subSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.scheduling.Subsystem;
import frc.robot.RobotMap;

public class Gripper extends Subsystem {
    private WPI_TalonSRX talonSRX;
    private static final double SPEED = 2;
    public Gripper(WPI_TalonSRX talon) {
        this.talonSRX = talon;
    }

    public void pickCone() {
        talonSRX.set(SPEED);
    }
    public void releaseCone(){
        talonSRX.set(-SPEED);
    }

    public void move(double speed){
        talonSRX.set(speed);
    }
    public void stop(){
        talonSRX.set(0);
    }

}
