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
package org.polarsys.capella.core.business.queries.epbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ConfigurationItemExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class ConfigurationItem_ImplementedPC implements IBusinessQuery {

  /**
   *
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(ConfigurationItem configurationItem_p, EClass cls_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    EPBSArchitecture arch = (EPBSArchitecture) SystemEngineeringExt.getRootBlockArchitecture(configurationItem_p);
    if (arch != null) {
      for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(block, false);
        while (allContents.hasNext()) {
          EObject object = (EObject) allContents.next();
          if (object.eClass().equals(cls_p)) {
            if (!configurationItem_p.getAllocatedPhysicalArtifacts().contains(object)) {
              availableElements.add((CapellaElement) object);
            }
          }
        }
      }
    }

    return availableElements;
  }

  /**
   * 
   */
  private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPLink_11(ConfigurationItem configurationItem_p) {
    return getElementsFromBlockArchitecture(configurationItem_p, CsPackage.Literals.PHYSICAL_LINK);
  }

  /**
   * 
   */
  private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPPort_11(ConfigurationItem configurationItem_p) {
    return getElementsFromBlockArchitecture(configurationItem_p, CsPackage.Literals.PHYSICAL_PORT);
  }

  /**
   * Gets all the PhysicalComponents contained in the Physical Architecture Layer. Except those that are already implemented by a ConfigurationItem (unique
   * implementor)
   */
  private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPC_11(ConfigurationItem currentConfigurationItem_p,
      SystemEngineering systemEngineering_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    PhysicalArchitecturePkg physicalArchPkg = SystemEngineeringExt.getOwnedPhysicalArchitecturePkg(systemEngineering_p);
    if (null != physicalArchPkg) {
      for (PhysicalArchitecture physicalArch : physicalArchPkg.getOwnedPhysicalArchitectures()) {
        for (PhysicalComponent pc : PhysicalArchitectureExt.getAllPhysicalComponents(physicalArch)) {
          if (ConfigurationItemExt.hasImplementedPC(currentConfigurationItem_p, pc)) {
            continue;
          }
          availableElements.add(pc);
        }
      }
    }
    PhysicalArchitecture physicalArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering_p);
    if (null != physicalArch) {
      for (PhysicalComponent pc : PhysicalArchitectureExt.getAllPhysicalComponents(physicalArch)) {
        if (ConfigurationItemExt.hasImplementedPC(currentConfigurationItem_p, pc)) {
          continue;
        }
        availableElements.add(pc);
      }
    }
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (null == systemEngineering) {
      return availableElements;
    }

    if (element_p instanceof ConfigurationItem) {
      ConfigurationItem currentCI = (ConfigurationItem) element_p;
      availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPC_11(currentCI, systemEngineering));
      availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPLink_11(currentCI));
      availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPPort_11(currentCI));
    }

    availableElements = ListExt.removeDuplicates(availableElements);

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof ConfigurationItem) {
      currentElements.addAll(((ConfigurationItem) element_p).getAllocatedPhysicalArtifacts());
    }

    return currentElements;
  }

  public EClass getEClass() {
    return EpbsPackage.Literals.CONFIGURATION_ITEM;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
  }
}
