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

package org.polarsys.capella.common.ui.providers;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

public class MDEAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider {
  /**
   * Delegated label provider (shared for all instances).
   */
  private static ILabelProviderDelegation __delegatedLabelProvider;

  /**
   * Flag used to know if ILabelProviderDelegation has been lookup.
   */
  private static boolean __alreadyLookup;

  // By default, we use the AdapterFactory from CapellaAdapterFactoryProvider
  // If other AdapterFactory is needed, there is a public method MDEAdapterFactoryLabelProvider.setAdapterFactory()
  public MDEAdapterFactoryLabelProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  @Override
  public Image getImage(Object object) {
    Image result = null;
    try {
      result = super.getImage(object);
    
      // Delegation for CDO usage for instance.
      ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
      if (null != delegatedLabelProvider) {
        Image decoratedImage = delegatedLabelProvider.getImage(result, object);
        result = (null != decoratedImage) ? decoratedImage : result;
      }
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Do nothing.
      } else {
        throw e;
      }
    }
    return result;
  }


  @Override
  public String getText(Object object) {
    String text = null;
    try {
      text = super.getText(object);

      // Delegation for CDO usage for instance.
      ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
      if (null != delegatedLabelProvider) {
        text = delegatedLabelProvider.getText(text, object);
      }
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Do nothing.
      } else {
        throw e;
      }
    }

    return text;
  }

  protected ILabelProviderDelegation getDelegatedLabelProvider() {
    if (!__alreadyLookup && (null == __delegatedLabelProvider)) {
      // Load ITabbedPropertiesLabelProviderDelegation contributor if any.
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements(MdeCommonUiActivator.getDefault().getPluginId(), "labelProviderDelegation"); //$NON-NLS-1$
      // Loop over contributed ITabbedPropertiesLabelProviderDelegation contributor, must be only one.
      if (configurationElements.length > 0) {
        __delegatedLabelProvider =
            (ILabelProviderDelegation) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
      }
      __alreadyLookup = true;
    }
    return __delegatedLabelProvider;
  }
}
