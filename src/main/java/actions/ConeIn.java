package actions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Gripper;

public class ConeIn extends ActionBase {
    private Gripper gripper;

    public ConeIn(Gripper gripper){
        this.gripper = gripper;

        requires(this.gripper);
    }

    @Override
    public void execute(ActionControl control){
        gripper.inside();
    }

    @Override
    public void end(FinishReason reason){
        gripper.stop();
    }
}
