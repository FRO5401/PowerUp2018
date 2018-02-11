# PowerUp2018

## Robot Files

Robot files are an important part of the code that are called back to/ call to other things often. They hold a lot of information that is used in other parts of the code, and help to serve as a place to organize the bulk of things like variables, buttons, or parts

### `Robot.java`

#### Imported Files
From `Wpilibj.command`
 - Command
 - Scheduler

From `Wpilibj.livewindow`
 - livewindow

From `wpilibj.SmartDashboard`:
 - SendableChooser
 - SmartDashboard

Import `Wpilibj.Subsystems`

### Methods/Uses

#### robotInit()
`robotInit` initializes the robot. It assigns each of the components as variables and starts the autonomous function, `AutoDrive` from the `chooser` class. It is called every time the Robot enters Disabled Mode.

`robotInit` doesn't require any arguments, and doesn't return any values.


#### disabledInit()

#### disabledPeriodic()
`disabledPeriodic`

#### autonomousInit()
`autnomousInit` is where the auto code being used on startup is being chosen. This is done by going to the Java SmartDashboard, and selecting one on the list of autonomous codes. When one is chosen, and Autonomous is selected on the DiverStation, the chosen auto command will be ran when robot is enabled.

`autnomousInit` does not required any arguments

#### autonomousPeriodic()
`autonmousPeriodic` keeps the autonomous command going until it is finished

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

`RobotMap.java` holds Global Constants for `DriveBase.java`, `XboxMove.java`, `AutoTurnAngle.java`, `OI.java` and PCM ID.
`RobotMap.java` also holds variables for hardware parts such as drive motors used in all methods that require driving, Solenoids, and Encoders and Sensors.

### `OI.java`

#### Imported Files
From `Wpliibj.button`
- Button
- Joystick Button

From `Wpilibj.Joystick`
- Joystick

Import `Wpilibj.Joystick`
Import `Wpilibl.Subsystems`

### Methods/Uses
`OI.java` is the "glue" that binds all of the controls to the physical operations that are performed by the Operator and Driver. It connects with the Commands and the Command Groups to perform the actions.

`OI.java` is the main place for creating all of the buttons (either normal buttons or bumpers/triggers) and joysticks that will be used. The first thing done in `OI.java` is the creation of the controller for both the Operator and the Driver, calling back to `RobotMap.java`. After the controllers are created, the buttons for each of the controllers are created. One type of button is a joystick button which is any button on a joystick. You create one by telling it which joystick it's on and which button number it is.

Sample:
- Joystick stick = new Joystick(port);
- Button button = new JoystickButton(stick, buttonNumber);

When using a button in a command, you can perform a task. Using `whenPressed`, `whileHeld`, and `whenReleased` this is possible and easy to understand what each button does.
For buttons, there can be a `whenPressed` that will do something when the button is pressed down once, and continue doing that on its own until the task is completed. However, for a trigger or joystick, depending on how hard or how far you push in the button, it will change what the robot does, meaning a `whileHeld` can be used using axes With the joysticks, a value is given based on how far the stick is being pushed on the X or Y axis, and if it is a certain value, it will go. `whenReleased` is used to end the task the button is performing, and then possibly performing a task afterwards.

## Autnomous Files

ALL MAIN AUTNOMOUS FILES USE GAMEDATA (those that will have many commands). Game Data is layout on the field of which side of the two Alliance switches and the scale is which color, either red or blue. Different combinations of 3 characters using "L" and "R" (Left and Right) are given. For example, if the combination is "LRL", when looking down at the field from the Driver Station, the side of the switches and scale that will be your color are left, right, then left. If your alliance is blue, the left side of *your Switch* will be blue, the right side of the *Scale* will be blue, and the left side of the *opposite Switch* will be blue (LRL). Game Data is received upon the match's start, after the "3...2...1...Go!" This is represented through  "String gameData = DriverStation.getInstance().getGameSpecificMessage();", where the Game Data is received. Most autonomous files will have Game Data input, and require Game Data. All Main Auto files are command groups that run a list of commands. ALL MAIN AUTO FILES will have a "Catch/Try" for when the Game Data is empty, and tell the robot just to drive forward to the baseline.

### `AutoDrive.java`

#### Imported Files
From Wpilib.command
- Commands
From Wpilibj.smartdashboard
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
Imports `Wpilibj.DriverStation`

#### Methods/Uses

In `AutoCenterSwitch.java`, the robot starts against the wall, to the right of the Exchange area on the field. This way, it is centered. `AutoCenterSwitch.java` uses "If/Else" statements to determine what the robot is going to do based on the Game Data that is received. `AutocenterSwitch.java` has a series of "AddSequentials" that either tell the robot to move forward a certain distance, or tell the robot to turn a certain angle left or right. If the Game Data returns the side of the switch that is our alliance color is the left, the robot will go through the commands that get it in front of the switch, then place the block down using `ArmDeploy`. Else, if the Game Data returns the side of the switch that is our alliance color is the right, because the robot is in the middle, the commands from the AddSequentials will just be reversed, and the Power Cube will be placed on the right side.

### `AutoLeftSwitch.java`

#### Imported Files
Imports `Wpilib.DriverStation`

#### Methods/Uses

In `AutoLeftSwitch.java`, the robot starts against the wall, to the left of the Exchange area on the field. This way it is on the left side of the field. `AutoLeftSwitch.java` uses "If/Else" statements to determine what the robot is going to do based on the Game Dates that is received. `AutoLeftSwitch.java` has a series of "AddSequentials" that either tell the robot to move forward a certain distance, or tell the robot to turn a certain angle left or right. If the Game Data returns the side of the switch that is our alliance color is the left, the robot will go through its commands that get it in front of the left side of the switch, then place the block down using `ArmDeploy`. Else, if the Game Data returns the side of the switch that is our alliance color is the right, the robot will not go for the right side of the switch. Instead, it will just drive forward to the baseline, which is all that is needed for all of the alliance members to get a ranking point, and just stop. This is to make sure the robot does not interfere with other alliance members, as they will most likely be starting on that side if we are not.

### `AutoRightSwitch.java`

#### Imported Files
Imports `Wpilibj.DriverStation`

#### Methods/Uses

In `AutoRightSwitch`, the robot starts against the wall, and will ideally be aligned directly in front of the right side of the switch. This way it will just be a straight drive up to the switch. `AutoRightSwitch` uses "If/Else" statements to determine what the robot is going to do based on the Game Data that is received. `AutoRightSwitch` has only two "AddSequentials" that will be used. No matter what the Game Data is, the robot will go all the way up to the switch. Because it is all the way up to the switch, it is past the baseline too, so it can be used whether the robot will be putting a Power Cube on the switch or not. If the Game Data returns that the side of the switch that is our alliance color is the right, the robot will stay in position, along with putting the Power Cube on the switch through `ArmDeploy`. Else, if the Game Data returns that the side of the switch that is our alliance color is the left, the robot will only stay in place. This is to make sure the robot does not interfere with other alliance members, as they will most likely be starting on that side if we are not.

### `AutoTurnAngle.java`

#### Imported Files
From Wpilib.command
- Command
- Scheduler
From Wpilibj.smartdashboard
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
From Wpilib.command
- Command
From Wpilibj.smartdashboard
- SmartDashboard

From team5401.robot
- Robot
- RobotMap

#### Methods/Uses
Creates a variable for the distance that the robot will drive. Requires `Robot.drivebase`.

#### initialize()
`initialize` is called before the robot runs for the first time. It resets the encoders on the drive base to refresh the values and get more accurate readings. 
