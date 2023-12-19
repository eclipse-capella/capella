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
package org.polarsys.capella.core.ui.semantic.browser.label.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.NavigatorContentServiceLabelProvider;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.PrimitiveWrapper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;

public class SemanticBrowserLabelProvider extends NavigatorContentServiceLabelProvider
    implements ILabelProvider, IColorProvider, IFontProvider, IToolTipProvider {
  
  protected static NavigatorContentService contentService = new NavigatorContentService(CapellaCommonNavigator.ID);

  
  public SemanticBrowserLabelProvider() {
    super(contentService);
  }

  /**
   * The font used for category, to not forget to dispose it
   */
  private Font _font;

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    // Precondition.
    if (null == element) {
      return null;
    }
    // Initialize with the category image.
    Image result = CapellaBrowserActivator.getDefault().getImage(IImageKeys.IMG_CATEGORY);
    EObject modelElement = null;
    // Find out a model element from given element.
    if (element instanceof EObjectWrapper) {
      modelElement = ((EObjectWrapper) element).getElement();
    } else if (element instanceof EObject) {
      modelElement = (EObject) element;
    } else if (element instanceof PrimitiveWrapper) {
      return CapellaBrowserActivator.getDefault().getImage(IImageKeys.IMG_PRIMITIVE_VARIABLES);
    }
    // If a model element was found, get its image.
    if (null != modelElement) {
      result = super.getImage(modelElement);
    }
    return result;
  }

  @Override
  public String getText(Object element) {
    String result = null;
    // Precondition.
    if (null == element) {
      return result;
    }
    if (element instanceof CategoryWrapper) {
      Object modelElement = ((BrowserElementWrapper) element).getElement();
      result = ((ICategory) modelElement).getName();
    } else if (element instanceof PrimitiveWrapper) {
      Object modelElement = ((PrimitiveWrapper) element).getElement();
      result = modelElement.toString();
    } else {
      Object modelElement = null;
      if (element instanceof EObjectWrapper) {
        modelElement = ((BrowserElementWrapper) element).getElement();
      } else {
        modelElement = element;
      }
      result = super.getText(modelElement);
    }
    return result;
  }
  
  @Override
  public StyledString getStyledText(Object element) {
    // Precondition.
    if (null == element) {
      return null;
    }
    
    if (element instanceof EObjectWrapper) {
        element = ((BrowserElementWrapper) element).getElement();      
    }
    
    if (element instanceof BrowserElementWrapper) {
      return new StyledString(getText(element));
    }
    
    return super.getStyledText(element);
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
  public Color getForeground(Object element) {
    if (element instanceof CategoryWrapper) {
      return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_BLACK);
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element) {
    if (element instanceof CategoryWrapper) {
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

  @Override
  public String getToolTipText(Object element) {
    return null;
  }
  
  

}
