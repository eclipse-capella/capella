/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.common.validation.project;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.kitalpha.emde.model.ElementExtension;

public class ProjectReferencedLibrariesRule extends AbstractValidationRule {

  /**
   * The constraint ID
   */
  public static final String ID = "org.polarsys.capella.core.data.common.validation.I_03";
  
  @Override
  public IStatus validate(IValidationContext validationContext) {

    EObject target = validationContext.getTarget();

    Project project = ProjectExt.getProject(target);
    if (project != null) {
      for (ElementExtension extension : project.getOwnedExtensions()) {
        if (extension instanceof ModelInformation) {
          ModelInformation modelInformation = (ModelInformation) extension;
          for (LibraryReference reference : modelInformation.getOwnedReferences()) {
            if ((reference.getLibrary() != null) && reference.getLibrary().eIsProxy()) {
              return validationContext.createFailureStatus(((InternalEObject) reference.getLibrary()).eProxyURI());
            }
          }
        }
      }
    }
    return Status.OK_STATUS;
  }
}
