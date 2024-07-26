package com.fsa.crudvehiculos.springbootfsa.modelo;

import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class VehiculoDto {

    private int idVehiculos;

    @NotEmpty(message = "La marca no puede estar vacía")
    @Size(max = 255, message = "La marca no puede tener más de 255 caracteres")
    private String marca;

    @NotEmpty(message = "El modelo no puede estar vacío")
    @Size(max = 255, message = "El modelo no puede tener más de 255 caracteres")
    private String modelo;

    @NotNull(message = "El año no puede estar vacío")
    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    @Max(value = 2100, message = "El año debe ser menor a 2100")
    private Integer anio;

    @NotEmpty(message = "La generación no puede estar vacía")
    @Size(max = 225, message = "La generación no puede tener más de 225 caracteres")
    private String generacion;

    @NotEmpty(message = "El motor no puede estar vacío")
    @Size(max = 255, message = "El motor no puede tener más de 255 caracteres")
    private String motor;

    @NotEmpty(message = "El tipo de combustible no puede estar vacío")
    @Size(max = 50, message = "El tipo de combustible no puede tener más de 50 caracteres")
    private String tipoCombustible;

    @NotNull(message = "El consumo no puede estar vacío")
    @Min(value = 0, message = "El consumo debe ser mayor o igual a 0")
    @Max(value = 100, message = "El consumo debe ser menor o igual a 100")
    private Float consumoKmL;

    @NotEmpty(message = "El tipo de tracción no puede estar vacío")
    @Size(max = 50, message = "El tipo de tracción no puede tener más de 50 caracteres")
    private String tipoTraccion;

    @NotEmpty(message = "El tipo de carrocería no puede estar vacío")
    @Size(max = 50, message = "El tipo de carrocería no puede tener más de 50 caracteres")
    private String tipoCarroceria;

    @NotNull(message = "El número de plazas no puede estar vacío")
    @Min(value = 1, message = "El número de plazas debe ser al menos 1")
    @Max(value = 20, message = "El número de plazas debe ser menor o igual a 20")
    private Integer numeroPlazas;

    private String rutaImagen;
    
    private MultipartFile imagen;
    
    public VehiculoDto() {}

    public VehiculoDto(Vehiculo vehiculo) {
        this.idVehiculos = vehiculo.getIdVehiculos();
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
        this.anio = vehiculo.getAnio();
        this.generacion = vehiculo.getGeneracion();
        this.motor = vehiculo.getMotor();
        this.tipoCombustible = vehiculo.getTipoCombustible();
        this.consumoKmL = vehiculo.getConsumoKmL();
        this.tipoTraccion = vehiculo.getTipoTraccion();
        this.tipoCarroceria = vehiculo.getTipoCarroceria();
        this.numeroPlazas = vehiculo.getNumeroPlazas();
        this.rutaImagen = vehiculo.getRutaImagen();
    }

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
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

    public Float getConsumoKmL() {
        return consumoKmL;
    }

    public void setConsumoKmL(Float consumoKmL) {
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

    public Integer getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(Integer numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }   
    
    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    public Vehiculo toVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculos(this.idVehiculos);
        vehiculo.setMarca(this.marca);
        vehiculo.setModelo(this.modelo);
        vehiculo.setAnio(this.anio);
        vehiculo.setGeneracion(this.generacion);
        vehiculo.setMotor(this.motor);
        vehiculo.setTipoCombustible(this.tipoCombustible);
        vehiculo.setConsumoKmL(this.consumoKmL);
        vehiculo.setTipoTraccion(this.tipoTraccion);
        vehiculo.setTipoCarroceria(this.tipoCarroceria);
        vehiculo.setNumeroPlazas(this.numeroPlazas);
        vehiculo.setRutaImagen(this.rutaImagen);
        return vehiculo;
    }
}