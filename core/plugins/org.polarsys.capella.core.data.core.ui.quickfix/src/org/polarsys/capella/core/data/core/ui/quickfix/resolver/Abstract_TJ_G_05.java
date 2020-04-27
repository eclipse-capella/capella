/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Rename source or target name
 */
public abstract class Abstract_TJ_G_05 extends AbstractCapellaMarkerResolution {

  protected Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    final boolean[] flag = { false };
    if (!modelElements.isEmpty()) {
      final EObject obj = modelElements.get(0);
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return getLabel();
        }

        public void run() {
          if (obj instanceof TraceableElement) {
            TraceableElement cst = (TraceableElement) obj;
            EList<AbstractTrace> outgoingTraces = cst.getOutgoingTraces();
            for (AbstractTrace abstractTrace : outgoingTraces) {
              // OK for validation
              if (CapellaElementExt.isValidTransitionTrace(abstractTrace)) {
                TraceableElement sourceElement = abstractTrace.getSourceElement();
                TraceableElement targetElement = abstractTrace.getTargetElement();
                // not null
                if ((null != sourceElement) && (null != targetElement) && (sourceElement instanceof AbstractNamedElement)
                    && (targetElement instanceof AbstractNamedElement)) {
                  // are of valid layering
                  if ((sourceElement instanceof CapellaElement) && (targetElement instanceof CapellaElement)) {
                    if (CapellaLayerCheckingExt.isElementFromUpperLayer((CapellaElement) targetElement, (CapellaElement) sourceElement)) {
                      // are nameable
                      AbstractNamedElement sourceNamedElement = (AbstractNamedElement) sourceElement;
                      AbstractNamedElement targetNamedElement = (AbstractNamedElement) targetElement;
                      // if names not equal raise warning message
                      if (!sourceNamedElement.getName().equals(targetNamedElement.getName())) {
                        rename(sourceNamedElement, targetNamedElement);
                        flag[0] = true;
                      }
                    }
                  }
                }
              }
            }
          }
        }

      };

      // execute the command
      TransactionHelper.getExecutionManager(obj).execute(abstrctCommand);
      if (flag[0]) {
        try {
          marker.delete();
        } catch (CoreException exception) {
          _logger.error("Exception while deleting marker : " + exception.toString()); //$NON-NLS-1$
        }
      }
    }
  }

  abstract public void rename(AbstractNamedElement sourceNamedElement, AbstractNamedElement targetNamedElement);
}
