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
package org.polarsys.capella.common.tig.model.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.tig.model.IHelper;

public class TigHelperAdapterFactory implements IAdapterFactory {
  /**
   * Store loaded helpers, registered by namespace URI.
   */
  private Map<String, IHelper> _helpers;

  /**
   * Constructor.
   */
  public TigHelperAdapterFactory() {
    _helpers = new HashMap<String, IHelper>(0);
  }

  /**
   * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
    IHelper result = null;
    // Make sure given parameters match expected types offered by this factory.
    // In theory, this factory is contributed through an extension that only exposes these adapters for EObject-based objects.
    if (adaptableObject_p instanceof EObject) {
      if (adapterType_p.equals(IHelper.class)) {
        EObject object = (EObject) adaptableObject_p;
        // Package that contains the meta-class for given EObject.
        EPackage package_l = object.eClass().getEPackage();
        // Root package of owner package.
        EPackage rootPackage = EcoreHelper.getRootPackage(package_l);
        String nsURI = rootPackage.getNsURI();
        result = _helpers.get(nsURI);
        // No helper loaded yet, try loading it.
        if (null == result) {
          // Try finding a contributed helper for name-space URI.
          IConfigurationElement helperConfigurationElement =
                                                             ExtensionPointHelper.getConfigurationElement(IInternalModelConstants.TIG_MODEL_PLUG_IN_ID,
                                                                                                          IInternalModelConstants.HELPER_EXTENSION_POINT_ID,
                                                                                                          IInternalModelConstants.HELPER_ATT_NS_URI, nsURI);
          // Instantiate the helper if found.
          if (null != helperConfigurationElement) {
            result = (IHelper) ExtensionPointHelper.createInstance(helperConfigurationElement, ExtensionPointHelper.ATT_CLASS);
            // Store it for a later usage.
            _helpers.put(nsURI, result);
          }
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
   */
  @SuppressWarnings("unchecked")
  public Class[] getAdapterList() {
    return new Class[] { IHelper.class };
  }
}
