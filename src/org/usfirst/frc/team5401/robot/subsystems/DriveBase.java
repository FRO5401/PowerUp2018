package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
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

/**
 *
 */
public class DriveBase extends Subsystem {
	//All constants are now in RobotMap
	
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;

	private SpeedControllerGroup leftDriveGroup;
	private SpeedControllerGroup rightDriveGroup;
	

	private PIDController leftPID1;
	private PIDController leftPID2;
	private PIDController rightPID1;
	private PIDController rightPID2;
	
	private PIDController leftTurnController;
	private PIDController rightTurnController;
	
//	private DoubleSolenoid gearShifter;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private AHRS navxGyro;
	

	public DriveBase(){		
		leftDrive1   	= new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		leftDrive2  	= new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive1  	= new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		rightDrive2 	= new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
		
		leftDriveGroup = new SpeedControllerGroup(leftDrive1, leftDrive2);
		rightDriveGroup = new SpeedControllerGroup(rightDrive1, rightDrive2);
				
//		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);

		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																					vvv if this was false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		
		//Jason - I think the following is unnecessary because the initEncoder method, which called in the encoder constructor sets the PIDSourceType to displacement
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		
		leftPID1 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, leftEncoder, leftDrive1);
		leftPID2 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, leftEncoder, leftDrive2);		
		rightPID1 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, rightEncoder, rightDrive1);
		rightPID2 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, rightEncoder, rightDrive2);
		
		navxGyro = new AHRS(I2C.Port.kMXP);
		navxGyro.reset();

//		navxGyro.setPIDSourceType(PIDSourceType.kDisplacement);
		leftTurnController = new PIDController(RobotMap.TURN_P, RobotMap.TURN_I, RobotMap.TURN_D, RobotMap.TURN_F, navxGyro, leftDriveGroup);
		rightTurnController = new PIDController(RobotMap.TURN_P, RobotMap.TURN_I, RobotMap.TURN_D, RobotMap.TURN_F, navxGyro, rightDriveGroup);
		
		
		SmartDashboard.putNumber("navx Angle", 	getGyroAngle());
		SmartDashboard.putNumber("navx Pitch", 	getGyroPitch());
		SmartDashboard.putNumber("navx Roll", 	getGyroRoll());
		
		
		SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
		SmartDashboard.putNumber("Mean Enc Adj", getEncoderDistance(3));
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    
    //TODO need to verify the negatives are in right place
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive1 .set(leftDriveDesired);
    	rightDrive1.set(-1* rightDriveDesired);
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

    public void stop(){
    	leftDrive1 .set(0);
    	rightDrive1.set(0);
    	leftDrive2.set(0);
    	rightDrive2.set(0);
    }

    public void shiftGearLowToHigh(){
    	//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
//    	gearShifter.set(DoubleSolenoid.Value.kForward);
    	leftEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_RIGHT_DPP);
    	System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
//    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    	leftEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_RIGHT_DPP);
    	System.out.println("Shifting Drive Gear to Low Gear");
    }
    public double getVelocityOfRobot(){
    	double velocity = (Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate()))/2;
    	//For testing
    	SmartDashboard.putNumber("Robot Velocity", velocity);
    	return velocity;
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
    	SmartDashboard.putNumber("Mean Enc Adj", encoderDistance);
    	
    	if(encoderNumber == 1)
    	{
    		return leftDistance;
    	}
    	else if(encoderNumber == 2)
    	{
    		return rightDistance;
    	}
    	else
    	{
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
    
    public double getGyroPitch()
    {
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
    
    public void enableDriveStraightPID () {
    	leftPID1.enable();
    	leftPID2.enable();
    	rightPID1.enable();
    	rightPID2.enable();
    }
    
    public void disableDriveStraightPID () {
    	leftPID1.disable();
    	leftPID2.disable();
    	rightPID1.disable();
    	rightPID2.disable();
    }

    public void setDriveStraightSetpoint(double setpoint)	{
    	leftPID1.setSetpoint(setpoint);
    	leftPID2.setSetpoint(setpoint);
    	rightPID1.setSetpoint(-setpoint);
    	rightPID2.setSetpoint(-setpoint);
    }
    
    public double getDriveStraightSetpoint(double leftOrRight)	{
    	//setpoint is only set with the above function setSetpoint()
    	//So all set points are the same so only one setpoint needs to be sent
    	double setpoint = 0;
    	if(leftOrRight == 1){
    		setpoint = leftPID1.getSetpoint();
    	}
    	else if(leftOrRight == 2){
    		setpoint = rightPID1.getSetpoint();
    	}
    	return setpoint;
    }
    

    public void enableTurnPID () {
    	leftTurnController.enable();
    	rightTurnController.enable();
    }
    
    public void disableTurnPID () {
    	leftTurnController.disable();
    	rightTurnController.disable();
    }

    public void setTurnSetpoint(double setpoint)	{
    	//If both motors are used to turn, the motor are both positive or both negative
    	leftTurnController.setSetpoint(setpoint);
    	rightTurnController.setSetpoint(setpoint);
    }
    
    public double getLeftTurnPIDError()	{
    	return leftTurnController.getError();
    }
    
    public double getRightTurnPIDError()	{
    	return rightTurnController.getError();
    }
}
