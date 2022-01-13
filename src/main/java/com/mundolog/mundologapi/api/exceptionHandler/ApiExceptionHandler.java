package com.mundolog.mundologapi.api.exceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mundolog.mundologapi.domain.exception.GenericException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<CamposErro> camposErro = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			camposErro.add(new CamposErro(nome, mensagem));
		}

		Erro erro = new Erro(status.value(),
				"Um ou mais campos estão inválidos. Faça o preenchimentoa correto e tente novamente.", new Date(),
				camposErro);
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		Erro erro = new Erro(status.value(), ex.getMessage(), new Date());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
}
