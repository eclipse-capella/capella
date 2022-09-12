/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.sirius.content.provider;

import java.util.Arrays;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.CurrentElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencedElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencingElementCP;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.ui.semantic.browser.content.provider.SemanticBrowserContentProviderFactory;
import org.polarsys.capella.core.ui.semantic.browser.sirius.wrapper.CustomEObjectWrapper;

public class SiriusSemanticBrowserContentProviderFactory extends SemanticBrowserContentProviderFactory {

  public SiriusSemanticBrowserContentProviderFactory() {
    super();
  }

  @Override
  public ITreeContentProvider getCurrentContentProvider() {

    return (ITreeContentProvider) new CurrentElementCP(getAdapterFactory(), model) {

      int lastNotification;

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification) {
        if (model.isListeningToPageSelectionEvents() && notification.hashCode() != lastNotification) {
          lastNotification = notification.hashCode();
          super.notifyChanged(notification);
        }
      }

      @Override
      public Object[] getElements(Object rootElement) {
        LinkedList<Object> initialChildrenList = null;
        if (rootElement instanceof DRepresentationDescriptor) {
          initialChildrenList = new LinkedList<Object>();
          DRepresentationDescriptor descriptor = (DRepresentationDescriptor) rootElement;
          EObject target = descriptor.getTarget();
          if (target instanceof Scenario || target instanceof FunctionalChain || target instanceof PhysicalPath) {
            CustomEObjectWrapper rootElementWrapper = new CustomEObjectWrapper((EObject) rootElement);
            CustomEObjectWrapper targetWrapper = new CustomEObjectWrapper(target);
            inputHasChanged = false;
            semanticParentHashMap.put(rootElementWrapper, null);
            initialChildrenList.add(rootElementWrapper);
            if (hasChildren(targetWrapper))
              initialChildrenList.add(targetWrapper);
          } else {
            initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
          }
        } else {
          initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
        }
        return initialChildrenList.toArray();
      }

      @Override
      public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
      }
    };
  }

  @Override
  public ITreeContentProvider getReferencedContentProvider() {
    return (ITreeContentProvider) new ReferencedElementCP(getAdapterFactory(), model) {

      int lastNotification;

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification) {
        if (model.isListeningToPageSelectionEvents() && notification.hashCode() != lastNotification) {
          lastNotification = notification.hashCode();
          super.notifyChanged(notification);
        }
      }

      @Override
      public Object[] getElements(Object rootElement) {
        LinkedList<Object> initialChildrenList = null;
        if (rootElement instanceof DRepresentationDescriptor) {
          initialChildrenList = new LinkedList<Object>();
          DRepresentationDescriptor descriptor = (DRepresentationDescriptor) rootElement;
          EObject target = descriptor.getTarget();
          if (target instanceof Scenario || target instanceof FunctionalChain || target instanceof PhysicalPath) {
            CustomEObjectWrapper rootElementWrapper = new CustomEObjectWrapper((EObject) rootElement);
            CustomEObjectWrapper targetWrapper = new CustomEObjectWrapper(target);
            inputHasChanged = false;
            semanticParentHashMap.put(rootElementWrapper, null);
            if (hasChildren(rootElementWrapper))
              initialChildrenList.add(rootElementWrapper);
            if (hasChildren(targetWrapper))
              initialChildrenList.add(targetWrapper);
          } else {
            initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
          }
        } else {
          initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
        }
        return initialChildrenList.toArray();
      }

      @Override
      public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencingContentProvider() {
    return (ITreeContentProvider) new ReferencingElementCP(getAdapterFactory(), model) {

      int lastNotification;

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification) {
        if (model.isListeningToPageSelectionEvents() && notification.hashCode() != lastNotification) {
          lastNotification = notification.hashCode();
          super.notifyChanged(notification);
        }
      }

      @Override
      public Object[] getElements(Object rootElement) {
        LinkedList<Object> initialChildrenList = null;
        if (rootElement instanceof DRepresentationDescriptor) {
          initialChildrenList = new LinkedList<Object>();
          DRepresentationDescriptor descriptor = (DRepresentationDescriptor) rootElement;
          EObject target = descriptor.getTarget();
          if (target instanceof Scenario || target instanceof FunctionalChain || target instanceof PhysicalPath) {
            CustomEObjectWrapper rootElementWrapper = new CustomEObjectWrapper((EObject) rootElement);
            CustomEObjectWrapper targetWrapper = new CustomEObjectWrapper(target);
            inputHasChanged = false;
            semanticParentHashMap.put(rootElementWrapper, null);
            if (hasChildren(rootElementWrapper))
              initialChildrenList.add(rootElementWrapper);
            if (hasChildren(targetWrapper))
              initialChildrenList.add(targetWrapper);
          } else {
            initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
          }
        } else {
          initialChildrenList = new LinkedList<Object>(Arrays.asList(super.getElements(rootElement)));
        }
        return initialChildrenList.toArray();
      }

      @Override
      public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
      }
    };
  }
}
