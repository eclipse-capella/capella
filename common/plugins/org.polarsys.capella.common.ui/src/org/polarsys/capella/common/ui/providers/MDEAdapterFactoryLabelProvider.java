/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.providers;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class MDEAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider {
  /**
   * Delegated label provider (shared for all instances).
   */
  private static ILabelProviderDelegation __delegatedLabelProvider;

  /**
   * Flag used to know if ILabelProviderDelegation has been lookup.
   */
  private static boolean __alreadyLookup;

  // By default, we use the AdapterFactory from CapellaAdapterFactoryProvider
  // If other AdapterFactory is needed, there is a public method MDEAdapterFactoryLabelProvider.setAdapterFactory()
  public MDEAdapterFactoryLabelProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  @Override
  public Image getImage(Object object) {
    if (object instanceof DAnnotation && ((DAnnotation)object).getSource().contains("TitleBlock")) {
      String imagePath = "/icons/full/obj16/TitleBlock_16.gif";
      URL url = FileLocator.find(Platform.getBundle("org.polarsys.capella.core.sirius.analysis"), new Path(imagePath), null);
      return ImageDescriptor.createFromURL(url).createImage();
    }
    Image result = super.getImage(object);

    // Delegation for CDO usage for instance.
    ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
    if (null != delegatedLabelProvider) {
      Image decoratedImage = delegatedLabelProvider.getImage(result, object);
      result = (null != decoratedImage) ? decoratedImage : result;
    }
    return result;
  }

  @Override
  public String getText(Object object) {
    if (object instanceof DAnnotation && ((DAnnotation)object).getSource().contains("TitleBlock")) {
      String text = ((DAnnotation) object).getSource();
      text = text.replaceAll("([^_])([A-Z])", "$1 $2");
      
      DAnnotation annotation = (DAnnotation) object;
      String referenceLabel = null;
      if (!(annotation.getReferences().get(0) instanceof DAnnotation)) {
        EObject obj = annotation.getReferences().get(0);
        if (obj instanceof AbstractNamedElement) {
          referenceLabel  = ((AbstractNamedElement) obj).getName();
        }
      } else {
        DSemanticDiagram diagram = (DSemanticDiagram) annotation.eContainer();
        if (diagram instanceof DRepresentation) {
          referenceLabel = ((DRepresentation) diagram).getName();
        }
      }
      if(referenceLabel != null) {
        text = text + " : " + referenceLabel;
      }
      return text;
    }

    String text = super.getText(object);

    // Delegation for CDO usage for instance.
    ILabelProviderDelegation delegatedLabelProvider = getDelegatedLabelProvider();
    if (null != delegatedLabelProvider) {
      text = delegatedLabelProvider.getText(text, object);
    }

    return text;
  }

  protected ILabelProviderDelegation getDelegatedLabelProvider() {
    if (!__alreadyLookup && (null == __delegatedLabelProvider)) {
      // Load ITabbedPropertiesLabelProviderDelegation contributor if any.
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements(MdeCommonUiActivator.getDefault().getPluginId(), "labelProviderDelegation"); //$NON-NLS-1$
      // Loop over contributed ITabbedPropertiesLabelProviderDelegation contributor, must be only one.
      if (configurationElements.length > 0) {
        __delegatedLabelProvider =
            (ILabelProviderDelegation) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
      }
      __alreadyLookup = true;
    }
    return __delegatedLabelProvider;
  }
}
