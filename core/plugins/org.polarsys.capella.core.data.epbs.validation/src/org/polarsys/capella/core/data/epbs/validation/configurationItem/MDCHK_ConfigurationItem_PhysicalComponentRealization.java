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
package org.polarsys.capella.core.data.epbs.validation.configurationItem;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Ensures that ant Physical Component Realization targeting a Configuration Item has a Physical artifacts as a source.
 */
public class MDCHK_ConfigurationItem_PhysicalComponentRealization extends AbstractValidationRule {
  /**
   * 
   */
  private static final String NULL = "null"; //$NON-NLS-1$

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ConfigurationItem) {
        ConfigurationItem confItem = (ConfigurationItem) eObj;
        EList<PhysicalArtifactRealization> ownedPhysicalComponentRealisations = confItem.getOwnedPhysicalArtifactRealizations();
        Iterator<PhysicalArtifactRealization> iterator = ownedPhysicalComponentRealisations.iterator();
        while (iterator.hasNext()) {
          PhysicalArtifactRealization next = iterator.next();
          TraceableElement allocatedComponent = next.getTargetElement();
          String allocatedComponentName = NULL;
          String allocatedComponentEclass = NULL;
          if (allocatedComponent instanceof AbstractNamedElement) {
            allocatedComponentName = ((AbstractNamedElement) allocatedComponent).getName();
            allocatedComponentEclass = allocatedComponent.eClass().getName();
          }
          if (null == allocatedComponent || !((allocatedComponent instanceof PhysicalComponent) || (allocatedComponent instanceof PhysicalLink || (allocatedComponent instanceof PhysicalPort))   )) {
            return ctx.createFailureStatus(new Object[] { confItem.getName(), allocatedComponentName, allocatedComponentEclass });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
