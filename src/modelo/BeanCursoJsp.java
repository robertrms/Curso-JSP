package modelo;

public class BeanCursoJsp {
	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String fone;
	private String cep;
	private String rua;
	private String bairro;
	private String numero;
	private String cidade;
	private String uf;
	private String fotoBase64;
	private String fotoBase64Miniatura;
	private String contentType;
	private String pdfBase64;
	private String contentTypePdf;
	
	private boolean ativo;
	
	private String tempFotoUser;
	
	public String getTempFotoUser() {
		
		tempFotoUser = "data:" + this.contentType + ";base64," + this.fotoBase64;
		
		return tempFotoUser;
	}
	
	
	
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}



	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}
	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPdfBase64() {
		return pdfBase64;
	}
	public void setPdfBase64(String pdfBase64) {
		this.pdfBase64 = pdfBase64;
	}

	public String getContentTypePdf() {
		return contentTypePdf;
	}
	public void setContentTypePdf(String contentTypePdf) {
		this.contentTypePdf = contentTypePdf;
	}
	
}
