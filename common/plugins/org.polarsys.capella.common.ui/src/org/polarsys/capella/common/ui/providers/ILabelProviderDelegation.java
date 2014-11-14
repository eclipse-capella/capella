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
package org.polarsys.capella.common.ui.providers;

import org.eclipse.swt.graphics.Image;

/**
 * Used to delegate additional behavior in label provider.
 */
public interface ILabelProviderDelegation {
  /**
   * Get image.
   * @param element_p
   * @param initialImage_p
   * @return
   */
  public Image getImage(Image initialImage_p, Object element_p);

  /**
   * Get text.
   * @param element_p
   * @return
   */
  public String getText(String initialText_p, Object element_p);
}
