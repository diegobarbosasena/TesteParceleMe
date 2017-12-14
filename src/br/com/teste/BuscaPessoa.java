package br.com.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.dao.PessoaDao;
import br.com.model.Pessoa;

public class BuscaPessoa {

	public static void main(String[] args) {
		
		
		List<Pessoa> lstPessoa = new ArrayList<>();
		
		PessoaDao pessoaDao = new PessoaDao();
		
		lstPessoa = pessoaDao.getPessoa((long) 4);
		
		for (Pessoa pessoa : lstPessoa) {
			System.out.println(pessoa.getId());
			System.out.println(pessoa.getNome());
			System.out.println(pessoa.getSobrenome());
			System.out.println(pessoa.getTelefone());
		}
		
	}
}
