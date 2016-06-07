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
package org.polarsys.capella.core.data.gen.edit.decorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

public class ItemProviderAdapterDecorator extends ItemProviderDecorator implements Adapter.Internal {

  private List<Notifier> targets;
  
  /** cache to store the contributors to the delegatedDecorators extension point */
  private static Map<String, List<IItemLabelProvider>> _contributors = null;
  private static Map<IItemLabelProvider, String> _contributorsPosition = null;

  /** constants related to the delegatedDecorators extension point */
  private static final String PLUGIN_ID = "org.polarsys.capella.core.data.gen.edit.decorators";
  private static final String EXTENSION_POINT_ID = "delegatedDecorator";

  protected static final String DECORATOR_POSITION_OVERRIDES = "overrides";
  protected static final String DECORATOR_POSITION_PREFIX = "prefix";
  protected static final String DECORATOR_POSITION_SUFFIX = "suffix";

  public ItemProviderAdapterDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   *
   */
  protected String getDecoratorPosition(IItemLabelProvider labelProvider) {
    if (labelProvider == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (_contributorsPosition == null) {
      fillContributorsCache();
    }
    return _contributorsPosition.get(labelProvider);
  }

  /**
   * @param eobject the object from which a delegated decorator is requested
   * @return a list of {@link IItemLabelProvider} related to the given eobject
   */
  protected List<IItemLabelProvider> getDelegatedDecorators(EObject eobject) {
    if (eobject == null) {
      return Collections.<IItemLabelProvider> emptyList();
    }
    if (_contributors == null) {
      fillContributorsCache();
    }
    List<IItemLabelProvider> providers = _contributors.get(eobject.eClass().getInstanceClassName());
    return providers != null ? providers : Collections.<IItemLabelProvider> emptyList();
  }

  /**
   * Fills both caches '_contributors' and '_contributorsPosition'
   */
  private void fillContributorsCache() {
    _contributors = new HashMap<String, List<IItemLabelProvider>>();
    _contributorsPosition = new HashMap<IItemLabelProvider, String>();
    List<IConfigurationElement> attributesProvider =
        Arrays.asList(ExtensionPointHelper.getConfigurationElements(PLUGIN_ID, EXTENSION_POINT_ID));
    for (IConfigurationElement provider : attributesProvider) {
      String type = provider.getAttribute("type");
      List<IItemLabelProvider> labelProviders = _contributors.get(type);
      if (labelProviders == null) {
        labelProviders = new ArrayList<IItemLabelProvider>();
        _contributors.put(type, labelProviders);
      }
      IItemLabelProvider cls = (IItemLabelProvider) ExtensionPointHelper.createInstance(provider, "class");
      labelProviders.add(cls);
      String position = provider.getAttribute("position");
      _contributorsPosition.put(cls, position == null ? ICommonConstants.EMPTY_STRING : position);
    }
  }

  /**
   * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getRootAdapterFactory()
   */
  protected AdapterFactory getRootAdapterFactory() {
    if (adapterFactory instanceof ComposeableAdapterFactory) {
      return ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory();
    }
    return adapterFactory;
  }

  /**
   * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#overlayImage(Object, Object)
   */
  protected Object overlayImage(Object object, Object image) {
    if (AdapterFactoryEditingDomain.isControlled(object)) {
      List<Object> images = new ArrayList<Object>(2);
      images.add(image);
      images.add(EMFEditPlugin.INSTANCE.getImage("full/ovr16/ControlledObject"));
      image = new ComposedImage(images);
    }
    return image;
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter#getTarget()
   */
  public Notifier getTarget() {
    if (targets == null || targets.isEmpty()) {
      return null;
    }
    return targets.get(targets.size() - 1);
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
   */
  public void setTarget(Notifier newTarget) {
    if (targets == null) {
      targets = new ArrayList<Notifier>();
    }
    targets.add(newTarget);
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter.Internal#unsetTarget(org.eclipse.emf.common.notify.Notifier)
   */
  public void unsetTarget(Notifier oldTarget) {
    if (targets != null) {
      targets.remove(oldTarget);
    }
  }
}
