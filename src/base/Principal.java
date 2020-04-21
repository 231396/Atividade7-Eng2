package base;

import java.util.Scanner;

public class Principal 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Metodos m = new Metodos();		
		
		Equipe equipeSaude = m.LerEquipe("Equipe de Saude");

		if (equipeSaude == null) 
			equipeSaude = new Equipe("Equipe de Saude");
		
		int tipoConta, opc;
		boolean emExecucao = true;
		Paciente pacienteAtual = null;
		String[] mensagens;
		
		System.out.println("Entrar como:");
		System.out.println("Paciente digite 1");
		System.out.println("Equipe de sáude digite 2");
		tipoConta = sc.nextInt();			
		
		if (tipoConta == 1) 
		{
			System.out.println("Possue Conta? (1- Sim | 2- Nao)");
			opc = sc.nextInt();
			
			if (opc == 1) 
			{
				pacienteAtual = m.LoginPaciente(equipeSaude.pacientes);
				if (pacienteAtual == null)
					System.out.println("Falha ao logar");
			}
			else if (opc == 2) 
			{
				pacienteAtual = m.CadastrarPaciente();
				equipeSaude.AddPaciente(pacienteAtual);
			}
			
			if (pacienteAtual != null) 
				while (emExecucao) 
				{
					System.out.println("O que deseja fazer?");
					System.out.println("0- Sair");
					System.out.println("1- Ver Sintomas");
					System.out.println("2- Enviar Mensagens");
					System.out.println("3- Ver Mensagens");
					opc = sc.nextInt();
					
					switch(opc) 
					{
						case 0:
							emExecucao = false;
						break;
						case 1:
							Sintomas.VerSintomas();
						break;
						case 2:
							System.out.println("Escreva a mensagem:");							
							pacienteAtual.EnviarMensagem(pacienteAtual.nome + ": " + sc.next());
						break;
						case 3:
							mensagens = pacienteAtual.GetMensagens();
							for (int i = 0; i < mensagens.length; i++) 
								System.out.println(mensagens[i]);						
						break;	
					}
					System.out.println("\n---------------------------");
				}		
		}
		else if (tipoConta == 2)
		{			
			while (emExecucao)
			{
				System.out.println("O que deseja fazer?");
				System.out.println("0- Sair");
				System.out.println("1- Listar Pacientes");
				System.out.println("2- Agendar Visita");
				System.out.println("3- Realizar Visita");
				System.out.println("4- Enviar Menssagem");
				System.out.println("5- Ver Menssagens");
				opc = sc.nextInt();
											
				switch(opc) 
				{
					case 0:
						emExecucao = false;
					break;
					case 1:
						System.out.println("Listar Pacientes por...");
						System.out.println("0- Normal");
						System.out.println("1- Cidade");
						System.out.println("2- Estado");
						System.out.println("3- Bairro");
						System.out.println("4- Sintoma");
						opc = sc.nextInt();
						m.ListarPaciente(equipeSaude.pacientes, opc);							
					break;
					case 2:
						System.out.println("CPF do paciente:");
						pacienteAtual = m.FindPacienteCpf(sc.nextInt(), equipeSaude.pacientes);
						if (pacienteAtual != null) 
						{
							System.out.println("Digite a Data e a Hora");
							equipeSaude.MarcarVisita(pacienteAtual, sc.next());
						}
						else
							System.out.println("Paciente não existe");
					break;		
					case 3:
						System.out.println("Visitas Agendadas:");
						equipeSaude.ListaVisitasAgendadas();
						System.out.println("Escreva o número da visita:");
						pacienteAtual = equipeSaude.GetVisita(sc.nextInt()).GetPaciente();							
						if (pacienteAtual != null) 							
							m.RealizarVisita(pacienteAtual);
						else
							System.out.println("Visita não marcada");
					break;
					case 4:
						System.out.println("CPF do paciente:");
						pacienteAtual = m.FindPacienteCpf(sc.nextInt(), equipeSaude.pacientes);
						if (pacienteAtual != null) 
						{
							System.out.println("Escreva a Mensagem:");
							pacienteAtual.EnviarMensagem(equipeSaude.nome + ": " + sc.next());
						}						
						else
							System.out.println("Paciente não existe");
					break;
					case 5:
						System.out.println("CPF do paciente:");
						pacienteAtual = m.FindPacienteCpf(sc.nextInt(), equipeSaude.pacientes);
						if (pacienteAtual != null) 
						{
							mensagens = pacienteAtual.GetMensagens();
							for (int i = 0; i < mensagens.length; i++) 
								System.out.println(mensagens[i]);
						}
						else
							System.out.println("Paciente não existe");
					break;
				}
				System.out.println("\n---------------------------");
			}
		}		
		m.Salvar(equipeSaude);
		sc.close();
	}		
}
