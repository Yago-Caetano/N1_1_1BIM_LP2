package com.agenda.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AlarmController {

    private Clip Clip;
    private boolean AlarmeExecutando = false;

    public AlarmController()
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/resources/alarm.wav"));
            Clip = AudioSystem.getClip();
            Clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startAlarm(String Titulo)
    {
        if(AlarmeExecutando)
            return;

        try {
            clearConsole();
            System.out.println("########################################");
            System.out.printf("Lembrete da tarefa: %s \r\n",Titulo);
            System.out.println("########################################");
            System.out.printf("\r\n\r\nPressione qualquer teclar para desativar o alarme\r\n");
            Clip.start();
            Clip.loop(Clip.LOOP_CONTINUOUSLY); //Para repetir o som.
            AlarmeExecutando = true;
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }

    public void stopAlarm()
    {
        Clip.stop();
        AlarmeExecutando = false;

    }

    public boolean alarmeExecutando()
    {
        return AlarmeExecutando;
    }
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
