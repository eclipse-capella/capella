/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.extension.style;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * The style configuration for parts.
 */
public class PartComponentStyleConfiguration extends SimpleStyleConfiguration implements IStyleConfigurationProvider {

  public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {
    return this;
  }

  @Override
  public Image getLabelIcon(DDiagramElement representationElement, IGraphicalEditPart editPart) {
    if (representationElement.getTarget() instanceof Part) {
      if (CapellaProjectHelper.isReusableComponentsDriven(representationElement.getTarget()) != TriStateBoolean.True) {
        Part part = (Part) representationElement.getTarget();
        IItemLabelProvider provider = (IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance()
            .getAdapterFactory().adapt(part.getType(), IItemLabelProvider.class);
        return EObjectImageProviderHelper.getImageFromObject(provider.getImage(part.getType()));
      }
    }
    return super.getLabelIcon(representationElement, editPart);
  }

  public boolean provides(DiagramElementMapping mapping, Style style) {
    if (mapping instanceof ContainerMapping) {
      String domainClass = ((ContainerMapping) mapping).getDomainClass();
      return "cs.Part".equals(domainClass) || "cs.DeployableElement".equals(domainClass);
    }
    return false;
  }
}
