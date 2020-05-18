/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;


public class AbstractCapabilityExtend_Extended implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture((AbstractCapabilityExtend) element);
    for (Iterator<EObject> it = arch.eAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof AbstractCapability) {
        if (next != ((AbstractCapabilityExtend)element).getExtension()) { // can't extend itself..
          availableElements.add(next);
        }
      }
    }
    return availableElements;
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    return Collections.<EObject>singletonList(((AbstractCapabilityExtend) element).getExtended());
  }

}
