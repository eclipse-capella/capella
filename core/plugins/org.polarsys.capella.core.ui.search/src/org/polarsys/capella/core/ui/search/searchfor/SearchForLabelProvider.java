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

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.ui.search.Activator;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;

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
    Image img = getTargetModelElementImage(object);
    return img != null ? img : super.getImage(object);
  }
  
  public Image getImageToBeDone(Object element) {
    if (element instanceof EClass) {
      EClass eCls = (EClass) element;
      EPackage pkq = eCls.getEPackage();
      ComposedAdapterFactory compAdapterFactory = (ComposedAdapterFactory) adapterFactory;
      AdapterFactory factoryForType = compAdapterFactory.getFactoryForType(pkq);

      IItemLabelProvider itemProvider = (IItemLabelProvider) factoryForType.adapt(element, IItemLabelProvider.class);
      if (null != itemProvider) {
        return ExtendedImageRegistry.getInstance().getImage(itemProvider.getImage(element));
      }
    }
    return null;
  }
 
  protected Image getTargetModelElementImage(Object object) {
    try {
      if (object instanceof ENamedElement) {
        String imagePath = "/icons/full/obj16/" + ((ENamedElement) object).getName() + ".gif";
        URL url = FileLocator.find(CapellaModellerEditPlugin.getPlugin().getBundle(), new Path(imagePath), null);
        if (url != null) {
          return ModelSearchImagesUtil.getImage(url);
        }
      }
    } catch (Throwable t) {
      Activator.getDefault().getLog()
          .log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while attempmting to retrieve image from edit"
              + CapellaModellerEditPlugin.getPlugin().getBundle() + " bundle"));
    }
    return null;
  }

}
