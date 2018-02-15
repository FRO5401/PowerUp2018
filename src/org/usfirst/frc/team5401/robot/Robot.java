package org.usfirst.frc.team5401.robot;
import org.usfirst.frc.team5401.robot.autonomous.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.autonomous.AutoDrive;
import org.usfirst.frc.team5401.robot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//OI has to be last
	public static ArmWrist armwrist;
	public static Climber climber;
	public static DriveBase drivebase;
	public static RollerClaw rollerclaw;
	public static Wrist wrist;
	public static CompressorSubsystem compressorsubsystem;
	public static OI oi;
	

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//OI has to be last
		armwrist = new ArmWrist();
		climber = new Climber();
		drivebase = new DriveBase();
		rollerclaw = new RollerClaw();
		wrist = new Wrist();
		compressorsubsystem = new CompressorSubsystem();
		oi = new OI();
		
		//chooser.addDefault("Default Auto", new ExampleCommand());
		chooser.addDefault("Do Nothing", new AutoPIDDrive(0));
		//chooser.addObject("Drive Straight", new AutoDrive(55, .5)); //non pid
		chooser.addObject("Baseline Only", new AutoPIDDrive(97));
		chooser.addObject("PID Drive with Wait", new AutoPIDDriveWithWait());
		chooser.addObject("AutoCenterSwitch", new AutoCenterSwitch());
		chooser.addObject("AutoLeftSwitch", new AutoLeftSwitch());
		chooser.addObject("AutoRightSwitch", new AutoRightSwitch());
		chooser.addObject("AutoScaleLeft", new AutoScaleLeft());
		chooser.addObject("AutoScaleRight", new AutoScaleRight());
		chooser.addObject("AutoTurnTest", new AutoTurnAngle(360));
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		SmartDashboard.putString("Auto Side in INIT", DriverStation.getInstance().getGameSpecificMessage());
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("Auto Side PERIODIC", DriverStation.getInstance().getGameSpecificMessage());
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
