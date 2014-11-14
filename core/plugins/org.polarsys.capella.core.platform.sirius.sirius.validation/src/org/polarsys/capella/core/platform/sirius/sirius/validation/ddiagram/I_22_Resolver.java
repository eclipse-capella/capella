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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.utils.saxparser.WriteCapellaElementDescriptionSAXParser;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

public class I_22_Resolver extends AbstractCapellaMarkerResolution {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    final List<EObject> modelElements = getModelElements(marker_p);
    final boolean[] flag = { false };
    if (!modelElements.isEmpty()) {
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return getLabel();
        }

        public void run() {
          flag[0] = false;
          WriteCapellaElementDescriptionSAXParser writeDescription = new WriteCapellaElementDescriptionSAXParser() {
            /**
             * {@inheritDoc}
             */
            @Override
            protected String getName(EObject object_p) {
              String result = super.getName(object_p);
              if ((null == result) || result.isEmpty()) {
                if (object_p instanceof DRepresentation) {
                  DRepresentation res = (DRepresentation) object_p;
                  String repName = res.getName();
                  if (null != repName) {
                    result = repName;
                  }
                }
              }
              return result;
            }
            /**
             * {@inheritDoc}
             */
            @Override
             protected boolean managedObject(EObject object_p) {
              return super.managedObject(object_p) || (object_p instanceof DRepresentation);
            }
          };
          flag[0] = writeDescription.updateDescription(modelElements);
        }
      };

      // execute the command
      MDEAdapterFactory.getExecutionManager().execute(abstrctCommand);
      if (flag[0]) {
        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          _logger.error("Exception while deleting marker : " + exception_p.toString()); //$NON-NLS-1$
        }
      }
    }
  }
}
