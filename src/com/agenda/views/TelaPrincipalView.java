package com.agenda.views;

public class TelaPrincipalView extends  PadraoView{

    @Override
    public void mostraTela() {
        setCabecalho("Projeto Agenda");
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
                break;
            default:
                exibeNoConsole("Comando Invalido");
                break;
        }
    }

    private void exibeNoConsole(String texto)
    {
        System.out.println(texto);
    }

}
