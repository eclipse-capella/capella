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
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;

/**
 * Utility class for Merge
 *
 */
public class MergeUtils {
  
  /**
   * Delete an {@link EObject}
   * @param eObject_p the object to delete
   * @see DeleteStructureCommand
   */
  public static void deleteElement(EObject eObject_p) {
    if (null != eObject_p) {
      deleteElements(Collections.singletonList(eObject_p));
    }
  }
  
  /**
   * Delete a list of {@link EObject}
   * @param eObjects_p the list of object to delete
   * @see DeleteStructureCommand
   */
  public static void deleteElements(List<EObject> eObjects_p) {
    if ( null != eObjects_p && !eObjects_p.isEmpty() ) {
      EditingDomain editingDomain = TransactionHelper.getEditingDomain(eObjects_p);
      if (null != editingDomain) {
        DeleteStructureCommand cmd = new DeleteStructureCommand(editingDomain, eObjects_p, false);
        cmd.execute();
      }
    }
  }
}
