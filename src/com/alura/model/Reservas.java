package com.alura.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;

public class Reservas {
	
	private Integer id;
	private Date fecha_de_ingreso;
	private Date fecha_de_salida;	
	private BigDecimal valorTotal;
	private String forma_de_pago;
	private Long dias;
	private BigDecimal cuota;
	
	public Reservas() {}

	public Reservas(Integer id, Date fecha_de_ingreso, Date fecha_de_salida, Integer cuota, String forma_de_pago) {
		this.id = id;
		this.fecha_de_ingreso = fecha_de_ingreso;
		this.fecha_de_salida = fecha_de_salida;
		this.cuota = new BigDecimal(cuota);
		if(fecha_de_salida.after(fecha_de_ingreso)) {
			LocalDate in = fecha_de_ingreso.toLocalDate();
			LocalDate out = fecha_de_salida.toLocalDate();
			ZoneId zona = ZoneId.systemDefault();
			
			Duration estancia = Duration.between(in.atStartOfDay(zona), out.atStartOfDay(zona));
			this.dias = estancia.toDays();
		}else {
			System.out.println("error");
			this.dias = 0L;
			
		}
		
		this.valorTotal = BigDecimal.valueOf(this.dias).multiply(this.cuota);
		this.forma_de_pago = forma_de_pago;
	}
	
	

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public Date getFecha_de_ingreso() {
		return fecha_de_ingreso;
	}

	public void setFecha_de_ingreso(Date fecha_de_ingreso) {
		this.fecha_de_ingreso = fecha_de_ingreso;
	}

	public Date getFecha_de_salida() {
		return fecha_de_salida;
	}

	public void setFecha_de_salida(Date fecha_de_salida) {
		this.fecha_de_salida = fecha_de_salida;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	

	public String getForma_de_pago() {
		return forma_de_pago;
	}

	public void setForma_de_pago(String forma_de_pago) {
		this.forma_de_pago = forma_de_pago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, fecha de ingreso: %s, fecha de salida: %s,"
				+ "Costo total: %s,"
				+ "forma de pago: %s",
				this.id,
				this.fecha_de_ingreso,
				this.fecha_de_salida,
				this.valorTotal,
				this.forma_de_pago);
	}
	
	

}
