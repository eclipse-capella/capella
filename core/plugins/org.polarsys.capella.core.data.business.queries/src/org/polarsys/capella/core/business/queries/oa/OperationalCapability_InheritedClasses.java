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
package org.polarsys.capella.core.business.queries.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class OperationalCapability_InheritedClasses implements IBusinessQuery {

  /**
   * <p>
   * Gets all the capabilities except those that are in the inheritance
   * hierarchy of the current Capability and those that are already
   * extended/included by the current capability from the system engineering
   * package.
   * </p>
   * <p>
   * Refer MQRY_Capability_Inherited_11
   * </p>
   * 
   * @param sysEng_p
   *            the system engineering
   * @param currentCapabilityUseCase_p
   *            the current capability
   * @param isFilterRequired
   *            flag for checking with current capability
   * @return list of {@link CapabilityUseCase}
   */
  private List<CapellaElement> getRule_MQRY_CapabiiltyUseCase_Inherited_11(SystemEngineering sysEng_p, OperationalCapability currentCapabilityUseCase_p, boolean isFilterRequired) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (currentCapabilityUseCase_p != null) {
      BlockArchitecture rootBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(currentCapabilityUseCase_p);
      AbstractCapabilityPkg ownedAbstractCapabilityPkg = rootBlockArchitecture.getOwnedAbstractCapabilityPkg();
      List<AbstractCapability> absCap = AbstractCapabilityPkgExt.getAllCapabilities(ownedAbstractCapabilityPkg);
      for (AbstractCapability capabilityUseCase : absCap) {
        if (currentCapabilityUseCase_p.equals(capabilityUseCase)) {
          continue;
        }
        if (!AbstractCapabilityExt.isRelated(currentCapabilityUseCase_p, capabilityUseCase)
            && !AbstractCapabilityExt.isRelated(capabilityUseCase, currentCapabilityUseCase_p)) {
          availableElements.add(capabilityUseCase);
        }else {
          availableElements.add(capabilityUseCase);
        }
      }
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the capabilities except those that are in the inheritance
   * hierarchy of the current Capability from teh system engineering package.
   * </p>
   * <p>
   * Refer MQRY_Capability_Inherited_1
   * </p>
   * 
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return availableElements;
    }

    if (element_p instanceof OperationalCapability) {
      OperationalCapability currentCapabilityUseCase = (OperationalCapability) element_p;
      availableElements.addAll(getRule_MQRY_CapabiiltyUseCase_Inherited_11(systemEngineering, currentCapabilityUseCase, true));
    }  
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  /**
   * <p>
   * Gets all the capabilities that extend the current Capability.
   * </p>
   * <p>
   * Refer MQRY_Capability_Inherited_1
   * </p>
   * 
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return currentElements;
    }

    if (element_p instanceof OperationalCapability) {
      OperationalCapability currentCapabilityUseCase = (OperationalCapability) element_p;
      currentElements.addAll(currentCapabilityUseCase.getSuper());
      currentElements = ListExt.removeDuplicates(currentElements);
      currentElements.remove(currentCapabilityUseCase);
    }

    return currentElements;
  }

  public EClass getEClass() {
    return OaPackage.Literals.OPERATIONAL_CAPABILITY;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS);
  }
  
}
