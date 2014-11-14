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
package org.polarsys.capella.common.ui.toolkit.browser.content.provider;

/**
 */
public interface IBrowserContentProvider {
  /**
   * Browser IDs.
   */
  static String ID_CURRENT_CP = "CurrentElementBrowser"; //$NON-NLS-1$
  static String ID_REFERENCED_CP = "ReferencedElementBrowser"; //$NON-NLS-1$
  static String ID_REFERENCING_CP = "ReferencingElementBrowser"; //$NON-NLS-1$

  /**
   * IdAccessor
   * @return
   */
  String getBrowserId();
}
