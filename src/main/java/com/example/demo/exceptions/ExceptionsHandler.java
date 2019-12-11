package com.example.demo.exceptions;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ErrorCode;

@RestControllerAdvice
public class ExceptionsHandler {

	private static final Logger LOG = Logger.getLogger(ExceptionsHandler.class.getName());

	private void logError(ErrorCode ec, Exception e) {
		LOG.severe(ec.getCodigo());
		LOG.severe(ec.getMensaje());
		LOG.severe(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorCode handleException(Exception e) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo("901");
		ec.setMensaje("Error no encontrado");
		logError(ec, e);
		return ec;
	}

	@ExceptionHandler(EditadoHandlerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode handleAccepted(EditadoHandlerException editado) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo("no-editado");
		ec.setMensaje(editado.getMessage());
		logError(ec, editado);
		return ec;
	}

	@ExceptionHandler(NoEliminadoHandleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorCode handleNoEliminado(NoEliminadoHandleException eliminado) {
		ErrorCode ec = new ErrorCode();
		ec.setCodigo("no-eliminado");
		ec.setMensaje(eliminado.getMessage());
		logError(ec, eliminado);
		return ec;
	}

}
