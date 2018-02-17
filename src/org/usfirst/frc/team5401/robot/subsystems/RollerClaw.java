package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RollerClaw extends Subsystem {
	
	private DigitalInput limitSwitch;
	private DoubleSolenoid rollerClawShort;
	private DoubleSolenoid rollerClawLong;
	
	private VictorSP topRoller;
	private VictorSP bottomRoller;
	
	public RollerClaw(){
//		topRoller = new VictorSP(RobotMap.ROLLER_CLAW_TOP_ROLLER);
//		bottomRoller = new VictorSP(RobotMap.ROLLER_CLAW_BOTTOM_ROLLER);
		rollerClawShort = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.ROLLER_CLAW_FAR_IN, RobotMap.ROLLER_CLAW_FAR_OUT);
		rollerClawLong = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.ROLLER_CLAW_CLOSE_IN, RobotMap.ROLLER_CLAW_CLOSE_OUT);
//		limitSwitch = new DigitalInput(RobotMap.ROLLER_CLAW_LIMIT_SWITCH);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void rollerInOut(int inOutDirection){
    	topRoller.set(RobotMap.ROLLER_SPEED * inOutDirection);
    	bottomRoller.set(RobotMap.ROLLER_SPEED * -inOutDirection);
    }

    //XXX Switches are all reversed because they default to true and go false when tripped
    public boolean getLimitSwitch(){
    	SmartDashboard.putBoolean("Cube in", !limitSwitch.get());
    	return !limitSwitch.get();
    }
    
    public void stopMotors(){
    	topRoller.set(0);
    	bottomRoller.set(0);
    }
    
    public void rollerClawShortChange(int clawFarDirection){
    	if (clawFarDirection == 1) {
    		rollerClawShort.set(DoubleSolenoid.Value.kForward);
    	} 
    	else {
    		rollerClawShort.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void rollerClawLongChange(int clawCloseDirection){
    	if (clawCloseDirection == 1){
    		rollerClawLong.set(DoubleSolenoid.Value.kForward);
    	}
    	else {
    		rollerClawLong.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    	
}

