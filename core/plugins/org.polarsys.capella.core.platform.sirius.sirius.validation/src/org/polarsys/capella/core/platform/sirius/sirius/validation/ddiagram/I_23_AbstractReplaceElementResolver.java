/*******************************************************************************
 * Copyright (c) 2023 Thales Global Services S.A.S.
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/

package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionLinkParserHandler;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionParserHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.kitalpha.richtext.widget.tools.dialogs.IEncodedURLHandler;
import org.polarsys.kitalpha.richtext.widget.tools.dialogs.MDELinkDialog;
import org.polarsys.kitalpha.richtext.widget.tools.manager.LinkManager;
import org.polarsys.kitalpha.richtext.widget.tools.manager.LinkManagerImpl;

public class I_23_AbstractReplaceElementResolver extends AbstractCapellaMarkerResolution {
  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected LinkManager linkManager = null;
  String defaultLabel = "";

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    final List<EObject> modelElements = getModelElements(marker_p);
    Diagnostic diagnostic = MarkerViewHelper.getDiagnostic(marker_p);
    String linkId = DescriptionParserHelper.getLinkIdFromStatus(diagnostic.getMessage());
    defaultLabel = DescriptionLinkParserHandler.extractName(diagnostic.getMessage());

    final boolean[] flag = { false };
    if (!modelElements.isEmpty()) {
      // get only the target element
      EObject targetModelElement = modelElements.get(0);

      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return getLabel();
        }

        public void run() {
          flag[0] = false;
          AbstractReplaceInvalidHyperLinkInDescription replaceDescription = getReplaceDescription();

          IEncodedURLHandler handler = getEncodedURLHandler(targetModelElement, replaceDescription);
          flag[0] = replaceDescription.updateDescription(targetModelElement, linkId, label, handler);
        }
      };

      // execute the command
      TransactionHelper.getExecutionManager(targetModelElement).execute(abstrctCommand);
      if (flag[0]) {
        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          logger.error("Exception while deleting marker : " + exception_p.toString()); //$NON-NLS-1$
        }
      }
    }
  }

  protected AbstractReplaceInvalidHyperLinkInDescription getReplaceDescription() {
    return null;
  }

  protected IEncodedURLHandler getEncodedURLHandler(EObject targetModelElement,
      AbstractReplaceInvalidHyperLinkInDescription replaceDescription) {

    MDELinkDialog dialog = new MDELinkDialog(Display.getCurrent().getActiveShell(), targetModelElement, defaultLabel,
        getLinkManager(replaceDescription));

    return dialog;
  }

  protected LinkManager getLinkManager(AbstractReplaceInvalidHyperLinkInDescription replaceDescription) {
    if (linkManager == null) {
      linkManager = new LinkManagerImpl() {
        @Override
        public List<String> getAllLinkLabels() {
          return replaceDescription.getLinkLabels();
        }
      };
    }
    return linkManager;
  }
}