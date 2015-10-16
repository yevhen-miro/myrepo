package de.hydro.gv.orgpm.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import de.hydro.gv.orgpm.dao.ImplByConsole;
import de.hydro.gv.orgpm.dao.LogService;

@Interceptor
@Logged
public class PerformanceInterceptor {

	@Inject
	@ImplByConsole
	private LogService logService;

	@AroundInvoke
	// intercepter invocation
	public Object intercept( InvocationContext context ) throws Exception { // intercepter
																			// definition
		long startTime = System.currentTimeMillis(); // before
		Object returnValue = context.proceed();
		long endTime = System.currentTimeMillis();// after
		this.logService.logMessage( "execution of " + context.getMethod().getName() + " took " + ( endTime - startTime )
				+ " ms." );
		return returnValue;
	}

}
