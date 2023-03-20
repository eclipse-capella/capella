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
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.model.utils.saxparser.WriteCapellaElementDescriptionSAXParser;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionParserHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class I_22_Resolver extends AbstractCapellaMarkerResolution {

  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    final List<EObject> modelElements = getModelElements(marker_p);
    Diagnostic diagnostic = MarkerViewHelper.getDiagnostic(marker_p);
    String linkId = DescriptionParserHelper.getLinkIdFromStatus(diagnostic.getMessage());
    final boolean[] flag = { false };
    if (!modelElements.isEmpty()) {
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return getLabel();
        }

        public void run() {
          WriteCapellaElementDescriptionSAXParser writeDescription = new WriteCapellaElementDescriptionSAXParser() {
            @Override
            protected String getName(EObject object_p) {
              return DescriptionParserHelper.getElementName(object_p);
            }
          };
          
          flag[0] = writeDescription.updateDescription(modelElements, linkId);
        }
      };

      // execute the command
      TransactionHelper.getExecutionManager(modelElements).execute(abstrctCommand);
      if (flag[0]) {
        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          logger.error("Exception while deleting marker : " + exception_p.toString()); //$NON-NLS-1$
        }
      }
    }
  }
}
