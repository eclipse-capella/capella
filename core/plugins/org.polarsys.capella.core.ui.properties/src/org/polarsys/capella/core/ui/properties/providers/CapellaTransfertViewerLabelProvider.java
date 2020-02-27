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
package org.polarsys.capella.core.ui.properties.providers;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

public class CapellaTransfertViewerLabelProvider extends DataLabelProvider {

  private static String PATTERN1 = " [{0} -> {1}]{2}"; //$NON-NLS-1$
  private static String UNAMED = "<unnamed>"; //$NON-NLS-1$

  private boolean disableLabelComputation = CapellaUIPropertiesPlugin.getDefault().isDisableLabelComputation();

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object) {
    if (object instanceof DView) { // Sirius-2822
      Viewpoint viewpoint = ((DView) object).getViewpoint();
      if (viewpoint != null) {
        return getImage(viewpoint);
      }
    }
    if (object instanceof DAnnotation) {
      DAnnotation annotation = (DAnnotation) object;
      String imagePath = "/icons/full/obj16/TitleBlock_16.png";
      URL url = FileLocator.find(CapellaUIResourcesPlugin.getDefault().getBundle(), new Path(imagePath), null);
      return ImageDescriptor.createFromURL(url).createImage();
    }
    return super.getImage(object);
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object) {
    if (disableLabelComputation) {
      if (object instanceof AbstractNamedElement) {
        return ((AbstractNamedElement) object).getName();
      } else if (object instanceof EObject) {
        return "[" + ((EObject) object).eClass().getName() + "]";
      }
      return UNAMED;
    }
    return doGetText(object);
  }

  private String doGetText(Object object) {
    NamedElement sourceElement = null;
    NamedElement targetElement = null;
    String sourceLabel = null;
    String targetLabel = null;
    String sufixLabel = "";

    if ((object instanceof ComponentExchange) && !(object instanceof CommunicationMean)) {
      ComponentExchange connection = (ComponentExchange) object;
      if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(connection))) {
        Component sourceComponent = ComponentExchangeExt.getSourceComponent(connection);
        Component targetComponent = ComponentExchangeExt.getTargetComponent(connection);
        if (ComponentExchangeExt.isConnectionBetweenTypes(connection)) {
          sourceElement = sourceComponent;
          targetElement = targetComponent;
        } else {
          Collection<Part> sourceParts = ComponentExchangeExt.getSourceParts(connection);
          Collection<Part> targetParts = ComponentExchangeExt.getTargetParts(connection);
          sourceElement = (NamedElement) (!sourceParts.isEmpty() ? sourceParts.toArray()[0] : null);
          targetElement = (NamedElement) (!targetParts.isEmpty() ? targetParts.toArray()[0] : null);
          if (sourceElement != null && targetElement != null) {
            sourceLabel = sourceElement.getName() + " : " + sourceComponent.getName();
            targetLabel = targetElement.getName() + " : " + targetElement.getName();
            return super.getText(object) + MessageFormat.format(PATTERN1, sourceLabel, targetLabel, sufixLabel);
          }
        }
      } else {
        sourceElement = ComponentExchangeExt.getSourceComponent(connection);
        targetElement = ComponentExchangeExt.getTargetComponent(connection);
      }
      if (connection.getKind() == ComponentExchangeKind.DELEGATION) {
        sufixLabel = " [DELEGATION]";
      }
    } else if (object instanceof CommunicationMean) {
      // Communication mean @OA level
      // assuming this link is always between Entities
      ComponentExchange connection = (ComponentExchange) object;
      InformationsExchanger source = connection.getSource();
      InformationsExchanger target = connection.getTarget();
      if ((source instanceof Entity) && (target instanceof Entity)) {
        sourceElement = (Entity) source;
        targetElement = (Entity) target;
      }
    } else if (object instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) object;
      sourceElement = FunctionExt.getIncomingAbstractFunction(exchange);
      targetElement = FunctionExt.getOutGoingAbstractFunction(exchange);
    } else if (object instanceof PhysicalLink) {
      PhysicalLink link = (PhysicalLink) object;
      if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(link))) {
        sourceElement = PhysicalLinkExt.getSourcePart(link);
        targetElement = PhysicalLinkExt.getTargetPart(link);
      } else {
        sourceElement = PhysicalLinkExt.getSourceComponent(link);
        targetElement = PhysicalLinkExt.getTargetComponent(link);
      }

    } else if (object instanceof DAnalysis) { // Sirius-2822
      Resource resource = ((DAnalysis) object).eResource();
      if ((resource != null) && (resource.getURI() != null)) {
        return resource.getURI().lastSegment();
      }

    } else if (object instanceof DView) { // Sirius-2822
      Viewpoint viewpoint = ((DView) object).getViewpoint();
      if ((viewpoint != null)) {
        if (viewpoint.eIsProxy()) {
          return ((InternalEObject) viewpoint).eProxyURI().toString();
        }
        return viewpoint.getName();
      }

    } else if (object instanceof DAnnotation) {
      if (!(((DAnnotation) object).getReferences().get(0) instanceof DAnnotation)) {
        EObject obj = ((DAnnotation) object).getReferences().get(0);
        if (obj instanceof AbstractNamedElement) {
          return ((AbstractNamedElement) obj).getName();
        }
      } else {
        DSemanticDiagram diagram = (DSemanticDiagram) ((DAnnotation) object).eContainer();
        if (diagram instanceof DRepresentation) {
          return ((DRepresentation) diagram).getName();
        }
      }
    } else {
      // Default case
      return super.getText(object);
    }

    if ((sourceElement == null) || sourceElement.getName().equals(ICommonConstants.EMPTY_STRING)) {
      sourceLabel = UNAMED;
    } else {
      sourceLabel = sourceElement.getName();
    }
    if ((targetElement == null) || targetElement.getName().equals(ICommonConstants.EMPTY_STRING)) {
      targetLabel = UNAMED;
    } else {
      targetLabel = targetElement.getName();
    }
    return super.getText(object) + MessageFormat.format(PATTERN1, sourceLabel, targetLabel, sufixLabel);
  }
}
