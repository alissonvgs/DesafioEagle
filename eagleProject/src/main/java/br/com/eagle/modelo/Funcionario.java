package br.com.eagle.modelo;

import javax.persistence.Entity;

@Entity
public class Funcionario extends pessoa {
	private String cargo;
	private int HoraServico;
	private int salario;

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getHoraServico() {
		return HoraServico;
	}

	public void setHoraServico(int horaServico) {
		HoraServico = horaServico;
	}

}
