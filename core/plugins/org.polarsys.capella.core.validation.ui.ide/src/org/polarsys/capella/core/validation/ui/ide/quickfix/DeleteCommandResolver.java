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
    setContributorId(org.polarsys.capella.core.ui.toolkit.Activator.PLUGIN_ID);
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
