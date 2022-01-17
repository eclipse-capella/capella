/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * DWF_DF_16 - SequenceLink has empty condition
 */
public class DWF_DF_16_Resolver extends AbstractCapellaMarkerResolution {
  private final String PROCESS_ICON = "icons/full/obj16/capella_process.gif";
  private String overridenLabel;

  public DWF_DF_16_Resolver(String label) {
    this.overridenLabel = label;
    super.setContributorId(FrameworkUtil.getBundle(CapellaUIResourcesPlugin.class).getSymbolicName());
    super.setImgKey(PROCESS_ICON);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);

    if (!modelElements.isEmpty()) {
      if (modelElements.get(0) instanceof SequenceLink) {
        openEditWizard((SequenceLink) modelElements.get(0), marker);
      }
    }
  }

  private void openEditWizard(SequenceLink semanticElement, IMarker marker) {
    boolean ok = CapellaUIPropertiesPlugin.getDefault().openWizard(semanticElement);
    if (ok) {
      if (hasCondition(semanticElement)) {
        deleteMarker(marker);
      }
    }
  }

  @Override
  protected void deleteMarker(IMarker marker) {
    // delete marker
    try {
      marker.delete();
    } catch (CoreException exception) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception.getMessage(), exception));
    }
  }

  private boolean hasCondition(SequenceLink seqLink) {
    boolean hasCondition = false;
    if (seqLink.getCondition() != null) {
      String constraint = CapellaServices.getService().getConstraintLabel(seqLink.getCondition());
      hasCondition = !(constraint.isEmpty());
    }
    return hasCondition;
  }

  @Override
  public String getLabel() {
    return overridenLabel;
  }
}
