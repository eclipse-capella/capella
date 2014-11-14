/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.extension.style;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.util.FaSwitch;

/**
 * The style for logical data flow.
 */
public class LogicalDataFlowStyleConfiguration extends SimpleStyleConfiguration implements IStyleConfigurationProvider {

  protected static class GetLabelIconSwitch extends FaSwitch<Image> {

    @Override
    public Image caseAbstractFunction(AbstractFunction object) {
      final IItemLabelProvider labelProvider =
          (IItemLabelProvider) SiriusDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory().adapt(object.getBehavior(), IItemLabelProvider.class);
      if (labelProvider != null) {
        ImageDescriptor descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(object.getBehavior()));
        if (descriptor == null) {
          descriptor = ImageDescriptor.getMissingImageDescriptor();
        }
        return SiriusDiagramEditorPlugin.getInstance().getImage(descriptor);
      }
      // }
      return null;
    }

    @Override
    public Image caseFunctionalExchange(FunctionalExchange object) {
      if (!object.getExchangeSpecifications().isEmpty()) {
        final ExchangeSpecification exchange = object.getExchangeSpecifications().iterator().next();
        final IItemLabelProvider labelProvider =
            (IItemLabelProvider) SiriusDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory().adapt(exchange, IItemLabelProvider.class);
        if (labelProvider != null) {
          ImageDescriptor descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(exchange));
          if (descriptor == null) {
            descriptor = ImageDescriptor.getMissingImageDescriptor();
          }
          return SiriusDiagramEditorPlugin.getInstance().getImage(descriptor);
        }
      }
      return null;
    }
  }

  public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {
    return this;
  }

  @Override
  public Image getLabelIcon(DDiagramElement representationElement, IGraphicalEditPart editPart) {
    Image result = null;
    if (isShowIcon(representationElement, editPart)) {
      final EObject target = ((DSemanticDecorator) representationElement).getTarget();
      return new GetLabelIconSwitch().doSwitch(target);
    }
    if (result == null) {
      result = super.getLabelIcon(representationElement, editPart);
    }
    return result;
  }

  public boolean provides(DiagramElementMapping mapping, Style style) {
    return ((mapping instanceof EdgeMapping) && "Transition".equals(((EdgeMapping) mapping).getDomainClass())) //$NON-NLS-1$
           || ((mapping instanceof AbstractNodeMapping) && "FunctionalAction".equals(((AbstractNodeMapping) mapping).getDomainClass())); //$NON-NLS-1$

  }
}
