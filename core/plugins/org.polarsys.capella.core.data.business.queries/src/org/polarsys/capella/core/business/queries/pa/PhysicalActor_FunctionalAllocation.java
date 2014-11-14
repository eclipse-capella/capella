/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.pa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.cs.Component_FunctionalAllocation;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class PhysicalActor_FunctionalAllocation extends Component_FunctionalAllocation implements IBusinessQuery {

  /**
   * <p>
   * Gets available FunctionalAllocation for the physical actor.
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

    if (null == systemEngineering) {
      return availableElements;
    }
    if (element instanceof PhysicalActor) {
      availableElements.addAll(getRule_MQRY_Component_FunctionalAllocation_11(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the owned FunctionalAllocation of the physical actor.
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      return currentElements;
    }

    if (element_p instanceof PhysicalActor) {
      PhysicalActor actor = (PhysicalActor) element_p;
      EList<ComponentFunctionalAllocation> ownedFunctionalAllocation = actor.getOwnedFunctionalAllocation();
      for (ComponentFunctionalAllocation componentFunctionalAllocation : ownedFunctionalAllocation) {
        TraceableElement targetElement = componentFunctionalAllocation.getTargetElement();
        if (targetElement instanceof AbstractFunction) {
          currentElements.add((CapellaElement) targetElement);
        }

      }
    }

    return currentElements;
  }

  public EClass getEClass() {
    return PaPackage.Literals.PHYSICAL_ACTOR;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
  }
}
