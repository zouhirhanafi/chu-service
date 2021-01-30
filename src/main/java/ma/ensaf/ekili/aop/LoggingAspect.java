package ma.ensaf.ekili.aop;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//	@Pointcut("within(@ma.ensaf.ekili.aop.LogExecutionTime *)")
	@Pointcut("@annotation(LogExecutionTime)")
	public void annotation() {}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repositoryClassMethods() {}

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void serviceClassMethods() {}

	
	private Logger getLogger(ProceedingJoinPoint pjp) {
//		return LoggerFactory.getLogger(this.getClass());
		return LoggerFactory.getLogger(pjp.getTarget().getClass());
	}
	
	private void waitting(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	@Around("serviceClassMethods() || annotation()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		Logger log = getLogger(pjp);
		long currentTimeMillis = System.currentTimeMillis();
		String signature = pjp.getSignature().getName();
		log.trace("Appel de la méthode {} ...", signature);
        if (log.isDebugEnabled()) {
    		log.debug("avec les argument[s] = {}", Arrays.toString(pjp.getArgs()));
        }
		// exécution de la méthode
		Object retval = null;
		Throwable exception = null;
		try {
			//@Before
			retval = pjp.proceed();
			//@AfterReturning
		} catch (RuntimeException e) {
			log.warn("Erreur execution de la methode {} : {}", signature, e.getMessage());
			exception = e;
		} catch (Throwable e) {
			// @AfterThrowing
			log.error("Erreur execution de la methode {} : {}", signature, e.getMessage());
			exception = e;
		} finally {
			// @After finally
		}
		long time = System.currentTimeMillis() - currentTimeMillis;
		
		log.trace("Fin de l'éxection de la méthode {} dans {} ms", signature, time);
		log.debug("Résultat : {}", retval);
		if (time > 5000) {
			log.error("L'execution de la méthode {} a pris plus de 5s ({})", signature, time);
		} else if (time > 2000) {
			log.warn("L'execution de la méthode {} a pris entre 2 de 5s ({})", signature, time);
		}
		if (exception != null) {
			throw exception;
		}
		return retval;
    }
	
}
