package com.agenda.views;

import com.agenda.models.AgendaModel;
import com.agenda.models.CompromissoModel;

public interface TelaCallback {

     void trocarTela(int idTela);
     void InsertCompromisso (CompromissoModel comp);
     AgendaModel GetAgenda();
     int SolicitarID();

}
