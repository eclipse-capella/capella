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
package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;

abstract public class UpdateCategoriesController {
  Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * decides if elements are valid or not for update categories action
   * @param selection_p
   * @return
   */
  public static boolean isValidSelection(List<EObject> selection_p) {
    // functionalExchanges
    if (WizardActionHelper.areAllElementFunctionalExchange(selection_p)) {
      return true;
    }
    // componentExchanges
    else if (WizardActionHelper.areAllElementsComponentExchanges(selection_p)) {
      return true;
    }
    // physicalLinks
    else if (WizardActionHelper.areAllElementsPhysicalLinks(selection_p)) {
      return true;
    }

    return false;
  }

  /**
   * Return list of elements used for allocation or deployment
   * @param element_p
   * @param titleMessage_p
   * @return
   */
  public List<EObject> getAvailableCategories(List<EObject> element_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (element_p == null) {
      return result;
    }

    return null;
  }

  /**
   * @param selection_p
   * @return
   */
  public static UpdateCategoriesController createUpdateCategoriesController(List<EObject> selection_p) {
    if (selection_p.isEmpty()) {
      return null;
    }
    EObject first = selection_p.get(0);
    if (first instanceof FunctionalExchange) {
      return new UpdateFECategoriesController();
    }
    if (first instanceof ComponentExchange) {
      return new UpdateCECategoriesController();
    }
    if (first instanceof PhysicalLink) {
      return new UpdatePLCategoriesController();
    }
    return null;
  }

  protected void logResults(String message, List<EObject> categories) {
    for (EObject cat : categories) {
      if (cat instanceof NamedElement) {
        _logger.info(new EmbeddedMessage(StringHelper.formatMessage(message, new String[] { ((NamedElement) cat).getName() }),
            IReportManagerDefaultComponents.MODEL, cat));
      }
    }
  }

  /**
   * Add a given list of categories and remove another one from the given elements
   * @param selectedElements_p
   * @param categoriesToAdd_p
   * @param categoriesToRemove_p
   */
  public void updateCategories(List<EObject> selectedElements_p, List<EObject> categoriesToAdd_p, List<EObject> categoriesToRemove_p) {
    return;
  }

  /**
   * @param selection_p
   * @return
   */
  public List<EObject> getCommonCategories(List<EObject> selection_p) {
    return null;
  }

}
