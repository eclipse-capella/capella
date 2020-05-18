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
package org.polarsys.capella.core.ui.metric.dialog;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateOrSelectElementCommand.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.ui.metric.core.MetricTree;

public class MetricLabelProvider extends LabelProvider implements ITableLabelProvider {

  protected static final String REGEX = "\\[|(\\].*)"; //$NON-NLS-1$

  public static final int OBJ_COLUMN_INDEX = 0;
  public static final int QTY_COLUMN_INDEX = 1;

  private static AdapterFactory capellaAdapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();

  public Image getColumnImage(Object element, int columnIndex) {

    Image image = null;

    if (OBJ_COLUMN_INDEX == columnIndex) {
      image = getImage(element);
    }

    return image;
  }

  @SuppressWarnings("unchecked")
  public String getColumnText(Object element, int columnIndex) {

    String text = null;

    switch (columnIndex) {
    case OBJ_COLUMN_INDEX:
      text = getText(element);
      break;

    case QTY_COLUMN_INDEX:
      MetricTree<EObject> metricTreeNode = (MetricTree<EObject>) element;
      int count = metricTreeNode.getCount();
      if (count > 0) {
        text = Integer.toString(count);
      }
      break;

    default:
      break;
    }

    return text;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Image getImage(Object element) {
    MetricTree<EObject> metricTreeNode = (MetricTree<EObject>) element;
    EObject eobject = metricTreeNode.getElement();

    IItemLabelProvider itemProvider = (IItemLabelProvider) capellaAdapterFactory.adapt(eobject,
        IItemLabelProvider.class);

    if (null != itemProvider) {
      return ExtendedImageRegistry.getInstance().getImage(itemProvider.getImage(eobject));
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getText(Object element) {
    MetricTree<EObject> metricTreeNode = (MetricTree<EObject>) element;
    EObject eObject = metricTreeNode.getElement();

    String text = "";

    IItemLabelProvider itemProvider = (IItemLabelProvider) capellaAdapterFactory.adapt(eObject,
        IItemLabelProvider.class);
    if (null != itemProvider) {
      text = itemProvider.getText(eObject);
    }

    if (text == null || text.isEmpty() || "[]".equals(text.trim()) || "[null]".equals(text.trim()) || "null".equals(text.trim())) {
      text = EObjectLabelProviderHelper.getMetaclassLabel(eObject, true);
    }

    return text.replaceAll(REGEX, "");
  }
}
