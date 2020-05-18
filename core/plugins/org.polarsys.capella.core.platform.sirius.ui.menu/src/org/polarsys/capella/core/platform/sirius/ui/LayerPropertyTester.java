/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui;

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Tester which is able to check that a selected object is from the correct Layer capella 
 */
public class LayerPropertyTester extends PropertyTester {
  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
    boolean	 result = false;
    if (propertyName_p.equals("layerOf") || propertyName_p.equals("graphicalLayerOf") || propertyName_p.equals("layerOfWithOutDataFlowScenario") || propertyName_p.equals("graphicalLayerOfWithOutDataFlowScenario")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      // getting the capella element
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);
      if (element != null && element instanceof CapellaElement) {
        String layerName = (String) testedValue_p;

        if (layerName.equalsIgnoreCase("OperationalAnalysis")) { //$NON-NLS-1$
          result = CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement)element) || (element instanceof OperationalAnalysis);
        } else if (layerName.equalsIgnoreCase("SystemAnalysis")) { //$NON-NLS-1$
          result = CapellaLayerCheckingExt.isInContextLayer((CapellaElement)element) || (element instanceof SystemAnalysis);
        } else if (layerName.equalsIgnoreCase("LogicalArchitecture")) { //$NON-NLS-1$
          result = CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement)element) || (element instanceof LogicalArchitecture);
        } else if (layerName.equalsIgnoreCase("PhysicalArchitecture")) { //$NON-NLS-1$
          result = CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement)element) || (element instanceof PhysicalArchitecture);
        }  else if (layerName.equalsIgnoreCase("EPBSArchitecture")) { //$NON-NLS-1$
          result = CapellaLayerCheckingExt.isInEPBSLayer((CapellaElement)element) || (element instanceof EPBSArchitecture);
        }

        if (result && (propertyName_p.equals("layerOfWithOutDataFlowScenario") || propertyName_p.equals("graphicalLayerOfWithOutDataFlowScenario"))) { //$NON-NLS-1$ //$NON-NLS-2$
          result = false;
          if (element instanceof Scenario) {
            // Check current Scenario is an interface Scenario
            result = isInterfaceScenario((Scenario) element);
          }
          else if (element instanceof AbstractCapability) {
            // Check current AbstractCapability contains at least one Scenario
            result = containsInterfaceScenario((AbstractCapability) element);
          }
          else if (element instanceof CapabilityPkg) {
            // Check current CapabilityPkg contains at least one Scenario
            result = containsInterfaceScenario((CapabilityPkg) element);
          }
          else if (element instanceof CapabilityRealizationPkg) {
            // Check current CapabilityRealizationPkg contains at least one Scenario
            result = containsInterfaceScenario((CapabilityRealizationPkg) element);
          }
        }
      }

    }
    return result;
  }
  
  private boolean isInterfaceScenario(Scenario scenario_p) {
    return scenario_p.getKind().equals(ScenarioKind.INTERFACE);
  }

  private boolean containsInterfaceScenario(AbstractCapability abstractCapability_p) {
    for (Scenario scenario : abstractCapability_p.getOwnedScenarios()) {
      if (isInterfaceScenario(scenario)) {
        return true;
      }
    }
    return false;
  }

  private boolean containsInterfaceScenario(CapabilityPkg capabilityPkg_p) {
    for (Capability capability : capabilityPkg_p.getOwnedCapabilities()) {
      if (containsInterfaceScenario(capability)) {
        return true;
      }
    }
    for (CapabilityPkg capabilityPkg : capabilityPkg_p.getOwnedCapabilityPkgs()) {
      if (containsInterfaceScenario(capabilityPkg)) {
        return true;
      }
    }
    return false;
  }

  private boolean containsInterfaceScenario(CapabilityRealizationPkg capabilityRealizationPkg_p) {
    for (CapabilityRealization capabilityRealization : capabilityRealizationPkg_p.getOwnedCapabilityRealizations()) {
      if (containsInterfaceScenario(capabilityRealization)) {
        return true;
      }
    }
    for (CapabilityRealizationPkg capabilityRealizationPkg : capabilityRealizationPkg_p.getOwnedCapabilityRealizationPkgs()) {
      if (containsInterfaceScenario(capabilityRealizationPkg)) {
        return true;
      }
    }
    return false;
  }
}
