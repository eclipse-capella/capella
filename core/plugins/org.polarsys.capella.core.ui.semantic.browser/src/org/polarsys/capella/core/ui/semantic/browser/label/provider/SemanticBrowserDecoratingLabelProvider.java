/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.label.provider;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;

@SuppressWarnings("restriction")
public class SemanticBrowserDecoratingLabelProvider extends NavigatorDecoratingLabelProvider {

  public SemanticBrowserDecoratingLabelProvider(ILabelProvider commonLabelProvider) {
    super(commonLabelProvider);
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof EObjectWrapper) {
      // Unwrap all other wrappers
      Object wrappedElement = ((EObjectWrapper) element).getElement();
      return super.getImage(wrappedElement);
    }
    return super.getImage(element);
  }

  @Override
  protected StyledString getStyledText(Object element) {
    if (element instanceof EObjectWrapper) {
      // Unwrap all other wrappers
      Object wrappedElement = ((EObjectWrapper) element).getElement();
      return super.getStyledText(wrappedElement);
    }
    return super.getStyledText(element);
  }

  @Override
  public String getText(Object element) {
    if (element instanceof EObjectWrapper) {
      // Unwrap all other wrappers
      Object wrappedElement = ((EObjectWrapper) element).getElement();
      return super.getText(wrappedElement);
    }
    return super.getText(element);
  }
}
