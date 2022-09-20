package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";

		Connection conn = null;

		PreparedStatement pStatement = null;

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
}
