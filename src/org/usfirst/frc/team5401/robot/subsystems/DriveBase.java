package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
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
	//All constants are now in RobotMap
	
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;

    private Solenoid gearShifter;
	private PIDController leftPID1;
	private PIDController leftPID2;
	private PIDController rightPID1;
	private PIDController rightPID2;

	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private AHRS navxGyro;
	

	public DriveBase(){

		leftDrive1   = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		rightDrive1  = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		leftDrive2  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive2 = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
		gearShifter = new Solenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT);

		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																					vvv if this was false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);
		
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		
		leftPID1 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, leftEncoder, leftDrive1);
		leftPID2 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, leftEncoder, leftDrive2);		
		rightPID1 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, rightEncoder, rightDrive1);
		rightPID2 	= new PIDController(RobotMap.DRIVE_P,RobotMap.DRIVE_I,RobotMap.DRIVE_D, rightEncoder, rightDrive2);
		
		
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
    	gearShifter.set(true);
    	leftEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_RIGHT_DPP);
    	System.out.println("Shifting Drive Gear to High Gear");
    }

    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	gearShifter.set(false);
    	leftEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_RIGHT_DPP);
    	System.out.println("Shifting Drive Gear to Low Gear");
    }
    
    public boolean getGearShifterValue () {
    	return gearShifter.get();
    }
    
    public void setDPPLowGear(){
    	leftEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.LOW_GEAR_RIGHT_DPP);
    }
    
    public void setDPPHighGear(){
    	leftEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_LEFT_DPP);
    	rightEncoder.setDistancePerPulse(RobotMap.HIGH_GEAR_RIGHT_DPP);
    }
    
    //Type Average for encoderDistance
    public double getEncoderDistance(String whichEncoder){
    	SmartDashboard.putNumber("Left Enc Raw", leftEncoder.get());
    	SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
    	SmartDashboard.putNumber("Left Enc Adj", leftEncoder.getDistance());
    	SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
    	double encoderDistance = (leftEncoder.getDistance() +  rightEncoder.getDistance())/2;
    	SmartDashboard.putNumber("Mean Enc Adj", encoderDistance);
    	if(whichEncoder.equals("left"))
    	{
    		return leftEncoder.getDistance();
    	}
    	else if(whichEncoder.equals("right"))
    	{
    		return rightEncoder.getDistance();
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
    
    public double getGyroAngle() {
    	double currentAngle = navxGyro.getAngle();
    	SmartDashboard.putNumber("navx Angle", currentAngle);
    	return currentAngle;
    }
    
    public double getGyroPitch()
    {
    	double currentPitch = navxGyro.getPitch();
    	SmartDashboard.putNumber("navx Pitch", currentPitch);
    	return currentPitch;
    }	
    
    public double getGyroRoll(){
    	double currentRoll = navxGyro.getRoll();
    	SmartDashboard.putNumber("navx Roll", currentRoll);
    	return currentRoll;
    }
    
    public void enablePID () {
    	leftPID1.enable();
    	leftPID2.enable();
    	rightPID1.enable();
    	rightPID2.enable();
    }
    
    public void disablePID () {
    	leftPID1.disable();
    	leftPID2.disable();
    	rightPID1.disable();
    	rightPID2.disable();
    }
    
    public double returnPIDInput () {
    	// Return your input value for the PID loop
    	// e.g. a sensor, like a potentiometer
    	// yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return getEncoderDistance(3);
    }
    
    public void usePIDOutput (double output) {
    	// Use output to drive your system, like a motor
    	// e.g. yourMotor.set(output);
    	SmartDashboard.putNumber("PIDOutput", output);
    	drive(output, output);
    }
    
    public void setSetpoint(double setpoint)	{
    	leftPID1.setSetpoint(setpoint);
    	leftPID2.setSetpoint(setpoint);
    	rightPID1.setSetpoint(-setpoint);
    	rightPID2.setSetpoint(-setpoint);
    }
    
    public double getSetpoint(double leftOrRight)	{
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
}
