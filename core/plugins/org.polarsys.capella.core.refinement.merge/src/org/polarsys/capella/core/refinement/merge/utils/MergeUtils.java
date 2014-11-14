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

import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractCommand;

/**
 * Utility class for Merge
 *
 */
public class MergeUtils {

  /**
   * Execute a command...
   * @param cmd_p the command to execute
   */
  public static void executeCmd(final AbstractCommand cmd_p) {
    MDEAdapterFactory.getExecutionManager().execute(cmd_p);
  }
  
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
      DeleteStructureCommand cmd = new DeleteStructureCommand(MDEAdapterFactory.getEditingDomain(), eObjects_p, false);
      cmd.execute();
    }
  }
}
