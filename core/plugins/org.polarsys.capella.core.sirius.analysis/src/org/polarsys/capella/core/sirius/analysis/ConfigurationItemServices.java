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
package org.polarsys.capella.core.sirius.analysis;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

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
   * 
   * @param context
   *          : is a CongurationItem
   * @return list of Eobject
   */
  public List<EObject> getAllRealizablePhysicalArtefacts(CapellaElement context) {
    return QueryInterpretor.executeQuery("GetAllRealizablePhysicalArtefacts__Lib", context, new QueryContext());//$NON-NLS-1$
  }

  public boolean isParentContainedInDiagram(PhysicalPort context,
      List<PhysicalComponent> allPhysicalComponentInDiagram) {

    EObject container = context.eContainer();
    if ((null != container) && allPhysicalComponentInDiagram.contains(container)) {
      return true;
    }

    return false;
  }

  /**
   * @param context
   * @param aOperation
   * @param diagram
   */
  public ConfigurationItem createConfigurationItem(EObject container, ConfigurationItemKind kind) {
    ConfigurationItem item = null;
    EObject owner = null;

    if ((container instanceof ConfigurationItem)) {
      owner = container;
    } else if ((container instanceof ConfigurationItemPkg)) {
      owner = container;
    } else if (container instanceof Part) {
      EObject componentType = CsServices.getService().getComponentType((Part) container);
      if (componentType instanceof ConfigurationItem) {
        owner = componentType;
      }
    }

    if (null == owner) {
      EPBSArchitecture architecture = ((EPBSArchitecture) BlockArchitectureExt.getRootBlockArchitecture(container));
      if (architecture.getSystem() != null) {
        owner = architecture.getSystem();
      } else {
        owner = architecture;
      }
    }

    if (owner != null) {
      item = EpbsFactory.eINSTANCE.createConfigurationItem();
      item.setKind(kind);

      if (item != null) {
        if (owner instanceof ConfigurationItem) {
          ((ConfigurationItem) owner).getOwnedConfigurationItems().add(item);
        } else if (owner instanceof ConfigurationItemPkg) {
          ((ConfigurationItemPkg) owner).getOwnedConfigurationItems().add(item);
        } else if (owner instanceof EPBSArchitecture) {
          ((EPBSArchitecture) owner).getOwnedConfigurationItemPkg().getOwnedConfigurationItems().add(item);
        }
        CapellaServices.getService().creationService(item);
      }
    }
    return item;
  }

  public ConfigurationItem createSystemCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.SYSTEM_CI);
    return ci;
  }

  public ConfigurationItem createPrimeItemCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.PRIME_ITEM_CI);
    return ci;
  }

  public ConfigurationItem createNDICI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.NDICI);
    return ci;
  }

  public ConfigurationItem createInterfaceCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.INTERFACE_CI);
    return ci;
  }

  public ConfigurationItem createHWCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.HWCI);
    return ci;
  }

  public ConfigurationItem createCSCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.CSCI);
    return ci;
  }

  public ConfigurationItem createCOTSCI(EObject container) {
    ConfigurationItem ci = createConfigurationItem(container, ConfigurationItemKind.COTSCI);
    return ci;
  }
}
