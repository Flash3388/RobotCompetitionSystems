package frc.robot.actions.gripperActions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.Gripper;

public class CollectCone extends ActionBase {
    private Gripper gripper;

    public CollectCone(Gripper gripper) {
        this.gripper = gripper;

        requires(gripper);
    }

    @Override
    public void execute(ActionControl control) {
        gripper.pickCone();
    }

    @Override
    public void end(FinishReason reason) {
        gripper.stop();
    }
}
