/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.ToHighSpeed;
import frc.robot.commands.ToLowSpeed;

/**
 * Add your docs here.
 */
public class OI {
    public Joystick movestick = new Joystick(Constants.movestick);

    public JoystickButton faster = new JoystickButton(movestick, 1);
    public JoystickButton slower = new JoystickButton(movestick, 2);

    public OI(){
        faster.whenPressed(new ToHighSpeed());//drive speed to fast
        slower.whenPressed(new ToLowSpeed());//drive speed to slow
      }
}
