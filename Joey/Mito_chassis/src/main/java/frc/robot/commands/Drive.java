/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  double y_value;
  double z_value;

  public Drive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.chassis);
    addRequirements(RobotContainer.judge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    z_value = RobotContainer.judge.accuracy(RobotContainer.oi.movestick.getZ());
    y_value = RobotContainer.judge.accuracy(RobotContainer.oi.movestick.getY());
    SmartDashboard.putNumber("y_value", y_value);
    SmartDashboard.putNumber("z_value", z_value);
    RobotContainer.chassis.drive(z_value, y_value);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
