/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;


/**
 *
 * @author Developer
 */
public class ShooterWheel 
{
    private Joystick m_joystick;
    private Jaguar m_motor;
    RateEncoder encoder;
    private double MAX_VOLTAGE = 0.65;
    
    public ShooterWheel(Joystick joystick)
    {
        m_joystick = joystick;
        m_motor = new Jaguar(Config.ShooterSlot);
        encoder = new RateEncoder (Config.encoderA,Config.encoderB,false,CounterBase.EncodingType.k1X);
        encoder.start();
    }
    
    public void ManualControl()
    {
        m_motor.set(-((m_joystick.getZ())-1)/2);
        System.out.println(-((m_joystick.getZ())-1)/2);
        m_motor.Feed();
    }
    
    public void set(double voltage)
    {
        if (voltage > MAX_VOLTAGE)
        {
            voltage = MAX_VOLTAGE;
        }
        m_motor.set(voltage);
        m_motor.Feed();
    }
    
    public void setSpeed(double speed)
    {
        if (speed > MAX_VOLTAGE)
        {
            speed = MAX_VOLTAGE;
        }
        m_motor.set(speed);
        m_motor.Feed();
    }
    
    public double getRate()
    {
        return encoder.getRate();
    }
    
    private int count = 0;
    public void PrintValues()    
    {
        if (count == 0)
        {
            System.out.println("PID: " + encoder.pidGet() + " voltage: " + m_motor.get() + " rate: " + this.getRate() + " distance:" + RangeFinder.getDist()); 
        }
        
        count++;
        if (count > 25)
        {
            count = 0;
        }
    }
}
