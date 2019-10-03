package com.example.helloworld.errorhandling;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A generic exception mapper for all the exceptions which are not AppException
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionMapper.class);
 
    private final static int GENERIC_APP_ERROR_CODE = 5001;

	@Override
	public Response toResponse(Throwable ex) {
		LOGGER.error("Build response: " + ex.getMessage(),ex);

		ErrorMessage errorMessage = new ErrorMessage();		
		setHttpStatus(ex, errorMessage);
		errorMessage.setCode(GENERIC_APP_ERROR_CODE);
		errorMessage.setMessage(ex.getMessage());
		StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        //ToDo: Only show developer message in debug mode
		//errorMessage.setDeveloperMessage(errorStackTrace.toString());
		errorMessage.setLink("Some link to get administrator contact info");
				
		return Response.status(errorMessage.getStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();	
	}

	private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
		if(ex instanceof WebApplicationException ) {
			errorMessage.setStatus(((WebApplicationException)ex).getResponse().getStatus());
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
	}
}