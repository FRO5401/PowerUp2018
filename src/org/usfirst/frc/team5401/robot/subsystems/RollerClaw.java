package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RollerClaw extends Subsystem {
	
	//Solenoid Subject to change, depending on stock
	private DoubleSolenoid clawOpenClose;
	private DoubleSolenoid clawUpDown;
	private VictorSP clawRollerLeft;
	private VictorSP clawRollerRight;

    public RollerClaw(){
    	clawOpenClose = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.ROLLER_CLAW_OPEN, RobotMap.ROLLER_CLAW_CLOSE);
    	clawUpDown = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.ROLLER_CLAW_UP, RobotMap.ROLLER_CLAW_DOWN);
    	clawRollerLeft = new VictorSP(RobotMap.ROLLER_CLAW_ROLLER_LEFT);
    	clawRollerRight = new VictorSP(RobotMap.ROLLER_CLAW_ROLLER_RIGHT);
    }
    
    public void clawOpenClose(int horizDirection){
    	if(horizDirection == 1){
    		clawOpenClose.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("RollerClaw Open/Close", 1);
    	}
    	else if(horizDirection == -1){
    		clawOpenClose.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("RollerClaw Open/Close", -1);
    	}
    }
    
    public void clawUpDown(int vertDirection){
    	if(vertDirection == 1){
    		clawUpDown.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("RollerClaw Up/Down", 1);
    	}
    	else if(vertDirection == -1){
    		clawUpDown.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("RollerClaw Up/Down", -1);
    	}
    }
    
    public void feedInOut(int feederDirection){
    	if(feederDirection == 1)
    	{
    		clawRollerLeft.set(RobotMap.ROLLER_SPEED * feederDirection);
    		clawRollerRight.set(-RobotMap.ROLLER_SPEED * feederDirection);
    	}
    	else if(feederDirection == -1)
    	{
    		clawRollerLeft.set(RobotMap.ROLLER_SPEED * feederDirection);
    		clawRollerRight.set(-RobotMap.ROLLER_SPEED * feederDirection);
       	}
    	else
    	{
    		clawRollerLeft.set(0);
    		clawRollerRight.set(0);
    	}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

