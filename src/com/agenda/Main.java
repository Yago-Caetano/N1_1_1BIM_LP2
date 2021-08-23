package com.agenda;

import com.agenda.models.AgendaModel;
import com.agenda.views.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final int TELA_INICIAL = 0;
    public static final int TELA_CADASTRO_COMPROMISSO = 1;
    public static final int TELA_EDICAO_COMPROMISSO = 2;
    public static final int TELA_SELECIONAR_COMPROMISSO = 3;
    public static final int TELA_ALARME = 4;

    private static AgendaModel Agenda;

    private static List<PadraoView> Telas;
    private static int IndiceDeTelaSelecionado;


    /*
        Callback das telas
     */
    static TelaCallback cbTela = new TelaCallback() {
        @Override
        public void trocarTela(int idTela) {
            for(int counter = 0; counter < Telas.size(); counter++)
            {
                if(Telas.get(counter).getId() == idTela)
                {
                        IndiceDeTelaSelecionado = counter;
                        mostraTelaSelecionada();
                }
            }
        }
    };


    public static void gerenciarTelas()
    {
        IndiceDeTelaSelecionado = 0;

        Telas = new ArrayList<PadraoView>();

        Telas.add(new TelaPrincipalView());
        Telas.add(new TelaCadastroView());
        Telas.add(new TelaEdicaoView());

        mostraTelaSelecionada();

    }

    private static void mostraTelaSelecionada()
    {
        Telas.get(IndiceDeTelaSelecionado).mostraTela(cbTela);

    }

    private static void aguardaInput()
    {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        Telas.get(IndiceDeTelaSelecionado).manipulaInput(input);
    }


    public static void main(String[] args) {

        gerenciarTelas();

        while (true)
        {
            aguardaInput();
        }
    }
}
