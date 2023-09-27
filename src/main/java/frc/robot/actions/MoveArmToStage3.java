package frc.robot.actions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.ArmSystem;

public class MoveArmToStage3 extends ActionBase {
    private ArmSystem armSystem;


    public MoveArmToStage3(ArmSystem armSystem){
        this.armSystem = armSystem;
        requires(this.armSystem);
    }

    @Override
    public void initialize(ActionControl control) {
        armSystem.resetPID();
    }

    @Override
    public void execute(ActionControl control) {
        armSystem.moveToStage3();
    }

    @Override
    public boolean isFinished(){
        return armSystem.isAtStage3();
    }

    @Override
    public void end(FinishReason reason) {
        armSystem.stop();
    }
}
