package br.com.alertWeather.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CITY")
public class City {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="STATE", nullable=false)
	private String state;
	
	
	
			
}
