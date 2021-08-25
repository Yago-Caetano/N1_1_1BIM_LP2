package com.agenda.views;

import com.agenda.models.AgendaModel;
import com.agenda.models.CompromissoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TelaAgendaView extends PadraoView{


    public final static int CONSULTA_MESMA_DATA = 0;
    public final static int CONSULTA_ANTES_DA_DATA = 2;
    public final static int CONSULTA_DEPOIS_DA_DATA = 3;

    final static byte ESCOLHER_OPCAO=1;
    final static byte PEGAR_DATA_REF=2;

    public int opcao_selecionada=-1;
    public Calendar DataReferencia;

    public byte menu=0;

    boolean DataFlag;

    public TelaAgendaView()
    {
        setId(5);
    }

    AgendaModel _agenda;
    @Override
    public void mostraTela(TelaCallback callback) {

        Callback = callback;
        _agenda=Callback.GetAgenda();
        menu=ESCOLHER_OPCAO;
        setCabecalho("VISUALIZAR AGENDA");
        montaCabecalho();
        ExibeMenu();

    }

    @Override
    public void manipulaInput(String Input) {

        switch (menu)
        {
            case ESCOLHER_OPCAO:
                SelecionarOpcoes(Input);
                break;
            case PEGAR_DATA_REF:
                GetDataReferencia(Input);
                break;
        }

    }
    private void GetDataReferencia (String Input)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try
        {
            date = sdfDate.parse(Input);
            cal.setTime(date);
            DataReferencia=cal;
            printLista(_agenda.recuperarCompromisos(DataReferencia,opcao_selecionada));
            ExibeMenu();
        }
        catch (ParseException e)
        {
            exibeNoConsole("Data Inválida!");
            SetToDataRef();
        }


    }
    private void printLista (List<CompromissoModel> lista)
    {
        for(CompromissoModel c : lista)
        {
            exibeNoConsole(c.PrintCompromisso());
        }
    }
    private void SelecionarOpcoes (String Input)
    {
        switch (Input)
        {
            case "1":
                opcao_selecionada=CONSULTA_MESMA_DATA;
                SetToDataRef();
                break;
            case "2":
                opcao_selecionada=CONSULTA_ANTES_DA_DATA;
                SetToDataRef();
                break;
            case "3":
                opcao_selecionada=CONSULTA_DEPOIS_DA_DATA;
                SetToDataRef();
                break;
            case "4":
                Callback.trocarTela(0);
                break;
        }
    }
    private void SetToDataRef()
    {
        menu=PEGAR_DATA_REF;
        exibeNoConsole("Qual data ? digite a data no formato dd/MM/yyyy");
    }
    private void ExibeMenu()
    {
        opcao_selecionada=-1;
        menu=ESCOLHER_OPCAO;
        exibeNoConsole("Digite 1 - > Visualizar compromissos de uma data");
        exibeNoConsole("Digite 2 - > Visualizar compromissos antes de uma data");
        exibeNoConsole("Digite 3 - > Visualizar compromissos depois de uma data");
        exibeNoConsole("Digite 4 - > Voltar");
    }
}