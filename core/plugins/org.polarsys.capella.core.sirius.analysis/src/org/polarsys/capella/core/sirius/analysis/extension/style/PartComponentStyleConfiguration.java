/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.extension.style;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.data.capellacore.Type;
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
    if (representationElement != null) {
      if (isShowIcon(representationElement, editPart)) {
        EObject target = representationElement.getTarget();

        if (target instanceof Part && !useCustomIcon(representationElement, editPart)) {
          if (CapellaProjectHelper.isReusableComponentsDriven(target) != TriStateBoolean.True) {
            Type type = ((Part) target).getType();
            if (type != null) {
              IItemLabelProvider provider = (IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance()
                  .getAdapterFactory().adapt(type, IItemLabelProvider.class);
              return ExtendedImageRegistry.getInstance().getImage(provider.getImage(type));
            }
          }
        }
        return super.getLabelIcon(representationElement, editPart);
      }
    }
    return null;
  }

  public boolean provides(DiagramElementMapping mapping, Style style) {
    if (mapping instanceof ContainerMapping) {
      String domainClass = ((ContainerMapping) mapping).getDomainClass();
      return "cs.Part".equals(domainClass) || "cs.DeployableElement".equals(domainClass);
    }
    return false;
  }
}
