package org.usfirst.frc.team5401.robot.subsystems;
import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class Wrist extends Subsystem {

	private DoubleSolenoid wristMover;

	public Wrist(){
		wristMover = new DoubleSolenoid(RobotMap.WRIST_MOVER_FWD_CHANNEL,RobotMap.WRIST_MOVER_BACK_CHANNEL); 
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    	//Controlled by a button
    public void wristInOut(int direction){
    	if(direction == 1) {
    		wristMover.set(DoubleSolenoid.Value.kForward);
    		//Wrist will be extending outwards
    	} else if(direction == -1) {
    		wristMover.set(DoubleSolenoid.Value.kReverse);
    		//Wrist will be coming back inward
    	}
    }
}

