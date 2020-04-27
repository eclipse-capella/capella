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
   * @param labelProvider
   */
  public ValidLabelProvider(ILabelProvider labelProvider) {
    _labelProvider = labelProvider;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object) {
    return _labelProvider.getImage(object);
  }

  @Override
  public String getText(Object object) {
    return _labelProvider.getText(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addListener(ILabelProviderListener listener) {
    _labelProvider.addListener(listener);
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
  public boolean isLabelProperty(Object element, String property) {
    return _labelProvider.isLabelProperty(element, property);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(ILabelProviderListener listener) {
    _labelProvider.removeListener(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element) {
    if (!isValid(element)) {
      return getInvalidElementFont();
    }
    if (_labelProvider instanceof IFontProvider) {
      return ((IFontProvider) _labelProvider).getFont(element);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getForeground(Object element) {
    if (!isValid(element)) {
      return getInvalidElementColor();
    }
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getForeground(element);
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
  public Color getBackground(Object element) {
    if (!isValid(element)) {
      return getInvalidElementBackground();
    }
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getBackground(element);
    }
    return null;
  }

  /**
   * @param element
   * @return
   */
  protected boolean isValid(Object element) {
    return true;
  }

}
