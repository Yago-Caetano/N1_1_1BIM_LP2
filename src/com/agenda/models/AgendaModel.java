package com.agenda.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AgendaModel {

    public final static int CONSULTA_MESMA_DATA = 0;
    public final static int CONSULTA_DATA_EXATA = 1;
    public final static int CONSULTA_ANTES_DA_DATA = 2;
    public final static int CONSULTA_DEPOIS_DA_DATA = 3;


    List<CompromissoModel> Compromissos;

    public AgendaModel() {
        Compromissos = new ArrayList<>(Compromissos);
    }

    public boolean cadastrarCompromisso(CompromissoModel compromisso)
    {
        if(horarioLivre(compromisso.getData()))
        {
            Compromissos.add(compromisso);
            return true;
        }
        return false;
    }

    public List<CompromissoModel> recuperarCompromisos(Calendar DataReferencia,int Modo)
    {
        List<CompromissoModel> ListaDeRetorno = new ArrayList<>(Compromissos);

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
               Calendar limiteSuperior = DataReferencia;
               limiteSuperior.set(limiteSuperior.get(Calendar.YEAR),limiteSuperior.get(Calendar.MONTH),limiteSuperior.get(Calendar.DAY_OF_MONTH),limiteSuperior.get(Calendar.HOUR_OF_DAY),23,59);

                Calendar limiteInferior = DataReferencia;
                limiteInferior.set(limiteInferior.get(Calendar.YEAR),limiteInferior.get(Calendar.MONTH),limiteInferior.get(Calendar.DAY_OF_MONTH),limiteInferior.get(Calendar.HOUR_OF_DAY),0,0);

                if(c.getData().before(limiteSuperior) && c.getData().after(limiteInferior))
                {
                    ListaDeRetorno.add(c);
                }
            }

        }

        return ListaDeRetorno;
    }

    public boolean removerCompromisso(int idReferencia)
    {
        for(int counter = 0; counter < Compromissos.size(); counter++)
        {
            if(Compromissos.get(counter).getId() == idReferencia)
            {
                Compromissos.remove(counter);
                return true;
            }
        }
        return false;
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
}
