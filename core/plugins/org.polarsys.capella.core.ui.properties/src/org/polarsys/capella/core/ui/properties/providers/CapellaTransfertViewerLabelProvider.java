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
package org.polarsys.capella.core.ui.properties.providers;

import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 */
public class CapellaTransfertViewerLabelProvider extends DataLabelProvider {

  private static String PATTERN1 = " [{0} -> {1}]"; //$NON-NLS-1$
  private static String UNAMED = "<unnamed>"; //$NON-NLS-1$

  /**
   * Default constructor
   */
  public CapellaTransfertViewerLabelProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  /**
   * Default constructor
   */
  public CapellaTransfertViewerLabelProvider(TransactionalEditingDomain editingDomain) {
    super(editingDomain, CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object_p) {
    if (object_p instanceof DView) { //Sirius-2822
      Viewpoint viewpoint = ((DView) object_p).getViewpoint();
      if (viewpoint != null) {
        return getImage(viewpoint);
      }
    }
    return super.getImage(object_p);
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object_p) {
    NamedElement sourceElement = null;
    NamedElement targetElement = null;
    String sourceLabel = null;
    String targetLabel = null;

    if ((object_p instanceof ComponentExchange) && !(object_p instanceof CommunicationMean)) {
      ComponentExchange connection = (ComponentExchange) object_p;
      if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(connection))) {
        Collection<Part> sourceParts = ComponentExchangeExt.getSourceParts(connection);
        Collection<Part> targetParts = ComponentExchangeExt.getTargetParts(connection);
        sourceElement = (NamedElement) (sourceParts.size() > 0 ? sourceParts.toArray()[0] : null);
        targetElement = (NamedElement) (targetParts.size() > 0 ? targetParts.toArray()[0] : null);
      } else {
        sourceElement = ComponentExchangeExt.getSourceComponent(connection);
        targetElement = ComponentExchangeExt.getTargetComponent(connection);
      }
    } else if (object_p instanceof CommunicationMean) {
      // Communication mean @OA level
      // assuming this link is always between Entities
      ComponentExchange connection = (ComponentExchange) object_p;
      InformationsExchanger source = connection.getSource();
      InformationsExchanger target = connection.getTarget();
      if ((source instanceof Entity) && (target instanceof Entity)) {
        sourceElement = (Entity) source;
        targetElement = (Entity) target;
      }
    } else if (object_p instanceof FunctionalExchange) {
      FunctionalExchange exchange = (FunctionalExchange) object_p;
      sourceElement = FunctionExt.getIncomingAbstractFunction(exchange);
      targetElement = FunctionExt.getOutGoingAbstractFunction(exchange);
    } else if (object_p instanceof PhysicalLink) {
      PhysicalLink link = (PhysicalLink) object_p;
      if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(link))) {
        sourceElement = PhysicalLinkExt.getSourcePart(link);
        targetElement = PhysicalLinkExt.getTargetPart(link);
      } else {
        sourceElement = PhysicalLinkExt.getSourceComponent(link);
        targetElement = PhysicalLinkExt.getTargetComponent(link);
      }

    } else if (object_p instanceof DAnalysis) { //Sirius-2822
      Resource resource = ((DAnalysis) object_p).eResource();
      if ((resource != null) && (resource.getURI() != null)) {
        return resource.getURI().lastSegment();
      }

    } else if (object_p instanceof DView) { //Sirius-2822
      Viewpoint viewpoint = ((DView) object_p).getViewpoint();
      if ((viewpoint != null)) {
        if (viewpoint.eIsProxy()) {
          return ((InternalEObject) viewpoint).eProxyURI().toString();
        }
        return viewpoint.getName();
      }

    } else {
      // Default case
      return super.getText(object_p);
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
    return super.getText(object_p) + MessageFormat.format(PATTERN1, new Object[] { sourceLabel, targetLabel });
  }
}
