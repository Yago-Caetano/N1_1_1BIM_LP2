package com.agenda.models;



import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CompromissoModel {

        public  int PosLista;
        private String Titulo;

        private Calendar Data;

        private String Descricao;

        private Calendar DataAviso;

        private boolean AlarmeTocou;

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
                result=result+"Data de aviso: " + sdf.format(DataAviso.getTime()) + "\n";

                return result;
        }
        public String SerializedObject()
        {
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String Resultado="";
                Resultado=String.valueOf(Id)+"-";
                Resultado=Resultado + Titulo+"-";
                Resultado=Resultado + Descricao+"-";
                Resultado=Resultado + format1.format(Data.getTime())+"-";
                Resultado=Resultado + format1.format(DataAviso.getTime());

                return Resultado;

        }

        public void setId(int id) {
                Id = id;
        }

        public void setAlarmeExecutado()
        {
                AlarmeTocou = true;
        }

        public boolean getAlarmeExecutado()
        {
                return AlarmeTocou;
        }

        public boolean executaAlarme(Calendar c)
        {
                if((DataAviso.getTime().compareTo(c.getTime()) <= 0) && !AlarmeTocou)
                        return true;
                else
                        return false;
        }
}
