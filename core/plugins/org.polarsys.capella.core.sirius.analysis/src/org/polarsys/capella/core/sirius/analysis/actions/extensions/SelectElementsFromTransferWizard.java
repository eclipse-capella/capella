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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;

/**
 * PARAMETERS <br>
 * EObject context -- for getting the appropriate interpreter <br>
 * List<EObject> scope -- the set of elements to select from, may be null (empty collection)<br>
 * List<EObject> initialSelection -- the initial set of elements on the right side, may be null (empty collection)<br>
 * String wizardMessage -- the message to display in the wizard, may be null (empty string)<br>
 * String resultVariable -- the Sirius variable to put the user selection into<br>
 */
public class SelectElementsFromTransferWizard extends AbstractExternalJavaAction {

  @SuppressWarnings({ "unchecked" })
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get(CONTEXT);
    Collection<EObject> scope = (Collection<EObject>) parameters.get(SCOPE);
    Collection<EObject> initialSelection = (Collection<EObject>) parameters.get(INITIAL_SELECTION);
    String wizardMessage = (String) parameters.get(WIZARD_MESSAGE);
    String resultVariable = (String) parameters.get(RESULT_VARIABLE);

    // Pre-conditions.
    if (null == wizardMessage) {
      wizardMessage = ICommonConstants.EMPTY_STRING;
    }
    if (null == scope) {
      scope = Collections.emptyList();
    }
    if (null == initialSelection) {
      initialSelection = Collections.emptyList();
    }

    // Create a transfer dialog.
    TransferTreeListDialog dialog;
    boolean expandLeftViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandLeftViewerContent();
    boolean expandRightViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandRightViewerContent();
    int leftViewerExpandLevel = expandLeftViewer ? AbstractTreeViewer.ALL_LEVELS : 0;
    int rightViewerExpandLevel = expandRightViewer ? AbstractTreeViewer.ALL_LEVELS : 0;

    if (!scope.isEmpty() && (scope.iterator().next() instanceof InstanceRole)) {
      dialog = new TransferTreeListDialog(getShell(), Messages.SelectElementFromListWizard_Title, wizardMessage,
          new IRDataLabelProvider(),
          new IRDataLabelProvider(), leftViewerExpandLevel,
          rightViewerExpandLevel);
    } else {
      dialog = new TransferTreeListDialog(getShell(), Messages.SelectElementFromListWizard_Title, wizardMessage,
          getCustoLabelProvider(), getCustoLabelProvider(), leftViewerExpandLevel, rightViewerExpandLevel);
    }

    // Create the list of available elements.
    List<EObject> availableElements = new ArrayList<>(scope);
    // Remove from available elements already selected elements.
    availableElements.removeAll(initialSelection);

    // Handle transfer dialog inputs.
    List<EObject> initialTransferDialogSelection = new ArrayList<>(initialSelection);
    handleTransferDialogInputs(initialTransferDialogSelection, dialog, availableElements, handleContext(context));

    // I can't think of a better way to handle the cancel case since Acceleo does not
    // distinguish the null value from an empty collection
    Object result = (Window.OK == dialog.open()) ? Arrays.asList(dialog.getResult()) : WIZARD_CANCELED;
    InterpreterUtil.getInterpreter(context).setVariable(resultVariable, result);
  }

  /**
   * Handle specified context.<br>
   * Default implementation returns <code>null</code>.
   * 
   * @param context
   * @return can be <code>null</code>.
   */
  protected Object handleContext(Object context) {
    return null;
  }

  /**
   * Handle transfer dialog inputs (i.e left and right viewers inputs).
   * 
   * @param initialSelection
   * @param dialog
   * @param availableElements
   * @param context
   */
  protected void handleTransferDialogInputs(List<EObject> initialSelection, TransferTreeListDialog dialog,
      List<EObject> availableElements, Object context) {
    dialog.setLeftInput(availableElements, context);
    dialog.setRightInput(initialSelection, context);
  }

  /**
   * A datalabelProvider specific for the case instanceRoles
   */
  class IRDataLabelProvider extends DataLabelProvider {
    @Override
    public String getText(Object object) {
      if (object instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) object;
        AbstractInstance i = ir.getRepresentedInstance();
        if (i != null) {
          AbstractType t = i.getAbstractType();
          if (t != null) {
            return i.getName() + " : " + t.getName(); //$NON-NLS-1$
          }
        }
      }
      return super.getText(object);
    }
  }

  /*
   * return default label provider
   */
  public DataLabelProvider getCustoLabelProvider() {
    return new CapellaTransfertViewerLabelProvider();
  }
}
