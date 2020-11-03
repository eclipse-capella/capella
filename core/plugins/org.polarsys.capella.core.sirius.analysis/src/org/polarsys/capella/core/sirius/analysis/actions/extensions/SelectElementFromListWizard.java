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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;

/**
 * PARAMETERS EObject context -- for getting the appropriate interpreter Collection<EObject> scope -- the set of elements to select from, may be null (empty
 * collection) boolean multiple -- whether multiple selection is accepted or not, may be null (false) String wizardMessage -- the message to display in the
 * wizard, may be null (empty string) String resultVariable -- the Sirius variable to put the user selection into
 */
public class SelectElementFromListWizard extends AbstractExternalJavaAction {
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Object execute(EObject context, Collection scope_p, String wizardMessage_p, boolean multiple, boolean displayDialog) {
    String wizardMessage = wizardMessage_p;
    Collection scope = scope_p;
    if (wizardMessage == null) {
      wizardMessage = ICommonConstants.EMPTY_STRING;
    }
    if (scope == null) {
      scope = Collections.emptyList();
    }
    if(scope.size() == 1 && !displayDialog){
      return scope.iterator().next();
    }
    boolean expandViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandSingleViewerContent();
    int viewerExpandLevel = expandViewer ? AbstractTreeViewer.ALL_LEVELS : 0;
    SelectElementsDialog selectionDialog = new SelectElementsDialog(getShell(),
    		new CapellaTransfertViewerLabelProvider(),
            Messages.SelectElementFromListWizard_Title,
            wizardMessage,
            new ArrayList<EObject>(scope),
            multiple,
            null, viewerExpandLevel);

    Object result = WIZARD_CANCELED;
    if (Window.OK == selectionDialog.open()) {
      List<? extends EObject> selectedElements = selectionDialog.getResult();
      result = selectedElements;
      if (!multiple && !selectedElements.isEmpty()) {
        // Mono selection case.
        result = selectedElements.get(0);
      }
    }
    if (result.equals(WIZARD_CANCELED)) {
      throw  new OperationCanceledException();
    }
    return result;
  }

  @SuppressWarnings("rawtypes")
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

    EObject context = (EObject) parameters.get(CONTEXT);
    Collection scope = (Collection) parameters.get(SCOPE);
    String wizardMessage = (String) parameters.get(WIZARD_MESSAGE);
    String resultVariable = (String) parameters.get(RESULT_VARIABLE);
    String sMultiple = (String) parameters.get(MULTIPLE);
    String sDisplayDialog = (String) parameters.get(DISPLAY_DIALOG_FOR_ONE_ELEMENT);

    Assert.isNotNull(context);
    Assert.isNotNull(resultVariable);
    boolean multiple = ("true".equals(sMultiple)) ? true : false; //$NON-NLS-1$
    boolean displayDialog = sDisplayDialog == null ? true : ("true".equals(sDisplayDialog)) ? true : false;

    Object result = execute(context, scope, wizardMessage, multiple, displayDialog);
    InterpreterUtil.getInterpreter(context).setVariable(resultVariable, result);
  }
}
