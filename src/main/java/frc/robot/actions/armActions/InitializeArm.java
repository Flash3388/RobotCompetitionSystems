package frc.robot.actions.armActions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.ArmSystem;

public class InitializeArm extends ActionBase {
    private ArmSystem arm;

    public InitializeArm(ArmSystem arm){
        this.arm = arm;

        requires(arm);
    }

    @Override
    public void initialize(ActionControl control) {

    }

    @Override
    public void execute(ActionControl actionControl) {
        if(arm.getPosition() <= 12)
            arm.move(0.2);

        else actionControl.finish();
    }

    @Override
    public void end(FinishReason reason) {
       arm.resetEncoder();
       arm.stop();
    }
}
