package com.example.reservasala.model.dao;

import com.example.reservasala.model.Reserva;
import com.example.reservasala.model.database.DatabaseMySQL;
import javafx.fxml.FXML;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Boolean inserir (Reserva reserva){
        String sql = "INSERT INTO reserva( numeroSala, curso, disciplina, professor, data, horarioEntrada, horarioSaida, informatica, turno) VALUES (?,?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, reserva.getNumeroSala());
            stmt.setString(2, reserva.getCurso());
            stmt.setString(3, reserva.getDisciplina());
            stmt.setString(4,reserva.getProfessor());
            stmt.setString(5, reserva.getData());
            stmt.setString(6, reserva.getHoraEntrada());
            stmt.setString(7, reserva.getHoraSaida());
            stmt.setBoolean(8, reserva.getInformatica());
            stmt.setString(9, reserva.getTurno());
            stmt.execute();

            return true;

        }catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }
    public Reserva getReservaById(int id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";
        Reserva retorno = new Reserva();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId(resultado.getInt("id"));
                retorno.setNumeroSala(resultado.getString("numeroSala"));
                retorno.setCurso(resultado.getString("curso"));
                retorno.setDisciplina(resultado.getString("disciplina"));
                retorno.setProfessor(resultado.getString("professor"));
                retorno.setData(resultado.getString("data"));
                retorno.setHoraEntrada(resultado.getString("horarioEntrada"));
                retorno.setHoraSaida(resultado.getString("horarioSaida"));
                retorno.setInformatica(resultado.getBoolean("informatica"));
                retorno.setTurno(resultado.getString("turno"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Boolean delete (int id){
        String sql = "DELETE FROM reserva WHERE id = ? ";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    public List<Reserva> getReservas() {
        String sql = "SELECT * FROM reserva";
        List<Reserva> listReservas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultado.getInt("id"));
                reserva.setNumeroSala(resultado.getString("numeroSala"));
                reserva.setCurso(resultado.getString("curso"));
                reserva.setDisciplina(resultado.getString("disciplina"));
                reserva.setProfessor(resultado.getString("professor"));
                reserva.setData(resultado.getString("data"));
                reserva.setHoraEntrada(resultado.getString("horarioEntrada"));
                reserva.setHoraSaida(resultado.getString("horarioSaida"));
                reserva.setInformatica(resultado.getBoolean("informatica"));
                reserva.setTurno(resultado.getString("turno"));

                listReservas.add(reserva);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listReservas;
    }
    public Boolean update(Reserva reserva) {
        String sql = "UPDATE reserva SET numeroSala = ?, curso = ?, disciplina = ?, professor = ?, data = ?, horarioEntrada = ?, horarioSaida = ?, informatica = ?, turno = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, reserva.getNumeroSala());
            stmt.setString(2, reserva.getCurso());
            stmt.setString(3, reserva.getDisciplina());
            stmt.setString(4, reserva.getProfessor());
            stmt.setString(5, reserva.getData());
            stmt.setString(6, reserva.getHoraEntrada());
            stmt.setString(7, reserva.getHoraSaida());
            stmt.setBoolean(8, reserva.getInformatica());
            stmt.setString(9, reserva.getTurno());
            stmt.setInt(10, reserva.getId());


            stmt.executeUpdate();


            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}