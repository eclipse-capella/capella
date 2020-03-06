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
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.eclipse.swt.graphics.Image;

public class SearchForLabelProvider extends AdapterFactoryLabelProvider {
  public SearchForLabelProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  @Override
  public String getText(Object element) {
    if (element instanceof EClass) {
      if (((EClass) element).getInstanceClass().equals(DRepresentationDescriptor.class))
        return CapellaSearchConstants.Diagram_Label;
    }
    if (element instanceof ENamedElement)
      return ((ENamedElement) element).getName();
    return super.getText(element);
  }

  @Override
  public Image getImage(Object object) {
    Image img = null;
    if (object instanceof EClass && adapterFactory != null) {
      img = GetImagesFromEClassUtil.getInstance().getImageForEClass((EClass) object,
          (ComposedAdapterFactory) adapterFactory);
    }
    return img != null ? img : super.getImage(object);
  }
}
