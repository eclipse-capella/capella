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
package org.polarsys.capella.core.data.gen.edit.decorators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

public class ItemProviderAdapterDecorator extends ItemProviderDecorator implements Adapter.Internal {

  private List<Notifier> targets;

  private static Map<String, List<IDelegatedDecorator>> directContributors;
  private static Map<String, List<IDelegatedDecorator>> allContributors;
  private static Map<IDelegatedDecorator, String> contributorPositions;

  /** constants related to the delegatedDecorators extension point */
  public static final String PLUGIN_ID = "org.polarsys.capella.core.data.gen.edit.decorators"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "delegatedDecorator"; //$NON-NLS-1$

  protected static final String DECORATOR_POSITION_OVERRIDES = "overrides"; //$NON-NLS-1$
  protected static final String DECORATOR_POSITION_PREFIX = "prefix"; //$NON-NLS-1$
  protected static final String DECORATOR_POSITION_SUFFIX = "suffix"; //$NON-NLS-1$

  protected static final String DECORATOR_ATTRIBUTE_POSITION = "position"; //$NON-NLS-1$
  protected static final String DECORATOR_ATTRIBUTE_TYPE = "type"; //$NON-NLS-1$
  protected static final String DECORATOR_ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$



  public ItemProviderAdapterDecorator(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public String getText(Object object) {
    StringBuilder text = new StringBuilder(super.getText(object));

    for (IDelegatedDecorator labelProvider : getDelegatedDecorators((EObject) object)) {
      if (labelProvider.appliesTo(object)) {
        String position = getDecoratorPosition(labelProvider);
        if (DECORATOR_POSITION_PREFIX.equals(position)) {
          text.insert(0, labelProvider.getText(object));
        } else if (DECORATOR_POSITION_SUFFIX.equals(position)) {
          text.append(labelProvider.getText(object));
        } else if (DECORATOR_POSITION_OVERRIDES.equals(position)) {
          text.replace(0, text.length(), labelProvider.getText(object));
        }
      }
    }

    return text.toString();
  }

  /**
   *
   */
  protected String getDecoratorPosition(IDelegatedDecorator labelProvider) {
    if (labelProvider == null) {
      return ICommonConstants.EMPTY_STRING;
    }
    if (contributorPositions == null) {
      fillContributorsCache();
    }
    return contributorPositions.get(labelProvider);
  }

  /**
   * @param eobject
   *          the object from which a delegated decorator is requested
   * @return a list of {@link IDelegatedDecorator} related to the given eobject
   */
  protected List<IDelegatedDecorator> getDelegatedDecorators(EObject eobject) {
    if (eobject == null) {
      return Collections.<IDelegatedDecorator> emptyList();
    }
    if (directContributors == null) {
      fillContributorsCache();
    }

    return getContributorsPerType(eobject.eClass());
  }

  private List<IDelegatedDecorator> getContributorsPerType(EClass clazz) {
    if (!allContributors.containsKey(clazz.getInstanceClassName())) {
      List<IDelegatedDecorator> result = new ArrayList<IDelegatedDecorator>(2);
      List<IDelegatedDecorator> current = directContributors.get(clazz.getInstanceClassName());
      if (current != null) {
        for (IDelegatedDecorator next : current) {
          if (!result.contains(next)) {
            result.add(next);
          }
        }
      }
      for (EClass superType : clazz.getEAllSuperTypes()) {
        for (IDelegatedDecorator next : getContributorsPerType(superType)) {
          if (!result.contains(next)) {
            result.add(next);
          }
        }
      }
      if (result.isEmpty()) {
        result = Collections.emptyList();
      }
      allContributors.put(clazz.getInstanceClassName(), result);
    }
    return allContributors.get(clazz.getInstanceClassName());
  }

  /*
   * Fills both caches '_contributors' and '_contributorsPosition'
   */
  private void fillContributorsCache() {
    directContributors = new HashMap<String, List<IDelegatedDecorator>>();
    contributorPositions = new HashMap<IDelegatedDecorator, String>();
    allContributors = new HashMap<String, List<IDelegatedDecorator>>();

    for (IConfigurationElement provider : ExtensionPointHelper.getConfigurationElements(PLUGIN_ID, EXTENSION_POINT_ID)) {
      String type = provider.getAttribute(DECORATOR_ATTRIBUTE_TYPE);
      List<IDelegatedDecorator> labelProviders = directContributors.get(type);
      if (labelProviders == null) {
        labelProviders = new ArrayList<IDelegatedDecorator>();
        directContributors.put(type, labelProviders);
      }
      IDelegatedDecorator cls = (IDelegatedDecorator) ExtensionPointHelper.createInstance(provider, DECORATOR_ATTRIBUTE_CLASS);
      labelProviders.add(cls);
      String position = provider.getAttribute(DECORATOR_ATTRIBUTE_POSITION);
      contributorPositions.put(cls, position == null ? ICommonConstants.EMPTY_STRING : position);
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
      images.add(EMFEditPlugin.INSTANCE.getImage("full/ovr16/ControlledObject")); //$NON-NLS-1$
      image = new ComposedImage(images);
    }
    return image;
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter#getTarget()
   */
  @Override
  public Notifier getTarget() {
    if (targets == null || targets.isEmpty()) {
      return null;
    }
    return targets.get(targets.size() - 1);
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
   */
  @Override
  public void setTarget(Notifier newTarget) {
    if (targets == null) {
      targets = new ArrayList<Notifier>();
    }
    targets.add(newTarget);
  }

  /**
   * @see org.eclipse.emf.common.notify.Adapter.Internal#unsetTarget(org.eclipse.emf.common.notify.Notifier)
   */
  @Override
  public void unsetTarget(Notifier oldTarget) {
    if (targets != null) {
      targets.remove(oldTarget);
    }
  }
}
