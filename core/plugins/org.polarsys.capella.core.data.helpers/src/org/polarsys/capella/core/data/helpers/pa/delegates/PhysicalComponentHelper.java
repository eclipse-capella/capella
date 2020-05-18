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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacommon.delegates.CapabilityRealizationInvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractPhysicalArtifactHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.DeployableElementHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.DeploymentTargetHelper;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

public class PhysicalComponentHelper {
  private static PhysicalComponentHelper instance;

  private PhysicalComponentHelper() {
    // do nothing
  }

  public static PhysicalComponentHelper getInstance() {
    if (instance == null)
      instance = new PhysicalComponentHelper();
    return instance;
  }

  public Object doSwitch(PhysicalComponent element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS)) {
      ret = getSubPhysicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS)) {
      ret = getLogicalInterfaceRealizations(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS)) {
      ret = getRealizedLogicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS)) {
      ret = getAllocatedPhysicalFunctions(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS)) {
      ret = getDeployedPhysicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS)) {
      ret = getDeployingPhysicalComponents(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = ComponentHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = CapabilityRealizationInvolvedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = DeploymentTargetHelper.getInstance().doSwitch(element, feature);
    }
    
    return ret;
  }

  protected List<LogicalInterfaceRealization> getLogicalInterfaceRealizations(PhysicalComponent element) {
    List<LogicalInterfaceRealization> ret = new ArrayList<>();
    for (InterfaceAllocation componentAllocation : element.getProvisionedInterfaceAllocations()) {
      if (componentAllocation instanceof LogicalInterfaceRealization) {
        ret.add((LogicalInterfaceRealization) componentAllocation);
      }

    }
    return ret;
  }

  protected List<PhysicalComponent> getSubPhysicalComponents(PhysicalComponent element) {
    List<PhysicalComponent> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        Type type = ((Part) feature).getType();
        // we need to be invariant
        if (null != type && type.eClass().equals(PaPackage.Literals.PHYSICAL_COMPONENT)) {
          ret.add((PhysicalComponent) type);
        }
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getRealizedLogicalComponents(PhysicalComponent element) {
    return element.getRealizedComponents().stream().filter(LogicalComponent.class::isInstance)
        .map(LogicalComponent.class::cast).collect(Collectors.toList());
  }

  protected List<PhysicalFunction> getAllocatedPhysicalFunctions(PhysicalComponent element) {
    List<PhysicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getDeployedPhysicalComponents(PhysicalComponent element) {
    List<PhysicalComponent> ret = new ArrayList<>();
    for (EObject obj : EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
      if (obj instanceof Part) {
        for (AbstractDeploymentLink deploymentLink : ((Part) obj).getDeploymentLinks()) {
          DeployableElement deployableElement = deploymentLink.getDeployedElement();
          if (deployableElement instanceof PhysicalComponent) {
            ret.add((PhysicalComponent) deployableElement);
          } else if (deployableElement instanceof Part) {
            AbstractType type = ((Part) deployableElement).getAbstractType();
            if (type instanceof PhysicalComponent) {
              ret.add((PhysicalComponent) type);
            }
          }
        }
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getDeployingPhysicalComponents(PhysicalComponent element) {
    List<PhysicalComponent> ret = new ArrayList<>();
    for (EObject obj : EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
      if (obj instanceof Part) {
        for (AbstractDeploymentLink deployingLink : ((Part) obj).getDeployingLinks()) {
          DeploymentTarget deploymentTarget = deployingLink.getLocation();
          if (deploymentTarget instanceof PhysicalComponent) {
            ret.add((PhysicalComponent) deploymentTarget);
          } else if (deploymentTarget instanceof Part) {
            AbstractType type = ((Part) deploymentTarget).getAbstractType();
            if (type instanceof PhysicalComponent) {
              ret.add((PhysicalComponent) type);
            }
          }
        }
      }
    }
    return ret;
  }
}
