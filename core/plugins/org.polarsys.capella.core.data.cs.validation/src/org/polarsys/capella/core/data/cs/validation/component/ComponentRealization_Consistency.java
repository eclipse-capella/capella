/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that ComponentRealizations have compatible source/target
 */
public class ComponentRealization_Consistency extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    HashMap<EClass, EClass> validTarget = new HashMap<>();
    validTarget.put(CtxPackage.Literals.SYSTEM_COMPONENT, OaPackage.Literals.ENTITY);
    validTarget.put(LaPackage.Literals.LOGICAL_COMPONENT, CtxPackage.Literals.SYSTEM_COMPONENT);
    validTarget.put(PaPackage.Literals.PHYSICAL_COMPONENT, LaPackage.Literals.LOGICAL_COMPONENT);

    HashMap<EClass, EClass> validSource = new HashMap<>();
    validSource.put(OaPackage.Literals.ENTITY, CtxPackage.Literals.SYSTEM_COMPONENT);
    validSource.put(CtxPackage.Literals.SYSTEM_COMPONENT, LaPackage.Literals.LOGICAL_COMPONENT);
    validSource.put(LaPackage.Literals.LOGICAL_COMPONENT, PaPackage.Literals.PHYSICAL_COMPONENT);

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        List<IStatus> statuses = new ArrayList<IStatus>();
        Component actor = (Component) eObj;
        if (!BlockArchitectureExt.isRootComponent(actor)) {
          EList<AbstractTrace> traces = actor.getOutgoingTraces();
          // if no realization found, no consistency check needed
          if (traces.size() < 1) {
            return ctx.createSuccessStatus();
          }
          Iterator<AbstractTrace> iterator = traces.iterator();
          while (iterator.hasNext()) {
            AbstractTrace next = iterator.next();
            if (next instanceof ComponentRealization) {
              TraceableElement source = next.getSourceElement();
              TraceableElement target = next.getTargetElement();
              // if target is not actor create failure status message
              String actorInfo = CapellaElementExt.getValidationRuleMessagePrefix(actor);
              if (null == source) {
                statuses.add(ctx.createFailureStatus(actorInfo // $NON-NLS-1$
                    + " contain realization with inconsistent Source (it should be not empty)")); //$NON-NLS-1$
                continue;
              }
              if (null == target) {
                statuses.add(ctx.createFailureStatus(actorInfo // $NON-NLS-1$
                    + " contain realization with inconsistent Target (it should be not empty)")); //$NON-NLS-1$
                continue;
              }
              if (validTarget.containsKey(source.eClass()) && !validTarget.get(source.eClass()).isInstance(target)) {
                statuses.add(ctx.createFailureStatus(actorInfo // $NON-NLS-1$
                    + " contain realization with inconsistent Target (it should be instance of " //$NON-NLS-1$
                    + validTarget.get(source.eClass()).getName() + ")"));
              }

              if (validSource.containsKey(target.eClass()) && !validSource.get(target.eClass()).isInstance(source)) {
                statuses.add(ctx.createFailureStatus(actorInfo // $NON-NLS-1$
                    + " contain realization with inconsistent Source (it should be instance of " //$NON-NLS-1$
                    + validSource.get(target.eClass()).getName() + ")"));
              }
            }
          }
        }
        // return list of failure status message if any
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();

  }
}
