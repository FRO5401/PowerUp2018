//TalonSRX integrated encoder does not have DPP
//Don't know the why loopIndex is used vs slotIndex
package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/*
 *
 */

public class ArmWrist extends Subsystem {
	
	public ArmWrist(){
		    
	}
	
    @Override
	public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

