package frc.robot.subSystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.scheduling.Subsystem;
import frc.robot.RobotMap;

public class Gripper extends Subsystem {
    private WPI_TalonSRX talonSRX;
    public Gripper(WPI_TalonSRX talon) {
        this.talonSRX = talon;
    }

    public void pickCone() {
        talonSRX.set(2);
    }
    public void releaseCone(){
        talonSRX.set(-2);
    }

    public void move(double speed){
        talonSRX.set(speed);
    }
    public void stop(){
        talonSRX.set(0);
    }

}
