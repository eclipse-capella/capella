/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.helpers;

import java.util.Collection;
import java.util.MissingResourceException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Provide services to display EObject.
 */
public class EObjectLabelProviderHelper {
  /**
   * Suffix used by EMF Edit when generating feature labels.
   */
  private static final String FEATURE_GENERATED_KEY_SUFFIX = "_feature"; //$NON-NLS-1$
  /**
   * Suffix used by EMF Edit when generating metaclasse labels.
   */
  private static final String METACLASS_GENERATED_KEY_SUFFIX = "_type"; //$NON-NLS-1$
  /**
   * Prefix used by EMF Edit when generating labels.
   */
  private static final String GENERATED_KEY_PREFIX = "_UI_"; //$NON-NLS-1$
  /**
   * Prefix used in label rendering if requested.
   */
  private static final String METACLASS_DISPLAY_PREFIX = "["; //$NON-NLS-1$
  /**
   * Suffix used in label rendering if requested.
   */
  private static final String METACLASS_DISPLAY_SUFFIX = "] "; //$NON-NLS-1$

  /**
   * Get the generated item provider for given object.
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no provider is found.
   */
  protected static IItemLabelProvider getItemLabelProvider(EObject object) {
    // Precondition.
    if (null == object) {
      return null;
    }
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(object);
    // Precondition.
    if (null == editingDomain) {
      return null;
    }
    return (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(object, IItemLabelProvider.class);
  }

  /**
   * Get the label for given object based on generated item provider.
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no label is found.
   */
  public static String getText(EObject object) {
    String label = ICommonConstants.EMPTY_STRING;

    IItemLabelProvider provider = getItemLabelProvider(object);
    if (null != provider) {
      label = provider.getText(object);
    }
    return label;
  }

  /**
   * Get the metaclass label (emitted by EMF Edit generation) for given object.
   * @param object
   * @param addBrackets if <code>true</code> the returned label is surrounded by brackets.
   * @return <code>null</code> if one of parameters is <code>null</code> or if no label is found.
   */
  public static String getMetaclassLabel(EObject object, boolean addBrackets) {
    String label = null;
    // Precondition.
    if (null == object) {
      return label;
    }
    ItemProviderAdapter provider = getItemProvider(object);
    if (provider instanceof ItemProviderAdapter) {
      label = getMetaclassLabel(object.eClass(), (ItemProviderAdapter) provider);
    }
    if (addBrackets) {
      label = METACLASS_DISPLAY_PREFIX + label + METACLASS_DISPLAY_SUFFIX;
    }
    return label;
  }
  
  /**
   * Get the item provider adapter for the given object
   */
  public static ItemProviderAdapter getItemProvider(EObject object) {
    // Precondition.
    if (null == object) {
      return null;
    }
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(object);
    // Precondition.
    if (null == editingDomain) {
      return null;
    }

    // Adaptation to ItemProviderAdapter returns null due to EMF Edit generated ItemProviderAdapterFactory that do not support this type.
    // So, we adapt to IItemLabelProvider and then we cast...
    Adapter adapter = editingDomain.getAdapterFactory().adapt(object, IItemLabelProvider.class);
    if (adapter instanceof ItemProviderDecorator) {
      IChangeNotifier notifier = ((ItemProviderDecorator) adapter).getDecoratedItemProvider();
      if (notifier instanceof ItemProviderAdapter) {
        return (ItemProviderAdapter) notifier;
      }
    }
    return (ItemProviderAdapter) adapter;
  }
  

  /**
   * Get the metaclass label (emitted by EMF Edit generation) for given object according given editing domain.
   * @param cls
   * @param provider
   * @return <code>null</code> if one of parameters is <code>null</code> or if no label is found.
   */
  public static String getMetaclassLabel(EClass cls, ItemProviderAdapter provider) {
    String label = null;
    // Preconditions.
    if ((null == cls) || (null == provider)) {
      return label;
    }
    try {
      label = provider.getString(GENERATED_KEY_PREFIX + cls.getName() + METACLASS_GENERATED_KEY_SUFFIX);
    }
    catch (MissingResourceException e) {
      label = "<<MissingResourceException>> [" + cls.getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return label;
  }

  /**
   * Get the metaclass label (emitted by EMF Edit generation) for given object according given editing domain.
   * @param feature
   * @param provider
   * @return <code>null</code> if one of parameters is <code>null</code> or if no label is found.
   */
  public static String getFeatureLabel(EStructuralFeature feature, ItemProviderAdapter provider) {
    String label = null;
    // Preconditions.
    if ((null == feature) || (null == provider)) {
      return label;
    }
    String featureKey = feature.getEContainingClass().getName() + ICommonConstants.UNDERSCORE_CHARACTER + feature.getName();
    label = provider.getString(GENERATED_KEY_PREFIX + featureKey + FEATURE_GENERATED_KEY_SUFFIX);
    return label;
  }
  
  /**
   * Retrieve a readable text of the element
   */
  protected static void getText(Object affectedObject, StringBuilder value) {
    if (affectedObject != null) {
      if (affectedObject instanceof EObject) {
        value.append(getText((EObject) affectedObject));
      } else if (affectedObject instanceof Collection<?>) {
        Collection<?> coll = (Collection<?>) affectedObject;
        value.append("{"); //$NON-NLS-1$
        for (Object o : coll) {
          getText(o, value);
          value.append(ICommonConstants.SEMICOLON_CHARACTER);
        }
        if (coll.size() > 0) {
          value.deleteCharAt(value.length() - 1);
        }
        value.append("}"); //$NON-NLS-1$
      } else {
        value.append(affectedObject.toString());
      }
    }
  }

  /**
   * Retrieve a readable text of the element. May be an Object or a Collection of objects. 
   */
  public static String getText(Object object) {
    StringBuilder buffer = new StringBuilder();
    getText(object, buffer);
    return buffer.toString();
  }
  
  
}
