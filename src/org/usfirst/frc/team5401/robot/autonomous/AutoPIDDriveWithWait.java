package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoPIDDriveWithWait extends CommandGroup {

    public AutoPIDDriveWithWait() {
        addSequential(new WaitCommand(2));
        addSequential(new AutoPIDDrive(50));
        addSequential(new WaitCommand(2));
        //addSequential(new AutoPIDTurnAngle(10));
    }
}
