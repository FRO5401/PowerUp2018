# PowerUp2018

## **Robot Files**

Robot files are an important part of the code that are called back to/ call to other things often. They hold a lot of information that is used in other parts of the code, and help to serve as a place to organize the bulk of things like variables, buttons, or parts

### `Robot.java`

#### Imported Files
From `wpilibj.command`
 - Command
 - Scheduler

From `wpilibj.livewindow`
 - livewindow

From `wpilibj.SmartDashboard`:
 - SendableChooser
 - SmartDashboard

Import `wpilibj.Subsystems`

### Methods/Uses

#### robotInit()
`robotInit` initializes the robot and runs once every time the robot code begins. This method initializes each Subsystem class and the OI class. Classes for Autnomous mode are also added to the SendableChooser chooser object to allow selection from the SmartDashboard.

`robotInit` doesn't require any arguments, and doesn't return any values.


#### disabledInit()
`disabledInit` runs every time the robot becomes disabled.

`disabledInit` does not require any arguments, and does not return any values.

#### disabledPeriodic()
`disabledPeriodic` runs in a loop during the period that the robot is disabled.

`disabledPeriodic`does not require any arguments, and does not return any values.

#### autonomousInit()
`autnomousInit` is where the auto code being used on startup is being chosen. This is done by going to the Java SmartDashboard, and selecting one on the list of autonomous codes. When one is chosen, and Autonomous is selected on the DriverStation, the chosen auto command will be ran when robot is enabled.

`autnomousInit` does not required any arguments

#### autonomousPeriodic()
`autonmousPeriodic` keeps the autonomous command going until it is finished


`autnomousInit` does not required any arguments

#### teleopInit()
`teleopInit` prepares the Robot to enter into TeleOp mode from being in Autonomous. This makes sure the autonomous is stopped before the robot goes into TeleOp.

#### teleopPeriodic()
`teleopPeriodic` is used to makes the TeleOp constantly run while the robot is in TeleOp mode, so it can receive input from the Operator or the Driver

#### testPeriodic()
`testPeriodic` is used to make the robot run continuously during testing.

### `RobotMap.java`

#### Imported Files
-Package, using the whole of the robot code

#### Methods/Uses
`RobotMap.java` is a mapping from the ports, sensors and actuators which are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers floating around. The Robot Map is used by the hardware and software teams, and it is the main place for *everything* that will be used in the robot to help control it.

`RobotMap.java` holds Global Constants for `ArmWrist.java`, `Climber.java`, `DriveBase.java`, `ArmOverRide.java`, `XboxMove.java`, `AutoPIDDrive`, `AutoPIDTurnAngle`, and `OI.java`.

`RobotMap.java` also holds variables for hardware parts such as PWM slots for motors used in all methods that require motors, Solenoids, and Encoders and other Sensors.

### `OI.java`

#### Imported Files
From `Wpliibj.button`
- Button
- Joystick Button

From `wpilibj.Joystick`
- Joystick

Import `wpilibj.Joystick`
Import `Wpilibl.Subsystems`

### Methods/Uses
`OI.java` is the "glue" that binds all of the controls to the physical operations that are performed by the Operator and Driver. It connects with the Commands and the Command Groups to perform the actions.

`OI.java` is the main place for creating all of the buttons (either normal buttons or bumpers/triggers) and joysticks that will be used. The first thing done in `OI.java` is the creation of the controller for both the Operator and the Driver, calling back to `RobotMap.java`. After the controllers are created, the buttons for each of the controllers are created. One type of button is a joystick button which is any button on a joystick. You create one by telling it which joystick it's on and which button number it is.

Sample:
- Joystick stick = new Joystick(port);
- Button button = new JoystickButton(stick, buttonNumber);

#### OI()
The OI constructor only uses methods from the button class. These methods accept Constructors from classes src.org.usfirst.frc.team5401.robot.commands as parameters. These methods begin to perform a task. Using `whenPressed` and `whenReleased` this is possible and easy to understand what each button does.
For buttons, there can be a `whenPressed` that will do something when the button is pressed down once, and continue doing that on its own until the task is completed. `whenReleased` is used to begin a task when the button is released. `whenReleased` can be used to stop tasks that `whenPressed` is used to start.

Note that other methods of the Button class can be used as well.

## **Command Files**

### `XboxMove.java`

#### Imported Files
From Wpilib.command
- Command
From wpilibj.smartdashboard
- SmartDashboard

From team5401.robot
- Robot
- RobotMap

#### Methods/Uses
`XboxMove.java` is the file that drives the robot and holds all of the variables that control drive base movement from the controllers. This includes moving forward and backward, the throttle, reverse, braking, and turning along with gear shifts.

#### XboxMove()
`XboxMove()` constructs `XboxMove.java`. Requires the drivebase Subsystem object to move.

#### initialize()
`initialize` is called before the robot runs for the first time. It shifts the gear from high to low for increased accuracy.

#### execute()
`execute` is called continuously when the robot is supposed to run. Inside the execute, input from the driver controller is assigned to variables. Different input correspond to different actions.
Manual gear shift occurs with two button: one to shift to low speeds and one to shift to high speeds.
Driving uses three main variables: throttle, reverse, and slew. The sum of throttle and reverse is how fast the robot goes foward or backward. Slew determines turn direction and will decrease wheel speed on the left or right to turn appropriately.

#### isFinished()
`isFinished` will return true when the robot does not need to travel anymore. It does this by seeing if the left and right sides are where they need to be. If the values of the distance traveled on both sides relative to desired distances are less than the tolerance (the amount of distance that the robot can be off of its target), it will return true. If they are off, it will return false.

#### end()
When the `isFinished` returns true, `end` will activate `disableDriveStraightPID`, stopping the robot in place.    

#### interrupted()
`interrupted` is called when another command which requires one or more of the same subsystems is scheduled to run, activating `disableDriveStraightPID`.**

## **Subsystem Files**

Subsystems are all of the different physical parts of the robot, and the code in them are all of the commands and controls that go with them.

### `DriveBase.java`

#### Imported Files
From wpilibj
- VictorSP
- DoubleSolenoid
- SpeedControllerGroup
- PIDController
- PIDSourceType
- Encoder
- I2C

From wpilibj.smartdashboard
- SmartDashboard

From wpilibj.command
- Subsystem
- PIDSubsystem

From - wpilibj.drive
- DifferentialDrive

Import kauailabs.navx.frc.AHRS

From team5401.robot
- RobotMap
- command.XboxMove

#### Methods/Uses
`DriveBase.java` holds the information for all parts that drive the base of the robot. The beginning of `DriveBase.java` contains constants that control the robot such as the VictorSP motor controllers for the wheels, speed controllers, PID controllers, Encoders, and the navxGyro.

#### `DriveBase()`
`DriveBase()` creates and names all of the new motor/speed controllers, PID controllers, Encoders, and the navxGyro, calling back to `RobotMap.java`. It also resets the current angle of the robot. `DriveBase()` puts all of the information of the robot such as encoder readings, and angles onto the SmartDashboard.

#### `initDefaultCommand()`
This sets the default command for the subsystem. It pulls the information from XboxMove to control the robot.

#### `stop()`
This stops all of the motors and sets them to zero.

#### `shiftGearLowToHigh()`
`shiftgearLowToHigh()` uses pneumatics to shift from low speed to high speed. Then, the left and right encoders are set to high gear DPP.

#### `shiftGearHighToLow()`
`shiftgearHighToLow()` uses pneumatics to shift from high speed to low speed. Then, the left and right encoders are set to low gear DPP.

#### `getVeloctiyOfRobot()`
This gets the velocity of the robot by getting the absolute value of the encoder rates. It also displays the velocity on the SmartDashboard.

#### `setDPPLowGear/HighGear()`
These set the DPP for the encoders to low and high gear.

#### `getEncoderDistance(double encoderNumber)`
This gets the raw and actual distance of the encoders, and displays them on the SmartDashboard, and then gets the mean distance of the encoders by averaging the actual distances of the left and right.

#### `encoder/gyroReset()`
Resets the value of the encoders and gyros.

#### `getGyroPitch/Roll/Angle()`
Receives the value of the gyro pitch, roll and angle, and displays them on the SmartDashboard.

#### `enable/disableDriveStraightPID()`
Enables or disables PID for driving.

#### `setDriveStraightSetpoint(double setpoint)`
This sets the setpoint for the PID to drive to.

#### `getDriveStraightSetpoint(double leftOrRight)`
Setpoints are set by `setDriveStraightSetpoint`, and they are all going to be the same, so only one setpoint needs to be sent.

#### `enable/disableTurnPID()`
These enable/disable the PID turn.

#### `setTurnSetpoint`
Receives set point for the turning. When both motors are used to turn, they will both be negative or positive, depending on the direction of the turn.

#### `getLeft/RightTurnPIDError()`
This gets the left and right turn PID error

#### `getTurnPIDOnTarget()`
This ensures the PID turn is on target on both sides.

### `Arm-Wrist.java`

#### Imported Files
From wpilibj
- VictorSP
- Solenoid
- Encoder
- AnalogPotentiometer
- DoubleSolenoid

From wpilibj.command
- Subsystem

From wpilibj.smartdashboard
- SmartDashboard

From com.ctre.phoenix.motorcontrol
- ControlMode
- FeedbackDevice
- can.TalonSRX

#### Methods/Uses
`Arm-Wrist.java` holds all of the information and commands for the Arm and Wrist, which work together when picking up a cube. It has the variables for the movements in the arm, such as the armTalon, angle, PID and brake.

#### `ArmWrist()`
`ArmWrist()` holds the main information and the instantiations for the movements of the arm through the TalonSRX, has PID information, and the DoubleSolenoids for the Wrist's movements, allowing them to be moved later on.

#### `short/longWristUpDown()`
Tells the robot to move the Double Solenoid for the short or long wrist either up or down depending on the value being sent in. If it is postive then they will move out, and if it is negative they will move in.

#### `checkWrist()`
`checkWrist()` tells the wrist to look at the angle of the arm, and change based on what the angle of the arm is by getting values from the sensor. If it is above a certain angle, then it will tell the wrists to move out, and if it is below the angle, then the wrist will go back in.

#### `setBrake(boolean brakeSet)`
This brakes the arm, and is activated by either the override or the completion of the task (getting to a PID set point). The arm is held in place.

#### `setPoint(double setPointIndexInDegrees)`
This method receives the set point that needs to be reached. It calls to the commands that receives it, and enables the arm PID.

#### `pidStop()`
Stops the PID in the arm when the set point is reached. It sets the brake, and enters the arm motor into neutral mode.

#### `overrideMove(double operatorJoystick)`
Controls the arm override through the operator joystick.

#### `onTarget()`
This returns true if the arm is on target. It gets the position of the arm and subtracts the distance it is needed to go. If it is in proper position, `onTarget` will be returned, and then arm will stop.

#### `overrideStopped()`
Sets the armTalon to neutral, disabling the arm, and enabling the brake.

#### `armInterrupted()`
Disables the armPID, neutralizing the armTalon.

#### `getArmAngle()`
This receives the sensor values for the angle of the arm, and displays them on SmartDashboard.

## **Autnomous Files**

ALL MAIN AUTNOMOUS FILES USE GAMEDATA (those that will have many commands). Game Data is layout on the field of which side of the two Alliance switches and the scale is which color, either red or blue. Different combinations of 3 characters using "L" and "R" (Left and Right) are given. For example, if the combination is "LRL", when looking down at the field from the Driver Station, the side of the switches and scale that will be your color are left, right, then left. If your alliance is blue, the left side of *your Switch* will be blue, the right side of the *Scale* will be blue, and the left side of the *opposite Switch* will be blue (LRL). Game Data is received upon the match's start, after the "3...2...1...Go!" This is represented through  "String gameData = DriverStation.getInstance().getGameSpecificMessage();", where the Game Data is received. Most autonomous files will have Game Data input, and require Game Data. All Main Auto files are command groups that run a list of commands. ALL MAIN AUTO FILES will have a "Catch/Try" for when the Game Data is empty, and tell the robot just to drive forward to the baseline.

### `AutoDrive.java`

#### Imported Files
From Wpilib.command
- Commands
From wpilibj.smartdashboard
- SmartDashboard

From team5401.robot
- Robot
- subsystems.DriveBase
- RobotMap
- commands.XboxMove

#### Methods/Uses

`AutoDrive.java` is the main place for the Autonomous information, and holds all of the commands and methods for all of the autonomous codes.
It holds important variabled used in Auto such as desiredDistance, autoDriveSpeed, doneTraveling, distanceTraveled, heading, drift, kP_Drift and velocitySample2. These variables are used to control what the robot will do in each Auto, and control how it does it. `AutoDrive.java` will also serve as the "Drive to Baseline" code for when the Robot is just going to drive to the baseline.

##### AutoDrive(double DistanceInput, double SpeedInput)
`AutoDrive` requires `DistanceInput` and `SpeedInput` to determine what the robot will do. It uses the `DistanceInput` to determine the distance, and the `SpeedInput` to determine the percent of full power will be used.

##### initialize()
`initialize` is called before the command runs for the first time. It resets all of the Encoders and angles to make them back to the default to help prepare for the Auto to run.

##### execute()
`execute` is called and runs continuously when the robot is supposed to run. It will go through a set of conditioms to determine whether or not the task is completed.

#### isfinished()
`isFinished` will return true when the robot does not need to travel anymore.

#### end()
`end` is called after the `isFinished` returns true, and ends the command, stopping the drivebase.

#### interrupted()
`interrupted` is called when another command which requires one or more of the same subsystems is scheduled to run.

### `AutoCenterSwitch.java`

#### Imported Files
Imports `wpilibj.DriverStation`

#### Methods/Uses

In `AutoCenterSwitch.java`, the robot starts against the wall, to the right of the Exchange area on the field. This way, it is centered. `AutoCenterSwitch.java` uses "If/Else" statements to determine what the robot is going to do based on the Game Data that is received. `AutocenterSwitch.java` has a series of "AddSequentials" that either tell the robot to move forward a certain distance, or tell the robot to turn a certain angle left or right. If the Game Data returns the side of the switch that is our alliance color is the left, the robot will go through the commands that get it in front of the switch, then place the block down using `ArmDeploy`. Else, if the Game Data returns the side of the switch that is our alliance color is the right, because the robot is in the middle, the commands from the AddSequentials will just be reversed, and the Power Cube will be placed on the right side.

### `AutoLeftSwitch.java`

#### Imported Files
Imports `Wpilib.DriverStation`

#### Methods/Uses

In `AutoLeftSwitch.java`, the robot starts against the wall, to the left of the Exchange area on the field. This way it is on the left side of the field. `AutoLeftSwitch.java` uses "If/Else" statements to determine what the robot is going to do based on the Game Dates that is received. `AutoLeftSwitch.java` has a series of "AddSequentials" that either tell the robot to move forward a certain distance, or tell the robot to turn a certain angle left or right. If the Game Data returns the side of the switch that is our alliance color is the left, the robot will go through its commands that get it in front of the left side of the switch, then place the block down using `ArmDeploy`. Else, if the Game Data returns the side of the switch that is our alliance color is the right, the robot will not go for the right side of the switch. Instead, it will just drive forward to the baseline, which is all that is needed for all of the alliance members to get a ranking point, and just stop. This is to make sure the robot does not interfere with other alliance members, as they will most likely be starting on that side if we are not.

### `AutoRightSwitch.java`

#### Imported Files
Imports `wpilibj.DriverStation`

#### Methods/Uses

In `AutoRightSwitch`, the robot starts against the wall, and will ideally be aligned directly in front of the right side of the switch. This way it will just be a straight drive up to the switch. `AutoRightSwitch` uses "If/Else" statements to determine what the robot is going to do based on the Game Data that is received. `AutoRightSwitch` has only two "AddSequentials" that will be used. No matter what the Game Data is, the robot will go all the way up to the switch. Because it is all the way up to the switch, it is past the baseline too, so it can be used whether the robot will be putting a Power Cube on the switch or not. If the Game Data returns that the side of the switch that is our alliance color is the right, the robot will stay in position, along with putting the Power Cube on the switch through `ArmDeploy`. Else, if the Game Data returns that the side of the switch that is our alliance color is the left, the robot will only stay in place. This is to make sure the robot does not interfere with other alliance members, as they will most likely be starting on that side if we are not.

### `AutoTurnAngle.java`

#### Imported Files
From Wpilib.command
- Command
- Scheduler
From wpilibj.smartdashboard
- SmartDashboard

From team5401.robot
- Robot
- commands.XboxMove
- RobotMap

#### Methods/Uses
`AutoTurnAngle.java` has variables that are required to run the methods later in the program.

#### AutoTurnAngle(double angle)
`AutoTurnAngle` requires `Robot.drivebase` to run. This is the method that prepares the code to run before it actually starts. It sets all of the values of the angles back down to 0, and readies the robot. `AutoturnAngle` needs `desiredAngleRealtiveToInitAngle`, sets the current angle back down to 0, and sets the initial angle back down to zero so the desired angle will be achieved fully.

#### initialize()
`initialize` is called before the command runs for the first time. This method sets the initial angle to the value of the `getGyroAngle` value, and again sets the currentAngleRelativeToInitAngle to 0.

#### execute()
`execute` is called continuously when the robot is supposed to run. It will go through a set of conditions to determine whether or not the task is completed.

#### isFinished()
`isFinished` will return true when the robot does not need to travel anymore.

#### end()
`end` is called after the `isFinished` returns true, and ends the command, stopping the drivebase.

#### interrupted()
`interrupted` is called when another command which requires one or more of the same subsystems is scheduled to run.

### `AutoPIDDrive.java`

#### Imported Files
From wpilibj.command
- Command
From wpilibj.smartdashboard
- SmartDashboard

From team5401.robot
- Robot
- RobotMap

#### Methods/Uses
Creates a variable for the distance that the robot will drive. Requires `Robot.drivebase`.

#### initialize()
`initialize` is called before the robot runs for the first time. It resets the encoders on the drive base to refresh the values and get more accurate readings. Then, it sets the distance the robot is supposed to drive, setting the set point to the `desiredDistance`/`distance`.

#### execute()
`execute` is called continuously when the robot is supposed to run. The execute first reads the angle of the robot. Then, it gets the position of the left and right sides of the robot. After it runs, it returns the amount of error on the left and right sides by subtracting the distance the robot is supposed to go by the encoder distances.

#### isFinished()
`isFinished` will return true when the robot does not need to travel anymore. It does this by seeing if the left and right sides are where they need to be. If the values of the distance traveled on both sides relative to desired distances are less than the tolerance (the amount of distance that the robot can be off of its target), it will return true. If they are off, it will return false.

#### end()
When the `isFinished` returns true, `end` will activate `disableDriveStraightPID`, stopping the robot in place.    

#### interrupted()
`interrupted` is called when another command which requires one or more of the same subsystems is scheduled to run, activating `disableDriveStraightPID`.

## ADD SCALE AUTOS WHEN DONE
