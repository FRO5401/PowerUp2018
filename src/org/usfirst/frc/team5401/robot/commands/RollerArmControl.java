package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;


public class RollerArmControl extends Command {

    private int rollerInOut;
    private int turnGrabber;
    private int leftTrigger;
    private int rightTrigger;
    private double joystick;
    private VictorSP grabberLeft = new VictorSP();
    private VictorSP grabberRight = new VictorSP();
    private DoubleSolenoid doubleSolenoidLeft = new DoubleSolenoid();
    private DoubleSolenoid doubleSolenoidRight = new DoubleSolenoid();

    public RollerArmControl() {
        rollerInOut = 0;
        turnGrabber = 0;
        joystick = 0;
        leftTrigger = 0;
        rightTrigger = 0;
        requires(Robot.rollerarm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }



    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        rollerInOut = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_BUTTON_L3_OPERATOR, Robot.oi.xboxController_Operator);
        turnGrabber = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_BUTTON_R3_OPERATOR, Robot.oi.xboxController_Operator);
        leftTrigger = -1 * Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_TRIGGER, Robot.oi.xboxController_Operator);
        rightTrigger = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_RIGHT_TRIGGER, Robot.oi.xboxController_Operator);
        joystick = Robot.oi.xboxAxisAsDigitalInput(RobotMap.XBOX_AXIS_LEFT_X, Robot.oi.xboxController_Operator);

        if (joystick == 1 || joystick == -1) {
            grabberLeft.set(joystick * RobotMap.ROLLER_SPEED);
            grabberRight.set(joystick * RobotMap.ROLLER_SPEED);
        } else {
            grabberLeft.set(0);
            grabberRight.set(0);
        }

        if (leftTrigger == -1) {
            grabberLeft.set(leftTrigger);
            grabberRight.set(leftTrigger);
        } else if (rightTrigger == 1) {
            grabberRight.set(rightTrigger);
            grabberRight.set(rightTrigger);
        }

        if (joystick == -1) {
            doubleSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
            doubleSolenoidRight.set(DoubleSolenoid.Value.kReverse);
        } else if (joystick == 1) {
            doubleSolenoidLeft.set(DoubleSolenoid.Value.kForward);
            doubleSolenoidRight.set(DoubleSolenoid.Value.kForward);
        }
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {

    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
