/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.actions.decorators;

import java.util.Collection;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * A Decorator displaying if an element is protected or not.
 * 
 * If a list of elements is provided, it will restrict the decoration to these elements
 */
public class ProtectedElementsDecorator implements ILabelDecorator, ILightweightLabelDecorator {

  public static String ID = ProtectedElementsDecorator.class.getCanonicalName();

  LocalResourceManager manager;

  Collection<?> elements;

  public ProtectedElementsDecorator() {
    this(null);
  }

  public ProtectedElementsDecorator(Collection<?> elements) {
    this.elements = elements;
  }

  public static boolean isEnabled() {
    return PlatformUI.getWorkbench().getDecoratorManager().getBaseLabelProvider(ID) != null;
  }

  @Override
  public void decorate(Object element, IDecoration decoration) {
    if (isProtected(element)) {
      decoration.addOverlay(CapellaActionsActivator.getDefault().getImageDescriptor("lock.png"));
    }
  }

  @Override
  public void dispose() {
    if (manager != null) {
      manager.dispose();
    }
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void addListener(ILabelProviderListener listener) {
    // Nothing here
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    // Nothing here
  }

  private boolean isProtected(Object element) {
    if (element instanceof Element && CapellaDeleteAction.isElementProtected((Element) element)) {
      return elements == null || elements.contains(element);
    }
    return false;
  }

  @Override
  public Image decorateImage(Image image, Object element) {
    if (isProtected(element)) {
      if (manager == null) {
        manager = new LocalResourceManager(JFaceResources.getResources(PlatformUI.getWorkbench().getDisplay()));
      }
      ImageDescriptor overlay = CapellaActionsActivator.getDefault().getImageDescriptor("lock.png");
      DecorationOverlayIcon icon = new DecorationOverlayIcon(image, overlay, IDecoration.BOTTOM_LEFT);
      return manager.createImage(icon);
    }
    return null;
  }

  @Override
  public String decorateText(String text, Object element) {
    return null;
  }

}
