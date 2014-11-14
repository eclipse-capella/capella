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
package org.polarsys.capella.common.flexibility.wizards.ui;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;

/**
 *
 */
public class DefaultLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {

  private ILabelProvider _labelProvider = null;

  /**
   * @param adapterFactory_p
   */
  public DefaultLabelProvider(ILabelProvider labelProvider_p) {
    _labelProvider = labelProvider_p;
  }

  public DefaultLabelProvider() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object_p) {
    if (object_p instanceof Collection<?>) {
      if (((Collection) object_p).size() == 1) {
        return getImage(((Collection) object_p).iterator().next());
      }
    }
    if (_labelProvider != null) {
      return _labelProvider.getImage(object_p);
    }
    if (object_p instanceof IAdaptable) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) ((IAdaptable) object_p).getAdapter(IItemLabelProvider.class);
      if (labelProvider != null) {
        return ExtendedImageRegistry.getInstance().getImage(labelProvider.getImage(object_p));
      }
    }
    if (object_p instanceof EObject) {
      return EObjectLabelProviderHelper.getImage((EObject) object_p);
    }
    return null;

  }

  @Override
  public String getText(Object object_p) {

    if (object_p instanceof Collection<?>) {
      Collection<?> col = (Collection<?>) object_p;
      StringBuffer result = new StringBuffer();
      int i = 0;
      for (Object a : col) {
        result.append(getText(a));
        if (i++ < (col.size() - 1)) {
          result.append(ICommonConstants.SEMICOLON_CHARACTER);
          result.append(ICommonConstants.WHITE_SPACE_CHARACTER);
        }
      }
      return result.toString();
    }

    if (_labelProvider != null) {
      return _labelProvider.getText(object_p);
    }
    if (object_p instanceof IAdaptable) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) ((IAdaptable) object_p).getAdapter(IItemLabelProvider.class);
      if (labelProvider != null) {
        return labelProvider.getText(object_p);
      }
    }
    return EObjectLabelProviderHelper.getText(object_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addListener(ILabelProviderListener listener_p) {
    if (_labelProvider != null) {
      _labelProvider.addListener(listener_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if (_labelProvider != null) {
      _labelProvider.dispose();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLabelProperty(Object element_p, String property_p) {

    if (_labelProvider != null) {
      return _labelProvider.isLabelProperty(element_p, property_p);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(ILabelProviderListener listener_p) {
    if (_labelProvider != null) {
      _labelProvider.removeListener(listener_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element_p) {
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
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getForeground(element_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getBackground(Object element_p) {
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getBackground(element_p);
    }
    return null;
  }

}
