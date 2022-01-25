/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

/**
 * Delete an Element, with confirmation also delete the marker (ok:yes, cancel:no)
 */
public class DeleteCommandResolver extends AbstractDeleteCommandResolver {
  /**
   * Delete icon path in <code>org.polarsys.capella.core.ui.toolkit</code> plugin.
   */
  public static final String DELETE_ICON_PATH = "icons/delete_edit.gif"; //$NON-NLS-1$
  /**
   * Element to delete.
   */
  protected final Object elementToDelete;

  /**
   * @param resolverLabel
   * @param elementToDelete
   */
  public DeleteCommandResolver(String resolverLabel, Object elementToDelete) {
    label = resolverLabel;
    this.elementToDelete = elementToDelete;
    setContributorId(org.polarsys.capella.core.ui.toolkit.Activator.getDefault().getBundle().getSymbolicName());
    setImgKey(DELETE_ICON_PATH);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getElementToDelete(Object obj) {
    return elementToDelete;
  }
}
