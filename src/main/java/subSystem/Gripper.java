package subSystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.flash3388.flashlib.scheduling.Subsystem;

public class Gripper extends Subsystem {
    private WPI_TalonSRX talonSRX;
    private static final double SPEED = 0.2;

    public Gripper(WPI_TalonSRX talonSRX){
        this.talonSRX = talonSRX;
    }

    public void move(double speed){
        talonSRX.set(speed);
    }

    public void stop(){
        talonSRX.stopMotor();
    }

    public void inside(){
        talonSRX.set(SPEED);
    }

    public void outside(){
        talonSRX.set(-SPEED);
    }
}
