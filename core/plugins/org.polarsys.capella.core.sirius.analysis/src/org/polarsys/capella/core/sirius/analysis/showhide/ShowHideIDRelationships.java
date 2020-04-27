/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.IBServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

public class ShowHideIDRelationships extends AbstractShowHide {

  public static final String INITIAL_SOURCE_VIEW = "sourceComponent"; //$NON-NLS-1$
  public static final String PROVIDED_INTERFACE = "provided"; //$NON-NLS-1$
  public static final String REQUIRED_INTERFACE = "required"; //$NON-NLS-1$

  public ShowHideIDRelationships(DDiagramContents content) {
    super(content);
  }

  @Override
  protected DiagramElementMapping getMapping(EObject semantic, DiagramContext context,
      HashMapSet<String, DSemanticDecorator> relatedViews) {

    if (semantic instanceof CommunicationLink) {
      return IBServices.getService().getMappingIDCommunicationLink(semantic, getContent().getDDiagram());

    } else if (semantic instanceof Generalization) {
      return IBServices.getService().getMappingIDGeneralization(semantic, getContent().getDDiagram());

    } else if (semantic instanceof ExchangeItem) {
      return IBServices.getService().getMappingIDExchangeItem(semantic, getContent().getDDiagram());

    } else if (semantic instanceof Interface) {
      return IBServices.getService().getMappingIDInterface(semantic, getContent().getDDiagram());

    } else if (semantic instanceof Component) {
      return IBServices.getService().getMappingIDComponent(semantic, getContent().getDDiagram());

    } else if (semantic instanceof InterfaceUse) {
      return IBServices.getService().getMappingIDInterfaceUse(semantic, getContent().getDDiagram());

    } else if (semantic instanceof InterfaceImplementation) {
      return IBServices.getService().getMappingIDInterfaceImplementation(semantic, getContent().getDDiagram());

    } else if (semantic instanceof ComponentPort) {
      //In case of a providedInterface links toward an componentPort, retrieve this edgeMapping
      if (relatedViews.containsKey(SOURCE) && relatedViews.containsKey(TARGET)
          && (context.getLastVariable(PROVIDED_INTERFACE) != null)) {
        return IBServices.getService().getMappingIDInterfaceProvide(semantic, getContent().getDDiagram());
      }
      //In case of a requiredInterface links toward an componentPort, retrieve this edgeMapping
      if (relatedViews.containsKey(SOURCE) && relatedViews.containsKey(TARGET)
          && (context.getLastVariable(REQUIRED_INTERFACE) != null)) {
        return IBServices.getService().getMappingIDInterfaceRequire(semantic, getContent().getDDiagram());
      }
      return IBServices.getService().getMappingIDComponentPort(semantic, getContent().getDDiagram());

    }

    return super.getMapping(semantic, context, relatedViews);
  }

  @Override
  protected HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {

    HashMapSet<String, EObject> result = new HashMapSet<String, EObject>();

    if (semantic instanceof CommunicationLink) {
      result.put(SOURCE, ((DSemanticDecorator) context.getLastVariable(INITIAL_SOURCE_VIEW).getValue()).getTarget());
      result.put(TARGET, ((CommunicationLink) semantic).getExchangeItem());
    }
    if (semantic instanceof Generalization) {
      result.put(SOURCE, ((Generalization) semantic).getSub());
      result.put(TARGET, ((Generalization) semantic).getSuper());
    }
    if (semantic instanceof InterfaceUse) {
      result.put(SOURCE, ((DSemanticDecorator) context.getLastVariable(INITIAL_SOURCE_VIEW).getValue()).getTarget());
      result.put(TARGET, ((InterfaceUse) semantic).getUsedInterface());
    }
    if (semantic instanceof InterfaceImplementation) {
      result.put(SOURCE, ((DSemanticDecorator) context.getLastVariable(INITIAL_SOURCE_VIEW).getValue()).getTarget());
      result.put(TARGET, ((InterfaceImplementation) semantic).getImplementedInterface());
    }
    if (semantic instanceof ComponentPort) {
      //Some specific cases for required/provided interface links:
      //we want to display an Edge<sourceNode=port,targetNode=interface,target=port>
      if ((context.getLast(SOURCE) == null) && (context.getLast(TARGET) == null)
          && (context.getLastVariable(PROVIDED_INTERFACE) != null)) {
        result.put(SOURCE, ((DSemanticDecorator) context.getLastVariable(INITIAL_SOURCE_VIEW).getValue()).getTarget());
        result.put(TARGET, (Interface) context.getLastVariable(PROVIDED_INTERFACE).getValue());

      } else if ((context.getLast(SOURCE) == null) && (context.getLast(TARGET) == null)
          && (context.getLastVariable(REQUIRED_INTERFACE) != null)) {
        result.put(SOURCE, ((DSemanticDecorator) context.getLastVariable(INITIAL_SOURCE_VIEW).getValue()).getTarget());
        result.put(TARGET, (Interface) context.getLastVariable(REQUIRED_INTERFACE).getValue());

      } else {
        //on default case (displaying a port, related element is its container)
        result.put(CONTAINER, ((ComponentPort) semantic).eContainer());
      }
    }
    return result;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    if (originCouple.getValue() instanceof CommunicationLink) {
      return true;
    }
    if (originCouple.getValue() instanceof Generalization) {
      return true;
    }
    if (originCouple.getValue() instanceof Component) {
      return false;
    }
    if (originCouple.getValue() instanceof Interface) {
      return false;
    }
    if (originCouple.getValue() instanceof ExchangeItem) {
      return false;
    }
    if (originCouple.getValue() instanceof ComponentPort) {
      //We want to hide provided/required links but not the ports
      if (originCouple.getKey().equals(ROOT)) {
        return true;
      }
      return false;
    }
    return super.mustHide(originCouple, context);
  }

  @Override
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context,
      Collection<DSemanticDecorator> targetViews) {
    if ((semantic instanceof ExchangeItem) || (semantic instanceof Interface) || (semantic instanceof Component)) {
      return Collections.singletonList((DSemanticDecorator) getContent().getDDiagram());
    }
    return super.retrieveDefaultContainer(semantic, context, targetViews);
  }

}
