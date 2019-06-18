package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getServiceInterpreterCache;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;

public class CacheServiceInterpreter extends ServiceInterpreter {

  /**
   * Cache interpreter Prefix
   */

  public static final String PREFIX = "cacheService:";

  @Override
  public IInterpreter createInterpreter() {

    return new CacheServiceInterpreter();
  }

  @Override
  public String getPrefix() {
    return PREFIX;
  }

  @Override
  public Object evaluate(EObject target, String expression) throws EvaluationException {
    if (target != null && expression != null && expression.startsWith(PREFIX)) {
      String callExpression = expression.substring(PREFIX.length()).trim();
      String serviceCallExpression = ServiceInterpreter.PREFIX + callExpression;
      EObject receiver = evaluateExpressionVariable(target, callExpression);

      BiFunction<EObject, String, Object> function = (t, u) -> {
        try {
          return super.evaluate(t, u);
        } catch (EvaluationException e) {
          e.printStackTrace();
        }
        return null;
      };
      return getServiceInterpreterCache(function, receiver, serviceCallExpression);

    }
    return null;
  }

  public EObject evaluateExpressionVariable(EObject target, String expression) throws EvaluationException {

    Optional<String> receiverVariableName = getReceiverVariableName(expression);
    EObject receiver = target;

    if (receiverVariableName.isPresent()) {

      Object objectReceiver = evaluateVariable(target, receiverVariableName.get().trim());
      if (objectReceiver instanceof EObject) {
        receiver = (EObject) objectReceiver;
      } else {
        throw new EvaluationException(MessageFormat.format(Messages.ServiceInterpreter_invalidReceiver, expression,
            objectReceiver != null ? objectReceiver.getClass().getName() : "null")); //$NON-NLS-1$
      }
    }
    return receiver;
  }

}
