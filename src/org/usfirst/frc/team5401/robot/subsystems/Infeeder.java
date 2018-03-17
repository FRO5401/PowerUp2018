package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */

public class Infeeder extends Subsystem {
	
	private VictorSP left_Infeed;
	private VictorSP right_Infeed; 
	
	private DoubleSolenoid infeed_OC;
	private DoubleSolenoid infeed_UD;
	
	Infeeder(){
		
		left_Infeed   = new VictorSP(RobotMap.LEFT_INFEED);
		right_Infeed  = new VictorSP(RobotMap.RIGHT_INFEED);
		
		infeed_OC  = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEED_OPEN, RobotMap.INFEED_CLOSED);
		infeed_UD  = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.INFEED_UP, RobotMap.INFEED_DOWN);
				
	}
	
	public void infeedUD ( int UorD ){
		if( UorD == 1){
			infeed_UD.set(DoubleSolenoid.Value.kForward);
			SmartDashboard.putNumber("Infeed Up/Down", 1);
		} else if ( UorD == -1) {
			infeed_UD.set(DoubleSolenoid.Value.kReverse);
			SmartDashboard.putNumber("Infeed Up/Down", -1);
		}
	}
	
	public void infeedOC ( int CorU ){
	   	if( CorU == 1){
    		infeed_OC.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putNumber("Infeed Open/Close", 1);
    	} else if( CorU == -1) {
    		infeed_OC.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putNumber("Infeed Open/Close", -1);
    	}
    }
	
	public void infeedIO(int IorO){
    	if( IorO == 1) {
    		left_Infeed.set(RobotMap.INFEED_SPEED * IorO);
    		right_Infeed.set(-RobotMap.INFEED_SPEED * IorO);
    	} else if( IorO == -1) {
    		left_Infeed.set(RobotMap.INFEED_SPEED * IorO);
    		right_Infeed.set(-RobotMap.INFEED_SPEED * IorO);
       	} else {
    		left_Infeed.set(0);
    		right_Infeed.set(0);
    	}
    }
	
	public infeedStopped(){
		left_Infeed.set(0);
		right_Infeed.set(0);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new Infeeder());
    }
}

