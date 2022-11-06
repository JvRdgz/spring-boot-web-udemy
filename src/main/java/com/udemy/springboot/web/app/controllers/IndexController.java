package com.udemy.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udemy.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app") // Una ruta base generica para todos los metodos (Rutas de primer nivel, las de
						// mas abajo son rutas de segundo nivel)
public class IndexController {
// Manejo de handlers => Manejo de peticiones de un usuario (borrar, modificar, insertar...)
// El Controller se encarga de manejar las peticiones del usuario.
// Siempre son métodos públicos
	
	// Inyectar valores usando la anotación @Value
	@Value("${text.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${text.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${text.indexcontroller.listar.titulo}")
	private String textoListar;

	// @RequestMapping(value="/index", method=RequestMethod.GET) // Relaciona este
	// metodo con una ruta URL (Mapping). Si se omite el method, cogerá por defecto
	// el método GET
	// @GetMapping("/index") // Es exactamente igual que utilizar el RequestMapping,
	// pero más sencillo y mas corto.
	@GetMapping({ "/index", "/", "/home" }) // Se pueden crear varias rutas (en forma de array)
	// public String index(Model model) { // Model sirve para pasar datos a la
	// interfaz
	public String index(ModelMap model) { // ModelMap funciona igual que Model.
		// model.addAttribute("titulo", "Hola Spring framework!!");
		// Con @Value hacemos referencia al valore del .properties:
		model.addAttribute("titulo", textoIndex);
		return "index"; // Devuelve la plantilla index, que la buscara en el paquete resources.templates
	}

	/**
	 * Este paso de datos a la interfaz también puede utilizarse a través de la
	 * implementación del método Map de java.util. de la siguiente manera:
	 */
	/*
	 * public String index(Map<String, Object> map) { map.put("titulo",
	 * "hola Spring Framework con Map!"); return "index"; }
	 */

	// Model
	@RequestMapping("/perfil") // Esto es Get ya que si no se especifica maneja un GET por defecto
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Andrés");
		usuario.setApellido("Pérez");
		usuario.setEmail("andres.perez@gmail.com");
		model.addAttribute("usuario", usuario);
		// model.addAttribute("titulo", "Perfil del usuario: " + usuario.getNombre() + " " + usuario.getApellido());
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre() + " " + usuario.getApellido()));
		return "perfil";
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
//		List<Usuario> usuarios = Arrays.asList(new Usuario("Andres", "Perez", "andres.perez@gmail.com"),
//				new Usuario("John", "Doe", "john.doe@gmail.com"),
//				new Usuario("Francisco", "Franco", "francisco.franco@gmail.com"),
//				new Usuario("Joe", "Biden", "Joe.biden@gmail.com")); // Añadir elementos a una lista separado por comas
//		usuarios.add(new Usuario("Andres", "Perez", "andres.perez@gmail.com"));
//		usuarios.add(new Usuario("John", "Doe", "john.doe@gmail.com"));
//		usuarios.add(new Usuario("Francisco", "Franco", "francisco.franco@gmail.com"));
		model.addAttribute("titulo", textoListar);
		// model.addAttribute("usuarios", usuarios);
		return "listar";
	}
	
	/**
	 * Pasar datos a la vista. En este caso una lista de usuarios. Esto nos sirve por si queremos utilizar un método para varias
	 * vistas.
	 * @return los usuarios, que se pasarán a la vista.
	 */
	@ModelAttribute("usuarios") // tenemos que añadir el nombre con el que queremos guardar en la vista por parametro de la anotación
	public List<Usuario> poblarUsuarios() {
		List<Usuario> usuarios = Arrays.asList(new Usuario("Andres", "Perez", "andres.perez@gmail.com"),
				new Usuario("John", "Doe", "john.doe@gmail.com"),
				new Usuario("Francisco", "Franco", "francisco.franco@gmail.com"),
				new Usuario("Joe", "Biden", "Joe.biden@gmail.com"));
		return usuarios;
	}
}
