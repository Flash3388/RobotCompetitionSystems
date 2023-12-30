package frc.robot.actions.elevatorActions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.ElevatorSystem;

public class MoveToLowerCone extends ActionBase {
    private ElevatorSystem elevatorSystem;


    public MoveToLowerCone(ElevatorSystem elevatorSystem){
        this.elevatorSystem = elevatorSystem;
        requires(this.elevatorSystem);
    }

    @Override
    public void initialize(ActionControl control) {
        elevatorSystem.resetPID();
    }

    @Override
    public void execute(ActionControl control) {
        elevatorSystem.moveToLowerCone();
    }

    @Override
    public boolean isFinished(){
        return elevatorSystem.isAtLowerCone();
    }

    @Override
    public void end(FinishReason reason) {
        elevatorSystem.stop();
    }
}
