package com.agenda.views;

public class TelaPrincipalView extends  PadraoView{

    public TelaPrincipalView()
    {
        setId(1);

    }

    @Override
    public void mostraTela(TelaCallback callback) {
        Callback = callback;

        setCabecalho("PROJETO AGENDA");
        montaCabecalho();

    }

    @Override
    public void manipulaInput(String Input) {
        switch(Input)
        {
            case "1":
                exibeNoConsole("Digitou 1");
                break;
            case "2":
                exibeNoConsole("Digitou 2");
                Callback.trocarTela(2);
                break;
            default:
                exibeNoConsole("Comando Invalido");
                break;
        }
    }


}
