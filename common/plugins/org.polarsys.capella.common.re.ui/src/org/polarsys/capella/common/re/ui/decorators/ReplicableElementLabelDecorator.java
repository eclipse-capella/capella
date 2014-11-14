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
package org.polarsys.capella.common.re.ui.decorators;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class ReplicableElementLabelDecorator implements ILabelDecorator {

  @Override
  public void addListener(ILabelProviderListener listener_p) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public boolean isLabelProperty(Object element_p, String property_p) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener_p) {

  }

  @Override
  public Image decorateImage(Image image_p, Object element_p) {
    // null means no decoration
    return null;
  }

  @Override
  public String decorateText(String text_p, Object element_p) {
    return text_p;
  }
}
