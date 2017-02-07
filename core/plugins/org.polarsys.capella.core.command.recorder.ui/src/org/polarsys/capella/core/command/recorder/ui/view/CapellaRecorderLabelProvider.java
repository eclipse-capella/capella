/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.command.recorder.ui.view;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.command.recorder.core.manager.utils.NotificationEnum;
import org.polarsys.capella.common.command.recorder.core.manager.utils.OperationEnum;
import org.polarsys.capella.common.command.recorder.core.writer.DummyTreeData;
import org.polarsys.capella.common.command.recorder.ui.view.RecorderSharedImages;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 */
public class CapellaRecorderLabelProvider implements ITableLabelProvider {

  /**
   * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
   */
  public Image getColumnImage(Object element, int columnIndex) {

    Image img = null;

    if (0 == columnIndex) {

      DummyTreeData data = (DummyTreeData) element;

      //
      // ENTRY
      //
      if (data.getDepth() < 0) {

        String[] ds = (String[]) data.getData();

        switch (OperationEnum.getOperationEnum(ds[0])) {
          case DONE:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_DONE);
          break;
          case UNDO:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_UNDO);
          break;
          case REDO:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_REDO);
          break;
          case NOT_OK:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_DELETE);
          break;
          default:
            img = null;
          break;
        }

      } else if (data.getDepth() == 0) { // SUB 0

        String[] ds = (String[]) data.getData();

        switch (NotificationEnum.getOperationEnum(ds[1])) {
          case ADD:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_ADD);
          break;
          case ADD_MANY:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_ADD_MANY);
            break;
          case SET:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_SET);
          break;
          case REMOVE:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_DELETE);
          break;
          case REMOVE_MANY:
            img = RecorderSharedImages.getImage(RecorderSharedImages.DESC_DELETE_MANY);
            break;
          default:
            img = null;
          break;
        }

      } else if (data.getDepth() > 0) {

        if (data.hasSubData()) {

          String[] ds = (String[]) data.getSubData();

          EPackage ep = EPackage.Registry.INSTANCE.getEPackage(ds[1]);
          if (null != ep) {
            EClass ec = (EClass) ep.getEClassifier(ds[0]);
            if (null != ec) {
              img = getImage(ep.getEFactoryInstance().create(ec));
            }
          }
        }
      }
    }

    return img;
  }

  /**
   * Get image for specified {@link EObject}
   * @param eObject
   * @return <code>null</code> if not found.
   */
  private static Image getImage(EObject eObject) {
    Image result = EObjectImageProviderHelper.getImage(eObject);
    if (null == result) {
      ItemProviderAdapter itemProvider = getItemProvider(eObject);
      if (null != itemProvider) {
        result = EObjectImageProviderHelper.getImageFromObject(itemProvider.getImage(eObject));
        itemProvider.dispose();
      }
    }

    return result;
  }

  /**
   * Get a generic item provider.
   * @return an {@link ItemProviderAdapter} if any.
   */
  private static ItemProviderAdapter getItemProvider(EObject object) {
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) TransactionHelper.getEditingDomain(object);
    IItemLabelProvider provider = (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(object, IItemLabelProvider.class);
    return (ItemProviderAdapter) provider;
  }

  /**
   * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
   */
  public String getColumnText(Object element, int columnIndex) {

    String result = ICommonConstants.EMPTY_STRING;

    DummyTreeData data = (DummyTreeData) element;
    String[] ds = (String[]) data.getData();

    if (data.getDepth() < 0) { // ENTRY

      if (0 == columnIndex) {
        result = ds[1];
      } else if (1 == columnIndex) {
        if (data.hasSubData()) {
          result = ((String[]) data.getSubData())[0];
        }
      }

    } else if (data.getDepth() == 0) { // SUB 0
      if (0 == columnIndex) {
        result = NotificationEnum.getOperationEnum(ds[1]).getLiteral();
      }
    } else if (data.getDepth() > 0) {
      if (0 == columnIndex) {
        result = ds[1];
      }
    }

    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  public void addListener(ILabelProviderListener listener) {
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
   */
  public void dispose() {
    // Do nothing
    return;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
   */
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  public void removeListener(ILabelProviderListener listener) {
    return;
  }
}
