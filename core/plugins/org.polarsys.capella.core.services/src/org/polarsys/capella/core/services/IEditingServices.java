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

package org.polarsys.capella.core.services;

import java.util.List;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The Capella editing services.
 */
public interface IEditingServices {
  /**
   * Deletes the specified Capella elements.
   * @param elements_p The Capella elements to delete.
   * @return <code>True</code> if the elements is deleted else <code>false</code>.
   */
  public boolean delete(List<ModelElement> elements_p);

  /**
   * Renames the specified Capella element.
   * @param element_p The Capella element to rename.
   */
  public void rename(ModelElement element_p);

  /**
   * Gets the command to refine scenario.
   * @param element_p The element to refine.
   * @return The command to refine scenario.
   */
  public AbstractReadWriteCommand getScenarioRefinementCommand(ModelElement element_p);

  /**
   * Gets the command to update stereotype.
   * @param element_p The element to update.
   * @return The command to update stereotype.
   */
  public AbstractReadWriteCommand getUpdateStereotypeCommand(ModelElement element_p);
}
