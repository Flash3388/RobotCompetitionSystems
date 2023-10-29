package subSystem;

import com.flash3388.flashlib.robot.RunningRobot;
import com.flash3388.flashlib.robot.control.PidController;
import com.flash3388.flashlib.scheduling.Subsystem;
import com.flash3388.flashlib.time.Time;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
    private CANSparkMax sparkMax;
    private PidController pidController;
    private final double KP = 0;
    private final double KI = 0;
    private final double KD = 0;
    private final double KF = 0;
    private final double PID_LIMIT = 0;
    private final double ERROR = 0;
    private final double EXTENSION1 = 1;
    private final double EXTENSION2 = 2;
    private final double EXTENSION3 = 3;


    public Arm(CANSparkMax sparkMax){
        this.sparkMax = sparkMax;
        SmartDashboard.putNumber("KP", KP);
        SmartDashboard.putNumber("KI", KI);
        SmartDashboard.putNumber("KD", KD);
        SmartDashboard.putNumber("KF", KF);

        this.pidController = new PidController(RunningRobot.getControl().getClock(),
                ()-> {
                        return SmartDashboard.getNumber("KP", KP);
                },
                ()->{
                        return SmartDashboard.getNumber("KI", KI);
                },
                ()->{
                        return SmartDashboard.getNumber("KD", KD);
                },
                ()->{
                        return SmartDashboard.getNumber("KF", KF);
                });
        pidController.setTolerance(ERROR, Time.milliseconds(500));
        pidController.setOutputLimit(PID_LIMIT);
    }

    public void move(double speed){sparkMax.set(speed);}

    public void pidReset(){
        pidController.reset();
    }

    public void reachExtension1(){
        double speed = pidController.applyAsDouble(sparkMax.getEncoder().getPosition(),EXTENSION1);
        sparkMax.set(speed);
    }

    public boolean endExtension1(){
        return this.pidController.isInTolerance(sparkMax.getEncoder().getPosition(), EXTENSION1);
    }

    public void reachExtension2(){
        double speed = pidController.applyAsDouble(sparkMax.getEncoder().getPosition(),EXTENSION2);
        sparkMax.set(speed);
    }

    public boolean endExtension2(){
        return this.pidController.isInTolerance(sparkMax.getEncoder().getPosition(), EXTENSION2);
    }

    public void reachExtension3(){
        double speed = pidController.applyAsDouble(sparkMax.getEncoder().getPosition(),EXTENSION3);
        sparkMax.set(speed);
    }

    public boolean endExtension3(){
        return this.pidController.isInTolerance(sparkMax.getEncoder().getPosition(), EXTENSION3);
    }

    public void stop(){sparkMax.stopMotor();}
}
