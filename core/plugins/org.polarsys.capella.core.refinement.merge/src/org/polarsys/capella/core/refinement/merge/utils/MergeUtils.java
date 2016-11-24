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
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;

/**
 * Utility class for Merge
 */
public class MergeUtils {
  
  /**
   * Delete an {@link EObject}
   * @param eObject the object to delete
   * @see DeleteStructureCommand
   */
  public static void deleteElement(EObject eObject) {
    if (null != eObject) {
      deleteElements(Collections.singletonList(eObject));
    }
  }
  
  /**
   * Delete a list of {@link EObject}
   * @param eObjects the list of object to delete
   * @see DeleteStructureCommand
   */
  public static void deleteElements(List<EObject> eObjects) {
    if ( null != eObjects && !eObjects.isEmpty() ) {
      EditingDomain editingDomain = TransactionHelper.getEditingDomain(eObjects);
      if (null != editingDomain) {
        DeleteStructureCommand cmd = new DeleteStructureCommand(editingDomain, eObjects, false);
        cmd.execute();
      }
    }
  }
}
