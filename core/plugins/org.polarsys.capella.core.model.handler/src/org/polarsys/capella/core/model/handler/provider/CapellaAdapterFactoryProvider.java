/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.polarsys.capella.common.data.activity.provider.ActivityItemProviderAdapterFactory;
import org.polarsys.capella.common.data.behavior.provider.BehaviorItemProviderAdapterFactory;
import org.polarsys.capella.common.data.modellingcore.provider.ModellingcoreItemProviderAdapterFactory;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IAdapterFactoryProvider;
import org.polarsys.capella.core.data.capellacommon.provider.CapellacommonItemProviderAdapterFactory;
import org.polarsys.capella.core.data.capellacore.provider.CapellacoreItemProviderAdapterFactory;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellamodellerItemProviderAdapterFactory;
import org.polarsys.capella.core.data.cs.provider.CsItemProviderAdapterFactory;
import org.polarsys.capella.core.data.ctx.provider.CtxItemProviderAdapterFactory;
import org.polarsys.capella.core.data.epbs.provider.EpbsItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.communication.provider.CommunicationItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.datatype.provider.DatatypeItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.datavalue.provider.DatavalueItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.provider.InformationItemProviderAdapterFactory;
import org.polarsys.capella.core.data.la.provider.LaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.pa.deployment.provider.DeploymentItemProviderAdapterFactory;
import org.polarsys.capella.core.data.pa.provider.PaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.requirement.provider.RequirementItemProviderAdapterFactory;
import org.polarsys.capella.core.data.sharedmodel.provider.SharedmodelItemProviderAdapterFactory;
import org.polarsys.kitalpha.ad.metadata.metadata.provider.MetadataItemProviderAdapterFactory;
import org.polarsys.kitalpha.emde.extension.ModelExtensionDescriptor;
import org.polarsys.kitalpha.emde.model.edit.provider.EmdeItemProviderAdapterFactory;

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
    adapterFactory.addAdapterFactory(new CapellamodellerItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CapellacoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new OaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CtxItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new LaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new PaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DeploymentItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EpbsItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new SharedmodelItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new RequirementItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CapellacommonItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new InformationItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CommunicationItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DatatypeItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DatavalueItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CsItemProviderAdapterFactory());
    //adapterFactory.addAdapterFactory(new FaItemProviderAdapterFactory());
    //adapterFactory.addAdapterFactory(new InteractionItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ModellingcoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ActivityItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new BehaviorItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EmdeItemProviderAdapterFactory());
    for (AdapterFactory extendedAdapterFactory : ModelExtensionDescriptor.INSTANCE.getExtendedModelAdapterFactories(
        CtxItemProviderAdapterFactory.class.getName())) {
      adapterFactory.addAdapterFactory(extendedAdapterFactory);
    }
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new MetadataItemProviderAdapterFactory());
    // Use this one to have Sirius labels and images correctly displayed.
    adapterFactory.addAdapterFactory(ViewHelper.INSTANCE.createAdapterFactory());

    return adapterFactory;
  }
}
