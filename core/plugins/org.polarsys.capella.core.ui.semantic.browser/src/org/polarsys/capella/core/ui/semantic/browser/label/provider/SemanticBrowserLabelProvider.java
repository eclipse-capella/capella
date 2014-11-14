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
package org.polarsys.capella.core.ui.semantic.browser.label.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;

/**
 */
public class SemanticBrowserLabelProvider extends CapellaNavigatorLabelProvider implements IColorProvider, IFontProvider {
  /**
   * The font used for category, to not forget to dispose it
   */
  private Font _font;

  public SemanticBrowserLabelProvider(AdapterFactory adapterFactory_p) {
    super(adapterFactory_p);
  }

  public SemanticBrowserLabelProvider(TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p) {
    super(editingDomain_p, adapterFactory_p);
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element_p) {
    // Precondition.
    if (null == element_p) {
      return null;
    }
    // Initialize with the category image.
    Image result = CapellaBrowserActivator.getDefault().getImage(IImageKeys.IMG_CATEGORY);
    EObject modelElement = null;
    // Find out a model element from given element.
    if (element_p instanceof EObjectWrapper) {
      modelElement = (EObject) ((EObjectWrapper) element_p).getElement();
    } else if (element_p instanceof EObject) {
      modelElement = (EObject) element_p;
    }
    // If a model element was found, get its image.
    if (null != modelElement) {
      result = super.getImage(modelElement);
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    String result = null;
    // Precondition.
    if (null == element_p) {
      return result;
    }
    if (element_p instanceof CategoryWrapper) {
      Object modelElement = ((BrowserElementWrapper) element_p).getElement();
      result = ((ICategory) modelElement).getName();
    } else {
      Object modelElement = null;
      if (element_p instanceof EObjectWrapper) {
        modelElement = ((BrowserElementWrapper) element_p).getElement();
      } else {
        modelElement = element_p;
      }
      result = super.getText(modelElement);
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    if (null != _font) {
      _font.dispose();
      _font = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  @Override
  public boolean isLabelProperty(Object element_p, String property_p) {
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element_p) {
    // Do nothing.
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element_p) {
    if (element_p instanceof CategoryWrapper) {
      return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_BLACK);
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element_p) {
    if (element_p instanceof CategoryWrapper) {
      if (null == _font) {
        Display defaultDisplay = Display.getDefault();
        Font result = defaultDisplay.getSystemFont();
        FontData fontData = result.getFontData()[0];
        fontData.setStyle(SWT.BOLD);
        _font = new Font(defaultDisplay, fontData);
      }
      return _font;
    }
    return null;
  }

  public String getToolTipText(Object element_p) {
    return null;
  }
}
