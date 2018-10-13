package br.com.eagle.modelo;

import javax.persistence.Entity;

@Entity
public class Cliente extends pessoa{
	
	private String servico;

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}
	
}