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
package org.polarsys.capella.core.data.capellamodeller.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.helpers.query.IRootQueries;
import org.polarsys.capella.core.model.helpers.query.impl.RootQueries;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensure the Eclipse Project Name is equal to Capella Project Name
 */
public class ProjectNamingDifferenceConflict extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // filter SystemEngineering [why SystemEngineering : because mostly the validation rool is applied on systemEngineering]
      // and one has an option in preference to hide the capella Project in ProjectExplorer
      if (eObj instanceof SystemEngineering) {
        SystemEngineering sysEng = (SystemEngineering) eObj;
        EObject eContainer = sysEng;
        // retrieve Project from SystemEngineering 
        IRootQueries root = new RootQueries();
        Project pro= root.getProject(sysEng);
         
        if (null != pro) {
          Resource eResource = eContainer.eResource();
          org.eclipse.emf.common.util.URI uri = eResource.getURI();
          String[] segments = uri.segments();
          if (uri.isPlatformResource() && CapellaResourceHelper.isCapellaResource(eResource)) {
            String logicalProjectName = segments[1];
            String capellaProjectName = pro.getName();
            if (logicalProjectName != null && !(capellaProjectName.equalsIgnoreCase(logicalProjectName))) {
              return createFailureStatus(ctx, new Object[] {logicalProjectName,capellaProjectName}); 
            }
          }
        }
      }
    }
    
    // No conflict found
    return ctx.createSuccessStatus();
  }

}
