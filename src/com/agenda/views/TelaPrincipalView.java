package com.agenda.views;


import com.agenda.models.CompromissoModel;

public class TelaPrincipalView extends  PadraoView{

    private byte menu;


    private byte  MOSTRAR_OPCOES=0;
    private byte  CADASTRAR_COMPROMISSO=1;

    private CompromissoModel Compromisso ;
    private byte step_cadastro;
    private byte CADASTRAR_TITULO=0;



    public TelaPrincipalView()
    {
        setId(0);

    }

    @Override
    public void mostraTela(TelaCallback callback) {
        Callback = callback;
        Compromisso= new CompromissoModel();
        setCabecalho("PROJETO AGENDA");
        menu=MOSTRAR_OPCOES;
        montaCabecalho();
        ExibeMenu();


    }

    @Override
    public void manipulaInput(String Input) {

        switch(Input)
        {
            case "1":
                Callback.trocarTela(1);
                break;
            case "2":
                Callback.trocarTela(5);
                break;
            case "3":
                Callback.trocarTela(2);
                break;
            case "4":
                Callback.trocarTela(6);
                break;
            default:
                exibeNoConsole(Input);
                break;
        }

    }

    private void ExibeMenu()
    {
        exibeNoConsole("Digite 1 - > Cadastrar Compromisso");
        exibeNoConsole("Digite 2 - > Visualizar Agenda");
        exibeNoConsole("Digite 3 - > Editar Compromissos");
        exibeNoConsole("Digite 4 - > Deletar Compromissos");
    }


}
