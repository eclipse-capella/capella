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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractPhysicalArtifactHelper;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

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
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_COMPONENT_REALIZATIONS)) {
      ret = getLogicalComponentRealizations(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS)) {
      ret = getRealizedLogicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS)) {
      ret = getAllocatedPhysicalFunctions(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS)) {
      ret = getDeployedPhysicalComponents(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_ACTORS)) {
      ret = getDeployingPhysicalActors(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS)) {
      ret = getDeployingPhysicalComponents(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPhysicalComponentHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<LogicalComponentRealization> getLogicalComponentRealizations(PhysicalComponent element) {
    List<LogicalComponentRealization> ret = new ArrayList<>();
    for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
      if (componentAllocation instanceof LogicalComponentRealization) {
        ret.add((LogicalComponentRealization) componentAllocation);
      }
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
    for (Partition thePartition : element.getOwnedPartitions()) {
      Type representedElement = thePartition.getType();
      // we need to be invariant
      if (null != representedElement && representedElement.eClass().equals(PaPackage.Literals.PHYSICAL_COMPONENT)) {
        ret.add((PhysicalComponent) representedElement);
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getRealizedLogicalComponents(PhysicalComponent element) {
    List<LogicalComponent> ret = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof LogicalComponentRealization) {
        TraceableElement elt = ((LogicalComponentRealization) trace).getTargetElement();
        if (elt instanceof LogicalComponent) {
          ret.add((LogicalComponent) elt);
        }
      }
    }
    return ret;
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

  protected List<PhysicalActor> getDeployingPhysicalActors(PhysicalComponent element) {
    List<PhysicalActor> ret = new ArrayList<>();
    for (EObject obj : EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
      if (obj instanceof Part) {
        for (AbstractDeploymentLink deployingLink : ((Part) obj).getDeployingLinks()) {
          DeploymentTarget deploymentTarget = deployingLink.getLocation();
          if (deploymentTarget instanceof PhysicalActor) {
            ret.add((PhysicalActor) deploymentTarget);
          } else if (deploymentTarget instanceof Part) {
            AbstractType type = ((Part) deploymentTarget).getAbstractType();
            if (type instanceof PhysicalActor) {
              ret.add((PhysicalActor) type);
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
