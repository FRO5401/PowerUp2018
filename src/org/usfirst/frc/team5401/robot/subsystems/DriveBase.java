package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 *
 */
public class DriveBase extends PIDSubsystem {
	//All constants are now in RobotMap
	
	private VictorSP leftDrive1;
	private VictorSP rightDrive1;
	private VictorSP leftDrive2;
	private VictorSP rightDrive2;

//	private DoubleSolenoid gearShifter;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private AHRS navxGyro;
	
	double p, i, d;
	
	public DriveBase(){
		super(0,0,0);
		getPIDController().setContinuous(false);
		
		leftDrive1   = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_1);
		rightDrive1  = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_1);
		leftDrive2  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR_2);
		rightDrive2 = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR_2);
//		gearShifter = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.DRIVE_SHIFT_IN, RobotMap.DRIVE_SHIFT_OUT);
		leftEncoder = new Encoder(RobotMap.DRIVE_ENC_LEFT_A, RobotMap.DRIVE_ENC_LEFT_B, true, Encoder.EncodingType.k4X);
		//																					vvv if this was false, DPP doesn't have to be negative
		rightEncoder = new Encoder(RobotMap.DRIVE_ENC_RIGHT_A, RobotMap.DRIVE_ENC_RIGHT_B, true, Encoder.EncodingType.k4X);

		navxGyro = new AHRS(I2C.Port.kMXP);
		navxGyro.reset();
		
		SmartDashboard.putNumber("navx Angle", 	getGyroAngle());
		SmartDashboard.putNumber("navx Pitch", 	getGyroPitch());
		SmartDashboard.putNumber("navx Roll", 	getGyroRoll());
		
		
		SmartDashboard.putNumber("Left Enc Raw" , leftEncoder.get());
		SmartDashboard.putNumber("Right Enc Raw", rightEncoder.get());
		SmartDashboard.putNumber("Left Enc Adj" , leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Enc Adj", rightEncoder.getDistance());
		SmartDashboard.putNumber("Mean Enc Adj", getEncoderDistance());
		
		//p = SmartDashboard.getNumber("DriveStraight P", 0);
		//i = SmartDashboard.getNumber("DriveStraight I", 0);
		//d = SmartDashboard.getNumber("DriveStraight D", 0);
		p = RobotMap.DRIVE_P;
		i = RobotMap.DRIVE_I;
		d = RobotMap.DRIVE_D;
		
		SmartDashboard.putNumber("DriveStraight Distance", 0);
		SmartDashboard.putNumber("DriveStraight P", p);
		SmartDashboard.putNumber("DriveStraight I", i);
		SmartDashboard.putNumber("DriveStraight D", d);
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
		
		System.out.println(getGyroAngle() + " " + getGyroPitch() + " " + getGyroRoll());
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
    
    public double getEncoderDistance(){
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
    	return encoderDistance;
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
    	double p = SmartDashboard.getNumber("DriveStraight P", 0);
    	double i = SmartDashboard.getNumber("DriveStraight I", 0);
    	double d = SmartDashboard.getNumber("DriveStraight D", 0);
    	getPIDController().setPID(p, i, d);
    	enable();
    }
    
    public void disablePID () {
    	disable();
    }
    
    public double returnPIDInput () {
    	// Return your input value for the PID loop
    	// e.g. a sensor, like a potentiometer
    	// yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return getEncoderDistance();
    }
    
    public void usePIDOutput (double output) {
    	// Use output to drive your system, like a motor
    	// e.g. yourMotor.set(output);
    	SmartDashboard.putNumber("PIDOutput", output);
    	drive(output, output);
    }
}
