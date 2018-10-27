package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRightSideSwitch extends CommandGroup {

    public AutoRightSideSwitch() {
    	//The following is a ternary operator, which is similar to a condensed if else statement
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if(gameData.isEmpty()){
    		gameData = "XXX";
    	} 
    	
    	if(gameData.charAt(0) == 'L')
    	{
    		addSequential(new AutoPIDDrive(144));
        	//addSequential(new AutoPIDTurnAngle(-90));
        	//addSequential(new AutoPIDDrive(20));
    	}
    	//Start at Auto Position #5. Drive Forward to the switch, place block on switch
    		
    	else if(gameData.charAt(0) == 'R')
    	{
        	addSequential(new AutoPIDDrive(150));
        	addSequential(new AutoPIDTurnAngle(-90));
        	addSequential(new AutoPIDDrive(13));
        	addSequential(new InfeedUpDownForAuto(-1));
        	addSequential(new WaitCommand(1));
        	addSequential(new InfeedInOutForAuto(-1));
    	}
    	else if(gameData.charAt(0) == 'X'){
    		addSequential(new AutoPIDDrive(97));
    		addSequential(new AutoPIDTurnAngle(90));
    	}

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
