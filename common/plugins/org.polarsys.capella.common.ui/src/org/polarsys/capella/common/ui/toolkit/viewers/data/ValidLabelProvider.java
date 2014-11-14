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
package org.polarsys.capella.common.ui.toolkit.viewers.data;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 *
 */
public class ValidLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {

  private Font _invalidElementFont;

  private ILabelProvider _labelProvider;

  /**
   * @param adapterFactory_p
   */
  public ValidLabelProvider(ILabelProvider labelProvider_p) {
    _labelProvider = labelProvider_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object_p) {
    return _labelProvider.getImage(object_p);
  }

  @Override
  public String getText(Object object_p) {
    return _labelProvider.getText(object_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addListener(ILabelProviderListener listener_p) {
    _labelProvider.addListener(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    _labelProvider.dispose();

    // Dispose created font.
    if (null != _invalidElementFont) {
      if (!_invalidElementFont.isDisposed()) {
        _invalidElementFont.dispose();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLabelProperty(Object element_p, String property_p) {
    return _labelProvider.isLabelProperty(element_p, property_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(ILabelProviderListener listener_p) {
    _labelProvider.removeListener(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element_p) {
    if (!isValid(element_p)) {
      return getInvalidElementFont();
    }
    if (_labelProvider instanceof IFontProvider) {
      return ((IFontProvider) _labelProvider).getFont(element_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getForeground(Object element_p) {
    if (!isValid(element_p)) {
      return getInvalidElementColor();
    }
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getForeground(element_p);
    }
    return null;
  }

  /**
   * @return
   */
  protected Color getInvalidElementColor() {
    return Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
  }

  protected Color getInvalidElementBackground() {
    return Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
  }

  /**
   * Create invalid element font.
   */
  protected Font getInvalidElementFont() {
    // Lazy creation pattern.
    if (null == _invalidElementFont) {
      _invalidElementFont = new Font(Display.getDefault(), "Arial", 9, SWT.NORMAL | SWT.ITALIC); //$NON-NLS-1$
    }
    return _invalidElementFont;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getBackground(Object element_p) {
    if (!isValid(element_p)) {
      return getInvalidElementBackground();
    }
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getBackground(element_p);
    }
    return null;
  }

  /**
   * @param element_p
   * @return
   */
  protected boolean isValid(Object element_p) {
    return true;
  }

}
