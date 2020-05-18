/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.information.Class;

/**
 * A resolver for org.eclipse.emf.ecore.4 style markers (Unresolved proxy (reference) to the super type of a Class).
 * 
 * This fix simply deletes the Generalization link that has no solvable super
 * 
 */

public class DanglingReferenceResolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {
    List<EObject> generalizationsToDelete = new ArrayList<>();

    if (obj instanceof Generalization) {

      generalizationsToDelete.add((Generalization) obj);

    } else if (obj instanceof Class) {

      Class c = (Class) obj;
      EList<Generalization> genList = c.getOwnedGeneralizations();
      for (Generalization gen : genList) {

        GeneralizableElement sup = gen.getSuper();

        // If the super is a Proxy, it means that the resolution has failed
        if (null != sup && sup.eIsProxy()) {
          generalizationsToDelete.add(gen);
        }
      }
    } else if (obj instanceof CatalogElement) {
      CatalogElement catalogElement = (CatalogElement) obj;
      if (catalogElement.getOrigin() != null && catalogElement.getOrigin().eIsProxy()) {
        ExecutionManager executionManager = TransactionHelper.getExecutionManager(catalogElement);
        executionManager.execute(new AbstractReadWriteCommand() {
          @Override
          public void run() {
            catalogElement.setOrigin(null);
          }
        });
      }
    } else if (obj instanceof CatalogElementLink) {
      CatalogElementLink catalogElementLink = (CatalogElementLink) obj;
      if (catalogElementLink.getOrigin() != null && catalogElementLink.getOrigin().eIsProxy()) {
        ExecutionManager executionManager = TransactionHelper.getExecutionManager(catalogElementLink);
        executionManager.execute(new AbstractReadWriteCommand() {
          @Override
          public void run() {
            catalogElementLink.setOrigin(null);
          }
        });
      }
    }

    return generalizationsToDelete;
  }
}
