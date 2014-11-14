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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class CapellaElement_OutgoingRequirement implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.ui.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if ((element_p instanceof OperationalAnalysis) || EcoreUtil2.isContainedBy(element_p, OaPackage.Literals.OPERATIONAL_ANALYSIS)) {
      OperationalAnalysis oa =
          (OperationalAnalysis) ((element_p instanceof OperationalAnalysis) ? element_p : EcoreUtil2.getFirstContainer(element_p,
              OaPackage.Literals.OPERATIONAL_ANALYSIS));
      if (oa != null) {
        availableElements.addAll(getRequirements(oa));
      }
    } else if ((element_p instanceof SystemAnalysis) || EcoreUtil2.isContainedBy(element_p, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
      SystemAnalysis ca =
          (SystemAnalysis) ((element_p instanceof SystemAnalysis) ? element_p : EcoreUtil2.getFirstContainer(element_p, CtxPackage.Literals.SYSTEM_ANALYSIS));
      if (ca != null) {
        availableElements.addAll(getRequirements(ca));
        for (BlockArchitecture ba : ca.getAllocatedArchitectures()) {
          OperationalAnalysis oa = (OperationalAnalysis) ba;
          availableElements.addAll(getRequirements(oa));
        }
      }
    } else if ((element_p instanceof LogicalArchitecture) || EcoreUtil2.isContainedBy(element_p, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
      LogicalArchitecture la =
          (LogicalArchitecture) ((element_p instanceof LogicalArchitecture) ? element_p : EcoreUtil2.getFirstContainer(element_p,
              LaPackage.Literals.LOGICAL_ARCHITECTURE));
      if (la != null) {
        availableElements.addAll(getRequirements(la));
        for (BlockArchitecture ba1 : la.getAllocatedArchitectures()) {
          SystemAnalysis ca = (SystemAnalysis) ba1;
          availableElements.addAll(getRequirements(ca));
          for (BlockArchitecture ba2 : ca.getAllocatedArchitectures()) {
            OperationalAnalysis oa = (OperationalAnalysis) ba2;
            availableElements.addAll(getRequirements(oa));
          }
        }
      }
    } else if ((element_p instanceof PhysicalArchitecture) || EcoreUtil2.isContainedBy(element_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
      PhysicalArchitecture pa =
          (PhysicalArchitecture) ((element_p instanceof PhysicalArchitecture) ? element_p : EcoreUtil2.getFirstContainer(element_p,
              PaPackage.Literals.PHYSICAL_ARCHITECTURE));
      if (pa != null) {
        availableElements.addAll(getRequirements(pa));
        for (BlockArchitecture ba1 : pa.getAllocatedArchitectures()) {
          LogicalArchitecture la = (LogicalArchitecture) ba1;
          availableElements.addAll(getRequirements(la));
          for (BlockArchitecture ba2 : la.getAllocatedArchitectures()) {
            SystemAnalysis ca = (SystemAnalysis) ba2;
            availableElements.addAll(getRequirements(ca));
            for (BlockArchitecture ba3 : ca.getAllocatedArchitectures()) {
              OperationalAnalysis oa = (OperationalAnalysis) ba3;
              availableElements.addAll(getRequirements(oa));
            }
          }
        }
      }
    } else if ((element_p instanceof EPBSArchitecture) || EcoreUtil2.isContainedBy(element_p, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
      EPBSArchitecture ea =
          (EPBSArchitecture) ((element_p instanceof EPBSArchitecture) ? element_p : EcoreUtil2.getFirstContainer(element_p,
              EpbsPackage.Literals.EPBS_ARCHITECTURE));
      if (ea != null) {
        availableElements.addAll(getRequirements(ea));
        for (BlockArchitecture ba1 : ea.getAllocatedArchitectures()) {
          PhysicalArchitecture pa = (PhysicalArchitecture) ba1;
          availableElements.addAll(getRequirements(pa));
          for (BlockArchitecture ba2 : pa.getAllocatedArchitectures()) {
            LogicalArchitecture la = (LogicalArchitecture) ba2;
            availableElements.addAll(getRequirements(la));
            for (BlockArchitecture ba3 : la.getAllocatedArchitectures()) {
              SystemAnalysis ca = (SystemAnalysis) ba3;
              availableElements.addAll(getRequirements(ca));
              for (BlockArchitecture ba4 : ca.getAllocatedArchitectures()) {
                OperationalAnalysis oa = (OperationalAnalysis) ba4;
                availableElements.addAll(getRequirements(oa));
              }
            }
          }
        }
      }
    }

    availableElements.removeAll(getCurrentElements(element_p, false));

    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element_p);

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.ui.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof RequirementsTrace) {
        currentElements.add((CapellaElement) trace.getTargetElement());
      }
    }

    currentElements = ListExt.removeDuplicates(currentElements);

    return currentElements;
  }

  public EClass getEClass() {
    return CapellacorePackage.Literals.CAPELLA_ELEMENT;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES);
  }

  /**
	 * 
	 */
  List<Requirement> getRequirements(BlockArchitecture arch) {
    List<Requirement> elements = new ArrayList<Requirement>();

    for (RequirementsPkg pkg : arch.getOwnedRequirementPkgs()) {
      for (EObject req : MDEQueries.getInstance().getAllQueries().getAll(pkg, Requirement.class)) {
        elements.add((Requirement) req);
      }
    }

    return elements;
  }
}
