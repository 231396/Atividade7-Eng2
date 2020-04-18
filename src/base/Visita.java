package base;

public class Visita
{
	public Visita(Paciente pacienteVisitado, String data_hora) 
	{
		this.pacienteVisitado = pacienteVisitado;
		this.data_hora = data_hora;
	}

	private Paciente pacienteVisitado;
	private String data_hora;
	
	public Paciente GetPaciente() 
	{
		return pacienteVisitado;
	}
	
	public String GetData_hora() 
	{
		return data_hora;
	}
}
