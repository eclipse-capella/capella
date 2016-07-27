/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
  Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

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

  public void createAndAttachCategory(List<EObject> selection) {
    if (selection.isEmpty()) {
      return;
    }

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
