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
package org.polarsys.capella.core.model.handler.provider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IAdapterFactoryProvider;

/**
 * The Capella adapter factory provider.
 */
public class CapellaAdapterFactoryProvider implements IAdapterFactoryProvider {

  /**
   * The singleton unique instance.
   */
  private static CapellaAdapterFactoryProvider __instance = null;
  /**
   * The adapter factory.
   */
  private AdapterFactory _adapterFactory = null;

  /**
   * Private constructor.
   */
  private CapellaAdapterFactoryProvider() {
    // nothing
  }

  /**
   * Singleton getter.
   */
  public static CapellaAdapterFactoryProvider getInstance() {
    if (null == __instance) {
      __instance = new CapellaAdapterFactoryProvider();
    }
    return __instance;
  }

  /**
   * Gets the Capella adapter factory singleton.
   * 
   * @return The Capella adapter factory.
   */
  public AdapterFactory getAdapterFactory() {
    if (null == _adapterFactory) {
      _adapterFactory = createAdapterFactory();
    }
    return _adapterFactory;
  }

  /**
   * Creates the composed adapter factory.
   */
  private AdapterFactory createAdapterFactory() {
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    // add decorators first
    IConfigurationElement[] contributors = ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.model.handler", "decoratorAdapterFactory");
    for (IConfigurationElement contributorElement : contributors) {
      DecoratorAdapterFactory decorator =
        (DecoratorAdapterFactory) ExtensionPointHelper.createInstance(contributorElement, ExtensionPointHelper.ATT_CLASS);
      if (decorator != null) {
        adapterFactory.addAdapterFactory(decorator);
      }
    }
    
    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

    return adapterFactory;
  }
}
