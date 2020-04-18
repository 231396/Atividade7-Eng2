package base;

public final class Endereco {

	private String estado;	
	private String cidade;
	private String bairro;
	private String rua;
	
	public Endereco(String estado, String cidade, String bairro, String rua) {
		SetEndereco(estado, cidade, bairro, rua);
	}

	public void SetEndereco(String estado, String cidade, String bairro, String rua) 
	{
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}
	
	public String GetEstado() 
	{
		return estado;
	}
	
	public String GetCidade() 
	{
		return cidade;
	}
	
	public String GetBairro() 
	{
		return bairro;
	}
	
	public String GetRua() 
	{
		return rua;
	}	
	
}
