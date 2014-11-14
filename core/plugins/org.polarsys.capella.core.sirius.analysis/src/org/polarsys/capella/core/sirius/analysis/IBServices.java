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
package org.polarsys.capella.core.sirius.analysis;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * Shared helpers for Architecture Blank diagrams
 */
public class IBServices {

  private static IBServices service = null;

  public static IBServices getService() {
    if (service == null) {
      service = new IBServices();
    }
    return service;
  }

  public Interface createCCDIInterface(DSemanticDecorator containerView_p) {
    EObject container = containerView_p.getTarget();
    if (container != null) {
      EObject target = CsServices.getService().getParentContainer(container);
      InterfacePkg pkg = null;
      if (target instanceof BlockArchitecture) {
        pkg = BlockArchitectureExt.getInterfacePkg((BlockArchitecture) target);

      } else if (target instanceof Component) {
        pkg = ComponentExt.getInterfacePkg((Component) target);
      }

      if (pkg != null) {
        Interface itf = CsFactory.eINSTANCE.createInterface();
        pkg.getOwnedInterfaces().add(itf);
        CapellaElementExt.creationService(itf);
        CsServices.getService().createInterfaceView(containerView_p, itf, CapellaServices.getService().getDiagramContainer(containerView_p));
        return itf;
      }
    }
    return null;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationCCDIInterface(DSemanticDecorator containerView_p) {
    if (!((!(containerView_p instanceof DDiagram)) && (containerView_p.getTarget() instanceof AbstractActor))
        && !(((DSemanticDecorator) CapellaServices.getService().getDiagramContainer(containerView_p)).getTarget() instanceof ConfigurationItem)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the edge between preSourceView and preTargetView is valid to create a Delegation
   */
  public boolean isValidCreationIBDelegationExchange(EObject root_p, DSemanticDecorator preSourceView_p, DSemanticDecorator preTargetView_p) {
    return CsServices.getService().isValidCreationABDelegationExchange(root_p, preSourceView_p, preTargetView_p);
  }

}
