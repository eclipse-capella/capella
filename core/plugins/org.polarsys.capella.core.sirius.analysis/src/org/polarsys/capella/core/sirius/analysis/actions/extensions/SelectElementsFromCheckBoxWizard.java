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

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.CheckboxTreeDialog;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.PhysicalServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * PARAMETERS<br>
 * EObject context -- for getting the appropriate interpreter Map<br>
 * <ExchangeCategory,Collection<Function>> scope -- the set of selectable elements, may be null (empty collection)<br>
 * Map<ExchangeCategory,Collection<Function>> initialSelection -- the initial set of checked elements, may be null (empty collection)<br>
 * String wizardMessage -- the message to display in the wizard, may be null (empty string)<br>
 * String resultVariable -- the Sirius variable to put the user selection into
 */
public class SelectElementsFromCheckBoxWizard extends AbstractExternalJavaAction {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    int EXCHANGE_CATEGORY = 1;
    int COMPONENT_CATEGORY = 2;
    int PHYSICAL_CATEGORY = 3;
    int EXCHANGES_FUNCTIONALCHAINS = 4;
    int PHYSICAL_LINKS__PHYSICAL_PATHS = 5;

    AbstractDNode context = (AbstractDNode) parameters.get(CONTEXT);
    DDiagram currentDiagram = CapellaServices.getService().getDiagramContainer(context);
    DDiagramContents content = new DDiagramContents(currentDiagram);

    int categoryType = EXCHANGE_CATEGORY;
    if ("COMPONENT_CATEGORY".equals(parameters.get("type"))) { //$NON-NLS-1$ //$NON-NLS-2$
      categoryType = COMPONENT_CATEGORY;

    } else if ("PHYSICAL_CATEGORY".equals(parameters.get("type"))) { //$NON-NLS-1$ //$NON-NLS-2$
      categoryType = PHYSICAL_CATEGORY;

    } else if ("EXCHANGES_FUNCTIONALCHAINS".equals(parameters.get("type"))) { //$NON-NLS-1$ //$NON-NLS-2$
      categoryType = EXCHANGES_FUNCTIONALCHAINS;

    } else if ("PHYSICAL_LINKS__PHYSICAL_PATHS".equals(parameters.get("type"))) {
      categoryType = PHYSICAL_LINKS__PHYSICAL_PATHS;
    }

    Map<? extends EObject, Collection<? extends EObject>> scope = null;
    Map<? extends EObject, Collection<? extends EObject>> initialSelection = null;

    // Scope
    if (categoryType == COMPONENT_CATEGORY) {
      scope = (Map) ABServices.getService().getABShowHideComponentCategoriesScope(context);

    } else if (categoryType == PHYSICAL_CATEGORY) {
      scope = (Map) ABServices.getService().getABShowHidePhysicalCategoriesScope(context);

    } else if (categoryType == EXCHANGES_FUNCTIONALCHAINS) {
      scope = (Map) FunctionalChainServices.getFunctionalChainServices().getFCDInvolveFunctionalExchangeAndFunctionalChainScope((DNode) context);

    } else if (categoryType == PHYSICAL_LINKS__PHYSICAL_PATHS) {
      scope = (Map) PhysicalServices.getService().getPPDInvolvePhysicalLinkAndPhysicalPathScope((DNode) context);

    } else {
      scope = (Map) FaServices.getFaServices().getAvailableCategoriesAndFunctionsToInsertInDataFlowBlank(context, content);
    }

    if (null == scope) {
      scope = (Map) new HashMapSet<ExchangeCategory, AbstractFunction>();
    }

    //Initial selection
    if (categoryType == COMPONENT_CATEGORY) {
      initialSelection = (Map) ABServices.getService().getABShowHideComponentCategoriesInitialSelection(context);

    } else if (categoryType == PHYSICAL_CATEGORY) {
      initialSelection = (Map) ABServices.getService().getABShowHidePhysicalCategoriesInitialSelection(context);

    } else if (categoryType == EXCHANGES_FUNCTIONALCHAINS) {
      initialSelection = (Map) FunctionalChainServices.getFunctionalChainServices().getFCDInvolveFunctionalExchangeAndFunctionalChainInitialSelection(context);

    } else if (categoryType == PHYSICAL_LINKS__PHYSICAL_PATHS) {
      initialSelection = (Map) PhysicalServices.getService().getInvolvePhysicalLinkAndPhysicalPathInitialSelection(context);

    } else {
      initialSelection = (Map) FaServices.getFaServices().getCategoriesAndFunctionsInitialSelectionInDataFlowBlank((DNodeContainer) context, content);
    }

    if (null == initialSelection) {
      initialSelection = (Map) new HashMapSet<ExchangeCategory, AbstractFunction>();
    }

    String wizardMessage = (String) parameters.get(WIZARD_MESSAGE);
    String resultVariable = (String) parameters.get(RESULT_VARIABLE);

    // Pre-conditions.
    if (null == wizardMessage) {
      wizardMessage = ICommonConstants.EMPTY_STRING;
    }

    // Create a checkBox dialog.
    CheckboxTreeDialog<ExchangeCategory, AbstractFunction> dialog =
        new CheckboxTreeDialog<ExchangeCategory, AbstractFunction>(getShell(), "Select Elements To Show", wizardMessage, //$NON-NLS-1$
            (AdapterFactoryEditingDomain) TransactionHelper.getEditingDomain(context));
    dialog.setInput((Map) scope, (Map) initialSelection);

    if (Window.OK == dialog.open()) {

      if (categoryType == COMPONENT_CATEGORY) {
        ABServices.getService().showABComponentCategories(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else if (categoryType == PHYSICAL_CATEGORY) {
        ABServices.getService().showABPhysicalCategories(context, new HashMapSet(scope), new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else if (categoryType == EXCHANGES_FUNCTIONALCHAINS) {
        FunctionalChainServices.getFunctionalChainServices().involvedFCDFunctionalExchangeFunctionalChain(context, new HashMapSet(scope),
            new HashMapSet(initialSelection), new HashMapSet(dialog.getResult()));

      } else if (categoryType == PHYSICAL_LINKS__PHYSICAL_PATHS) {
        PhysicalServices.getService().involvedPPDPhysicalLinkPhysicalPath(context, new HashMapSet(scope), new HashMapSet(initialSelection),
            new HashMapSet(dialog.getResult()));

      } else {
        FaServices.getFaServices().showHideCategoriesInDataFlowBlank(context, new HashMapSet(dialog.getResult()), currentDiagram);
      }

      InterpreterUtil.getInterpreter(context).setVariable(resultVariable, dialog.getResult().keySet());
    } else {
      InterpreterUtil.getInterpreter(context).setVariable(resultVariable, WIZARD_CANCELED);
    }

  }
}
