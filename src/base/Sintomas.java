package base;

public final class Sintomas 
{
	private Sintomas () {}
	
	public static String[] sintomas =
	{
		"Febre",
		"Cansaço",
		"Tosse seca",
		"Dores",
		"Congestão nasal",
		"Corrimento nasal",
		"Dor de garganta",
		"Diarréia",		
	};
	
	public static void VerSintomas() 
	{					
		System.out.println("Sintomas da doença:");
		for (int i = 0; i < sintomas.length; i++) 
		{
			System.out.println(i + " " + sintomas[i]);
		}
	}
	
}
