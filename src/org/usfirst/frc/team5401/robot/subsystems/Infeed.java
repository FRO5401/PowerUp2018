package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.InfeedControl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Infeed extends Subsystem {
	
	//Solenoid Subject to change, depending on stock
	private DoubleSolenoid clawOpenClose;
	private DoubleSolenoid clawUpDown;
	private VictorSP clawRollerLeft;
	private VictorSP clawRollerRight;

    public Infeed(){
    	clawOpenClose = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEED_OPEN, RobotMap.INFEED_CLOSE);
    	clawUpDown = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEED_UP, RobotMap.INFEED_DOWN);
    	clawRollerLeft = new VictorSP(RobotMap.INFEED_ROLLER_LEFT);
    	clawRollerRight = new VictorSP(RobotMap.INFEED_ROLLER_RIGHT);
    }
    
    public void clawOpenClose(int horizDirection){
    	if(horizDirection == 1){
    		clawOpenClose.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Infeed Open/Close", 1);
    	}
    	else if(horizDirection == -1){
    		clawOpenClose.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Infeed Open/Close", -1);
    	}
    }
    
    public void clawUpDown(int vertDirection){
    	if(vertDirection == 1){
    		clawUpDown.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Infeed Up/Down", 1);
    	}
    	else if(vertDirection == -1){
    		clawUpDown.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Infeed Up/Down", -1);
    	}
    }
    
    public void feedInOut(int feederDirection){
    	if(feederDirection == 1)
    	{
    		clawRollerLeft.set(RobotMap.INFEED_SPEED * feederDirection);
    		clawRollerRight.set(-RobotMap.INFEED_SPEED * feederDirection);
    	}
    	else if(feederDirection == -1)
    	{
    		clawRollerLeft.set(RobotMap.INFEED_SPEED * feederDirection);
    		clawRollerRight.set(-RobotMap.INFEED_SPEED * feederDirection);
       	}
    	else
    	{
    		clawRollerLeft.set(0);
    		clawRollerRight.set(0);
    	}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new InfeedControl());
    }
}

