package com.agenda.views;

import com.agenda.models.CompromissoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TelaCadastroView extends  PadraoView{

    public TelaCadastroView()
    {
        setId(1);
    }

    private byte menu;



    private CompromissoModel Compromisso ;

    private byte CADASTRAR_TITULO=0;
    private byte CADASTRAR_DATA=1;

    private byte CADASTRAR_DESCRICAO=3;
    private byte CADASTRAR_DATA_AVISO=4;

    @Override
    public void mostraTela(TelaCallback callback) {
        Callback = callback;
        setCabecalho("CADASTRAR COMPROMISSO");
        Compromisso= new CompromissoModel();
        montaCabecalho();
        exibeNoConsole("Pressione '0' para cancelar");
        exibeNoConsole("Digite o título e pressione 'ENTER'");
        menu=CADASTRAR_TITULO;
    }



    @Override
    public void manipulaInput(String Input)  {

        if (Input.equals("0"))
            Callback.trocarTela(0);
        else
        {
            if (menu==CADASTRAR_TITULO)
                GetTitulo(Input);

            else if (menu==CADASTRAR_DATA)
                GetDataTime(Input);

            else if (menu==CADASTRAR_DESCRICAO)
                GetDescricao(Input);

            else if (menu==CADASTRAR_DATA_AVISO)
                GetDataTimeAviso(Input);


        }

    }
    void GetTitulo(String Input)
    {
        Compromisso.setTitulo(Input);
        exibeNoConsole("Digite a data no formato dd/MM/yyyy HH:mm");
        menu=CADASTRAR_DATA;
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
            menu=CADASTRAR_DESCRICAO;
            exibeNoConsole("Digite a descrição do compromisso:");
        }
        catch ( ParseException ex)
        {
            exibeNoConsole("Data inválida!");
            exibeNoConsole("Digite a data no formato dd/MM/yyyy HH:mm");
            menu=CADASTRAR_DATA;
        }
    }
    void GetDescricao(String Input)
    {
        Compromisso.setDescricao(Input);
        exibeNoConsole("Digite a data de aviso de aviso no formato dd/MM/yyyy hh:mm");
        menu=CADASTRAR_DATA_AVISO;
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
            Compromisso.setId(Callback.SolicitarID());
            Compromisso.setDataAviso(cal);
            ValidaDataAviso();
        }
        catch ( ParseException ex)
        {
            exibeNoConsole("Data inválida!");
            exibeNoConsole("Digite a data de aviso de aviso no formato dd/MM/yyyy");
            menu=CADASTRAR_DATA_AVISO;
        }
    }
    void ValidaDataAviso()
    {
        exibeNoConsole(String.valueOf(Compromisso.getData().compareTo(Compromisso.getDataAviso())));
        if (Compromisso.getData().compareTo(Compromisso.getDataAviso())==1)
            Finalizacao();
        else
        {
            exibeNoConsole("a data de aviso deve ser menor que a data do compromisso");
            exibeNoConsole("Digite a data de aviso de aviso no formato dd/MM/yyyy");
            menu=CADASTRAR_DATA_AVISO;
        }
    }

    void Finalizacao()
    {
        exibeNoConsole(Compromisso.PrintCompromisso());
        Callback.InsertCompromisso(Compromisso);
    }
}
