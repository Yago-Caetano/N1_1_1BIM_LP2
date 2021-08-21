package com.agenda.views;

public abstract class PadraoView {

    private String Cabecalho;

    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCabecalho(String cabecalho) {
        Cabecalho = cabecalho;
    }

    public void montaCabecalho()
    {
        System.out.println(String.format("######################## %s ###########################",Cabecalho));
    }

    public abstract void mostraTela();

    public abstract void manipulaInput(String Input);
}
