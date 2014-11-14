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
package org.polarsys.capella.common.ui.providers;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;

/**
 */
public class MDEAdapterFactoryLabelProvider extends CapellaTransactionalAdapterFactoryLabelProvider {
  /**
   * Delegated label provider (shared for all instances).
   */
  private static ILabelProviderDelegation __delegatedLabelProvider;

  /**
   * Flag used to know if ILabelProviderDelegation has been lookup.
   */
  private static boolean __alreadyLookup;

  /**
   * Constructor.
   * @param adapterFactory_p
   */
  public MDEAdapterFactoryLabelProvider(AdapterFactory adapterFactory_p) {
    super(adapterFactory_p);
  }

  /**
   * Constructor.
   * @param editingDomain_p
   * @param adapterFactory_p
   */
  public MDEAdapterFactoryLabelProvider(TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p) {
    super(editingDomain_p, adapterFactory_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object_p) {
    Image result = super.getImage(object_p);

    // Delegation for CDO usage for instance.
    ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
    if (null != delegatedLabelProvider) {
      Image decoratedImage = delegatedLabelProvider.getImage(result, object_p);
      result = (null != decoratedImage) ? decoratedImage : result;
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText(Object object_p) {
    String text = super.getText(object_p);

    // Delegation for CDO usage for instance.
    ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
    if (null != delegatedLabelProvider) {
      text = delegatedLabelProvider.getText(text, object_p);
    }

    return text;
  }

  /**
   * Get the unique {@link ILabelProviderDelegation}.
   */
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
