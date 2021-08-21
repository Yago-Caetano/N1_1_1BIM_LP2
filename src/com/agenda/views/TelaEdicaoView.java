package com.agenda.views;

public class TelaEdicaoView extends PadraoView{

    public TelaEdicaoView()
    {
        setId(2);
    }

    @Override
    public void mostraTela(TelaCallback callback) {

        Callback = callback;

        setCabecalho("Editar Compromisso");
        montaCabecalho();
    }

    @Override
    public void manipulaInput(String Input) {
        switch (Input)
        {
            case "1":
                exibeNoConsole("Digitou 1");
                break;
            case "2":
                exibeNoConsole("Digitou 2");
                Callback.trocarTela(1);
                break;
            default:
                exibeNoConsole("Comando Invalido");
                break;
        }

    }
}
