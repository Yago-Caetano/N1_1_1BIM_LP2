package com.agenda.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CompromissoModel {
        private String Titulo;

        private Calendar Data;

        private String Descricao;

        private Calendar DataAviso;

        private int Id;

        public CompromissoModel() {
        }

        public String getTitulo() {
                return Titulo;
        }

        public void setTitulo(String titulo) {
                Titulo = titulo;
        }

        public Calendar getData() {
                return Data;
        }

        public void setData(Calendar data) {
                Data = data;
        }

        public String getDescricao() {
                return Descricao;
        }

        public void setDescricao(String descricao) {
                Descricao = descricao;
        }

        public Calendar getDataAviso() {
                return DataAviso;
        }

        public void setDataAviso(Calendar dataAviso) {
                DataAviso = dataAviso;
        }

        public int getId() {
                return Id;
        }

        public String PrintCompromisso()
        {
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String result="";
                result="Id: " + String.valueOf(Id) + "\n";
                result=result+"Título: " + Titulo + "\n";
                result=result+"Data: " + sdf.format(Data.getTime()) + "\n";
                result=result+"Descrição: " + Descricao + "\n";
                result=result+"Data: " + sdf.format(DataAviso.getTime()) + "\n";

                return result;
        }

        public void setId(int id) {
                Id = id;
        }
}
