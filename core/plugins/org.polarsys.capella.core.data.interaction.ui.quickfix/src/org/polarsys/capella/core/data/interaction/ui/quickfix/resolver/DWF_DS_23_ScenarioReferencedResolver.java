/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Set the referenced scenario for the selected interaction use
 * 
 */
public class DWF_DS_23_ScenarioReferencedResolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    List<EObject> modelElements = getModelElements(marker);
    EObject modelElement = modelElements.get(0);
    // Precondition.
    if (!(modelElement instanceof InteractionUse)) {
      return;
    }

    InteractionUse use = (InteractionUse) modelElement;
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // get the available referenced scenarios for the current use
        IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(use.eClass(),
            InteractionPackage.eINSTANCE.getInteractionUse_ReferencedScenario());
        if (query != null) {
          List<EObject> list = query.getAvailableElements(use);

          // calling selection wizard
          EObject firstResult = DialogHelper.openSimpleSelectionDialog(new Shell(), list);

          if (firstResult instanceof Scenario) {
            // Remove the associated marker.
            use.setReferencedScenario((Scenario) firstResult);
            try {
              marker.delete();
            } catch (CoreException exception) {
            }
          }
        }
      }
    };
    // execute the command
    TransactionHelper.getExecutionManager(modelElement).execute(command);
  }
}
