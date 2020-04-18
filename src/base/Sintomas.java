package base;

public final class Sintomas 
{
	private Sintomas () {}
	
	public static String[] sintomas =
	{
		"Febre",
		"Cansa�o",
		"Tosse seca",
		"Dores",
		"Congest�o nasal",
		"Corrimento nasal",
		"Dor de garganta",
		"Diarr�ia",		
	};
	
	public static void VerSintomas() 
	{					
		System.out.println("Sintomas da doen�a:");
		for (int i = 0; i < sintomas.length; i++) 
		{
			System.out.println(i + " " + sintomas[i]);
		}
	}
	
}
