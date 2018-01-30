package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*
 *
 */

public class ArmWrist extends Subsystem {

	private DoubleSolenoid wristMoveLeft1;
	private DoubleSolenoid wristMoveRight1;
	private DoubleSolenoid wristMoveLeft2;
	private DoubleSolenoid wristMoveRight2;
	private Solenoid brake;
	private Encoder armEncoder;
	private TalonSRX armTalon;
	private AnalogPotentiometer armPot;//Probably will not be used


	private double armAngle;
	private boolean armPidEnabled;
	private int loopIndex;
	private int setArrayPoint[] =  {0, 0 ,0 , 0};

	public ArmWrist(){

		loopIndex = 0;

		//Object instantiation
		armTalon = new TalonSRX(0);//TODO need to check on RoboRIO  //TODO Make this a constant ref to robot map, move other todo to robotmap
		armPot = new AnalogPotentiometer(RobotMap.ARM_POT_CHANNEL, RobotMap.ARM_RANGE, RobotMap.ARM_OFFSET);
		
		wristMoveLeft1 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_LEFT_FORWARD_1, RobotMap.WRIST_MOVE_LEFT_BACKWARD_1);
		wristMoveRight1 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_RIGHT_FORWARD_1, RobotMap.WRIST_MOVE_RIGHT_BACKWARD_1);
		wristMoveLeft2 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_LEFT_FOWARD_2, RobotMap.WRIST_MOVE_LEFT_BACKWARD_2);
		wristMoveRight2 = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.WRIST_MOVE_RIGHT_FORWARD_2, RobotMap.WRIST_MOVE_RIGHT_BACKWARD_2);

		/******REPEAT THE FOLLOWING LINE TO MAKE SET POINTS*********/

		//0 is the output value, possibly position wanted? in encoder tick or analog value, not sure native value

		//Sets up the Control mode to PID position
		armTalon.set(ControlMode.Position, 0);	
		
		//Sets up feedback device for PID
		armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, loopIndex, RobotMap.TIMEOUT_LIMIT_IN_Ms);//10 is a timeout that waits for successful conection to sensor
		armTalon.setSensorPhase(true);//true if sensor value is positive if the motor controller output is negative. False if both are positive or negative
		
		//Sets allowable error, which is how far the actual value is off from intended value
		//0 is slot index, which is parameter slot for the constant. Not sure what this actually does
		armTalon.configAllowableClosedloopError(loopIndex, RobotMap.ARM_THRESHOLD_FOR_PID, RobotMap.TIMEOUT_LIMIT_IN_Ms);
		
		//Sets max and min reverse and forward. First number is max/min output in percent 1 = 100%
        armTalon.configNominalOutputForward(0, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configNominalOutputReverse(0, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configPeakOutputForward(1, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.configPeakOutputReverse(-1, 	RobotMap.TIMEOUT_LIMIT_IN_Ms);
       
        //Sets F, P, I, and D
        //Middle parameter is the corresponding value for F, P, I, or D
        armTalon.config_kF(loopIndex, RobotMap.ARM_kF, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kP(loopIndex, RobotMap.ARM_kP, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kI(loopIndex, RobotMap.ARM_kI, RobotMap.TIMEOUT_LIMIT_IN_Ms);
        armTalon.config_kD(loopIndex, RobotMap.ARM_kD, RobotMap.TIMEOUT_LIMIT_IN_Ms);        
	}
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void wristUpDown(int wristDirection){
    	if(wristDirection == 1) {
    		wristMoveLeft1.set(DoubleSolenoid.Value.kForward);
    		wristMoveRight1.set(DoubleSolenoid.Value.kForward);
    		//Wrist will be extending outwards
    	} 
    	else if(wristDirection == -1) {
    		wristMoveLeft1.set(DoubleSolenoid.Value.kReverse);
    		wristMoveRight1.set(DoubleSolenoid.Value.kReverse);
    		//Wrist will be coming back inward
    	}
    }
    public void checkWrist(){
    	if(armEncoder.getDistance() > 89){
    		wristMoveLeft2.set(DoubleSolenoid.Value.kForward);
    		wristMoveRight2.set(DoubleSolenoid.Value.kForward);
    	}
    	else if(armEncoder.getDistance() < 89){
    		wristMoveLeft2.set(DoubleSolenoid.Value.kReverse);
    		wristMoveRight2.set(DoubleSolenoid.Value.kReverse);
    	}
    }

	public void setBrake(boolean brakeSet){
		//Controlled by either override or reaching end of PID setpoint
		//Disabled once override is engaged
		brake.set(brakeSet);
	}

	

	public void setPoint(int setPointIndex){
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		armTalon.set(ControlMode.Position, setArrayPoint[setPointIndex]);
		brake.set(false);
		armPidEnabled = true;
		//Finds set point
		//Calls to command for which set point

	}

	public void pidStop(){
		brake.set(true);
		armPidEnabled = false;
		armTalon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
	}

	public void overrideMove(double operatorJoystick){
		armTalon.set(ControlMode.PercentOutput, operatorJoystick);
		//Like DriveBase, sends out a direction to move to speed controller
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}

