//Created by Francesco Garofalo


public class Token {
	private String id;
	private String attribute;
	public Token(String id, String attribute) {
		super();
		this.id = id;
		this.attribute = attribute;
	}
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	@Override
	public String toString() {
		return "Token [Classe= " + id + ", attribute= " + attribute + "]";
	}

	
}
