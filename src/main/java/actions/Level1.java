package actions;


import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Elevator;

public class Level1 extends ActionBase {

    private Elevator elevator;

    public Level1(Elevator elevator){
         this.elevator = elevator;

         requires(this.elevator);
    }

    @Override
    public void initialize(ActionControl control) {
        elevator.pidReset();
    }

    @Override
    public void execute(ActionControl control){
        elevator.firstLevel();
    }

    @Override
    public boolean isFinished(){
        return elevator.EndLevel1();
    }

    @Override
    public void end(FinishReason reason){
        elevator.stop();
    }

}
