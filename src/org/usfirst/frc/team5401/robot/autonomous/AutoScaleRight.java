package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleRight extends CommandGroup {

    public AutoScaleRight() {
    	//Start at Auto Position #6
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "XXX" : DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'L')
    	{ 
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoPIDDrive(331.549));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoPIDDrive(40));

    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//This will be putting block the right side
    		addSequential(new AutoPIDDrive(70));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoPIDDrive(280));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoPIDDrive(261.549));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoPIDDrive(40));
    		//addSequential(new xxxx());
    	}
    }
}
