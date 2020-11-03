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
package org.polarsys.capella.core.data.common.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;

/**
 * The SystemComponentCapabilityRealizationInvolvement section.
 */
public class CapabilityRealizationInvolvementSection extends CapellaElementSection {
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT.isInstance(eObjectToTest);
  }
}
