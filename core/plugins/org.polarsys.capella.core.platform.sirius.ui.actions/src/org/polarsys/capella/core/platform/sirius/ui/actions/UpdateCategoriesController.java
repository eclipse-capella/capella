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
   * @param selection
   * @return
   */
  public static boolean isValidSelection(List<EObject> selection) {
    // functionalExchanges
    if (WizardActionHelper.areAllElementFunctionalExchange(selection)) {
      return true;
    }
    // componentExchanges
    else if (WizardActionHelper.areAllElementsComponentExchanges(selection)) {
      return true;
    }
    // physicalLinks
    else if (WizardActionHelper.areAllElementsPhysicalLinks(selection)) {
      return true;
    }

    return false;
  }

  /**
   * Return list of elements used for allocation or deployment
   * @param element
   * @param titleMessage
   * @return
   */
  public List<EObject> getAvailableCategories(List<EObject> element) {
    List<EObject> result = new ArrayList<EObject>();
    if (element == null) {
      return result;
    }

    return null;
  }

  /**
   * @param selection
   * @return
   */
  public static UpdateCategoriesController createUpdateCategoriesController(List<EObject> selection) {
    if (selection.isEmpty()) {
      return null;
    }
    EObject first = selection.get(0);
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
   * @param selectedElements
   * @param categoriesToAdd
   * @param categoriesToRemove
   */
  public void updateCategories(List<EObject> selectedElements, List<EObject> categoriesToAdd, List<EObject> categoriesToRemove) {
    return;
  }

  /**
   * @param selection
   * @return
   */
  public List<EObject> getCommonCategories(List<EObject> selection) {
    return null;
  }

}
