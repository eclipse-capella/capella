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
package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

/**
 * Base class to implement label providers with tooltip support to use in JFace viewers.
 */
public abstract class AbstractTooltipLabelProvider extends CellLabelProvider implements ILabelProvider {
  /**
   * Label provider used to get text and image for given object.
   */
  private ILabelProvider _labelProvider;

  /**
   * Constructor.
   * @param labelProvider_p label provider that provides the text and the image for given element.
   */
  protected AbstractTooltipLabelProvider(ILabelProvider labelProvider_p) {
    _labelProvider = labelProvider_p;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void addListener(ILabelProviderListener listener_p) {
    super.addListener(listener_p);
    _labelProvider.addListener(listener_p);
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    _labelProvider.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
   */
  public Image getImage(Object element_p) {
    return _labelProvider.getImage(element_p);
  }

  /**
   * @return the labelProvider
   */
  protected ILabelProvider getLabelProvider() {
    return _labelProvider;
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
   */
  public String getText(Object element_p) {
    return _labelProvider.getText(element_p);
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
   */
  @Override
  public abstract String getToolTipText(Object element_p);

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  @Override
  public boolean isLabelProperty(Object element_p, String property_p) {
    return super.isLabelProperty(element_p, property_p) && _labelProvider.isLabelProperty(element_p, property_p);
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void removeListener(ILabelProviderListener listener_p) {
    super.removeListener(listener_p);
    _labelProvider.removeListener(listener_p);
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
   */
  @Override
  public void update(ViewerCell viewer_p) {
    Object element = viewer_p.getElement();
    viewer_p.setText(getText(element));
    viewer_p.setImage(getImage(element));
  }
}
