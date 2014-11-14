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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * edit Container, i.e PhsyicalComponent
 */
public class EditContainterPCOfComponentPort extends AbstractCapellaMarkerResolution {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    List<EObject> modelElements = getModelElements(marker_p);
    if (!modelElements.isEmpty()) {
      final Object obj = modelElements.get(0);
      if ((null != obj) && (obj instanceof ComponentPort)) {
        // Physical port to delete
        ComponentPort port = (ComponentPort) obj;
        EObject eContainer = port.eContainer();
        if ((null != eContainer) && (eContainer instanceof PhysicalComponent)) {
          boolean editElement = CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) eContainer);
          if (editElement) {
            try {
              marker_p.delete();
            } catch (CoreException exception_p) {
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
