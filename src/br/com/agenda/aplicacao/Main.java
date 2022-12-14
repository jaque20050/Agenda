package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		ContatoDAO contatoDAO = new ContatoDAO();

		Contato contato = new Contato();
		contato.setNome("Eni Luciane");
		contato.setIdade(45);
		contato.setDataCadastro(new Date());

		// contatoDAO.save(contato);

		// Atualizar o contato
		Contato atualizar = new Contato();
		atualizar.setNome("Jaqueline C Dias");
		atualizar.setId(26);
		atualizar.setDataCadastro(new Date());
		atualizar.setId(1);

		// contatoDAO.update(atualizar);

		// Remover contato pelo Id
		contatoDAO.deleteById(3);

		// Visualização dos registros do banco de dados

		for (Contato c : contatoDAO.getContatos()) {
			System.out.println("Contato: " + c.getNome());
		}
	}

}
