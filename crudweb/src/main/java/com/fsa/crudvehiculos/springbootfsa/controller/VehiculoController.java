package com.fsa.crudvehiculos.springbootfsa.controller;

import com.fsa.crudvehiculos.springbootfsa.modelo.Vehiculo;
import com.fsa.crudvehiculos.springbootfsa.modelo.VehiculoDto;
import com.fsa.crudvehiculos.springbootfsa.services.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepositorio repo;

    @GetMapping({"", "/"})
    public String mostrarListaVehiculos(Model modelo, 
                                        @RequestParam(defaultValue = "idVehiculos") String ordenarPor,
                                        @RequestParam(defaultValue = "asc") String direccion) {
        Sort sort = Sort.by(Sort.Direction.fromString(direccion), ordenarPor);
        List<Vehiculo> vehiculos = repo.findAll(sort);
        modelo.addAttribute("vehiculos", vehiculos);
        modelo.addAttribute("ordenarPor", ordenarPor);
        modelo.addAttribute("direccion", direccion);
        return "vehiculos/index";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model modelo) {
        modelo.addAttribute("vehiculoDto", new VehiculoDto());
        return "vehiculos/agregar";
    }

    @PostMapping("/agregar")
    public String agregarVehiculo(@Valid @ModelAttribute("vehiculoDto") VehiculoDto vehiculoDto, 
                                BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "vehiculos/agregar";
        }

        Vehiculo vehiculo = vehiculoDto.toVehiculo();
        
        if (vehiculoDto.getImagen() != null && !vehiculoDto.getImagen().isEmpty()) {
            String rutaImagen = guardarImagen(vehiculoDto.getImagen());
            vehiculo.setRutaImagen(rutaImagen);
        }

        repo.save(vehiculo);
        redirectAttributes.addFlashAttribute("mensaje", "Vehículo agregado exitosamente");
        return "redirect:/vehiculos";
    }

    @GetMapping("/actualizar/{idVehiculos}")
    public String mostrarFormularioActualizar(@PathVariable int idVehiculos, Model modelo) {
        Vehiculo vehiculo = repo.findById(idVehiculos)
                .orElseThrow(() -> new IllegalArgumentException("ID de vehículo inválido:" + idVehiculos));
        modelo.addAttribute("vehiculoDto", new VehiculoDto(vehiculo));
        return "vehiculos/actualizar";
    }

    @PostMapping("/actualizar")
    public String actualizarVehiculo(@Valid @ModelAttribute("vehiculoDto") VehiculoDto vehiculoDto, 
                                 BindingResult result, Model modelo, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "vehiculos/actualizar";
        }

        Vehiculo vehiculo = vehiculoDto.toVehiculo();
        
        if (vehiculoDto.getImagen() != null && !vehiculoDto.getImagen().isEmpty()) {
            String rutaImagen = guardarImagen(vehiculoDto.getImagen());
            vehiculo.setRutaImagen(rutaImagen);
        }

        repo.save(vehiculo);
        redirectAttributes.addFlashAttribute("mensaje", "Vehículo actualizado exitosamente");
        return "redirect:/vehiculos";
    }

    @GetMapping("/eliminar")
    public String eliminarVehiculo(@RequestParam int idVehiculos, RedirectAttributes redirectAttributes) {
        try {
            repo.deleteById(idVehiculos);
            redirectAttributes.addFlashAttribute("mensaje", "Vehículo eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el vehículo: " + e.getMessage());
        }
        return "redirect:/vehiculos";
    }

    private String guardarImagen(MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return null;
        }
        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
        try {
            Path rutaArchivos = Paths.get("src/main/resources/static/uploads");
            Files.createDirectories(rutaArchivos);
            Path rutaCompleta = rutaArchivos.resolve(nombreArchivo);
            Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + nombreArchivo;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

    
    @GetMapping("/buscar")
    public String mostrarBusqueda(Model modelo) {
        List<String> marcas = repo.findDistinctMarcas();
        List<String> modelos = repo.findDistinctModelos();
        List<Integer> anios = repo.findDistinctAnios();
        
        modelo.addAttribute("marcas", marcas);
        modelo.addAttribute("modelos", modelos);
        modelo.addAttribute("anios", anios);
        
        return "vehiculos/buscar";
    }
    
    @GetMapping("/modelos")
    @ResponseBody
    public List<String> getModelos(@RequestParam String marca) {
        return repo.findModelosByMarca(marca);
    }

    @GetMapping("/anios")
    @ResponseBody
    public List<Integer> getAnios(@RequestParam String marca) {
        return repo.findAniosByMarca(marca);
    }

    @GetMapping("/resultados")
    public String buscarVehiculosResultados(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer anio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "marca") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model modeloVista) {
        
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Vehiculo> pageVehiculos = repo.buscarVehiculos(marca, modelo, anio, pageable);
        
        modeloVista.addAttribute("vehiculos", pageVehiculos.getContent());
        modeloVista.addAttribute("currentPage", page);
        modeloVista.addAttribute("totalPages", pageVehiculos.getTotalPages());
        modeloVista.addAttribute("totalItems", pageVehiculos.getTotalElements());
        modeloVista.addAttribute("sortField", sortBy);
        modeloVista.addAttribute("sortDir", sortDir);
        modeloVista.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "vehiculos/resultados";
    }
}