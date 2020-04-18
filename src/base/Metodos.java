package base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.journaldev.com/709/java-read-file-line-by-line
//https://stackoverflow.com/questions/13729625/overwriting-txt-file-in-java
//https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java

public class Metodos 
{
	Scanner sc = new Scanner(System.in);
	
	public Equipe LerEquipe(String nome)
	{
		File fe = new File(CurrentPath() + "//" + nome + ".txt");
		File fp = new File(CurrentPath() + "//pacientes.txt");	
		
		ArrayList<Visita> visitas = new ArrayList<Visita>();		
		
		ArrayList<Paciente> pacientes = LerPacientes(fp);		
		if (pacientes == null) {
			pacientes = new ArrayList<Paciente>();	
		}
		
		if (fe.exists())
		{
			try 
			{
				List<String> lines = Files.readAllLines(fe.toPath());
				int l = 0;
				int numVisitas = Integer.parseInt(lines.get(l++));
				
				nome = lines.get(l++);				
				for (int i = 0; i < numVisitas; i++) 
				{
					String dataHora = lines.get(l++);
					Paciente p = FindPacienteCpf(Integer.parseInt(lines.get(l++)) ,pacientes);
					Visita v = new Visita(p, dataHora);
					visitas.add(v);
				}
				return new Equipe(nome, pacientes, visitas);
			}
			catch (IOException e) {
			    e.printStackTrace();
			}
		}		
		return null;		
	}

	ArrayList<Paciente> LerPacientes(File f)
	{
		if (!f.exists())
			return null;

		try 
		{
			List<String> lines = Files.readAllLines(f.toPath());
			int l = 0;
			int numPacientes = Integer.parseInt(lines.get(l++));
			ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
			for (int i = 0; i < numPacientes; i++) 
			{
				String nome = lines.get(l++);
				int CPF = Integer.parseInt(lines.get(l++));
				int telefone = Integer.parseInt(lines.get(l++));			
				String senha = lines.get(l++);
				
				String estado = lines.get(l++);
				String cidade = lines.get(l++);
				String bairro = lines.get(l++);
				String rua = lines.get(l++);		
				
				Endereco end = new Endereco(estado, cidade, bairro, rua);
				
				int n_si = Integer.parseInt(lines.get(l++));
				ArrayList<String> si = new ArrayList<String>();
				for (int j = 0; j < n_si; j++) 
					si.add(lines.get(l++));
				
				int n_msg = Integer.parseInt(lines.get(l++));
				ArrayList<String> msg = new ArrayList<String>();
				for (int j = 0; j < n_msg; j++) 
					msg.add(lines.get(l++));
				
				Paciente p = new Paciente(nome, CPF, telefone, senha, end, si, msg);
				pacientes.add(p);
			}
			return pacientes;
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public void Salvar(Equipe equipe) 
	{		
		FileWriter fw;
		StringBuilder sbe = new StringBuilder(); 
		StringBuilder sbp = new StringBuilder();
		
		File fe = new File(CurrentPath() + "//" + equipe.nome + ".txt");
		File fp = new File(CurrentPath() + "//pacientes.txt");	

		AppendNL(sbe, equipe.nome);
		AppendNL(sbe, String.valueOf(equipe.visitas.size()));		
		for (int i = 0; i < equipe.visitas.size(); i++) 
		{
			Visita v = equipe.visitas.get(i);
			AppendNL(sbe, v.GetData_hora());
			AppendNL(sbe, String.valueOf(v.GetPaciente().CPF));
		}
		
		int n_si, n_msg, j;
		AppendNL(sbp, String.valueOf(equipe.pacientes.size()));	
		for (int i = 0; i < equipe.pacientes.size(); i++) 
		{
			Paciente p = equipe.pacientes.get(i);
			AppendNL(sbp, p.nome);
			AppendNL(sbp, String.valueOf(p.CPF));
			AppendNL(sbp, String.valueOf(p.telefone));
			AppendNL(sbp, p.senha);
			AppendNL(sbp, p.endereco.GetEstado());
			AppendNL(sbp, p.endereco.GetCidade());
			AppendNL(sbp, p.endereco.GetBairro());
			AppendNL(sbp, p.endereco.GetRua());		
			
			n_si = p.sintomasIdentificados.size();
			AppendNL(sbp, String.valueOf(n_si));
			for (j = 0; j < n_si; j++) 
				AppendNL(sbp, p.sintomasIdentificados.get(i));		
			
			n_msg = p.menssagens.size();
			AppendNL(sbp, String.valueOf(n_msg));
			for (j = 0; j < n_msg; j++) 
				AppendNL(sbp, p.menssagens.get(i));
		}		
		
		try {
		    fw = new FileWriter(fe, false);
		    fw.write(sbe.toString());
		    fw.close();
			
		    fw = new FileWriter(fp, false);
		    fw.write(sbp.toString());
		    fw.close();

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	void AppendNL(StringBuilder sb, String txt) 
	{
		sb.append(txt);
		sb.append("\n");
	}
	
	String CurrentPath() 
	{
		return System.getProperty("user.dir");
	}
	
	//String SetPath(String pasta, String nomeArquivo) 
	//{
		//return new File(".").getAbsolutePath() + "//" + pasta + "//" + nomeArquivo;
	//}
	
	public Paciente FindPacienteCpf(int cpf, ArrayList<Paciente> pacientes) 
	{		
		for (int i = 0; i < pacientes.size(); i++)
			if (pacientes.get(i).CPF == cpf)
				return pacientes.get(i);
		return null;
	}

	public Paciente LoginPaciente(ArrayList<Paciente> pacientes) {
		System.out.println("CPF:");
		int cpf = sc.nextInt();
		System.out.println("Senha:");
		String senha = sc.next();
		
		Paciente p = FindPacienteCpf(cpf, pacientes);
		if (p == null || p.senha != senha)		
			return null;		

		return p;
	}

	public Paciente CadastrarPaciente() 
	{
		System.out.println("Cadastro:");
		
		System.out.println("\nNome:");
		String nome = sc.next();
		
		System.out.println("\nCPF:");
		int cpf = sc.nextInt();
		
		System.out.println("\nTelefone:");
		int telefone = sc.nextInt();
		
		System.out.println("\nSenha:");
		String senha = sc.next();
		
		System.out.println("\nEndereco");
		System.out.println("estado:");
		String estado = sc.next();
		System.out.println("cidade:");
		String cidade = sc.next();
		System.out.println("bairro:");
		String bairro = sc.next();
		System.out.println("rua:");
		String rua = sc.next();
		
		Endereco end = new Endereco(estado, cidade, bairro, rua);
		
		System.out.println("\nSintomasIdentificados");		
		ArrayList<String> sintomasIdentificados = new ArrayList<String>();
		System.out.println("Digite 1 se possuir o sintoma e qualquer outro numero caso não possuir");
		for (int i = 0; i < Sintomas.sintomas.length; i++) 
		{
			System.out.println("Possue sintoma: " + Sintomas.sintomas[i]);
			if (sc.nextInt() == 1)
				sintomasIdentificados.add(Sintomas.sintomas[i]);
		}
		
		return new Paciente(nome, cpf, telefone, senha, end, sintomasIdentificados, new ArrayList<String>());
	}

	public void ListarPaciente(ArrayList<Paciente> pacientes, int opc) 
	{
		Paciente p;
		String filtro;
		switch (opc) 
		{			
			case 0:
				for (int i = 0; i < pacientes.size(); i++) 
				{
					p = pacientes.get(i);
					System.out.println(p.nome + " " + p.CPF);	
				}
			break;
			case 1:
				System.out.println("Escreva a cidade");
				filtro = sc.next();
				for (int i = 0; i < pacientes.size(); i++) 
				{
					if (filtro == pacientes.get(i).endereco.GetCidade()) 
					{
						p = pacientes.get(i);
						System.out.println(p.nome + " " + p.CPF);	
					}
				}						
			break;
			case 2:
				System.out.println("Escreva o Estado");
				filtro = sc.next();
				for (int i = 0; i < pacientes.size(); i++) 
				{
					if (filtro == pacientes.get(i).endereco.GetEstado()) 
					{
						p = pacientes.get(i);
						System.out.println(p.nome + " " + p.CPF);	
					}
				}						
			break;
			case 3:
				System.out.println("Escreva o Bairro");
				filtro = sc.next();
				for (int i = 0; i < pacientes.size(); i++) 
				{
					if (filtro == pacientes.get(i).endereco.GetBairro()) 
					{
						p = pacientes.get(i);
						System.out.println(p.nome + " " + p.CPF);	
					}
				}		
				
			break;
			case 4:
				System.out.println("Escreva os sintomas (digite 0 para finalizar)");
				boolean pegandoSintomas = true;
				ArrayList<String> filtros = new ArrayList<String>();
				while (pegandoSintomas) 
				{					
					filtro = sc.next();
					if (filtro != "0")
						filtros.add(filtro);
					else
						pegandoSintomas = false;
				}
				for (int i = 0; i < pacientes.size(); i++) 
				{
					p = pacientes.get(i);					
					if (p.sintomasIdentificados.containsAll(filtros)) 
						System.out.println(p.nome + " " + p.CPF);	
				}				
			break;
		}

	}
	
	public void RealizarVisita(Paciente paciente) 
	{
		System.out.println("Esta com sintomas? \n(Digite 1 confirmar e outra para proximo sintoma)");		
		for (int i = 0; i < paciente.sintomasIdentificados.size(); i++) 
		{
			System.out.println("Sintoma: " + paciente.sintomasIdentificados.get(i));
			if (sc.nextInt() != 1)				
				paciente.sintomasIdentificados.remove(i);
		}		
	}

}
