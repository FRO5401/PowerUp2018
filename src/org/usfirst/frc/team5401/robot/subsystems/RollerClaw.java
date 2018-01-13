package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RollerClaw extends Subsystem {
	
	private DigitalInput limitSwitch;
	private Solenoid claw;
	
	private VictorSP topRoller;
	private VictorSP bottomRoller;
	
	public RollerClaw(){
		topRoller = new VictorSP(RobotMap.ROLLER_CLAW_LEFT_ROLLER);
		bottomRoller = new VictorSP(RobotMap.ROLLER_CLAW_RIGHT_ROLLER);
		claw = new Solenoid(RobotMap.ROLLER_CLAW_SOLENOID);
		limitSwitch = new DigitalInput(RobotMap.ROLLER_CLAW_LIMIT_SWITCH);
		
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
    
    public void rollerRotate(int rotateDirection){
    	topRoller.set(RobotMap.ROLLER_SPEED * rotateDirection);
    	bottomRoller.set(RobotMap.ROLLER_SPEED * rotateDirection);
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
    
    public void clawExpandContract(int clawDirection){
    	if(clawDirection == 1){
    		claw.set(true);
    	}
    	else if(clawDirection == -1){
    		claw.set(false);
    	}
    	
    }
    	
}

