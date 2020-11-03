/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;

/**
 * Label provider that deals with {@link AbstractData} displayed in a {@link StructuredViewer}.<br>
 * The viewer must be set in this label provider after viewer creation time and before its setInput.
 */
public class DataLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider, IFontProvider {
  private TreeViewer _viewer;
  private Font _invalidElementFont;

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    // Dispose created font.
    if (null != _invalidElementFont) {
      if (!_invalidElementFont.isDisposed()) {
        _invalidElementFont.dispose();
      }
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element) {
    // Do nothing.
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element) {
    Font result = null;
    if (_viewer != null) {
      AbstractData input = (AbstractData) _viewer.getInput();
      if (!input.isValid(element)) {
        result = getInvalidElementFont();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element) {
    Color result = null;
    if (_viewer != null) {
      AbstractData input = (AbstractData) _viewer.getInput();
      if (!input.isValid(element)) {
        result = getInvalidElementColor();
      } else {
        result = getValidElementColor();
      }
    }
    return result;
  }

  /**
   * @return
   */
  protected Color getInvalidElementColor() {
    return Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
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
   * @return
   */
  protected Color getValidElementColor() {
    return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
  }

  /**
   * Set the viewer that uses this label provider to render text and images.
   * @param viewer
   */
  public void setViewer(TreeViewer viewer) {
    _viewer = viewer;
  }
}
