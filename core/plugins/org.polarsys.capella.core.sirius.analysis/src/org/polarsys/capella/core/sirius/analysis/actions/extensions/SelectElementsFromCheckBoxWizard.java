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

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.CheckboxTreeDialog;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.PhysicalServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * PARAMETERS<br>
 * EObject context -- for getting the appropriate interpreter Map<br>
 * <ExchangeCategory,Collection<Function>> scope -- the set of selectable elements, may be null (empty collection)<br>
 * Map<ExchangeCategory,Collection<Function>> initialSelection -- the initial set of checked elements, may be null
 * (empty collection)<br>
 * String wizardMessage -- the message to display in the wizard, may be null (empty string)<br>
 * String resultVariable -- the Sirius variable to put the user selection into
 */
public class SelectElementsFromCheckBoxWizard extends AbstractExternalJavaAction {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    int exchange_category = 1;
    int component_category = 2;
    int physical_category = 3;
    int physical_links__physical_paths = 4;
    
    final String type = "type";

    AbstractDNode context = (AbstractDNode) parameters.get(CONTEXT);
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);

    int categoryType = exchange_category;
    if ("COMPONENT_CATEGORY".equals(parameters.get(type))) { //$NON-NLS-1$ //$NON-NLS-2$
      categoryType = component_category;

    } else if ("PHYSICAL_CATEGORY".equals(parameters.get(type))) { //$NON-NLS-1$ //$NON-NLS-2$
      categoryType = physical_category;

    } else if ("PHYSICAL_LINKS__PHYSICAL_PATHS".equals(parameters.get(type))) {
      categoryType = physical_links__physical_paths;
    }

    Map<? extends EObject, Collection<? extends EObject>> scope = null;
    Map<? extends EObject, Collection<? extends EObject>> initialSelection = null;

    // Scope
    if (categoryType == component_category) {
      scope = (Map) ABServices.getService().getABShowHideComponentCategoriesScope(context);

    } else if (categoryType == physical_category) {
      scope = (Map) ABServices.getService().getABShowHidePhysicalCategoriesScope(context);

    } else if (categoryType == physical_links__physical_paths) {
      scope = (Map) PhysicalServices.getService().getPPDInvolvePhysicalLinkAndPhysicalPathScope((DNode) context);

    } else {
      scope = (Map) FaServices.getFaServices().getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(context, content);
    }

    // Initial selection
    if (categoryType == component_category) {
      initialSelection = (Map) ABServices.getService().getABShowHideComponentCategoriesInitialSelection(context);

    } else if (categoryType == physical_category) {
      initialSelection = (Map) ABServices.getService().getABShowHidePhysicalCategoriesInitialSelection(context);

    } else if (categoryType == physical_links__physical_paths) {
      initialSelection = (Map) PhysicalServices.getService().getInvolvePhysicalLinkAndPhysicalPathInitialSelection(context);

    } else {
      initialSelection = (Map) FaServices.getFaServices().getCategoriesAndFunctionsInitialSelectionInDataFlowBlank((DNodeContainer) context, content);
    }

    String wizardMessage = (String) parameters.get(WIZARD_MESSAGE);
    String resultVariable = (String) parameters.get(RESULT_VARIABLE);

    // Pre-conditions.
    if (null == wizardMessage) {
      wizardMessage = ICommonConstants.EMPTY_STRING;
    }

    // Create a checkBox dialog.
    CheckboxTreeDialog<ExchangeCategory, AbstractFunction> dialog = new CheckboxTreeDialog<ExchangeCategory, AbstractFunction>(getShell(),
        "Select Elements To Show", wizardMessage);
    dialog.setInput((Map) scope, (Map) initialSelection);

    if (Window.OK == dialog.open()) {

      if (categoryType == component_category) {
        ABServices.getService().showABComponentCategories(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else if (categoryType == physical_category) {
        ABServices.getService().showABPhysicalCategories(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else if (categoryType == physical_links__physical_paths) {
        PhysicalServices.getService().involvedPPDPhysicalLinkPhysicalPath(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else {
        FaServices.getFaServices().showFECategories(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));
      }

      InterpreterUtil.getInterpreter(context).setVariable(resultVariable, dialog.getResult().keySet());
    } else {
      InterpreterUtil.getInterpreter(context).setVariable(resultVariable, WIZARD_CANCELED);
    }

  }
}
