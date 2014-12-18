package org.polarsys.capella.core.data.la.validation.logicalComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that interfaces provided by standard ports are implemented by their owner Components.
 */
public class MDCHK_Component_providedItf_1 extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent lcomp = (LogicalComponent) eObj;

        Iterator<Interface> itProvided = ComponentExt.removeDuplicate(lcomp.getProvidedInterfaces()).iterator();

        // check that provided interfaces are delegated
        while (itProvided.hasNext()) {
          Interface itf = itProvided.next();
          statuses.add(ComponentExt.isProvidedorImplementedItfDelegated(ctx, lcomp, itf));
        }

        // return multistatus message
        if (!statuses.isEmpty()) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
