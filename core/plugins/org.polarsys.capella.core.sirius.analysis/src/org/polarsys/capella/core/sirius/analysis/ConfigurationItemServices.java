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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * Provides services for all interfaces diagram.
 */
public class ConfigurationItemServices {

  private static ConfigurationItemServices service = null;

  public static ConfigurationItemServices getService() {
    if (service == null) {
      service = new ConfigurationItemServices();
    }
    return service;
  }

  /**
   * get all the available PhyscialComponent, PhysicalLinks, and PhysicalPorts from PhyscialArchitecture
   * @param context_p : is a CongurationItem
   * @return list of Eobject
   */
  public List<EObject> getAllRealizablePhysicalArtefacts(CapellaElement context_p) {
    return QueryInterpretor.executeQuery("GetAllRealizablePhysicalArtefacts__Lib", context_p, new QueryContext());//$NON-NLS-1$
  }  

  public boolean isParentContainedInDiagram(PhysicalPort context_p, List<PhysicalComponent> allPhysicalComponentInDiagram) {

    EObject container = context_p.eContainer();
    if ((null != container) && allPhysicalComponentInDiagram.contains(container)) {
      return true;
    }

    return false;
  }

  /**
   * @param context_p
   * @param aOperation_p
   * @param diagram_p
   */
  public ConfigurationItem createConfigurationItem(EObject container_p, ConfigurationItemKind kind_p) {
    ConfigurationItem item = null;
    EObject container = null;

    if ((container_p instanceof ConfigurationItem)) {
      container = container_p;
    } else if ((container_p instanceof ConfigurationItemPkg)) {
      container = container_p;
    } else if (container_p instanceof Part) {
      EObject componentType = CsServices.getService().getComponentType((Part) container_p);
      if (componentType instanceof ConfigurationItem) {
        container = componentType;
      }
    }

    if (null == container) {
      EPBSArchitecture architecture = ((EPBSArchitecture) BlockArchitectureExt.getRootBlockArchitecture(container_p));
      if (architecture.getOwnedConfigurationItem() != null) {
        container = architecture.getOwnedConfigurationItem();
      } else {
        container = architecture;
      }
    }

    if (container != null) {
      item = EpbsFactory.eINSTANCE.createConfigurationItem();
      item.setKind(kind_p);

      if (item != null) {
        if (container instanceof ConfigurationItem) {
          ((ConfigurationItem) container).getOwnedConfigurationItems().add(item);
        } else if (container instanceof ConfigurationItemPkg) {
          ((ConfigurationItemPkg) container).getOwnedConfigurationItems().add(item);
        } else if (container instanceof EPBSArchitecture) {
          ((EPBSArchitecture) container).setOwnedConfigurationItem(item);
        }
        CapellaServices.getService().creationService(item);
      }
    }
    return item;
  }

  public ConfigurationItem createSystemCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.SYSTEM_CI);
    return ci;
  }

  public ConfigurationItem createPrimeItemCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.PRIME_ITEM_CI);
    return ci;
  }

  public ConfigurationItem createNDICI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.NDICI);
    return ci;
  }

  public ConfigurationItem createInterfaceCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.INTERFACE_CI);
    return ci;
  }

  public ConfigurationItem createHWCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.HWCI);
    return ci;
  }

  public ConfigurationItem createCSCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.CSCI);
    return ci;
  }

  public ConfigurationItem createCOTSCI(EObject container_p) {
    ConfigurationItem ci = createConfigurationItem(container_p, ConfigurationItemKind.COTSCI);
    return ci;
  }
}
