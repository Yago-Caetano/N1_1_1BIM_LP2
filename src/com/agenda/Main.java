package com.agenda;

import com.agenda.models.AgendaModel;
import com.agenda.views.PadraoView;
import com.agenda.views.TelaPrincipalView;

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

    public static void gerenciarTelas()
    {
        IndiceDeTelaSelecionado = 0;

        Telas = new ArrayList<PadraoView>();

        Telas.add(new TelaPrincipalView());

        Telas.get(0).mostraTela();

        while (true)
        {
            aguardaInput();
        }
    }

    private static void aguardaInput()
    {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        Telas.get(IndiceDeTelaSelecionado).manipulaInput(input);
    }


    public static void main(String[] args) {

        gerenciarTelas();
    }
}
