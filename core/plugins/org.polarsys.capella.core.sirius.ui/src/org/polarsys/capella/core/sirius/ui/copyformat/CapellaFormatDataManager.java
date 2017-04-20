/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.copyformat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.sirius.ui.copyformat.keyproviders.IKeyProvider;

public class CapellaFormatDataManager extends AbstractSiriusFormatDataManager implements SiriusFormatDataManager {

  protected final Map<AbstractCapellaFormatDataKey, AbstractFormatData> formatDataMap = new HashMap<AbstractCapellaFormatDataKey, AbstractFormatData>();

  protected Collection<IKeyProvider> _keyProviders = null;

  /**
   * @return the providers
   */
  public Collection<IKeyProvider> getKeyProviders() {
    if (_keyProviders == null) {
      _keyProviders = new ArrayList<IKeyProvider>();

      try {
        for (IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(
            "org.polarsys.capella.core.sirius.ui.copyformatProvider")) {
          try {
            IKeyProvider provider = (IKeyProvider) element.createExecutableExtension("class");
            _keyProviders.add(provider);

          } catch (Exception e) {
            System.out.println("Cannot load a copy format provider");
            e.printStackTrace();
          }
        }

      } catch (Exception e) {
        System.out.println("Cannot load copy format providers");
        e.printStackTrace();
      }

    }
    return _keyProviders;
  }

  @Override
  public void storeFormatData(final IGraphicalEditPart rootEditPart) {
    if (rootEditPart != null) {
      boolean isValid = true;

      EditPart parentView = rootEditPart.getParent();

      if (parentView != null) {
        Object currentModel = rootEditPart.getModel();
        Object parentModel = parentView.getModel();

        // If the current editPart is a labelNode we don't store the format. it is already stored elsewhere
        if ((currentModel != null) && (parentModel != null) && (parentModel instanceof View)) {
          Node labelNode = SiriusGMFHelper.getLabelNode((View) parentModel);
          if ((labelNode != null) && currentModel.equals(labelNode)) {
            isValid = false;
          }
        }
      }

      if (isValid) {
        super.storeFormatData(rootEditPart);
      }
    }
  }

  @Override
  protected void addEdgeFormatData(final NodeFormatData parentFormatData, final DEdge edge, final EditPartViewer editPartViewer) {
    try {
      super.addEdgeFormatData(parentFormatData, edge, editPartViewer);

    } catch (Exception e) {
      //We are not able to create a format (invalid edge). Catch exception silently and continue to store format of other (valid) elements
    }
  }

  @Override
  protected void addNodeChildren(final DNode parentNode, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart, final View gmfView,
      Collection<FormatDataKey> discoveredKeys) {

    try {
      super.addNodeChildren(parentNode, parentFormatData, parentEditPart, gmfView, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a format (invalid node). Catch exception silently and continue to store format of other (valid) elements
    }
  }

  @Override
  protected void addNodeContainerChildren(final DNodeContainer container, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart,
      Collection<FormatDataKey> discoveredKeys) {
    try {
      super.addNodeContainerChildren(container, parentFormatData, parentEditPart, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a format (invalid node). Catch exception silently and continue to store format of other (valid) elements
    }
  }

  @Override
  protected void addNodeListChildren(final DNodeList nodeList, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart,
      Collection<FormatDataKey> discoveredKeys) {
    try {
      super.addNodeListChildren(nodeList, parentFormatData, parentEditPart, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a format (invalid node). Catch exception silently and continue to store format of other (valid) elements
    }
  }

  @Override
  protected void addOutgoingEdge(final NodeFormatData parentFormatData, final EdgeTarget sourceOfEdge, final EditPartViewer editPartViewer) {
    try {
      super.addOutgoingEdge(parentFormatData, sourceOfEdge, editPartViewer);

    } catch (Exception e) {
      //We are not able to create a format (invalid edge). Catch exception silently and continue to store format of other (valid) elements
    }
  }

  @Override
  public AbstractFormatData getFormatData(FormatDataKey key, RepresentationElementMapping mapping) {
    AbstractFormatData formatData = null;
    if ((key instanceof AbstractCapellaFormatDataKey) && validateKey((AbstractCapellaFormatDataKey) key)) {
      formatData = getLinkedFormatData((AbstractCapellaFormatDataKey) key);
    }

    if (formatData != null) {
      if ((key instanceof CapellaNodeFormatDataKey) && !(formatData instanceof NodeFormatData)) {
        formatData = null;
      }
      if ((key instanceof CapellaEdgeFormatDataKey) && !(formatData instanceof EdgeFormatData)) {
        formatData = null;
      }
    }
    return formatData;
  }

  @Override
  public void addFormatData(FormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData) {

    if ((key instanceof AbstractCapellaFormatDataKey) && validateKey((AbstractCapellaFormatDataKey) key)) {
      if (key instanceof CapellaDecoratorFormatDataKey) {
        formatDataMap.put(((CapellaDecoratorFormatDataKey) key).getParent(), formatData);
      }
      formatDataMap.put((AbstractCapellaFormatDataKey) key, formatData);
    }
  }

  protected EObject getSemanticElement(DSemanticDecorator decorator) {
    if (decorator == null) {
      return null;
    }
    EObject result = decorator.getTarget();
    if (result == null) {
      return result;
    }

    if (result instanceof Entity) {
      Entity entity = (Entity) result;
      if (!entity.getRepresentingPartitions().isEmpty()) {
        result = entity.getRepresentingPartitions().get(0);
      }
    }

    return result;
  }

  @Override
  public FormatDataKey createKey(DSemanticDecorator semanticDecorator) {
    FormatDataKey result = null;

    EObject realSemanticElement = getSemanticElement(semanticDecorator);
    if (realSemanticElement == null) {
      return AbstractCapellaFormatDataKey.INVALID_KEY;
    }

    AbstractCapellaFormatDataKey semanticKey = new AbstractCapellaFormatDataKey(realSemanticElement);

    if (semanticDecorator instanceof DEdge) {
      result = new CapellaEdgeFormatDataKey((DEdge) semanticDecorator, semanticKey);

    } else if (semanticDecorator instanceof AbstractDNode) {
      result = new CapellaNodeFormatDataKey(semanticDecorator, semanticKey);

    } else if (semanticDecorator instanceof DDiagram) {
      result = new CapellaNodeFormatDataKey(semanticDecorator, semanticKey);
    }

    return result;
  }

  @Override
  public boolean containsData() {
    return !formatDataMap.isEmpty();
  }

  @Override
  public void clearFormatData() {
    formatDataMap.clear();
  }

  protected boolean validateKey(AbstractCapellaFormatDataKey key) {
    return (key != AbstractCapellaFormatDataKey.INVALID_KEY) && (key.getSemantic() != null) && !key.getSemantic().eIsProxy()
           && (key.getSemantic().eResource() != null);
  }

  protected AbstractFormatData getLinkedFormatData(AbstractCapellaFormatDataKey key) {
    AbstractFormatData formatData = findLinkedFormatData(key);

    if (formatData == null) {
      // Retrieve first format data found!
      for (IKeyProvider provider : getKeyProviders()) {

        for (FormatDataKey childKey : provider.getKeys(key)) {
          formatData = findLinkedFormatData(childKey);
          if (formatData != null) {
            break;
          }
        }

        if (formatData != null) {
          break;
        }
      }
      //TODO we should also check combination of all keyProviders..
    }

    if (key instanceof CapellaDecoratorFormatDataKey) {
      AbstractCapellaFormatDataKey parentKey = ((CapellaDecoratorFormatDataKey) key).getParent();
      if (parentKey != null) {
        formatData = getLinkedFormatData(parentKey);
      }
    }

    return formatData;
  }

  protected AbstractFormatData findLinkedFormatData(FormatDataKey key) {
    return decorateFormatData(key, formatDataMap.get(key));
  }

  protected AbstractFormatData decorateFormatData(FormatDataKey key, AbstractFormatData formatData) {
    return formatData;
  }
}
