package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.GreenTape;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class DumbCamera extends Subsystem {
	
	
	private VisionThread visionThread;
	private double centerX;
	private double centerY;
	
	private int cameraWidth;
	private int cameraHeight;
	
	private double inchesForHalfOfWidth;
	
	private final Object visionLock = new Object();
	public DumbCamera(){
	
		centerX = 0.0;
		centerY = 0.0;
		
		cameraWidth = 480;
		cameraHeight = 360;
		
		inchesForHalfOfWidth = 0;
		
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(cameraWidth, cameraHeight);
		camera.setFPS(10);
		camera.setBrightness(0);
		camera.setExposureManual(0);
		
		
		
		visionThread = new VisionThread(camera, new GreenTape(), pipeline -> {
			if(!pipeline.filterContoursOutput().isEmpty()){
				Rect boundingBox = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				
				synchronized(visionLock){
					//image location is determined by the top left corner .x finds x coordinate, .y finds y coordinate
					//If there is not a shape, the coordinate will be (0,0)
					centerX = boundingBox.x + (boundingBox.width/2); 
					centerY = boundingBox.y + (boundingBox.height/2);
				}
			}
		});
		visionThread.start();


	}
		
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
	public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double visionLoopSynchronized(){
    	double centerX;
		synchronized(visionLock){
			centerX = this.centerX;//Basically centerX in this method  = centerX declared above in the class
		}
		
		//If centerX is 0 (when there is no shape), the robot will turn until a shape is found
		System.out.println(centerX);
		
		
		//XXX Below gives turn, in pixel amount, to turn towards the middle, need to put in pixel to distance conversion 
		double turnInPixelDistance = centerX - (cameraWidth/2);//XXX may need to put it as (image width/2) - centerX.
		
		//12 inches over 160 pixel. Measured. 
		double turnInInchesDistance = turnInPixelDistance * (inchesForHalfOfWidth/cameraWidth);
		//atan is arc tan.  
		double turnAngleInRad = Math.atan(turnInInchesDistance/inchesForHalfOfWidth);
		double turnAngleInDegrees = turnAngleInRad * (180.0/(Math.PI));//Coverts Radian angle to degrees
		
		return turnAngleInDegrees;
    }
}

