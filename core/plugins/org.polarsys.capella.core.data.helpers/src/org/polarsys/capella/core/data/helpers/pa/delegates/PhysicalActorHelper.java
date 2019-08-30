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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.cs.delegates.AbstractActorHelper;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

public class PhysicalActorHelper {
  private static PhysicalActorHelper instance;

  private PhysicalActorHelper() {
    // do nothing
  }

  public static PhysicalActorHelper getInstance() {
    if (instance == null)
      instance = new PhysicalActorHelper();
    return instance;
  }

  public Object doSwitch(PhysicalActor element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(PaPackage.Literals.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS)) {
      ret = getLogicalActorRealizations(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS)) {
      ret = getAllocatedPhysicalFunctions(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS)) {
      ret = getRealizedLogicalActors(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS)) {
      ret = getDeployedPhysicalComponents(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractActorHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPhysicalComponentHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<LogicalActorRealization> getLogicalActorRealizations(PhysicalActor element) {
    List<LogicalActorRealization> ret = new ArrayList<>();
    for (ComponentAllocation componentAllocation : element.getProvisionedComponentAllocations()) {
      if (componentAllocation instanceof LogicalActorRealization) {
        ret.add((LogicalActorRealization) componentAllocation);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getAllocatedPhysicalFunctions(PhysicalActor element) {
    List<PhysicalFunction> ret = new ArrayList<>();
    for (AbstractFunction function : element.getAllocatedFunctions()) {
      if (function instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) function);
      }
    }
    return ret;
  }

  protected List<LogicalActor> getRealizedLogicalActors(PhysicalActor element) {
    List<LogicalActor> ret = new ArrayList<>();
    for (Component cpnt : element.getAllocatedComponents()) {
      if (cpnt instanceof LogicalActor) {
        ret.add((LogicalActor) cpnt);
      }
    }
    return ret;
  }

  protected List<PhysicalComponent> getDeployedPhysicalComponents(PhysicalActor element) {
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
}
