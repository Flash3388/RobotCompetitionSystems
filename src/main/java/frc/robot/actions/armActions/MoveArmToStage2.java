package frc.robot.actions.armActions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.ArmSystem;

public class MoveArmToStage2 extends ActionBase {
    private ArmSystem armSystem;


    public MoveArmToStage2(ArmSystem armSystem){
        this.armSystem = armSystem;
        requires(this.armSystem);
    }

    @Override
    public void initialize(ActionControl control) {
        armSystem.resetPID();
    }

    @Override
    public void execute(ActionControl control) {
        armSystem.moveToStage2();
    }

    @Override
    public boolean isFinished(){
        return armSystem.isAtStage2();
    }

    @Override
    public void end(FinishReason reason) {
        armSystem.stop();
    }
}
