package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleCenter extends CommandGroup {

    public AutoScaleCenter() {
    	//Start at Auto Position #3
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "XXX" : DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'L')
    	{ 
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(145, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(261.155, 1));
    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//This will be putting block the right side
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(150, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(261.155, 1));
    		//addSequential(new xxxx());
    	}
    }
}
