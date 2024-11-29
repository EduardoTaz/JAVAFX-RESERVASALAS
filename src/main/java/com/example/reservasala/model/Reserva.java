package com.example.reservasala.model;

import java.util.Date;


public class Reserva {
    private Integer id;
    private String numeroSala;
    private String curso;
    private String disciplina;
    private String professor;
    private String data;
    private String horaEntrada;
    private String horaSaida;
    private Boolean informatica;
    private String turno;

    public Reserva(String numeroSala, String curso, String disciplina, String professor, String data, String horaEntrada, String horaSaida, Boolean informatica, String turno) {
        this.numeroSala = numeroSala;
        this.curso = curso;
        this.disciplina = disciplina;
        this.professor = professor;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.informatica = informatica;
        this.turno = turno;


    }

    public Reserva() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Boolean getInformatica() {
        return informatica;
    }

    public void setInformatica(Boolean informatica) {
        this.informatica = informatica;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
