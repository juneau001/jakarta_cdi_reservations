
package com.mycompany.reservations.interceptor;

import com.mycompany.reservations.qualifier.Log;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.io.Serializable;

/**
 *
 * @author Juneau
 */
@Interceptor
@Log
public class LoggingInterceptor implements Serializable {

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("Perform logging...");
        return ctx.proceed();
    }
}

