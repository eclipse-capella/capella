/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * edit Container, i.e PhsyicalComponent
 */
public class EditContainterPCOfPhysicalPort extends AbstractCapellaMarkerResolution {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      final Object obj = modelElements.get(0);
      if ((null != obj) && (obj instanceof PhysicalPort)) {
        // Physical port to delete
        PhysicalPort port = (PhysicalPort) obj;
        EObject eContainer = port.eContainer();
        if ((null != eContainer) && (eContainer instanceof PhysicalComponent)) {
          boolean editElement = CapellaUIPropertiesPlugin.getDefault().openWizard(eContainer);
          if (editElement) {
            try {
              marker.delete();
            } catch (CoreException exception) {
              // no nothing
            }
          }
        }
      }
    }
  }
  
  @Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}
}
