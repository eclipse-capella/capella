/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

public class TitleBlockItemProviderDecorator extends ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

  public TitleBlockItemProviderDecorator(TitleBlockItemDecoratorAdapterFactory titleBlockDecoratorAdapterFactory) {
    super(titleBlockDecoratorAdapterFactory);
  }

  @Override
  public String getText(Object object) {
    String label = "";
    if (object instanceof DAnnotation) {
      label = TitleBlockHelper.getTitleBlockAnnotationLabel((EObject) object);
      String referencedElementLabel = TitleBlockHelper.getReferencedElementLabel((EObject) object);
      if (referencedElementLabel != null) {
        label = label + " : " + referencedElementLabel;
      }
    }
    return label;
  }

  @Override
  public Object getImage(Object object) {
    String imagePath = "/icons/full/obj16/TitleBlock.gif";
    
    if (TitleBlockHelper.isTitleBlockLine((DAnnotation) object)) {
      imagePath = "/icons/full/obj16/TitleBlockLine.gif";
      
    } else if (TitleBlockHelper.isTitleBlockCell((DAnnotation) object)) {
      imagePath = "/icons/full/obj16/TitleBlockCell.gif";
    }
    return SiriusViewActivator.imageDescriptorFromPlugin(SiriusViewActivator.ID, imagePath).createImage();
  }
}