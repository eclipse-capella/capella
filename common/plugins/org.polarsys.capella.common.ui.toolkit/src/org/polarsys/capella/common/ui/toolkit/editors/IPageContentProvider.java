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
package org.polarsys.capella.common.ui.toolkit.editors;

import org.eclipse.emf.ecore.EClass;

/**
 * The page content provider interface. Each {@link Editor} is composed with a single page which contains a list of tabs. The page content provider gives the
 * corresponding the tab descriptors list, the page title and the page description needed to build a specific page.
 */
public interface IPageContentProvider {
  /**
   * Gets the page identifier.
   * @return The page identifier.
   */
  public String getId();

  /**
   * Gets the element this editor applies to.
   * @return The element this editor applies to.
   */
  public EClass getElementType();

  /**
   * Gets the page title.
   * @return The page title.
   */
  public String getPageTitle();

  /**
   * Get the page description.
   * @return The page description.
   */
  public String getPageDescription();

  /**
   * Gets the tab descriptors list.
   * @return The tab descriptors list.
   */
  public ITabDescriptor[] getTabDescriptors();
}
