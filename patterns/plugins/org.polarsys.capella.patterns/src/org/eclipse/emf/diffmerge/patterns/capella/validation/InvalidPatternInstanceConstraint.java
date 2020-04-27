/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.validation;

import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonPatternInstance;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.InstanceCounterpart;
import org.eclipse.emf.diffmerge.patterns.templates.gen.templatepatterns.TemplatePatternData;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;


/**
 * A model constraint that checks whether a given pattern instance only refers to
 * existing model elements.
 */
public class InvalidPatternInstanceConstraint extends AbstractModelConstraint {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject target = ctx.getTarget();
    if (target instanceof CommonPatternInstance)
    {
      CommonPatternInstance instance = (CommonPatternInstance) target;
      
      if (instance.getPatternData() instanceof TemplatePatternData){
        final TemplatePatternData data = (TemplatePatternData) instance.getPatternData();
        EMap<String, InstanceCounterpart> instanceIds = data.getInstanceIds();
        if (instanceIds.entrySet().isEmpty())
          return ctx.createFailureStatus();
        for (Entry<String, InstanceCounterpart> entry : instanceIds) {
          EObject eObject = IdManager.getInstance().getEObject(entry.getKey(), new IScope() {
            @Override
            public List<Resource> getResources() {
              return TransactionHelper.getEditingDomain(data).getResourceSet().getResources();
            }
          });
          if (eObject == null)
            return ctx.createFailureStatus();
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
