package base;

import java.util.ArrayList;

public class Paciente
{
	public Paciente(String nome, int cPF, int telefone, String senha, Endereco endereco,
			ArrayList<String> sintomasIdentificados, ArrayList<String> menssagens) {
		this.nome = nome;
		CPF = cPF;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.sintomasIdentificados = sintomasIdentificados;
		this.menssagens = menssagens;
	}
	
	String nome;
	
	int CPF;
	
	int telefone;
	
	String senha;
	
	Endereco endereco;
	
	ArrayList<String> sintomasIdentificados;	
	
	ArrayList<String> menssagens;

	public void EnviarMensagem(String str) 
	{
		menssagens.add(str);
	}

	public String[] GetMensagens() {
		return (String[]) menssagens.toArray();
	}	
	
	
}
