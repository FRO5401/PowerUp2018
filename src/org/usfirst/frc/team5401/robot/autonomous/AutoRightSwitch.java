package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitch extends CommandGroup {

    public AutoRightSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = (DriverStation.getInstance().getGameSpecificMessage() == null) ? "XXX" : DriverStation.getInstance().getGameSpecificMessage();
    	//Start at Auto Position #5. Drive Forward to the switch, place block on switch
    		addSequential(new AutoPIDDrive(97));
    	if(gameData.charAt(0) == 'R')
    	{
    		//Arm deploy
    		
    		//addSequential(new xxxx());
    
    	}
    }
}
