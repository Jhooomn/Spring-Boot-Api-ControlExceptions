package com.example.demo.exceptions;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ErrorCode;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

@RestControllerAdvice
public class ExceptionsHandler {

	private static final Logger LOG = Logger.getLogger(ExceptionsHandler.class.getName());

	private void logError(ErrorCode ec, Exception e) {
		LOG.severe(ec.getCodigo());
		LOG.severe(ec.getMensaje());
		LOG.severe(e.getMessage());
	}

	public String getId() {
		return UUID.randomUUID().toString();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorCode handleException(Exception e) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje("Error no encontrado");
		logError(ec, e);
		return ec;
	}

	@ExceptionHandler(ValorException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode valorException(ValorException e) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		return ec;
	}

	@ExceptionHandler(TotalException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode totalException(TotalException e) {
		ErrorCode ec = new ErrorCode();

		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());

		logError(ec, e);

		return ec;
	}

	@ExceptionHandler(PrecioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode precioException(PrecioException editado) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(editado.getMessage());
		logError(ec, editado);

		return ec;
	}

	@ExceptionHandler(NombreMayusculaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode nombreMayuscula(NombreMayusculaException nombre) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(nombre.getMessage());
		logError(ec, nombre);
		return ec;
	}

	@ExceptionHandler(NombreCaracterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode nombreCaracter(NombreCaracterException nombre) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(nombre.getMessage());
		logError(ec, nombre);
		return ec;
	}

	@ExceptionHandler(EditadoHandlerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode handleAccepted(EditadoHandlerException editado) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(editado.getMessage());
		logError(ec, editado);
		return ec;
	}

	@ExceptionHandler(IdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode idException(IdException id) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(id.getMessage());
		logError(ec, id);
		return ec;
	}

	@ExceptionHandler(NoEliminadoHandleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode handleNoEliminado(NoEliminadoHandleException eliminado) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(eliminado.getMessage());
		logError(ec, eliminado);
		return ec;
	}

}
