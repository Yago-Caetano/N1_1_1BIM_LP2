package com.agenda.models;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Boolean.parseBoolean;

public class AgendaModel {

    public final static int CONSULTA_MESMA_DATA = 0;
    public final static int CONSULTA_DATA_EXATA = 1;
    public final static int CONSULTA_ANTES_DA_DATA = 2;
    public final static int CONSULTA_DEPOIS_DA_DATA = 3;

    private String Caminho="Agenda_Data.txt";
    CompromissoModel compAux;

    private int MaiorID;

    List<CompromissoModel> Compromissos;

    public AgendaModel() {
        MaiorID=-1;
        Compromissos = new ArrayList<>();
        FetchData();
    }

    private void write(final String s) {

        try(FileWriter fw = new FileWriter(Caminho, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(s);
            //more code
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo de dados");
        }
    }
    private void FetchData()
    {
        int qt=0;
        System.out.println("Analisando memória.....");
        File file = new File(Caminho);
        Scanner input = null;
        try
        {
            input = new Scanner(file);
        } catch (FileNotFoundException e)
        {
            System.out.println("Arquivo txt não encontrado");
            return;
        }
        while (input.hasNextLine())
        {
            String[] parts = input.nextLine().split("-");
            if (parts.length==6)
            {
                if (ConvertStringToCompromisso(parts))
                {
                    if(compAux.getId()>MaiorID)
                        MaiorID=compAux.getId();

                    compAux.PosLista=qt;
                    Compromissos.add(compAux);
                    qt++;
                    //System.out.println(Compromissos.get(Compromissos.size()-1).PrintCompromisso());
                }

            }
        }
        System.out.println(String.valueOf(qt) + " compromissos(s) recuperados");

    }
    public int SolicitarID()
    {
        return MaiorID+1;
    }
    private boolean ConvertStringToCompromisso (String[] data)
    {
        compAux= new CompromissoModel();
        if (! GetIdFromStringCompromisso(data[0]))
            return false;
        if (! GetTituloFromStringCompromisso(data[1]))
            return false;
        if (! GetDescricaoFromStringCompromisso(data[2]))
            return false;
        if (! GetDataFromStringCompromisso(data[3]))
            return false;
        if (! GetDataAvisoFromStringCompromisso(data[4]))
            return false;
        if (! GetAlarmeFromStringCompromisso(data[5]))
            return false;



        return true;
       
    }
    private boolean GetIdFromStringCompromisso(String s)
    {
        try
        {
            int number = Integer.parseInt(s);
            compAux.setId(number);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }
    private boolean GetTituloFromStringCompromisso(String s)
    {
        compAux.setTitulo(s);
        return true;
    }
    private boolean GetDescricaoFromStringCompromisso(String s)
    {
        compAux.setDescricao(s);
        return true;
    }
    private boolean GetDataFromStringCompromisso(String s)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date = null;
        try
        {
            date = sdfDate.parse(s);
        }
        catch (ParseException e)
        {
            return false;
        }
        cal.setTime(date);
        compAux.setData(cal);
        return true;
    }
    private boolean GetDataAvisoFromStringCompromisso(String s)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date = null;
        try
        {
            date = sdfDate.parse(s);
        }
        catch (ParseException e)
        {
            return false;
        }
        cal.setTime(date);
        compAux.setDataAviso(cal);
        return true;
    }
    private boolean GetAlarmeFromStringCompromisso(String s)
    {
        compAux.AlarmeTocou=parseBoolean(s);
        return true;
    }

    public CompromissoModel GetCompromissoById(int id)
    {
        for(int counter = 0; counter < Compromissos.size(); counter++)
        {
            if(Compromissos.get(counter).getId() == id)
            {
                return Compromissos.get(counter);
            }
        }
        return null;
    }

    public boolean cadastrarCompromisso(CompromissoModel compromisso)
    {
        if(horarioLivre(compromisso.getData()))
        {
            compromisso.PosLista=Compromissos.size();
            Compromissos.add(compromisso);
            if(compromisso.getId()>MaiorID)
                MaiorID=compromisso.getId();
            write(compromisso.SerializedObject());
            return true;
        }
        return false;
    }

    public List<CompromissoModel> recuperarCompromisos(Calendar DataReferencia,int Modo)
    {
        List<CompromissoModel> ListaDeRetorno = new ArrayList<CompromissoModel>();

        for(CompromissoModel c : Compromissos)
        {
            if(Modo == CONSULTA_ANTES_DA_DATA)
                if(c.getData().before(DataReferencia))
                    ListaDeRetorno.add(c);

            if(Modo == CONSULTA_DEPOIS_DA_DATA)
                if(c.getData().after(DataReferencia))
                    ListaDeRetorno.add(c);

            if(Modo == CONSULTA_DATA_EXATA)
                if(c.getData().equals(DataReferencia))
                    ListaDeRetorno.add(c);

            if(Modo == CONSULTA_MESMA_DATA)
            {

                if((DataReferencia.get(Calendar.DAY_OF_MONTH) == c.getData().get(Calendar.DAY_OF_MONTH)) && (DataReferencia.get(Calendar.MONTH) == c.getData().get(Calendar.MONTH)) && (DataReferencia.get(Calendar.DAY_OF_MONTH) == c.getData().get(Calendar.DAY_OF_MONTH)))
                {
                    ListaDeRetorno.add(c);
                }
            }

        }

        return ListaDeRetorno;
    }
    public boolean AtualizarFile()
    {
        String data="";
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(Caminho);
        } catch (FileNotFoundException e) {
            return false;
        }
        writer.print("");
        writer.close();

        for(int counter = 0; counter < Compromissos.size(); counter++)
            data=data+ Compromissos.get(counter).SerializedObject()+"\n";

        write(data);

        return true;
    }

    public boolean removerCompromisso(CompromissoModel comp)
    {
        Compromissos.remove(comp.PosLista);
        return AtualizarFile();
    }
    public boolean EditarCompromisso(CompromissoModel comp)
    {
        Compromissos.set(comp.PosLista,comp);
        return AtualizarFile();
    }

    private boolean horarioLivre(Calendar diaReferencia)
    {
        for(CompromissoModel c : Compromissos)
        {
            if(c.getData().equals(diaReferencia))
            {
                return false;
            }
        }
        return true;
    }

    /**
     *  Função utilizada para verificar se há alarmes no horário indicado
     * @return id do compromisso com alarme atingido, caso não haja alarmes o valor -1 é retornado
     */
    public int verificaExistenciaAlarme(Calendar dataAtual)
    {
        for(int counter = 0; counter < Compromissos.size();counter++)
        {
            if(Compromissos.get(counter).executaAlarme(dataAtual))
            {
                Compromissos.get(counter).setAlarmeExecutado();
                return counter;
            }
        }
        return -1;
    }
}
