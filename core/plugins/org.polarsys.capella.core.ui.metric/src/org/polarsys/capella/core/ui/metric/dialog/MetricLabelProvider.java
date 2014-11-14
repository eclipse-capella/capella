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
package org.polarsys.capella.core.ui.metric.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.metric.core.MetricTree;
import org.polarsys.capella.core.ui.metric.utils.Utils;

/**
 */
public class MetricLabelProvider implements ITableLabelProvider, ILabelProvider {

  protected final static String _regex = "\\[|(\\].*)"; //$NON-NLS-1$

  private final int OBJ_COLUMN_INDEX = 0;
  private final int QTY_COLUMN_INDEX = 1;

  /**
   * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
   */
  @SuppressWarnings("unchecked")
  public Image getColumnImage(Object element_p, int columnIndex_p) {

    Image image = null;

    if (OBJ_COLUMN_INDEX == columnIndex_p) {
      MetricTree<EObject> element = (MetricTree<EObject>) element_p;
      MetricTree<EObject> tree = element;

      EObject eobject = tree.getId();

      image = Utils.getImage(eobject);
    }

    return image;
  }

  /**
   * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
   */
  @SuppressWarnings("unchecked")
  public String getColumnText(Object element_p, int columnIndex_p) {

    String text = null;

    MetricTree<EObject> element = (MetricTree<EObject>) element_p;

    if (OBJ_COLUMN_INDEX == columnIndex_p) {
      text = getLabelText(element.getId(), null);
    } else if (QTY_COLUMN_INDEX == columnIndex_p) {
      Integer value = element.getData();
      if (null != value) {
        text = value.toString();
      }

    }

    return text;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  public void addListener(ILabelProviderListener listener_p) {
    return;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  public void dispose() {
    return;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  public boolean isLabelProperty(Object element_p, String property_p) {
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  public void removeListener(ILabelProviderListener listener_p) {
    return;
  }

  static public String getLabelText(EObject eobject_p, String regex_p) {
    String tmp = Utils.getText(eobject_p);

    return tmp.replaceAll(null == regex_p ? _regex : regex_p, ICommonConstants.EMPTY_STRING);
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
   */
  public Image getImage(Object element_p) {
    return getColumnImage(element_p, OBJ_COLUMN_INDEX);
  }

  /**
   * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
   */
  public String getText(Object element_p) {
    return getColumnText(element_p, OBJ_COLUMN_INDEX);
  }

}
