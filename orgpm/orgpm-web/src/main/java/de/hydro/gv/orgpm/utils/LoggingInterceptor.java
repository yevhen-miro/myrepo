package de.hydro.gv.orgpm.utils;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

import de.hydro.gv.orgpm.util.Logged;

@Interceptor
@Logged
public class LoggingInterceptor implements Serializable {

	private static final long serialVersionUID = -4252342851751510125L;

	@AroundInvoke
	public Object log( InvocationContext context ) throws Exception {
		final Logger logger = Logger.getLogger( context.getTarget().getClass() );
		logger.infov( "Executing method {0}", context.getMethod().toString() );
		return context.proceed();
	}

}
