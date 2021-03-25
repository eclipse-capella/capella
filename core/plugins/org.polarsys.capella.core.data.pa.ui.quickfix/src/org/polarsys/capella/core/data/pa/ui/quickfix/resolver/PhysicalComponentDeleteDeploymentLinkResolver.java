/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

/**
 * QF for DWF_DC_44 : Delete the Deployment Link
 */
public class PhysicalComponentDeleteDeploymentLinkResolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {
    if (obj instanceof PhysicalComponent) {
      PhysicalComponent deployedComponent = (PhysicalComponent) obj;
      EList<PhysicalComponent> deployingPhysicalComponents = deployedComponent.getDeployingPhysicalComponents();
      EObject parent = deployedComponent.eContainer();
      
      if(parent instanceof PhysicalComponent && deployingPhysicalComponents.contains(parent)) {
        return getDeploymentLinksToDelete((PhysicalComponent) parent, deployedComponent);
      }
    }
    return null;
  }
  
  /**
   * Get the list of deployment links of a the deployed component which is both contained and deployed in the parent component.
   * 
   * @param parentComponent
   * @param deployedComponent
   * @return the list of deployment links to delete
   */
  protected List<AbstractDeploymentLink> getDeploymentLinksToDelete(PhysicalComponent parentComponent, PhysicalComponent deployedComponent) {
    List<AbstractDeploymentLink> linksToRemove = new ArrayList<>();
    for(AbstractTypedElement type : parentComponent.getAbstractTypedElements()) {
      if(type instanceof Part) {
        EList<AbstractDeploymentLink> links = ((Part) type).getDeploymentLinks();
        for(AbstractDeploymentLink link : links) {
          DeployableElement deployedElement = link.getDeployedElement();
          if(deployedElement instanceof Part && deployedComponent.equals(((Part) deployedElement).getAbstractType())) {
            linksToRemove.add(link);
          }
        }
      }
    }
    return linksToRemove;
  }
  
}
