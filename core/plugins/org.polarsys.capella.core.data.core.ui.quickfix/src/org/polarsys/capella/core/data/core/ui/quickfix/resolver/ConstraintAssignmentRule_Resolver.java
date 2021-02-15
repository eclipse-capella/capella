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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Show details for package dependency cycles.
 */
public class ConstraintAssignmentRule_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    List<EObject> tgts = getModelElements(marker);
    Constraint constraint = (Constraint) tgts.get(0);
    EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
    List<ModelElement> newConstrainedElements = new ArrayList<>();

    AbstractReadWriteCommand abstractCommand = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        for (ModelElement element : constrainedElements) {
          // constrain the component instead of part/part deployment link
          if (element instanceof Part) {
            newConstrainedElements.add(((Part) element).getAbstractType());
          } else if (element instanceof PartDeploymentLink) {
            DeployableElement deployedElement = ((PartDeploymentLink) element).getDeployedElement();
            newConstrainedElements.add(((Part) deployedElement).getAbstractType());
          } else {
            newConstrainedElements.add(element);
          }
        }
        constrainedElements.clear();
        constrainedElements.addAll(newConstrainedElements);
      }
    };
    TransactionHelper.getExecutionManager(tgts).execute(abstractCommand);

    try {
      marker.delete();
    } catch (CoreException e) {
      // Catch exception silently,
      e.printStackTrace();
    }
  }

}
