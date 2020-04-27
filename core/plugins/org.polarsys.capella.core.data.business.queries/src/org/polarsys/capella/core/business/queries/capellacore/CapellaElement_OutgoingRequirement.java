/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class CapellaElement_OutgoingRequirement implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.ui.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  @Override
	public List<EObject> getAvailableElements(EObject element) {
    List<EObject> availableElements = new ArrayList<EObject>();

    if ((element instanceof OperationalAnalysis) || EcoreUtil2.isContainedBy(element, OaPackage.Literals.OPERATIONAL_ANALYSIS)) {
      OperationalAnalysis oa =
          (OperationalAnalysis) ((element instanceof OperationalAnalysis) ? element : EcoreUtil2.getFirstContainer(element,
              OaPackage.Literals.OPERATIONAL_ANALYSIS));
      if (oa != null) {
        availableElements.addAll(getRequirements(oa));
      }
    } else if ((element instanceof SystemAnalysis) || EcoreUtil2.isContainedBy(element, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
      SystemAnalysis ca =
          (SystemAnalysis) ((element instanceof SystemAnalysis) ? element : EcoreUtil2.getFirstContainer(element, CtxPackage.Literals.SYSTEM_ANALYSIS));
      if (ca != null) {
        availableElements.addAll(getRequirements(ca));
        for (BlockArchitecture ba : ca.getAllocatedArchitectures()) {
          OperationalAnalysis oa = (OperationalAnalysis) ba;
          availableElements.addAll(getRequirements(oa));
        }
      }
    } else if ((element instanceof LogicalArchitecture) || EcoreUtil2.isContainedBy(element, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
      LogicalArchitecture la =
          (LogicalArchitecture) ((element instanceof LogicalArchitecture) ? element : EcoreUtil2.getFirstContainer(element,
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
    } else if ((element instanceof PhysicalArchitecture) || EcoreUtil2.isContainedBy(element, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
      PhysicalArchitecture pa =
          (PhysicalArchitecture) ((element instanceof PhysicalArchitecture) ? element : EcoreUtil2.getFirstContainer(element,
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
    } else if ((element instanceof EPBSArchitecture) || EcoreUtil2.isContainedBy(element, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
      EPBSArchitecture ea =
          (EPBSArchitecture) ((element instanceof EPBSArchitecture) ? element : EcoreUtil2.getFirstContainer(element,
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

    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element);

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.ui.business.queries.IBusinessQuery#getCurrentElements(EObject, boolean)
   */
  @Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<EObject>();

    for (AbstractTrace trace : ((CapellaElement) element).getOutgoingTraces()) {
      if (trace instanceof RequirementsTrace) {
        currentElements.add((CapellaElement) trace.getTargetElement());
      }
    }

    currentElements = ListExt.removeDuplicates(currentElements);

    return currentElements;
  }

  @Override
	public EClass getEClass() {
    return CapellacorePackage.Literals.CAPELLA_ELEMENT;
  }

  @Override
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
