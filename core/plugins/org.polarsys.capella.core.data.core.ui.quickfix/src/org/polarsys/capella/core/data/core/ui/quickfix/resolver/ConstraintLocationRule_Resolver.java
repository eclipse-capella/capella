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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Show details for package dependency cycles.
 */
public class ConstraintLocationRule_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    List<EObject> tgts = getModelElements(marker);

    Constraint constraint = (Constraint) tgts.get(0);
    EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
    ModelElement element = constrainedElements.get(0);
    final ModelElement comp = getComponentFromElement(element);

    // move constraint to Component and delete it from current location
    if (comp != null) {
      AbstractReadWriteCommand abstractCommand = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          comp.getOwnedConstraints().add(constraint);
          if (element.getOwnedConstraints() != null && element.getOwnedConstraints().contains(constraint)) {
            element.getOwnedConstraints().remove(constraint);
          } else {
            DeployableElement deployedElement = ((PartDeploymentLink) element).getDeployedElement();
            if (deployedElement.getOwnedConstraints() != null
                && deployedElement.getOwnedConstraints().contains(constraint)) {
              deployedElement.getOwnedConstraints().remove(constraint);
            }
          }

        }
      };
      TransactionHelper.getExecutionManager(tgts).execute(abstractCommand);
    }

    try {
      marker.delete();
    } catch (CoreException e) {
      // Catch exception silently,
      e.printStackTrace();
    }
  }

  private ModelElement getComponentFromElement(ModelElement element) {
    final ModelElement comp;
    
    if (element instanceof Component) {
      comp = (Component) element;
    } else if (element instanceof Part) {
      comp = ((Part) element).getAbstractType();
    } else if (element instanceof PartDeploymentLink) {
      DeployableElement deployedElement = ((PartDeploymentLink) element).getDeployedElement();
      if (deployedElement instanceof Part) {
        comp = ((Part) deployedElement).getAbstractType();
      } else {
        comp = deployedElement;
      }
    } else {
      comp = null;
    }
    
    return comp;
  }

}
