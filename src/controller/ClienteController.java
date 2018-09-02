package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ClienteDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cliente;

public class ClienteController implements Initializable{
	
    @FXML
    private TextField tfNomeCliente, tfEndCliente, tfCpfCliente, tfTelCliente, tfEmailCliente, tfCidadeCliente, tfUfCliente;


    @FXML
    private Button btCadastrarCliente, btLimparCliente;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btCadastrarCliente.setOnMouseClicked((MouseEvent e)->{
			
			cadastrarPessoa();
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
			Cliente c = new Cliente(nome, cpf, endereco, email, uf, cidade, tel);
			
			ClienteDao dao = new ClienteDao();
			dao.add(c);
			
			Alert al = new Alert(AlertType.INFORMATION);
			al.setHeaderText("Cadastro Realizado com Sucesso!");
			al.show();
			
			
		}catch(Exception e){

			Alert alE = new Alert(AlertType.ERROR);
			alE.setHeaderText("Cadastro não realizado, confira os dados e tente novamente.");
			alE.show();
		}
		
		handleLimpar();

		
		
	}
//
//
//	@FXML
//    void handleCadastrar(ActionEvent event) {
//    	System.out.println(tfNomeCliente.getText());
//    }
    
    

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
	
	
}
