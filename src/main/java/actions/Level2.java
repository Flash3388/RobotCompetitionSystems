package actions;

import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Elevator;

public class Level2 extends ActionBase {

    private Elevator elevator;

    public Level2(Elevator elevator){
        this.elevator = elevator;

        requires(this.elevator);
    }

    @Override
    public void initialize(ActionControl control) {
        elevator.pidReset();
    }

    @Override
    public void execute(ActionControl control){elevator.secondLevel();}

    @Override
    public boolean isFinished(){return elevator.EndLevel2();}

    @Override
    public void end(FinishReason reason){
        elevator.stop();
    }
}
