/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public static Collection<AbstractCapability> getOwnedCapabilities(AbstractCapabilityPkg pkg) {
    Collection<AbstractCapability> result = new ArrayList<AbstractCapability>();

    if (pkg instanceof OperationalCapabilityPkg) {
      result.addAll(((OperationalCapabilityPkg) pkg).getOwnedOperationalCapabilities());

    } else if (pkg instanceof CapabilityPkg) {
      result.addAll(((CapabilityPkg) pkg).getOwnedCapabilities());

    } else if (pkg instanceof CapabilityRealizationPkg) {
      result.addAll(((CapabilityRealizationPkg) pkg).getOwnedCapabilityRealizations());
    }
    return result;
  }

  public static Collection<AbstractCapabilityPkg> getOwnedCapabilityPkgs(AbstractCapabilityPkg pkg) {
    Collection<AbstractCapabilityPkg> result = new ArrayList<AbstractCapabilityPkg>();

    if (pkg instanceof OperationalCapabilityPkg) {
      result.addAll(((OperationalCapabilityPkg) pkg).getOwnedOperationalCapabilityPkgs());

    } else if (pkg instanceof CapabilityPkg) {
      result.addAll(((CapabilityPkg) pkg).getOwnedCapabilityPkgs());

    } else if (pkg instanceof CapabilityRealizationPkg) {
      result.addAll(((CapabilityRealizationPkg) pkg).getOwnedCapabilityRealizationPkgs());
    }
    return result;
  }

  /**
   * This method retrieves all the capability realizations from the contained by the given functional aspect package.
   *
   * @param currentElement
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizations(AbstractCapabilityPkg currentElement) {
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(currentElement, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  /**
   * This method retrieves all the scenarios from the model (contained by the given AspectPkg).
   *
   * @param currentElement
   * @return List<Scenario>
   */
  public static List<Scenario> getAllScenarios(AbstractCapabilityPkg currentElement) {
    Set<EObject> scSet = EObjectExt.getAll(currentElement, InteractionPackage.Literals.SCENARIO);
    List<Scenario> scList = new ArrayList<Scenario>();
    for (EObject obj : scSet) {
      scList.add((Scenario) obj);
    }
    return scList;
  }

  /**
   * @param ownedAbstractCapabilityPkg
   * @return
   */
  public static List<AbstractCapability> getAllCapabilities(AbstractCapabilityPkg abstractCapabilityPkg) {
    List<AbstractCapability> list = new ArrayList<AbstractCapability>(1);

    if ((null != abstractCapabilityPkg) && (abstractCapabilityPkg instanceof OperationalCapabilityPkg)) {
      OperationalCapabilityPkg opCapPkg = (OperationalCapabilityPkg) abstractCapabilityPkg;
      list.addAll(opCapPkg.getOwnedOperationalCapabilities());
      for (OperationalCapabilityPkg subPkg : opCapPkg.getOwnedOperationalCapabilityPkgs()) {
        list.addAll(getAllCapabilities(subPkg));
      }
    }

    return list;
  }

  public static List<AbstractCapability> getAllAbstractCapabilities(AbstractCapabilityPkg pkg) {
    List<AbstractCapability> list = new ArrayList<AbstractCapability>();
    if (pkg != null) {
      if (pkg instanceof CapabilityPkg) {
        list.addAll(((CapabilityPkg) pkg).getOwnedCapabilities());
        for (CapabilityPkg aSubPkg : ((CapabilityPkg) pkg).getOwnedCapabilityPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      } else if (pkg instanceof CapabilityRealizationPkg) {
        list.addAll(((CapabilityRealizationPkg) pkg).getOwnedCapabilityRealizations());
        for (CapabilityRealizationPkg aSubPkg : ((CapabilityRealizationPkg) pkg).getOwnedCapabilityRealizationPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      } else if (pkg instanceof OperationalCapabilityPkg) {
        list.addAll(((OperationalCapabilityPkg) pkg).getOwnedOperationalCapabilities());
        for (OperationalCapabilityPkg aSubPkg : ((OperationalCapabilityPkg) pkg).getOwnedOperationalCapabilityPkgs()) {
          list.addAll(getAllAbstractCapabilities(aSubPkg));
        }
      }
    }
    return list;
  }
}
