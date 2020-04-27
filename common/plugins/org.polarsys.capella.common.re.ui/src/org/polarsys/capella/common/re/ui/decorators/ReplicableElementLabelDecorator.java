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

package org.polarsys.capella.common.re.ui.decorators;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class ReplicableElementLabelDecorator implements ILabelDecorator {

  @Override
  public void addListener(ILabelProviderListener listener) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {

  }

  @Override
  public Image decorateImage(Image image, Object element) {
    // null means no decoration
    return null;
  }

  @Override
  public String decorateText(String text, Object element) {
    return text;
  }
}
