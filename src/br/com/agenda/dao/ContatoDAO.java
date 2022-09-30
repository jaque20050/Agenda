package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";

		Connection conn = null;

		java.sql.PreparedStatement pStatement = null;

		// PreparedStatement
		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criamos uma PreparedStatement para excecutar uma query
			pStatement = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores que são esperados pela query
			pStatement.setString(1, contato.getNome());
			pStatement.setInt(2, contato.getIdade());
			pStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Executar a query
			pStatement.execute();

			System.out.println("Contato salvo com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Fechar as conexão
			try {
				if (pStatement != null) {
					pStatement.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement psStatement = null;

		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar a classe para executar a query
			psStatement = conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			psStatement.setString(1, contato.getNome());
			psStatement.setInt(2, contato.getIdade());
			psStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// O id do registro que deseja atualizar
			psStatement.setInt(4, contato.getId());

			// Executar a query

			psStatement.execute();
			System.out.println("Contato atualizado com sucesso!!!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psStatement != null) {
					psStatement.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;

		PreparedStatement pStatement = null;

		// Classe que vai recuperar os dados do banco ****SELECT*****
		ResultSet resultSet = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pStatement = (PreparedStatement) conn.prepareStatement(sql);

			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {

				Contato contato = new Contato();

				// Recuperar o id
				contato.setId(resultSet.getInt("id"));
				// Recuperar o nome
				contato.setNome(resultSet.getString("nome"));
				// Recuperar a idade
				contato.setIdade(resultSet.getInt("idade"));
				// Recuperar a data de cadastro
				contato.setDataCadastro(resultSet.getDate("dataCadastro"));

				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return contatos;
	}

}
