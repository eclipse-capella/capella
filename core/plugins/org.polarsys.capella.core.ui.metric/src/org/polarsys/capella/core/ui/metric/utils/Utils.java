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
package org.polarsys.capella.core.ui.metric.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * Utility class on resource //TODO to remove, probably available in another plug-ins
 */
public class Utils {

  /**
   * Return instance of a given layer. suppose that multiplicity of each layer is one
   * @param se_p the target {@link SystemEngineering}
   * @param eClassLayer_p eClass of target layer
   * @return <code>null</code> if not found or if input is null
   */
  public static EObject getLayer(SystemEngineering se_p, EClass eClassLayer_p) {

    if (null == se_p) {
      return null;
    }

    EObject result = null;

    for (EObject current : se_p.getOwnedArchitectures()) {
      if (current.eClass() == eClassLayer_p) {
        result = current;
        break;
      }
    }

    return result;
  }

  /** ... */
  public static List<EClass> getLayers() {
    List<EClass> result = new ArrayList<EClass>();

    result.add(OaPackage.Literals.OPERATIONAL_ANALYSIS);
    result.add(CtxPackage.Literals.SYSTEM_ANALYSIS);
    result.add(LaPackage.Literals.LOGICAL_ARCHITECTURE);
    result.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    result.add(EpbsPackage.Literals.EPBS_ARCHITECTURE);

    return result;
  }

  /**
   * Get image for specified {@link EObject}
   * @param eObject_p
   * @return <code>null</code> if not found.
   */
  public static Image getImage(EObject eObject_p) {
    Image result = null;
    ItemProviderAdapter itemProvider = getIItemLabelProvider(eObject_p);
    if (null != itemProvider) {
      result = EObjectLabelProviderHelper.getImageFromObject(itemProvider.getImage(eObject_p));
      itemProvider.dispose();
    }
    return result;
  }

  /**
   * Get text for specified {@link EObject}
   * @param eObject_p
   * @return <code>null</code> if not found.
   */
  public static String getText(EObject eObject_p) {
    String result = ICommonConstants.EMPTY_STRING;
    ItemProviderAdapter itemProvider = getIItemLabelProvider(eObject_p);
    if (null != itemProvider) {
      result = itemProvider.getText(eObject_p);
      itemProvider.dispose();
    }
    return result;
  }

  /**
   * Get a generic item provider.
   * @return an {@link ItemProviderAdapter} if any.
   */
  private static ItemProviderAdapter getIItemLabelProvider(EObject object_p) {
    IItemLabelProvider provider =
        (IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(object_p, IItemLabelProvider.class);
    return (ItemProviderAdapter) provider;
  }
}
