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

import java.util.Collection;
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
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class ConstraintLocationRule_Resolver extends AbstractCapellaMarkerResolution {
  private final String PROCESS_ICON = "icons/full/obj16/capella_process.gif";

  public ConstraintLocationRule_Resolver (String label) {
    this.setLabel(label);
    super.setContributorId(CapellaUIResourcesPlugin.PLUGIN_ID);
    super.setImgKey(PROCESS_ICON);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    List<EObject> tgts = getModelElements(marker);

    Constraint constraint = (Constraint) tgts.get(0);
    ModelElement element = getConstraintNewLocation(constraint);
    
    // move constraint to new location
    if (element != null) {
      AbstractReadWriteCommand abstractCommand = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          element.getOwnedConstraints().add(constraint);
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

  private EObject getConstraintLocation(Constraint constraint) {
    return constraint.eContainer();
  }

  /**
   * Get the new location for the constraint. This will be the Component corresponding to the current location (Part/PartDeploymentLink)
   */
  private ModelElement getConstraintNewLocation(Constraint constraint) {

    // Multipart mode : Move constraint under first value of ConstraintedElements
    if (CsServices.getService().isMultipartMode((ModelElement) constraint)) {
      // get first value of ConstrainedElements
      EList<ModelElement> constrainedElements = constraint.getConstrainedElements();
      return constrainedElements != null & constrainedElements.size() > 0 ? constrainedElements.get(0) : null;      
    }
    
    // Monopart mode : Move constraint under Component
    ModelElement comp; 
    EObject currentConstraintLocation = getConstraintLocation(constraint);
    if (currentConstraintLocation instanceof Part) {
      comp = ((Part) currentConstraintLocation).getAbstractType();
    } else {
      DeployableElement deployedElement = ((PartDeploymentLink) currentConstraintLocation).getDeployedElement();
      if (deployedElement instanceof Part) {
        comp = ((Part) deployedElement).getAbstractType();
      } else {
        comp = deployedElement;
      }
    }
    return comp;
  }
  
  @Override
  protected String[] getResolvableRuleIds() {
    return new String[] {"DWF_D_59"};
  }

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return true;
  }
  
  @Override
  public IMarker[] findOtherMarkers(IMarker[] markers) {
    return super.findOtherMarkers(markers);
  }
}
