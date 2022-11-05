package br.com.produtos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import br.com.produtos.model.Produto;

//Classe de conexão com o MySQL
public class ProdutoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();
	
	public void cadastrarProduto(Produto objproduto) {
		String query = "insert into produtos (nomeprodutos, fornecedorprodutos) values (?,?)";	
		conn = new ConexaoDAO().conectaBD();
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, objproduto.getNome());
			pstm.setString(2, objproduto.getFornecedor());
			
			pstm.execute();
			pstm.close();
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Cadastrar" + erro);
		}
		JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastrar", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public  ArrayList<Produto> PesquisarProduto(){
		String query = "select * from produtos";
		conn = new ConexaoDAO().conectaBD();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Produto objproduto = new Produto();
				objproduto.setIdproduto(rs.getInt("idprodutos"));
				objproduto.setNome(rs.getString("nomeprodutos"));
				objproduto.setFornecedor(rs.getString("fornecedorprodutos"));
				
				lista.add(objproduto);
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Pesquisar" + erro);
		}
		return lista;
		
	}
	
	public void alterarProduto(Produto objproduto) {
		String query = "update produtos set nomeprodutos = ?, fornecedorprodutos = ? where idprodutos = ?";
		conn = new ConexaoDAO().conectaBD();
		
			try {
			
				pstm = conn.prepareStatement(query);
				pstm.setString(1, objproduto.getNome());
				pstm.setString(2, objproduto.getFornecedor());
				pstm.setInt(3, objproduto.getIdproduto());
			
				pstm.execute();
				pstm.close();
			
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "ProdutoDAO Alterar" + erro);
			}
			JOptionPane.showMessageDialog(null, "Item alterado com sucesso!");
		}
	}


