package controller;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ClienteDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Cliente;

public class ClienteController implements Initializable{
	
    @FXML
    private TextField tfNomeCliente, tfEndCliente, tfCpfCliente, tfTelCliente, tfEmailCliente, tfCidadeCliente, tfUfCliente;
    @FXML private DatePicker dtNascCliente;
    @FXML private Button btCadastrarCliente, btLimparCliente, btDeletarCliente;
    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Long> tbIdCliente;
    @FXML private TableColumn<Cliente, String> tbNomeCliente;
    @FXML private TableColumn<Cliente, String> tbCpfCliente;
    @FXML private TableColumn<Cliente, String> tbEndCliente;
    @FXML private TableColumn<Cliente, String> tbCidadeCliente;
    @FXML private TableColumn<Cliente, String> tbUfCliente;
    @FXML private TableColumn<Cliente, String> tbTelCliente;
    @FXML private TableColumn<Cliente, String> tbEmailCliente;
    @FXML private TableColumn<Cliente, String> tbDataNascCliente;
    private Cliente selecionado;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initTable();
		btCadastrarCliente.setOnMouseClicked((MouseEvent e)->{
			cadastrarPessoa();
		});
		
		btDeletarCliente.setOnMouseClicked((MouseEvent e)->{
			deletaCliente();
		});

		tabelaClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				selecionado = (Cliente) newValue;
			}
		});
    }

    private void cadastrarPessoa() {
		String nome = tfNomeCliente.getText(),
				endereco = tfEndCliente.getText(),
				cpf = tfCpfCliente.getText(),
				tel = tfTelCliente.getText(),
				email = tfEmailCliente.getText(),
				cidade = tfCidadeCliente.getText(),
				uf = tfUfCliente.getText();
		
		try {
			Alert al = new Alert(AlertType.CONFIRMATION);
			al.setHeaderText("Novo Cadastro?");
			al.setContentText("Confira os Dados:\n\nNome: " +nome+ "\nCPF: "+cpf+"\nEndereço: "+endereco+"\nCidade: "+cidade+
								"\nEstado: "+uf+ "\nTelefone: "+tel+"Email: "+email);
			Optional<ButtonType> result = al.showAndWait();
			if(result.get() == ButtonType.OK) {
				Cliente c = new Cliente(nome, cpf, endereco, email, uf, cidade, tel);
				ClienteDao dao = new ClienteDao();
				dao.add(c);
				
				Alert alInfo = new Alert(AlertType.INFORMATION);
				alInfo.setHeaderText("Cadastro Realizado com Sucesso!");
				alInfo.show();
			}
			
		}catch(Exception e){

			Alert alE = new Alert(AlertType.ERROR);
			alE.setHeaderText("Cadastro não realizado, confira os dados e tente novamente.");
			alE.show();
		}
		
		handleLimpar();
    	tabelaClientes.setItems(atualizaTabela());
		
	}
    

    @FXML
    public void handleLimpar() {
		tfCpfCliente.setText("");
		tfNomeCliente.setText("");
		tfEmailCliente.setText("");
		tfEndCliente.setText("");
		tfTelCliente.setText("");
		tfCidadeCliente.setText("");
		tfUfCliente.setText("");
    }
	
    public void initTable() {
    	tbIdCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("id"));
    	tbNomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
    	tbEndCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
    	tbCpfCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
    	tbCidadeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
    	tbUfCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("estado"));
    	tbEmailCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
    	tbTelCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
    	tabelaClientes.setItems(atualizaTabela());
    }

    public ObservableList<Cliente> atualizaTabela(){
    	ClienteDao dao = new ClienteDao();
    	return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deletaCliente() {
    	try {

			Alert al = new Alert(AlertType.CONFIRMATION);
			al.setHeaderText("Deletar Cadastro");
			al.setContentText("Tem certeza que deseja excluir o item selecionado?");
			
			Optional<ButtonType> result = al.showAndWait();
			if(result.get() == ButtonType.OK) {
	        	ClienteDao dao = new ClienteDao();
	        	dao.delete(selecionado);
	        	tabelaClientes.setItems(atualizaTabela());
				Alert alI = new Alert(AlertType.INFORMATION);
				alI.setHeaderText("Cadastro excluído com sucesso!");
				alI.show();
			}

    	}catch(Exception e){
			Alert alE = new Alert(AlertType.ERROR);
			alE.setHeaderText("Selecione um item para ser excluido");
			alE.show();
    	}
    	

    }
}
