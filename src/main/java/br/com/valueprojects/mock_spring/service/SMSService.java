package br.com.valueprojects.mock_spring.service;

import br.com.valueprojects.mock_spring.interfac.IEnviar;
import infra.SMS;

public class SMSService implements IEnviar {
	private SMS sms;
	
	public SMSService(SMS sms) {
		this.sms = sms;
	}
	
	public SMS getSMS() {
		return this.sms;
	}
	
	public void setSMS(SMS sms) {
		this.sms = sms;
	}
	
	public String enviar() {
		return this.sms.getMessage().getText();
	}
}
