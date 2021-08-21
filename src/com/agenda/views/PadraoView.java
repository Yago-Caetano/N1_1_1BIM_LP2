package com.agenda.views;

public abstract class PadraoView {

    private String Cabecalho;
    private int Id;
    protected TelaCallback Callback;

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

    protected void exibeNoConsole(String texto)
    {
        System.out.println(texto);
    }

    public abstract void mostraTela(TelaCallback callback);

    public abstract void manipulaInput(String Input);
}
