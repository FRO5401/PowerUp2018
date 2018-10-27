package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitch extends CommandGroup {

    public AutoRightSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	} 
    	
    	if(gameData.charAt(0) == 'L')
    	{
    		addSequential(new AutoDrive(97, .5));
    	}
    	//Start at Auto Position #5. Drive Forward to the switch, place block on switch
    		
    	else if(gameData.charAt(0) == 'R')
    	{
    		addSequential(new AutoBaselineSwitch());	
/*    		
    		//Arm deploy
    		addSequential(new AutoPIDDrive(95));
    		//addSequential(new xxxxARM());
    		addSequential(new AutoPIDDrive(-15));
    		addSequential(new AutoPIDTurnAngle(-90));
    		//addSequential(new xxxx());
*/    
    	}else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoDrive(97, .5));
    		addSequential(new AutoTurnAngle(-90));
    		//addSequential(new xxxxARM());
    	}
    }
}
