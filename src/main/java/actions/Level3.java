package actions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Elevator;

public class Level3 extends ActionBase {

    private Elevator elevator;

    public Level3(Elevator elevator){
        this.elevator = elevator;

        requires(this.elevator);
    }

    @Override
    public void initialize(ActionControl control) {
        elevator.pidReset();
    }

    @Override
    public void execute(ActionControl control){elevator.thirdLevel();}

    @Override
    public boolean isFinished(){return elevator.EndLevel3();}

    @Override
    public void end(FinishReason reason){
        elevator.stop();
    }
}
