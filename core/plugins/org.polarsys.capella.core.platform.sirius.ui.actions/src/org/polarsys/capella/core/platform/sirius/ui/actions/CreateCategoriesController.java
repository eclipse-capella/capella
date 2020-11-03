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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public abstract class CreateCategoriesController {
  /**
   * Forbid empty String to be used as Exchange Category name.
   */
  public static final IInputValidator notEmptyTextValidator = new IInputValidator() {
    @Override
    public String isValid(String newText) {
      if (newText == null || newText.trim().isEmpty()) {
        return Messages.CreateCategoriesController_EmptyName_Error_Message;
      }
      return null;
    }
  };
  
  Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * Open an InputDialog to ask the user to give a name for a Category.
   * @param defaultName
   * @return
   */
  public String askCategoryName(String defaultName) {
    InputDialog inputDialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.CreateCategoriesController_create_cat, Messages.CreateCategoriesController_cat_name,
        defaultName, notEmptyTextValidator);
    inputDialog.open();
    return inputDialog.getValue();
  }
  
  
  public static CreateCategoriesController createCreateCategoriesController(List<EObject> selection) {
    if (selection.isEmpty()) {
      return null;
    }
    EObject first = selection.get(0);
    if (first instanceof FunctionalExchange) {
      return new CreateFECategoriesController();
    }
    if (first instanceof ComponentExchange) {
      return new CreateCECategoriesController();
    }
    if (first instanceof PhysicalLink) {
      return new CreatePLCategoriesController();
    }
    return null;
  }

  /**
   * Create a new Exchange Category.
   * @param selection the list of exchanges to add to the new Exchange Category
   * @param categoryNameToUse the name of the new Exchange Category, can be <code>null</code>, in this case an InputDialog will be shown to ask for a name
   */
  public abstract void createAndAttachCategory(List<EObject> selection, String categoryNameToUse);
  
  public void createAndAttachCategory(List<EObject> selection) {
    createAndAttachCategory(selection, null);
  }

  protected EObject createCategory(EObject container) {
    return null;
  }

  /**
   * @param categoryContainer
   * @param asList
   * @return
   */
  protected boolean isNullOrNotInstanceOf(EObject elt, List<EClass> eClasses) {
    boolean result = false;
    if (null == elt) {
      result = true;
    } else if (eClasses.isEmpty()) {
      result = false;
    } else {
      for (EClass eClass : eClasses) { // elt is instance of an eclass from the list ?
        if (eClass.isInstance(elt)) {
          result = false;
          return result;
        }
      }
      result = true;
    }
    return result;
  }

  /**
   * Computes the best container to store a category for selection
   * @param selection
   * @param expectedContainerType
   * @return
   */
  /**
   * {@inheritDoc}
   */
  protected EObject getBestContainerForCategory(List<EObject> selection, List<EClass> expectedContainerType) {
    EObject container;

    EObject commonAncestor = EcoreUtil2.getCommonAncestor(selection);
    if (EObjectExt.isInstanceOf(commonAncestor, expectedContainerType)) {
      container = commonAncestor;
    } else {
      container = EcoreUtil2.getFirstContainer(commonAncestor, expectedContainerType);
    }
    if (null == container) {// no correct container could be retrieved => return the Context
      BlockArchitecture currentArch = BlockArchitectureExt.getRootBlockArchitecture(selection.get(0));
      container = BlockArchitectureExt.getContext(currentArch, false);
    }
    return container;
  }

  protected void logResults(String message, NamedElement category) {
    _logger
        .info(new EmbeddedMessage(StringHelper.formatMessage(message, new String[] { category.getName() }), IReportManagerDefaultComponents.MODEL, category));
  }
}
