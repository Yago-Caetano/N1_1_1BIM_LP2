package com.agenda.views;

import com.agenda.models.AgendaModel;
import com.agenda.models.CompromissoModel;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TelaEdicaoView extends PadraoView{

    private byte menu;
    private int Del_ID;
    AgendaModel _agenda;

    final static byte GET_ID = 0;
    final static byte OPCOES_EDICAO = 1;
    final static byte EDITAR_TITULO = 2;
    final static byte EDITAR_DESCRICAO = 3;
    final static byte EDITAR_DATA = 4;
    final static byte EDITAR_DATAAVISO = 5;
    final static byte CONFIRMA_EDICAO = 6;

    private CompromissoModel Compromisso;


    public TelaEdicaoView()
    {
        setId(2);
    }

    @Override
    public void mostraTela(TelaCallback callback) {
        Callback = callback;
        _agenda=Callback.GetAgenda();
        Compromisso = new CompromissoModel();
        setCabecalho("EDITAR COMPROMISSOS");

        montaCabecalho();
        ExibeMenu();


    }

    @Override
    public void manipulaInput(String Input) {

        if(Input.equals("exit"))
        {
            Callback.trocarTela(0);
            return;
        }
        switch (menu)
        {
            case GET_ID:
                GetID(Input);
                break;
            case OPCOES_EDICAO:
                InputOpcoesEdicao(Input);
                break;
            case EDITAR_TITULO:
                GetTitulo(Input);
                break;
            case EDITAR_DESCRICAO:
                GetDescricao(Input);
                break;
            case EDITAR_DATA:
                GetDataTime(Input);
                break;
            case EDITAR_DATAAVISO:
                GetDataTimeAviso(Input);
                break;
            case CONFIRMA_EDICAO:
                Confirmar_Edicao(Input);
                break;
        }

    }
    void GetTitulo(String Input)
    {
        Compromisso.setTitulo(Input);
        ExibeMenuOpcoesEdicao();
    }
    void GetDataTime(String Input)
    {
        String data = Input;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try
        {
            exibeNoConsole(data);
            Date date = sdfDate.parse(data);
            cal.setTime(date);
            Compromisso.setData(cal);
            if(!ValidaDataAviso(Compromisso.getDataAviso()))
            {
                exibeNoConsole("a data de aviso deve ser menor que a data do compromisso");
                exibeNoConsole("a data de aviso será igualada a data nominal!");
                Compromisso.setDataAviso(cal);
            }
            ExibeMenuOpcoesEdicao();
        }
        catch ( ParseException ex)
        {
            exibeNoConsole("Data inválida!");
            ExibeMenuOpcoesEdicao();
        }
    }
    void GetDescricao(String Input)
    {
        Compromisso.setDescricao(Input);
        ExibeMenuOpcoesEdicao();
    }
    void GetDataTimeAviso(String Input)
    {
        String data = Input;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try
        {
            exibeNoConsole(data);
            Date date = sdfDate.parse(data);
            cal.setTime(date);
            if(ValidaDataAviso(cal))
                Compromisso.setDataAviso(cal);
            else
                exibeNoConsole("a data de aviso deve ser menor que a data do compromisso");


        }
        catch ( ParseException ex)
        {
            exibeNoConsole("Data inválida!");
            ExibeMenuOpcoesEdicao();
        }
        ExibeMenuOpcoesEdicao();
    }
    boolean ValidaDataAviso(Calendar cal)
    {
        //exibeNoConsole(String.valueOf(Compromisso.getData().compareTo(cal)));
        if (Compromisso.getData().compareTo(cal)==1)
            return true;
        else
            return false;
    }

    private void InputOpcoesEdicao(String input)
    {
        switch(input)
        {
            case "1":
                exibeNoConsole("Digite o novo título:");
                menu=EDITAR_TITULO;
                break;
            case "2":
                exibeNoConsole("Digite a nova descrição:");
                menu=EDITAR_DESCRICAO;
                break;
            case "3":
                exibeNoConsole("Digite a nova data no formato: dd/MM/yyyy HH:mm");
                menu=EDITAR_DATA;
                break;
            case "4":
                exibeNoConsole("Digite a nova data de aviso no formato: dd/MM/yyyy HH:mm");
                menu=EDITAR_DATAAVISO;
                break;
            case "save":
                exibeNoConsole("Deseja salvar essa edicao? digite 's'");
                menu=CONFIRMA_EDICAO;

                break;
        }

    }
    private void Confirmar_Edicao(String input)
    {
        switch(input)
        {
            case "s":
                if(_agenda.EditarCompromisso(Compromisso))
                {
                    exibeNoConsole("Editado!");
                    Callback.trocarTela(0);
                }
                else
                    exibeNoConsole("Erro ao editar!");
                break;
            default:
                ExibeMenuOpcoesEdicao();
                break;
        }

    }
    private void GetID(String input)
    {
        if (input.equals("exit"))
        {
            Callback.trocarTela(0);
            return;
        }
        try
        {
            Del_ID = Integer.parseInt(input);
            Compromisso=_agenda.GetCompromissoById(Del_ID);
            if(Compromisso==null)
                MsgDesistir_Continuar();
            else
            {
                exibeNoConsole("Compromisso encontrado..");
                exibeNoConsole(Compromisso.PrintCompromisso());
                ExibeMenuOpcoesEdicao();
            }

        }
        catch (NumberFormatException ex){
            MsgDesistir_Continuar();

        }

    }

    private void ExibeMenuOpcoesEdicao() {
        menu= OPCOES_EDICAO;
        exibeNoConsole("Digite 1 -> Editar Título");
        exibeNoConsole("Digite 2 -> Editar Descrição");
        exibeNoConsole("Digite 3 -> Editar Data");
        exibeNoConsole("Digite 4 -> Editar Data Aviso");
        exibeNoConsole("Digite 'save' para salvar ou 'exit' para sair deste menu");
    }
    private void ExibeMenu() {
        menu = GET_ID;
        exibeNoConsole("Digite o ID do compromisso que deseja editar");
        exibeNoConsole("exit para sair deste menu");
    }
    private void MsgDesistir_Continuar()
    {
        exibeNoConsole("ID digitado não é valido!");
        ExibeMenu();
    }
}
