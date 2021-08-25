package com.agenda.views;

import com.agenda.models.AgendaModel;
import com.agenda.models.CompromissoModel;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class TelaDeletarView extends  PadraoView {

    private byte menu;
    private int Del_ID;
    AgendaModel _agenda;
    CompromissoModel comp;

    final static byte GET_ID = 0;
    final static byte CONFIRMA_DELETE = 1;

    private CompromissoModel Compromisso;
    private byte step_cadastro;
    private byte CADASTRAR_TITULO = 0;


    public TelaDeletarView() {
        setId(6);

    }

    @Override
    public void mostraTela(TelaCallback callback) {
        Callback = callback;
        _agenda=Callback.GetAgenda();
        Compromisso = new CompromissoModel();
        setCabecalho("DELETAR COMPROMISSOS");

        montaCabecalho();
        ExibeMenu();


    }

    @Override
    public void manipulaInput(String Input) {

        switch (menu)
        {
            case GET_ID:
                GetID(Input);
                break;
            case CONFIRMA_DELETE:
                Confirmar_Delete(Input);
                break;
        }

    }
    private void DesistirContinuar(String input)
    {
        switch(input)
        {
            case "s":
                _agenda.removerCompromisso(comp);
            default:
                ExibeMenu();
                break;
        }

    }
    private void Confirmar_Delete(String input)
    {
        switch(input)
        {
            case "s":
                if (!_agenda.removerCompromisso(comp))
                    exibeNoConsole("Erro ao excluir");
                else
                    exibeNoConsole("Excluido com sucesso");
                break;
        }
        ExibeMenu();

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
            comp=_agenda.GetCompromissoById(Del_ID);
            if(comp==null)
                MsgDesistir_Continuar();
            else
            {
                exibeNoConsole("Compromisso encontrado..");
                exibeNoConsole(comp.PrintCompromisso());
                exibeNoConsole("Digite 's' para deletar este compromisso");
                menu=CONFIRMA_DELETE;
            }

        }
        catch (NumberFormatException ex){
            MsgDesistir_Continuar();

        }

    }

    private void ExibeMenu() {
        menu = GET_ID;
        exibeNoConsole("Digite o ID do compromisso que deseja deletar");
        exibeNoConsole("exit para sair deste menu");
    }
    private void MsgDesistir_Continuar()
    {
        exibeNoConsole("ID digitado não é valido!");
        ExibeMenu();
    }
}