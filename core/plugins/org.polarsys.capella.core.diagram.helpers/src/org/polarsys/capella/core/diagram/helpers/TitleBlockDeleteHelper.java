/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.model.handler.command.IDeleteHelper;

public class TitleBlockDeleteHelper implements IDeleteHelper {

  @Override
  public Collection<?> getExpandedSelection(Collection<?> selection) {
    Collection<Object> expandedSelection = new ArrayList<>(selection);

    for (Object element : selection) {
      if (element instanceof DAnnotation) {
        DAnnotation annotation = (DAnnotation) element;

        if (TitleBlockHelper.isTitleBlock(annotation)) {
          expandedSelection.addAll(TitleBlockHelper.getAllContents(annotation));
        }
      }
    }

    return expandedSelection;
  }

  @Override
  public boolean isDeleteSemanticStructure(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    return false;
  }

  @Override
  public boolean isDeleteElement(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    return false;
  }

  @Override
  public Collection<EObject> getAdditionalElements(EObject sourceObject, EObject linkObject,
      EStructuralFeature feature) {
    return Collections.emptyList();
  }

  @Override
  public Collection<Command> getAdditionalCommands(EObject sourceObject, EObject linkObject,
      EStructuralFeature feature) {
    return Collections.emptyList();
  }

}
