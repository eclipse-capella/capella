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
   * @param labelProvider label provider that provides the text and the image for given element.
   */
  protected AbstractTooltipLabelProvider(ILabelProvider labelProvider) {
    _labelProvider = labelProvider;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void addListener(ILabelProviderListener listener) {
    super.addListener(listener);
    _labelProvider.addListener(listener);
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
  public Image getImage(Object element) {
    return _labelProvider.getImage(element);
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
  public String getText(Object element) {
    return _labelProvider.getText(element);
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
   */
  @Override
  public abstract String getToolTipText(Object element);

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  @Override
  public boolean isLabelProperty(Object element, String property) {
    return super.isLabelProperty(element, property) && _labelProvider.isLabelProperty(element, property);
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void removeListener(ILabelProviderListener listener) {
    super.removeListener(listener);
    _labelProvider.removeListener(listener);
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
   */
  @Override
  public void update(ViewerCell viewer) {
    Object element = viewer.getElement();
    viewer.setText(getText(element));
    viewer.setImage(getImage(element));
  }
}
