package actions;

import com.flash3388.flashlib.hid.XboxAxis;
import com.flash3388.flashlib.hid.XboxController;
import com.flash3388.flashlib.scheduling.ActionControl;
import com.flash3388.flashlib.scheduling.FinishReason;
import com.flash3388.flashlib.scheduling.actions.ActionBase;
import subSystem.Swerve;

public class SwerveXbox extends ActionBase {

    private Swerve swerve;
    private XboxController xbox;

    private static final double MAX_SPEED = 4.4196;

    public SwerveXbox(Swerve swerve, XboxController xbox){
        this.swerve = swerve;
        this.xbox = xbox;

        requires(this.swerve);
    }

    @Override
    public void execute(ActionControl control){
        double speedX = xbox.getAxis(XboxAxis.LeftStickX).getAsDouble() * MAX_SPEED;
        double speedY = xbox.getAxis(XboxAxis.RightStickY).getAsDouble() * MAX_SPEED;
        double rotation = xbox.getAxis(XboxAxis.RightStickX).getAsDouble() * MAX_SPEED;
        if(Math.abs(speedX) < 0.2){
            speedX = 0;
        }
        if(Math.abs(speedY) < 0.2){
            speedY = 0;
        }
        if(Math.abs(rotation) < 0.2){
            rotation = 0;
        }
        swerve.drive(speedX,speedY,rotation);

    }


    @Override
    public void end(FinishReason reason){
        swerve.stop();
    }


}
