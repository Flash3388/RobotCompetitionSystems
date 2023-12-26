package frc.robot.actions.armActions;

import com.flash3388.flashlib.hid.XboxAxis;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import frc.robot.subSystems.ArmSystem;

public class ArmWithXbox extends ActionBase {
    private ArmSystem armSystem;
    private XboxController xbox;

    public ArmWithXbox(ArmSystem armSystem, XboxController xbox){
        this.armSystem = armSystem;
        this.xbox = xbox;

        requires(armSystem);
    }

    @Override
    public void initialize(ActionControl control) {

    }

    @Override
    public void execute(ActionControl control) {
        double armSpeed = xbox.getAxis(XboxAxis.LeftStickX).getAsDouble();
        armSpeed = Math.abs(armSpeed) > 0.2 ? armSpeed : 0;
        armSystem.move(armSpeed);
    }

    @Override
    public void end(FinishReason reason) {
        armSystem.stop();
    }
}
