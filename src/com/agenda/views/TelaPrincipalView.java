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
                exibeNoConsole("Digitou 2");
                Callback.trocarTela(2);
                break;
            default:
                exibeNoConsole(Input);
                break;
        }

    }

    private void ExibeMenu()
    {
        exibeNoConsole("Digite 1 - > Cadastrar Compromisso");
    }


}
