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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;

/**
 * SystemEngineering helpers
 * 
 */
public class AbstractCapabilityPkgExt {

  public static Collection<AbstractCapability> getOwnedCapabilities(AbstractCapabilityPkg pkg_p) {
    Collection<AbstractCapability> result = new ArrayList<AbstractCapability>();

    if (pkg_p instanceof OperationalCapabilityPkg) {
      result.addAll(((OperationalCapabilityPkg) pkg_p).getOwnedOperationalCapabilities());

    } else if (pkg_p instanceof CapabilityPkg) {
      result.addAll(((CapabilityPkg) pkg_p).getOwnedCapabilities());

    } else if (pkg_p instanceof CapabilityRealizationPkg) {
      result.addAll(((CapabilityRealizationPkg) pkg_p).getOwnedCapabilityRealizations());
    }
    return result;
  }

  public static Collection<AbstractCapabilityPkg> getOwnedCapabilityPkgs(AbstractCapabilityPkg pkg_p) {
    Collection<AbstractCapabilityPkg> result = new ArrayList<AbstractCapabilityPkg>();

    if (pkg_p instanceof OperationalCapabilityPkg) {
      result.addAll(((OperationalCapabilityPkg) pkg_p).getOwnedOperationalCapabilityPkgs());

    } else if (pkg_p instanceof CapabilityPkg) {
      result.addAll(((CapabilityPkg) pkg_p).getOwnedCapabilityPkgs());

    } else if (pkg_p instanceof CapabilityRealizationPkg) {
      result.addAll(((CapabilityRealizationPkg) pkg_p).getOwnedCapabilityRealizationPkgs());
    }
    return result;
  }

  /**
   * This method retrieves all the capability realizations from the contained by the given functional aspect package.
   *
   * @param currentElement_p
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizations(AbstractCapabilityPkg currentElement_p) {
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(currentElement_p, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  /**
   * This method retrieves all the scenarios from the model (contained by the given AspectPkg).
   *
   * @param currentElement_p
   * @return List<Scenario>
   */
  public static List<Scenario> getAllScenarios(AbstractCapabilityPkg currentElement_p) {
    Set<EObject> scSet = EObjectExt.getAll(currentElement_p, InteractionPackage.Literals.SCENARIO);
    List<Scenario> scList = new ArrayList<Scenario>();
    for (EObject obj : scSet) {
      scList.add((Scenario) obj);
    }
    return scList;
  }

  /**
   * @param ownedAbstractCapabilityPkg_p
   * @return
   */
  public static List<AbstractCapability> getAllCapabilities(AbstractCapabilityPkg abstractCapabilityPkg_p) {
    List<AbstractCapability> list = new ArrayList<AbstractCapability>(1);

    if ((null != abstractCapabilityPkg_p) && (abstractCapabilityPkg_p instanceof OperationalCapabilityPkg)) {
      OperationalCapabilityPkg opCapPkg = (OperationalCapabilityPkg) abstractCapabilityPkg_p;
      list.addAll(opCapPkg.getOwnedOperationalCapabilities());
      for (OperationalCapabilityPkg subPkg : opCapPkg.getOwnedOperationalCapabilityPkgs()) {
        list.addAll(getAllCapabilities(subPkg));
      }
    }

    return list;
  }

  public static List<AbstractCapability> getAllAbstractCapabilities(AbstractCapabilityPkg pkg_p) {
    List<AbstractCapability> list = new ArrayList<AbstractCapability>();
    if (pkg_p != null) {
      if (pkg_p instanceof CapabilityPkg) {
        list.addAll(((CapabilityPkg) pkg_p).getOwnedCapabilities());
        for (CapabilityPkg aSubPkg : ((CapabilityPkg) pkg_p).getOwnedCapabilityPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      } else if (pkg_p instanceof CapabilityRealizationPkg) {
        list.addAll(((CapabilityRealizationPkg) pkg_p).getOwnedCapabilityRealizations());
        for (CapabilityRealizationPkg aSubPkg : ((CapabilityRealizationPkg) pkg_p).getOwnedCapabilityRealizationPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      } else if (pkg_p instanceof OperationalCapabilityPkg) {
        list.addAll(((OperationalCapabilityPkg) pkg_p).getOwnedOperationalCapabilities());
        for (OperationalCapabilityPkg aSubPkg : ((OperationalCapabilityPkg) pkg_p).getOwnedOperationalCapabilityPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      }
    }
    return list;
  }
}
