package subSystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.scheduling.Subsystem;

public class Gripper extends Subsystem {
    private WPI_TalonSRX talonSRX;
    private static final double speed = 0.2;

    public Gripper(WPI_TalonSRX talonSRX){
        this.talonSRX = talonSRX;
    }

    public void move(){
        talonSRX.set(speed);
    }

    public void stop(){
        talonSRX.stopMotor();
    }
}
