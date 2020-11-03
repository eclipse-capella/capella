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
package org.polarsys.capella.core.platform.sirius.ui.services;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.IDiagramServices;

/**
 */
public class SiriusDiagramServices implements IDiagramServices {

  /**
   * The used logger.
   */
  private static Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DIAGRAM);

  /**
   * 
   */
  public SiriusDiagramServices() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.common.ui.services.IDiagramServices#refreshActiveDiagram()
   */
  public void refreshActiveDiagram(EObject ddiagram_p) {
    IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if (editor instanceof DiagramEditor) {
      DiagramEditPart part = null;
      Map<?, ?> map = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
      if (ddiagram_p instanceof DDiagram) {
        part = (DiagramEditPart) map.get(ddiagram_p);
      } else {
        for (Object obj : map.values()) {
          if (obj instanceof DiagramEditPart) {
            part = (DiagramEditPart) obj;
          }
        }
      }
      if (part != null) {
        new org.eclipse.sirius.diagram.sequence.ui.tool.api.SequenceDiagramLayout(part).refreshGraphicalCoverage();
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.services.IDiagramServices#openDiagram(org.polarsys.capella.common.model.IModelElement)
   */
  public void openDiagram(ModelElement element_p) {
    // TODO Not implemented yet
    __logger.warn("The method openDiagram is not implemented yet in SiriusDiagramServices."); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.services.IDiagramServices#closeDiagram(org.polarsys.capella.common.model.IModelElement)
   */
  public void closeDiagram(ModelElement element_p) {
    // TODO Not implemented yet
    __logger.warn("The method closeDiagram is not implemented yet in SiriusDiagramServices."); //$NON-NLS-1$
  }
}
