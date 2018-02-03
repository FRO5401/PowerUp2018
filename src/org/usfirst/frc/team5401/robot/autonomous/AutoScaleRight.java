package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleRight extends CommandGroup {

    public AutoScaleRight() {
    	//Start at Auto Position #6
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'L')
    	{ 
    	//This will be putting block on left side (Closer to Left)
    		addSequential(new AutoDrive(331.549, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(40, 1));

    		//addSequential(new xxxx());
    	}
    	else 
    	{    	
    	//This will be putting block the right side
    		addSequential(new AutoDrive(70, 1));
    		addSequential(new AutoTurnAngle(-90));
    		addSequential(new AutoDrive(280, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(261.549, 1));
    		addSequential(new AutoTurnAngle(90));
    		addSequential(new AutoDrive(40, 1));
    		//addSequential(new xxxx());
    	}
    }
}
