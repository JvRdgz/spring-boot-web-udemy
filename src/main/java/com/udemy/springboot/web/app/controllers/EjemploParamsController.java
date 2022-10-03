package com.udemy.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {

	@GetMapping("/")
	public String index() {
		return "params/index";
	}

	/**
	 * "text" es el nombre del queryparam que se va a enviar, junto con su valor,
	 * como parametro en la URL.
	 * 
	 * @param texto
	 * @param model
	 * @return
	 */
	@GetMapping("/string")
	public String param(@RequestParam(name = "text", required = false, defaultValue = "algún valor...") String texto,
			Model model) {
		model.addAttribute("resultado", "El texto enviado es: " + texto);
		return "params/ver";
	}

	/**
	 * El metodo se puede llamar igual que el anterior. Permite pasar más parámetros
	 * de distinto tipo (en este caso String e Integer)
	 * 
	 * @param saludo
	 * @param numero
	 * @param model
	 * @return
	 */
	@GetMapping("/mix-params")
	public String param(@RequestParam String saludo, @RequestParam Integer numero, Model model) {
		model.addAttribute("resultado", "El saludo enviado es: '" + saludo + "' y el numero es '" + numero + "'");
		return "params/ver";
	}

	/**
	 * Este método no es la manera más moderna de hacerlo, hace lo mismo pero es
	 * mejor utilizar anotaciones que permiten que el código quede más limpio. Además puedes hacer lo mismo
	 * con menos código, como en los ejemplos anteriores
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request, Model model) {
		String saludo = request.getParameter("saludo");
		Integer numero = null;
		try {
			numero = Integer.parseInt(request.getParameter("numero"));
		} catch (NumberFormatException e) {
			numero = 0;
		}
		model.addAttribute("resultado", "El saludo enviado es: '" + saludo + "' y el numero es '" + numero + "'");
		return "params/ver";
	}
}
