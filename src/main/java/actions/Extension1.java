package actions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Arm;

public class Extension1 extends ActionBase {

    private Arm arm;

    public Extension1(Arm arm){

        this.arm = arm;

        requires(this.arm);
    }

    @Override
    public void initialize(ActionControl control) {
       arm.pidReset();
    }

    @Override
    public void execute(ActionControl control){
        arm.reachExtension1();
    }

    @Override
    public boolean isFinished(){
        return arm.endExtension1();
    }

    @Override
    public void end(FinishReason reason){
        arm.stop();
    }
}