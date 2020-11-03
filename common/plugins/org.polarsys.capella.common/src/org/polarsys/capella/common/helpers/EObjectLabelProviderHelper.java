/*******************************************************************************
 * Copyright (c) 2017, 2020, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers;

import java.net.URL;
import java.util.Collection;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
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
  private static final String METACLASS_DISPLAY_PREFIX = "("; //$NON-NLS-1$
  /**
   * Suffix used in label rendering if requested.
   */
  private static final String METACLASS_DISPLAY_SUFFIX = ") "; //$NON-NLS-1$
  /**
   * Separator used by getFullPath
   */
  public static final String FULL_PATH_SEPARATOR = "::"; //$NON-NLS-1$

  /**
   * Get the generated item provider for given object.
   * 
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no provider is found.
   */
  protected static IItemLabelProvider getItemLabelProvider(EObject object) {
    // Precondition.
    if (null == object) {
      return null;
    }
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(object);
    // Precondition.
    if (null == editingDomain) {
      return null;
    }
    return (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(object, IItemLabelProvider.class);
  }

  /**
   * Get the label for given object based on generated item provider.
   * 
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no label is found.
   * @since 1.2.1, this method have a replacement with better performances, Please use EObjectExt.getText() instead
   *        until bugzilla 2036 is solved
   */
  private static String getTextEObject(EObject object) {
    IItemLabelProvider provider = getItemLabelProvider(object);
    String label = ICommonConstants.EMPTY_STRING;

    if (null != provider) {
      label = provider.getText(object);
    }
    return label;
  }

  /**
   * Get the metaclass label (emitted by EMF Edit generation) for given object.
   * 
   * @param object
   * @param addParenthesis
   *          if <code>true</code> the returned label is surrounded by brackets.
   * @return <code>null</code> if one of parameters is <code>null</code> or if no label is found.
   */
  public static String getMetaclassLabel(EObject object, boolean addParenthesis) {
    String label = null;
    // Precondition.
    if (null == object) {
      return label;
    }
    ItemProviderAdapter provider = getItemProvider(object);
    if (provider instanceof ItemProviderAdapter) {
      label = getMetaclassLabel(object.eClass(), provider);
    }
    if (addParenthesis) {
      label = METACLASS_DISPLAY_PREFIX + label + METACLASS_DISPLAY_SUFFIX;
    }
    return label;
  }

  /**
   * Get the metaclass label for the given EClass
   * 
   * @param eClass
   * @param editingDomain
   * @return
   */
  public static String getMetaclassLabel(EClass eClass, AdapterFactoryEditingDomain editingDomain) {
    Bundle bundle = getGenBundle(eClass, editingDomain);
    String label = "";
    if (bundle != null) {
      ResourceBundle resourceBundle = Platform.getResourceBundle(bundle);
      if (resourceBundle != null) {
        label = resourceBundle.getString(GENERATED_KEY_PREFIX + eClass.getName() + METACLASS_GENERATED_KEY_SUFFIX);
      }
    }
    return label;
  }

  /**
   * Get the imageDescriptor for the given EClass.
   * 
   * @param eClass
   * @param editingDomain
   * @return
   */
  public static ImageDescriptor getImage(EClass eClass, AdapterFactoryEditingDomain editingDomain) {
    Bundle bundle = getGenBundle(eClass, editingDomain);
    ImageDescriptor imageDescriptor = null;
    if (bundle != null) {
      ResourceBundle resourceBundle = Platform.getResourceBundle(bundle);
      if (resourceBundle != null) {
        URL imageURL = FileLocator.find(bundle, new Path("icons/full/obj16/" + eClass.getName() + ".gif"), null);
        imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(imageURL);
      }
    }
    return imageDescriptor;
  }

  /**
   * Get the genBundle for given eClass and editingDomain.
   * 
   * @param eClass
   * @param editingDomain
   * @return
   */
  private static Bundle getGenBundle(EClass eClass, AdapterFactoryEditingDomain editingDomain) {
    EPackage selectedEPackage = eClass.getEPackage();
    AdapterFactory adapterFactory = editingDomain.getAdapterFactory();
    Bundle bundle = null;
    if (adapterFactory instanceof ComposedAdapterFactory) {
      AdapterFactory selectedAdapterFactory = ((ComposedAdapterFactory) adapterFactory)
          .getFactoryForType(selectedEPackage);

      if (selectedAdapterFactory instanceof DecoratorAdapterFactory) {
        AdapterFactory decoratedAdapterFactory = ((DecoratorAdapterFactory) selectedAdapterFactory)
            .getDecoratedAdapterFactory();
        bundle = FrameworkUtil.getBundle(decoratedAdapterFactory.getClass());
      } else {
        bundle = FrameworkUtil.getBundle(selectedAdapterFactory.getClass());
      }
    }
    return bundle;
  }

  /**
   * Get the ItemProviderAdapter associated to the AdapterFactoryEditingDomain of the given EObject. returns null if
   * object is not attached to an EditingDomain
   */
  public static ItemProviderAdapter getItemProvider(EObject object) {
    // Precondition.
    if (null == object) {
      return null;
    }
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(object);
    // Precondition.
    if (null == editingDomain) {
      return null;
    }
    return getItemProvider(object, editingDomain.getAdapterFactory());
  }

  /**
   * Get the item provider adapter for the given object and given adapter factory
   */
  public static ItemProviderAdapter getItemProvider(EObject object, AdapterFactory factory) {
    // Precondition.
    if (null == object) {
      return null;
    }
    // Adaptation to ItemProviderAdapter returns null due to EMF Edit generated ItemProviderAdapterFactory that do not
    // support this type.
    // So, we adapt to IItemLabelProvider and then we cast...
    Adapter adapter = factory.adapt(object, IItemLabelProvider.class);
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
   * 
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
    } catch (MissingResourceException e) {
      label = "<<MissingResourceException>> [" + cls.getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return label;
  }

  /**
   * Get the metaclass label (emitted by EMF Edit generation) for given object according given editing domain.
   * 
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
    String featureKey = feature.getEContainingClass().getName() + ICommonConstants.UNDERSCORE_CHARACTER
        + feature.getName();
    label = provider.getString(GENERATED_KEY_PREFIX + featureKey + FEATURE_GENERATED_KEY_SUFFIX);
    return label;
  }

  /**
   * Retrieve a readable text of the element
   */
  protected static void getText(Object affectedObject, StringBuilder value) {
    if (affectedObject != null) {
      if (affectedObject instanceof EObject) {
        value.append(getText(affectedObject));
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
    if (object instanceof EObject) {
      return getTextEObject((EObject) object);
    }
    StringBuilder buffer = new StringBuilder();
    getText(object, buffer);
    return buffer.toString();
  }

  /**
   * Get the image for given object based on generated item provider.
   * 
   * To get Image, call {@link ExtendedImageRegistry#getImage(Object)} on the result of this method. To get
   * ImageDescriptor, call {@link ExtendedImageRegistry#getImageDescriptor(Object)} on the result of this method.
   * 
   * @param object
   * @return<code>null</code> if one of parameters is <code>null</code> or if no image is found.
   */
  public static Object getImage(EObject object) {
    IItemLabelProvider provider = getItemLabelProvider(object);
    if (null != provider) {
      return provider.getImage(object);
    }
    return null;
  }

  public static String getFullPathText(EObject element) {
    String path = getText(element);
    EObject container = element.eContainer();
    while (null != container) {
      path = getText(container).concat(FULL_PATH_SEPARATOR).concat(path);
      container = container.eContainer();
    }
    return path;
  }
}
