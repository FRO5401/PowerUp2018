package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class DriveBase extends Subsystem {

	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;
	
	private DoubleSolenoid gearShifter;

	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private AHRS navxGyro;

	public DriveBase(){

		leftDrive1   = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		rightDrive1  = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		leftDrive2  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive2 = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.SHIFTER_IN, RobotMap.SHIFTER_OUT);

		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																				^^^vvv if these were false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		
		//Jason - I think the following is unnecessary because the initEncoder method, which called in the encoder constructor sets the PIDSourceType to displacement
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);

		navxGyro = new AHRS(I2C.Port.kMXP);
		navxGyro.reset();
		
    	SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
		
		SmartDashboard.putNumber("navx Angle", 	getGyroAngle());
		SmartDashboard.putNumber("navx Pitch", 	getGyroPitch());
		SmartDashboard.putNumber("navx Roll", 	getGyroRoll());
	}
	
    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    

    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired);
    	rightDrive1.set(rightDriveDesired);
    	leftDrive2.set(leftDriveDesired);
    	rightDrive2.set(-1 * rightDriveDesired);
    	
    	SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
		
		SmartDashboard.putNumber("navx Angle", 	getGyroAngle());
		SmartDashboard.putNumber("navx Pitch", 	getGyroPitch());
		SmartDashboard.putNumber("navx Roll", 	getGyroRoll());
    }

    public void stopMotors(){
    	leftDrive1 .set(0);
    	rightDrive1.set(0);
    	leftDrive2.set(0);
    	rightDrive2.set(0);
    }

    public void shiftGearLowToHigh(){
    	//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	setDPPHighGear();
    	//System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	setDPPLowGear();
    	//System.out.println("Shifting Drive Gear to Low Gear");
    }
    
    public String getGearShifterValue() {
    	return gearShifter.get().toString();
    }
    
    public void setDPPLowGear(){
    	leftEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_RIGHT_DPP);
    }
    
    public void setDPPHighGear(){
    	leftEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_RIGHT_DPP);
    }
    
    public double getEncoderDistance(double encoderNumber){
    	double leftDistanceRaw = leftEncoder.get();
    	double rightDistanceRaw = rightEncoder.get();
    	SmartDashboard.putNumber("Left Enc Raw", leftDistanceRaw);
    	SmartDashboard.putNumber("Right Enc Raw", rightDistanceRaw);
    	double leftDistance = leftEncoder.getDistance();
    	double rightDistance = rightEncoder.getDistance();
    	SmartDashboard.putNumber("Left Enc Adj", leftDistance);
    	SmartDashboard.putNumber("Right Enc Adj", rightDistance);
    	double encoderDistance = (leftDistance + rightDistance)/2;
    	
    	if(encoderNumber == 1){
    		return leftDistance;
    	}
    	else if(encoderNumber == 2){
    		return rightDistance;
    	}
    	else{
    		return encoderDistance;
    	}
    }
    
    public void encoderReset(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void gyroReset(){
    	navxGyro.reset();
    }
    
    public double getGyroAngle() {
    	double currentAngle = navxGyro.getAngle();
    	SmartDashboard.putBoolean("NavX Connection", navxGyro.isConnected());
    	SmartDashboard.putNumber("navx Angle", currentAngle);
    	return currentAngle;
    }
    
    public double getGyroPitch(){
    	double currentPitch = navxGyro.getPitch();
    	SmartDashboard.putNumber("navx Pitch", currentPitch);
    	SmartDashboard.putBoolean("NavX Connection", navxGyro.isConnected());
    	return currentPitch;
    }	
    
    public double getGyroRoll(){
    	double currentRoll = navxGyro.getRoll();
    	SmartDashboard.putNumber("navx Roll", currentRoll);
    	SmartDashboard.putBoolean("NavX Connection", navxGyro.isConnected());
    	return currentRoll;
    }
}