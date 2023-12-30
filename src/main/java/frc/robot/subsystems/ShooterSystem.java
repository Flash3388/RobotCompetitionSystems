package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.flash3388.flashlib.scheduling.Subsystem;

public class ShooterSystem extends Subsystem {

    private final WPI_TalonFX mMotor;

    public ShooterSystem() {
        mMotor = new WPI_TalonFX(0);
    }

    public void shoot(double speed) {
        mMotor.set(speed);
    }

    public void stop() {
        mMotor.stopMotor();
    }
}
