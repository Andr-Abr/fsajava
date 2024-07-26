package com.fsa.crudvehiculos.springbootfsa.modelo;

import javax.persistence.*;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehiculos")
    private int idVehiculos;

    @Column(name = "marca", nullable = false, length = 255)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 255)
    private String modelo;

    @Column(name = "anio", nullable = false)
    private int anio;

    @Column(name = "generacion", nullable = false, length = 225)
    private String generacion;

    @Column(name = "motor", nullable = false, length = 255)
    private String motor;

    @Column(name = "tipo_combustible", nullable = false, length = 50)
    private String tipoCombustible;

    @Column(name = "consumo_km_l", nullable = false)
    private float consumoKmL;

    @Column(name = "tipo_traccion", nullable = false, length = 50)
    private String tipoTraccion;

    @Column(name = "tipo_carroceria", nullable = false, length = 50)
    private String tipoCarroceria;

    @Column(name = "numero_plazas", nullable = false)
    private int numeroPlazas;

    @Column(name = "ruta_imagen", length = 255)
    private String rutaImagen;

    // Getters y setters
    public int getIdVehiculos() {
        return idVehiculos;
    }

    public void setIdVehiculos(int idVehiculos) {
        this.idVehiculos = idVehiculos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public float getConsumoKmL() {
        return consumoKmL;
    }

    public void setConsumoKmL(float consumoKmL) {
        this.consumoKmL = consumoKmL;
    }

    public String getTipoTraccion() {
        return tipoTraccion;
    }

    public void setTipoTraccion(String tipoTraccion) {
        this.tipoTraccion = tipoTraccion;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

	@Override
	public String toString() {
		return "Vehiculo [idVehiculos=" + idVehiculos + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio
				+ ", generacion=" + generacion + ", motor=" + motor + ", tipoCombustible=" + tipoCombustible
				+ ", consumoKmL=" + consumoKmL + ", tipoTraccion=" + tipoTraccion + ", tipoCarroceria=" + tipoCarroceria
				+ ", numeroPlazas=" + numeroPlazas + ", rutaImagen=" + rutaImagen + ", getIdVehiculos()="
				+ getIdVehiculos() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getAnio()="
				+ getAnio() + ", getGeneracion()=" + getGeneracion() + ", getMotor()=" + getMotor()
				+ ", getTipoCombustible()=" + getTipoCombustible() + ", getConsumoKmL()=" + getConsumoKmL()
				+ ", getTipoTraccion()=" + getTipoTraccion() + ", getTipoCarroceria()=" + getTipoCarroceria()
				+ ", getNumeroPlazas()=" + getNumeroPlazas() + ", getRutaImagen()=" + getRutaImagen() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anio;
		result = prime * result + Float.floatToIntBits(consumoKmL);
		result = prime * result + ((generacion == null) ? 0 : generacion.hashCode());
		result = prime * result + idVehiculos;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((motor == null) ? 0 : motor.hashCode());
		result = prime * result + numeroPlazas;
		result = prime * result + ((rutaImagen == null) ? 0 : rutaImagen.hashCode());
		result = prime * result + ((tipoCarroceria == null) ? 0 : tipoCarroceria.hashCode());
		result = prime * result + ((tipoCombustible == null) ? 0 : tipoCombustible.hashCode());
		result = prime * result + ((tipoTraccion == null) ? 0 : tipoTraccion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (anio != other.anio)
			return false;
		if (Float.floatToIntBits(consumoKmL) != Float.floatToIntBits(other.consumoKmL))
			return false;
		if (generacion == null) {
			if (other.generacion != null)
				return false;
		} else if (!generacion.equals(other.generacion))
			return false;
		if (idVehiculos != other.idVehiculos)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (motor == null) {
			if (other.motor != null)
				return false;
		} else if (!motor.equals(other.motor))
			return false;
		if (numeroPlazas != other.numeroPlazas)
			return false;
		if (rutaImagen == null) {
			if (other.rutaImagen != null)
				return false;
		} else if (!rutaImagen.equals(other.rutaImagen))
			return false;
		if (tipoCarroceria == null) {
			if (other.tipoCarroceria != null)
				return false;
		} else if (!tipoCarroceria.equals(other.tipoCarroceria))
			return false;
		if (tipoCombustible == null) {
			if (other.tipoCombustible != null)
				return false;
		} else if (!tipoCombustible.equals(other.tipoCombustible))
			return false;
		if (tipoTraccion == null) {
			if (other.tipoTraccion != null)
				return false;
		} else if (!tipoTraccion.equals(other.tipoTraccion))
			return false;
		return true;
	}
}