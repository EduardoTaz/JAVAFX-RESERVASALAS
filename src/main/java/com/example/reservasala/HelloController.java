package com.example.reservasala;

import com.example.reservasala.model.Reserva;
import com.example.reservasala.model.dao.ReservaDao;
import com.example.reservasala.model.database.Database;
import com.example.reservasala.model.database.DatabaseFactory;
import com.example.reservasala.model.database.DatabaseMySQL;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class HelloController {
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ReservaDao reservaDao = new ReservaDao();

    @FXML
    private TextField txtNumeroSala;
    @FXML
    private TextField txtCurso;
    @FXML
    private TextField txtDisciplina;
    @FXML
    private TextField txtProfessor;
    @FXML
    private  TextField txtData;
    @FXML
    private  TextField txtHoraEntrada;
    @FXML
    private TextField txtHoraSaida;
    @FXML
    private TextField txtId;
    @FXML
    private ToggleGroup grpTurno;
    @FXML
    private RadioButton rbManha;
    @FXML
    private RadioButton rbTarde;
    @FXML
    private RadioButton rbNoite;
    @FXML
    private CheckBox chkInformatica;
    @FXML
    private TextField txtIdBuscar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnDeletar;












    private Reserva reserva;

    private ObservableList<Reserva> listReservas;

    @FXML
    private TableView<Reserva>  tbvReservas;
    @FXML
    private  TableColumn<Reserva, Integer> tbcId;
    @FXML
    private TableColumn<Reserva, String> tbcNumeroSala;
    @FXML
    private TableColumn<Reserva, String> tbcCurso;
    @FXML
    private TableColumn<Reserva, String> tbcDisciplina;
    @FXML
    private TableColumn<Reserva, String> tbcProfessor;
    @FXML
    private TableColumn<Reserva, String> tbcData;
    @FXML
    private TableColumn<Reserva, String> tbcHoraEntrada;
    @FXML
    private TableColumn<Reserva, String> tbcHoraSaida;
    @FXML
    private TableColumn<Reserva, String> tbcTurno;
    @FXML
    private TableColumn<Reserva, Boolean> tbcInformatica;

    Integer id = null;

    @FXML
    protected void iniciar() {

        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        tbcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tbcDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tbcProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        tbcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tbcHoraEntrada.setCellValueFactory(new PropertyValueFactory<>("horaEntrada"));
        tbcHoraSaida.setCellValueFactory(new PropertyValueFactory<>("horaSaida"));
        tbcTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        tbcInformatica.setCellValueFactory(new PropertyValueFactory<>("informatica"));

        reservaDao.setConnection(connection);
        tbvReservas.setItems(FXCollections.observableArrayList(reservaDao.getReservas()));

    }

    @FXML
    protected void onClickCadastrar(ActionEvent actionEvent) {
        if (txtNumeroSala.getText().equals("")) {
            campoVazio("Número da sala está vazio");
            txtNumeroSala.requestFocus();
        } else if (txtCurso.getText().equals("")) {
            campoVazio("O Curso está vazio");
            txtCurso.requestFocus();
        } else if (txtDisciplina.getText().equals("")) {
            campoVazio("A Disciplina está vazia");
            txtDisciplina.requestFocus();
        } else if (txtProfessor.getText().equals("")) {
            campoVazio("O campo Professor está vazio");
            txtProfessor.requestFocus();
        } else if (txtData.getText().equals("")) {
            campoVazio("A data não foi informada");
            txtData.requestFocus();
        } else if (txtHoraEntrada.getText().equals("")) {
            campoVazio("Hora de Entrada não informada");
            txtHoraEntrada.requestFocus();
        } else if (txtHoraSaida.getText().equals("")) {
            campoVazio("Hora de Saída não informada");
            txtHoraSaida.requestFocus();
        } else {
            String turno = rbManha.isSelected() ? "Manhã" : rbTarde.isSelected() ? "Tarde" : "Noite";

            reserva = new Reserva(txtNumeroSala.getText(), txtCurso.getText(), txtDisciplina.getText(), txtProfessor.getText(), txtData.getText(), txtHoraEntrada.getText(), txtHoraSaida.getText(), chkInformatica.isSelected(), turno);

        }


        reservaDao.setConnection(connection);
        if (id != null) {

            reserva.setId(id);
            if (reservaDao.atualizarReserva(reserva)) {
                aviso("Registro Atualizado", "Atualizar reserva", "Atualizado com sucesso");
                id = null;
            } else {
                aviso("Erro", "Erro ao atualizar", "Atualização sem sucesso");
            }
        } else {
            if (reservaDao.inserir(reserva)) {
                aviso("Registro salvo", "Cadastrar reserva", "Cadastrado com sucesso");
            } else {
                aviso("Erro", "Erro ao cadastrar", "Cadastro mal sucedido");
            }
        }

        limparCampos();
        iniciar();
    }



    @FXML
    protected void onClickBuscar() {
        Integer idBuscar;
        try {
            idBuscar = Integer.parseInt(txtId.getText());
        } catch (Exception erro) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Erro de Conversão");
            alerta.setContentText("ID não encontrado");
            alerta.show();
            return;
        }

        reservaDao.setConnection(connection);
        reserva = reservaDao.getReservaById(idBuscar);

        if (reserva != null && reserva.getId() != null) {
            populaCampos(reserva);
        } else {
            aviso("Buscar reserva", "Busca da Reserva", "Erro ao realizar a busca da reserva");
        }
    }



    private void limparCampos() {
        txtId.setText("");
        txtNumeroSala.setText("");
        txtCurso.setText("");
        txtDisciplina.setText("");
        txtProfessor.setText("");
        txtData.setText("");
        txtHoraEntrada.setText("");
        txtHoraSaida.setText("");
        rbManha.setSelected(false);
        rbTarde.setSelected(false);
        rbNoite.setSelected(false);
        chkInformatica.setSelected(false);
        txtNumeroSala.requestFocus();
    }


    private void campoVazio(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText("Campo Vasio");
        alert.setContentText(msg);
        alert.show();
        return;

    }


    private void populaCampos(Reserva reserva){
        txtNumeroSala.setText(reserva.getNumeroSala());
        txtCurso.setText(reserva.getCurso());
        txtDisciplina.setText(reserva.getDisciplina());
        txtProfessor.setText(reserva.getProfessor());
        txtData.setText(reserva.getData());
        txtHoraEntrada.setText(reserva.getHoraEntrada());
        txtHoraSaida.setText(reserva.getHoraSaida());

        if (reserva.getId() != null){
            txtId.setText(String.valueOf(reserva.getId()));
            id = reserva.getId();
        }

        // Verificação do valor de turno para evitar NullPointerException
        if (reserva.getTurno() != null) {
            if (reserva.getTurno().equals("Noite")) {
                rbNoite.setSelected(true);
                rbTarde.setSelected(false);
                rbManha.setSelected(false);
            } else if (reserva.getTurno().equals("Tarde")) {
                rbNoite.setSelected(false);
                rbTarde.setSelected(true);
                rbManha.setSelected(false);
            } else {
                rbNoite.setSelected(false);
                rbTarde.setSelected(false);
                rbManha.setSelected(true);
            }
        } else {
            rbNoite.setSelected(false);
            rbTarde.setSelected(false);
            rbManha.setSelected(false);
        }

        // Verificação do valor de Informatica para evitar NullPointerException
        if (reserva.getInformatica() != null && reserva.getInformatica()) {
            chkInformatica.setSelected(true);
        } else {
            chkInformatica.setSelected(false);
        }
    }



    @FXML
    protected void onExcluir(ActionEvent actionEvent) {
        Reserva reserva = new Reserva();
        limparCampos();
        Integer idBuscar;
        Reserva reservaDeletada = null;

        try{
            if (!txtIdBuscar.getText().isEmpty()) {
                idBuscar = Integer.parseInt(txtIdBuscar.getText());
            }else if (id == null) {
                aviso("Erro", "Erro ao excluir.","Não foi possivel excluir");
                return;
            }else{
                idBuscar = id;
            }


        }catch (Exception erro){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERRO");
            alerta.setHeaderText("Erro de Conversão");
            alerta.setContentText("Verifique se foi informado o código corretamente");
            return;
        }
        reservaDao.setConnection(connection);


        reservaDeletada = reservaDao.getReservaById(idBuscar); // Buscando a reserva a ser deletada.
        if (reservaDao.delete(idBuscar)) {
            aviso("Deletar", "Reserva excluída", "Reserva excluída com sucesso.");
            limparCampos();
        } else {
            aviso("Erro", "Falha na exclusão", "Erro ao tentar excluir a reserva.");
        }

        iniciar();
    }

    private void aviso(String title,String hearder,String content){
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle(title);
        aviso.setHeaderText(hearder);
        aviso.setContentText(content);
        aviso.show();
        return;
    }


    @FXML
    public void onTableClick(MouseEvent mouseEvent) {
        if (!tbvReservas.getSelectionModel().getSelectedCells().isEmpty()) {
            TablePosition position = tbvReservas.getSelectionModel().getSelectedCells().get(0);
            int linha = position.getRow();
            Reserva reserva1 = tbvReservas.getItems().get(linha);
            populaCampos(reserva1);
        } else {
            aviso("Erro", "Nenhuma linha selecionada", "Selecione uma linha válida antes de clicar.");
        }
    }

    public void onClickNovoCadastro(ActionEvent actionEvent) {
        limparCampos();
        id = null; // Reseta o ID para indicar que será um novo cadastro
    }

}
