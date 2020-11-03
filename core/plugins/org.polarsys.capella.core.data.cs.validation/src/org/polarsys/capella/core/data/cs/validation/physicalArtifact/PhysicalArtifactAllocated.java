/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.physicalArtifact;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class PhysicalArtifactAllocated extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if ((eObj instanceof AbstractPhysicalArtifact) && isInPhysicalLayer(eObj)) {
      AbstractPhysicalArtifact artifact = (AbstractPhysicalArtifact) eObj;
      List<EObject> crossReferenceList =
          EObjectExt.getReferencers(artifact, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT);
      if (!crossReferenceList.isEmpty()) {
        return ctx.createSuccessStatus();
      }
      String name = null;
      String className = "(" + artifact.eClass().getName() + ")";
      if (artifact instanceof PhysicalLink) {
        name = ((PhysicalLink) artifact).getName();
      } else if (artifact instanceof PhysicalComponent) {
        name = ((PhysicalComponent) artifact).getName();
        className = NamingHelper.getTitleLabel(artifact);
      } else if (artifact instanceof PhysicalPort) {
        name = ((PhysicalPort) artifact).getName();
      }
      return ctx.createFailureStatus(name, className);
    }
    return null;
  }

  /**
   * @param eObj
   * @return
   */
  private boolean isInPhysicalLayer(EObject eObj) {

    EObject container = eObj.eContainer();
    boolean hasContainer = null != container;
    boolean containerIsPhysicalComponent = container instanceof PhysicalComponent;

    return hasContainer && containerIsPhysicalComponent;
  }

}
