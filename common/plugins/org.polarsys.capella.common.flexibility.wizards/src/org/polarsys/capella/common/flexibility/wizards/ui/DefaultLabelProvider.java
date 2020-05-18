/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

public class DefaultLabelProvider implements ILabelProvider, IColorProvider, IFontProvider {

  private ILabelProvider _labelProvider = null;

  /**
   * @param labelProvider
   */
  public DefaultLabelProvider(ILabelProvider labelProvider) {
    _labelProvider = labelProvider;
  }

  public DefaultLabelProvider() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object) {
    if (object instanceof Collection<?>) {
      if (((Collection) object).size() == 1) {
        return getImage(((Collection) object).iterator().next());
      }
    }
    if (_labelProvider != null) {
      return _labelProvider.getImage(object);
    }
    if (object instanceof IAdaptable) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) ((IAdaptable) object).getAdapter(IItemLabelProvider.class);
      if (labelProvider != null) {
        return ExtendedImageRegistry.getInstance().getImage(labelProvider.getImage(object));
      }
    }
    if (object instanceof EObject) {
      return ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage((EObject) object));
    }
    return null;

  }

  @Override
  public String getText(Object object) {

    if (object instanceof Collection<?>) {
      Collection<?> col = (Collection<?>) object;
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
      return _labelProvider.getText(object);
    }
    if (object instanceof IAdaptable) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) ((IAdaptable) object).getAdapter(IItemLabelProvider.class);
      if (labelProvider != null) {
        return labelProvider.getText(object);
      }
    }
    return EObjectLabelProviderHelper.getText(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addListener(ILabelProviderListener listener) {
    if (_labelProvider != null) {
      _labelProvider.addListener(listener);
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
  public boolean isLabelProperty(Object element, String property) {

    if (_labelProvider != null) {
      return _labelProvider.isLabelProperty(element, property);
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeListener(ILabelProviderListener listener) {
    if (_labelProvider != null) {
      _labelProvider.removeListener(listener);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element) {
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
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getForeground(element);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getBackground(Object element) {
    if (_labelProvider instanceof IColorProvider) {
      return ((IColorProvider) _labelProvider).getBackground(element);
    }
    return null;
  }

}
