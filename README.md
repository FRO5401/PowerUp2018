# PowerUp2018

## OI.java

### Imports
From wpilibj:
  - `.buttons.Button`
  - `.Joystick;`
  - `.buttons.JoystickButton;`

From team5401:
  - `.robot.commands.*;`

### Methods
Constants for arguments are defined in this file above the methods.

`xboxAxis(input, userMap)`

  This method determines raw directional values from input to the left and right thumbsticks on an xbox controller. It takes arguments `input` and `userMap`.
    - `input` refers to the stick and axis, e.g. `leftStickX` or `leftTrigger`
    - `userMap` refers to the type of user setup, e.g. `xboxController_Driver`

`xboxButton(button, userMap)`

  This method determines button values from input on an xbox controller. It takes arguments `button` and `userMap`.
    - `button` refers to the button, e.g. `buttonB` or `buttonL3`
    - `userMap` refers to the type of user setup, e.g. `xboxController_Operator`

`xboxDPad(userMap)`

  This method determines input from the D-Pad on an xbox controller. It takes argument `userMap`, which refers to the type of user setup, e.g. `xboxController_Driver`
