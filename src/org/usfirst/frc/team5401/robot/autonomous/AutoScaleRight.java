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
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	} 
<<<<<<< HEAD
    	
    	if(gameData.charAt(0) == 'L')
    	{
    		addSequential(new AutoPIDDrive(97));
    	}
    	//Start at Auto Position #5. Drive Forward to the switch, place block on switch
    		
    	else if(gameData.charAt(0) == 'R')
    	{
    		//Arm deploy
    		addSequential(new AutoPIDDrive(97));
    		//addSequential(new xxxx());
    
    	}else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(10));
=======
    	if(gameData.charAt(0) == 'R')
    	{
    	//Start at Auto Position #5.
    	//This will be putting block on left side 
    		addSequential(new AutoPIDDrive(100));
    		/*addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoPIDDrive(20));
    		addSequential(new xxxxARM());*/
   
    	}
    	else if(gameData.charAt(0) == 'L')
    	{    	
    	//Start at Auto Position #5. 
    	//This will be putting block the right side
    		addSequential(new AutoPIDDrive(85));
    		/*addSequential(new AutoTurnAngle(37));
    		addSequential(new AutoPIDDrive(85));
    		addSequential(new AutoTurnAngle(-37));
    		addSequential(new AutoPIDDrive(24));
    		addSequential(new xxxxARM());*/
    		
    	}
    	else if(gameData.charAt(0) == 'X')
    	{
    		addSequential(new AutoPIDDrive(85));
>>>>>>> 3ca46f72f8553d228cdc57a7782cfbf2d19f4d3b
    	}
    }
}
