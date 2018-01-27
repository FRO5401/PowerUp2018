package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleLeft extends CommandGroup {

    public AutoScaleLeft() {
    	//Start at Auto Position #1
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "X" : DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'L')
    	{ 
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoDrive(331.549, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(40, 1));

    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//This will be putting block the right side
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(280, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(261.549, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(40, 1));
    		//addSequential(new xxxx());
    	}
    }
}
