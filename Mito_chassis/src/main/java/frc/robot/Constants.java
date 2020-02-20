/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Joystick port
    public static final int movestick = 0;

    //Motors
    public static final int leftMastermotor = 1;
    public static final int leftSlavemotor = 2;
    public static final int rightMastermotor = 3;
    public static final int rightSlavemotor = 4;
 
    //constants
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static final int kSlotIdx = 0;

    //switcher
    public static final int driveSwitcher = 1;

    //Cum
    public static final int cum_rpm = 600/4096;

    //chassis
    public static final int highSpeedConstant = 4000 /600*4096;
    public static final int lowSpeedConstant = 2000 /600*4096;

    public static void Talon_init(TalonFX _talon, int _peakOutput, boolean isInverted){
        //encoder
        _talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kPIDLoopIdx, kTimeoutMs);
        _talon.configFactoryDefault();
        _talon.setSensorPhase(true);
    
        _talon.configNominalOutputForward(0, kTimeoutMs);
        _talon.configNominalOutputReverse(0, kTimeoutMs);
        _talon.configPeakOutputForward(1, kTimeoutMs);
        _talon.configPeakOutputReverse(-1, kTimeoutMs);
       
        _talon.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
        
        _talon.configAllowableClosedloopError(1, kPIDLoopIdx, kTimeoutMs);
        
        _talon.configClosedLoopPeakOutput(kSlotIdx, _peakOutput, kTimeoutMs);

        _talon.setInverted(isInverted);
    }
    
    public static void setFalconPID(TalonFX _talon, double kF, double kP, double kI, double kD){
        _talon.config_kF(kSlotIdx, kF);
        _talon.config_kP(kSlotIdx, kP);
        _talon.config_kI(kSlotIdx, kI);
        _talon.config_kD(kSlotIdx, kD);
    }
}
