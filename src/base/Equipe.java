package base;

import java.util.ArrayList;

public class Equipe  
{	
	public Equipe(String nome, ArrayList<Paciente> pacientes, ArrayList<Visita> visitas)
	{
		this.nome = nome;
		this.pacientes = pacientes;		
		this.visitas = visitas;
	}	
	
	public Equipe(String nome) 
	{
		this.nome = nome; 
		pacientes = new ArrayList<Paciente>();
		visitas = new ArrayList<Visita>();
	}

	String nome;
	ArrayList<Paciente> pacientes;
	ArrayList<Visita> visitas;
	
	public void AddPaciente(Paciente paciente) 
	{
		pacientes.add(paciente);		
	}	
	
	public Visita GetVisita(int index) 
	{
		return visitas.get(index);		
	}
	
	public Paciente[] GetAllPacientes() 
	{
		return (Paciente[]) pacientes.toArray();		
	}
	
	
	public void ListaVisitasAgendadas() 
	{
		for (int i = 0; i < visitas.size(); i++) 
		{
			System.out.println(i + " " + visitas.get(i).GetPaciente().CPF);
		}		
	}

	public void MarcarVisita(Paciente paciente, String data_hora) 
	{
		visitas.add(new Visita(paciente, data_hora));
	}

}
