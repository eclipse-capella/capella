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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 * PARAMETERS <br>
 * EObject context -- for getting the appropriate interpreter <br>
 * List<EObject> scope -- the set of elements to select from, may be null (empty collection)<br>
 * List<EObject> initialSelection -- the initial set of elements on the right side, may be null (empty collection)<br>
 * String wizardMessage -- the message to display in the wizard, may be null (empty string)<br>
 * String resultVariable -- the Sirius variable to put the user selection into<br>
 */
public class SelectElementsFromTransferWizard extends AbstractExternalJavaAction {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get(CONTEXT);
    List<EObject> scope = (List) parameters.get(SCOPE);
    List<EObject> initialSelection = (List) parameters.get(INITIAL_SELECTION);
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

    if ((scope.size() != 0) && (scope.get(0) instanceof InstanceRole)) {
      dialog =
          new TransferTreeListDialog(getShell(), Messages.SelectElementFromListWizard_Title, wizardMessage, new IRDataLabelProvider(),
              new IRDataLabelProvider());
    } else {
      dialog =
          new TransferTreeListDialog(getShell(), Messages.SelectElementFromListWizard_Title, wizardMessage, getCustoLabelProvider(), getCustoLabelProvider());
    }

    // Create the list of available elements.
    List<EObject> availableElements = new ArrayList<EObject>(scope);
    // Remove from available elements already selected elements.
    availableElements.removeAll(initialSelection);
    // Handle transfer dialog inputs.
    handleTransferDialogInputs(initialSelection, dialog, availableElements, handleContext(context));
    // I can't think of a better way to handle the cancel case since Acceleo does not
    // distinguish the null value from an empty collection
    Object result = (Window.OK == dialog.open()) ? dialog.getResult() : WIZARD_CANCELED;
    InterpreterUtil.getInterpreter(context).setVariable(resultVariable, result);
  }

  /**
   * Handle specified context.<br>
   * Default implementation returns <code>null</code>.
   * @param context_p
   * @return can be <code>null</code>.
   */
  protected Object handleContext(Object context_p) {
    return null;
  }

  /**
   * Handle transfer dialog inputs (i.e left and right viewers inputs).
   * @param initialSelection_p
   * @param dialog_p
   * @param availableElements_p
   * @param context_p
   */
  protected void handleTransferDialogInputs(List<EObject> initialSelection_p, TransferTreeListDialog dialog_p, List<EObject> availableElements_p,
      Object context_p) {
    dialog_p.setLeftInput(availableElements_p, context_p);
    dialog_p.setRightInput(initialSelection_p, context_p);
  }

  /**
   * A datalabelProvider specific for the case instanceRoles
   */
  class IRDataLabelProvider extends DataLabelProvider {

    /**
     * Default constructor
     */
    public IRDataLabelProvider() {
      super(MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
    }

    /**
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object_p) {
      if (object_p instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) object_p;
        AbstractInstance i = ir.getRepresentedInstance();
        if (i != null) {
          AbstractType t = i.getAbstractType();
          if (t != null) {
            return i.getName() + " : " + t.getName(); //$NON-NLS-1$
          }
        }
      }
      return super.getText(object_p);
    }
  }

  /*
   * return default label provider
   */
  public DataLabelProvider getCustoLabelProvider() {
    return new CapellaTransfertViewerLabelProvider();
  }
}
