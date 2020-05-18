/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.delete;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;

import org.polarsys.capella.core.model.handler.command.IDeleteHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.RePackage;

/**
 *
 */
public class ReDeleteHelper implements IDeleteHelper {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<?> getExpandedSelection(Collection<?> selection_p) {
    return Collections.emptySet();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteSemanticStructure(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    if (RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET.equals(feature_p)) {
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteElement(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> getAdditionalElements(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Command> getAdditionalCommands(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    if (sourceObject_p instanceof CatalogElement) {
      if (linkObject_p instanceof CatalogElement) {
        if (RePackage.Literals.CATALOG_ELEMENT__ORIGIN.equals(feature_p)) {

          CatalogElement source = (CatalogElement) sourceObject_p;
          CatalogElement link = (CatalogElement) linkObject_p;
          if (source.getKind() == CatalogElementKind.RPL) {
            if (link.getKind() == CatalogElementKind.REC) {
              return Collections.singleton(SetCommand.create(TransactionHelper.getEditingDomain(sourceObject_p), sourceObject_p,
                  RePackage.Literals.CATALOG_ELEMENT__KIND, CatalogElementKind.REC));
            }
          }
        }
      }
    }
    return null;
  }
}
