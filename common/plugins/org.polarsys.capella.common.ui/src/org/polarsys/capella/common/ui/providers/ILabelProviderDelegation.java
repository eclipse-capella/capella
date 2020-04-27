/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.providers;

import org.eclipse.swt.graphics.Image;

/**
 * Used to delegate additional behavior in label provider.
 */
public interface ILabelProviderDelegation {
  /**
   * Get image.
   * @param element
   * @param initialImage
   * @return
   */
  public Image getImage(Image initialImage, Object element);

  /**
   * Get text.
   * @param element
   * @return
   */
  public String getText(String initialText, Object element);
}
