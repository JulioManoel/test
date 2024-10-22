package infra;

import br.com.valueprojects.mock_spring.model.Message;

public class SMS {
	private Message message;
	
	public SMS(Message message) {
		this.message = message;
	}
	
	public Message getMessage() {
		return this.message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
}
