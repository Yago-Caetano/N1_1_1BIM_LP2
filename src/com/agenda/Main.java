package com.agenda;

import com.agenda.models.AgendaModel;
import com.agenda.models.CompromissoModel;
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
    public static final int TELA_AGENDA = 5;
    public static final int TELA_DELETAR = 6;

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

        @Override
        public void InsertCompromisso(CompromissoModel comp) {
            if(Agenda.cadastrarCompromisso((comp)))
                System.out.println("Compromisso Criado!");
            else
                System.out.println("Erro ao cadastrar compromisso");

            System.out.println(comp.SerializedObject());
            trocarTela(0);

        }

        @Override
        public AgendaModel GetAgenda() {
            return Agenda;
        }

        @Override
        public int SolicitarID() {
            return Agenda.SolicitarID();
        }

    };


    public static void gerenciarTelas()
    {
        IndiceDeTelaSelecionado = 0;
        Agenda= new AgendaModel();
        Telas = new ArrayList<PadraoView>();

        Telas.add(new TelaPrincipalView());
        Telas.add(new TelaCadastroView());
        Telas.add(new TelaAgendaView());
        Telas.add(new TelaEdicaoView());
        Telas.add(new TelaDeletarView());

        mostraTelaSelecionada();

    }

    private static void mostraTelaSelecionada()
    {
        Telas.get(IndiceDeTelaSelecionado).mostraTela(cbTela);

    }

    private static void aguardaInput()
    {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
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
