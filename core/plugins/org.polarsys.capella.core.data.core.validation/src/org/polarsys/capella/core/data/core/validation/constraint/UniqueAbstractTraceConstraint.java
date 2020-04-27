/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class UniqueAbstractTraceConstraint extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    AbstractTrace trace = (AbstractTrace) ctx.getTarget();
    Collection<AbstractTrace> equivalentTraces = new ArrayList<AbstractTrace>();
    ECrossReferenceAdapter adapter = ECrossReferenceAdapter.getCrossReferenceAdapter(trace);
    if (adapter != null && trace.getSourceElement() != null && trace.getTargetElement() != null){
      for (EStructuralFeature.Setting setting : adapter.getInverseReferences(trace.getSourceElement())){
        if (setting.getEObject().eClass() == trace.eClass() && setting.getEStructuralFeature() == ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT){
          AbstractTrace otherTrace = (AbstractTrace) setting.getEObject();
          if (otherTrace.getTargetElement() == trace.getTargetElement()){
            equivalentTraces.add(otherTrace);
          }
        }
      }
    }
    
    if (equivalentTraces.size() > 1){
      ctx.skipCurrentConstraintForAll(equivalentTraces);
      Object sparam = trace.getSourceElement() instanceof AbstractNamedElement ? ((AbstractNamedElement) trace.getSourceElement()).getName() : trace.getSourceElement();
      Object tparam = trace.getTargetElement() instanceof AbstractNamedElement ? ((AbstractNamedElement) trace.getTargetElement()).getName() : trace.getTargetElement();
      return ConstraintStatus.createStatus(ctx, equivalentTraces, "Multiple equivalent traces of type ''{0}'' between ''{1}'' and ''{2}''",     //$NON-NLS-1$
        new Object[] {trace.eClass().getName(), sparam, tparam });
    }
    return ctx.createSuccessStatus();
  }
}
