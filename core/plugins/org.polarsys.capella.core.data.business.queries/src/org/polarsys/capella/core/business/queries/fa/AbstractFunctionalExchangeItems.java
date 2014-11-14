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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public abstract class AbstractFunctionalExchangeItems implements IBusinessQuery, RefactoredBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getOldAvailableElements(CapellaElement element) {

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

    if (null == systemEngineering) {
      return availableElements;
    }

    availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, element));

    return availableElements;
  }

  /**
   * @param currentProperty_p_p
   * @param oa_p
   */
  @SuppressWarnings("unchecked")
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, CapellaElement currentProperty_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    if (arch_p != null) {
      DataPkg ownedDataPkg = arch_p.getOwnedDataPkg();
      if (null != ownedDataPkg) {
        // retrieve all the ExchangeItems from all the dataPkgs
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(ownedDataPkg));
      }

      Set<EObject> allInterPkg = EObjectExt.getAll(arch_p, CapellacorePackage.Literals.ABSTRACT_EXCHANGE_ITEM_PKG);
      // retrieve all the ExchangeItems from all the InterfacePkgs
      for (EObject eObject : allInterPkg) {
        if (eObject instanceof AbstractExchangeItemPkg) {
          AbstractExchangeItemPkg intPkg = (AbstractExchangeItemPkg) eObject;
          availableElements.addAll(intPkg.getOwnedExchangeItems());
        }
      }

      // remove AbstractExchangeItem related to current
      for (CapellaElement abstractExchangeItem : getCurrentElements(currentProperty_p, false)) {
        availableElements.remove(abstractExchangeItem);
      }
    }

    return availableElements;
  }

  // same level
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng_p, CapellaElement currentProperty_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);

    availableElements.addAll(getElementsFromBlockArchitecture(arch, currentProperty_p));

    // For the layer visibility
    if (!(arch instanceof OperationalAnalysis)) {
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng_p, currentProperty_p));
    }

    return availableElements;
  }

  /*
   * For the layer visibility
   */
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng_p, CapellaElement currentProperty_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);

    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty_p);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa, currentProperty_p));
    } else {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
      availableElements.addAll(getElementsFromBlockArchitecture(ca, currentProperty_p));
    }

    if (null != arch) {
      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty_p));
      }
      if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty_p));
      }
      if ((arch instanceof EPBSArchitecture)) {
        PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentProperty_p));
      }
    }
    return availableElements;
  }

}
