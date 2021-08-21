package com.agenda.models;

import java.util.Calendar;
import java.util.Date;

public class CompromissoModel {
        private String Titulo;

        private Calendar Data;

        private String Descricao;

        private Date DataAviso;

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

        public Date getDataAviso() {
                return DataAviso;
        }

        public void setDataAviso(Date dataAviso) {
                DataAviso = dataAviso;
        }

        public int getId() {
                return Id;
        }

        public void setId(int id) {
                Id = id;
        }
}
