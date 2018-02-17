package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class DumbCamera extends Subsystem {

	public DumbCamera(){
	
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		camera.setFPS(29);
	
	}
		
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
	public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

