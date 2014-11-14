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
package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionHelper;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;

public class PartHelper {
  private static PartHelper instance;

  private PartHelper() {
    // do nothing
  }

  public static PartHelper getInstance() {
    if (instance == null)
      instance = new PartHelper();
    return instance;
  }

  public Object doSwitch(Part element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.PART__PROVIDED_INTERFACES)) {
      ret = getProvidedInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PART__REQUIRED_INTERFACES)) {
      ret = getRequiredInterfaces(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PART__DEPLOYED_PARTS)) {
      ret = getDeployedParts(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PART__DEPLOYING_PARTS)) {
      ret = getDeployingParts(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = PartitionHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractPathInvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    return ret;

  }

  protected List<Interface> getProvidedInterfaces(Part element_p) {
    List<Interface> ret = new ArrayList<Interface>();

    Type representedElement = element_p.getType();
    if (representedElement instanceof Component) {
      ret = ComponentHelper.getInstance().getProvidedInterfaces((Component) representedElement);
    }

    return ret;
  }

  protected List<Interface> getRequiredInterfaces(Part element_p) {
    List<Interface> ret = new ArrayList<Interface>();

    Type representedElement = element_p.getType();
    if (representedElement instanceof Component) {
      ret = ComponentHelper.getInstance().getRequiredInterfaces((Component) representedElement);
    }

    return ret;
  }

  protected List<Part> getDeployedParts(Part element_p) {
    List<Part> ret = new ArrayList<Part>();
    for (AbstractDeploymentLink deploymentLink : element_p.getDeploymentLinks()) {
      DeployableElement deployableElement = deploymentLink.getDeployedElement();
      if (deployableElement instanceof Part) {
        ret.add((Part) deployableElement);
      }
    }
    return ret;
  }

  protected List<Part> getDeployingParts(Part element_p) {
    List<Part> ret = new ArrayList<Part>();
    for (AbstractDeploymentLink deploymentLink : element_p.getDeployingLinks()) {
      DeploymentTarget deploymentTarget = deploymentLink.getLocation();
      if (deploymentTarget instanceof Part) {
        ret.add((Part) deploymentTarget);
      }
    }
    return ret;
  }
}
