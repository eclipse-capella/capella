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
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that interfaces requires by standard port is used by its owner Component.
 */
public class MDCHK_Component_requiredItf_2 extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent lcomp = (LogicalComponent) eObj;

        Iterator<Interface> itRequired = ComponentExt.removeDuplicate(lcomp.getRequiredInterfaces()).iterator();

        // check that required interfaces are delegated
        while (itRequired.hasNext()) {
          Interface itf = itRequired.next();
          statuses.add(ComponentExt.isRequiredorUsedItfDelegated(ctx, lcomp, itf));
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
