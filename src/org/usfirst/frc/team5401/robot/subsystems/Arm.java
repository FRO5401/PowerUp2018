package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 *
 */
public class Arm extends Subsystem {
	
	private Solenoid brake;
	private Encoder armEncoder;
	private TalonSRX armTalon;
	private AnalogPotentiometer armPot;
	private VictorSP armMotor;
	
	private double motorSpeed;
	private double kP, kI, kD;
	private double armAngle;
	
	private boolean armPidEnabled;
	
	private int THRESH;
	
	public Arm(){
		
		//TalonSRX
		armTalon = new TalonSRX(0);
		armTalon.set(ControlMode.Velocity, 0);
		
		armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		THRESH = 0;
		
		//PID Stuff
		armPidEnabled = true;
		   /*SmartDashboard.putBoolean("PID_Enabled", pidEnabled);
		   SmartDashboard.putNumber("feed_forward", feed_forward);
		   SmartDashboard.putNumber("kP", kP);
		   SmartDashboard.putNumber("kI", kI);
		   SmartDashboard.putNumber("kD", kD);
		   */
		
		//armPot
		armPot = new AnalogPotentiometer(RobotMap.armPotChannel, RobotMap.armRange, RobotMap.armOffest);
		
		
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void setBreak(){
		//Controlled by either override or reaching end of PID setpoint
		//Disabled once override is engaged
	}
	
	public void SetPoint(){
		//Finds set point
		//Calls to command for which set point
		//Disables PID
	}
	
	public void OverrideMove(){
		//Like DriveBase, sends out a direction to move to speed controller

	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

