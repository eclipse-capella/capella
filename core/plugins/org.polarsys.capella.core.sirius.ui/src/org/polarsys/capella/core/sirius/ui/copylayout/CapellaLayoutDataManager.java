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
package org.polarsys.capella.core.sirius.ui.copylayout;

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
import org.eclipse.sirius.diagram.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.layout.AbstractSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.sirius.ui.copylayout.keyproviders.IKeyProvider;

public class CapellaLayoutDataManager extends AbstractSiriusLayoutDataManager implements SiriusLayoutDataManager {

  protected final Map<AbstractCapellaLayoutDataKey, AbstractLayoutData> layoutDataMap = new HashMap<AbstractCapellaLayoutDataKey, AbstractLayoutData>();

  protected Collection<IKeyProvider> _keyProviders = null;

  /**
   * @return the providers
   */
  public Collection<IKeyProvider> getKeyProviders() {
    if (_keyProviders == null) {
      _keyProviders = new ArrayList<IKeyProvider>();

      try {
        for (IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(
            "org.polarsys.capella.core.sirius.ui.copylayoutProvider")) {
          try {
            IKeyProvider provider = (IKeyProvider) element.createExecutableExtension("class");
            _keyProviders.add(provider);

          } catch (Exception e) {
            System.out.println("Cannot load a copy layout provider");
            e.printStackTrace();
          }
        }

      } catch (Exception e) {
        System.out.println("Cannot load copy layout providers");
        e.printStackTrace();
      }

    }
    return _keyProviders;
  }

  @Override
  public void storeLayoutData(final IGraphicalEditPart rootEditPart) {
    if (rootEditPart != null) {
      boolean isValid = true;

      EditPart parentView = rootEditPart.getParent();

      if (parentView != null) {
        Object currentModel = rootEditPart.getModel();
        Object parentModel = parentView.getModel();

        // If the current editPart is a labelNode we don't store the layout. it is already stored elsewhere
        if ((currentModel != null) && (parentModel != null) && (parentModel instanceof View)) {
          Node labelNode = SiriusGMFHelper.getLabelNode((View) parentModel);
          if ((labelNode != null) && currentModel.equals(labelNode)) {
            isValid = false;
          }
        }
      }

      if (isValid) {
        super.storeLayoutData(rootEditPart);
      }
    }
  }

  @Override
  protected void addEdgeLayoutData(final NodeLayoutData parentLayoutData, final DEdge edge, final EditPartViewer editPartViewer) {
    try {
      super.addEdgeLayoutData(parentLayoutData, edge, editPartViewer);

    } catch (Exception e) {
      //We are not able to create a layout (invalid edge). Catch exception silently and continue to store layout of other (valid) elements
    }
  }

  @Override
  protected void addNodeChildren(final DNode parentNode, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart, final View gmfView,
      Collection<LayoutDataKey> discoveredKeys) {

    try {
      super.addNodeChildren(parentNode, parentLayoutData, parentEditPart, gmfView, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a layout (invalid node). Catch exception silently and continue to store layout of other (valid) elements
    }
  }

  @Override
  protected void addNodeContainerChildren(final DNodeContainer container, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart,
      Collection<LayoutDataKey> discoveredKeys) {
    try {
      super.addNodeContainerChildren(container, parentLayoutData, parentEditPart, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a layout (invalid node). Catch exception silently and continue to store layout of other (valid) elements
    }
  }

  @Override
  protected void addNodeListChildren(final DNodeList nodeList, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart,
      Collection<LayoutDataKey> discoveredKeys) {
    try {
      super.addNodeListChildren(nodeList, parentLayoutData, parentEditPart, discoveredKeys);

    } catch (Exception e) {
      //We are not able to create a layout (invalid node). Catch exception silently and continue to store layout of other (valid) elements
    }
  }

  @Override
  protected void addOutgoingEdge(final NodeLayoutData parentLayoutData, final EdgeTarget sourceOfEdge, final EditPartViewer editPartViewer) {
    try {
      super.addOutgoingEdge(parentLayoutData, sourceOfEdge, editPartViewer);

    } catch (Exception e) {
      //We are not able to create a layout (invalid edge). Catch exception silently and continue to store layout of other (valid) elements
    }
  }

  public AbstractLayoutData getLayoutData(LayoutDataKey key) {
    AbstractLayoutData layoutData = null;
    if ((key instanceof AbstractCapellaLayoutDataKey) && validateKey((AbstractCapellaLayoutDataKey) key)) {
      layoutData = getLinkedLayoutData((AbstractCapellaLayoutDataKey) key);
    }

    if (layoutData != null) {
      if ((key instanceof CapellaNodeLayoutDataKey) && !(layoutData instanceof NodeLayoutData)) {
        layoutData = null;
      }
      if ((key instanceof CapellaEdgeLayoutDataKey) && !(layoutData instanceof EdgeLayoutData)) {
        layoutData = null;
      }
    }
    return layoutData;
  }

  public void addLayoutData(LayoutDataKey key, AbstractLayoutData layoutData) {

    if ((key instanceof AbstractCapellaLayoutDataKey) && validateKey((AbstractCapellaLayoutDataKey) key)) {
      if (key instanceof CapellaDecoratorLayoutDataKey) {
        layoutDataMap.put(((CapellaDecoratorLayoutDataKey) key).getParent(), layoutData);
      }
      layoutDataMap.put((AbstractCapellaLayoutDataKey) key, layoutData);
    }
  }

  protected EObject getSemanticElement(DSemanticDecorator decorator_p) {
    if (decorator_p == null) {
      return null;
    }
    EObject result = decorator_p.getTarget();
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

  public LayoutDataKey createKey(DSemanticDecorator semanticDecorator) {
    LayoutDataKey result = null;

    EObject realSemanticElement = getSemanticElement(semanticDecorator);
    if (realSemanticElement == null) {
      return AbstractCapellaLayoutDataKey.INVALID_KEY;
    }

    AbstractCapellaLayoutDataKey semanticKey = new AbstractCapellaLayoutDataKey(realSemanticElement);

    if (semanticDecorator instanceof DEdge) {
      result = new CapellaEdgeLayoutDataKey((DEdge) semanticDecorator, semanticKey);

    } else if (semanticDecorator instanceof AbstractDNode) {
      result = new CapellaNodeLayoutDataKey(semanticDecorator, semanticKey);

    } else if (semanticDecorator instanceof DDiagram) {
      result = new CapellaNodeLayoutDataKey(semanticDecorator, semanticKey);
    }

    return result;
  }

  public boolean containsData() {
    return !layoutDataMap.isEmpty();
  }

  public void clearLayoutData() {
    layoutDataMap.clear();
  }

  protected boolean validateKey(AbstractCapellaLayoutDataKey key) {
    return (key != AbstractCapellaLayoutDataKey.INVALID_KEY) && (key.getSemantic() != null) && !key.getSemantic().eIsProxy()
           && (key.getSemantic().eResource() != null);
  }

  protected AbstractLayoutData getLinkedLayoutData(AbstractCapellaLayoutDataKey key) {
    AbstractLayoutData layoutData = findLinkedLayoutData(key);

    if (layoutData == null) {
      // Retrieve first layout data found!
      for (IKeyProvider provider : getKeyProviders()) {

        for (LayoutDataKey childKey : provider.getKeys(key)) {
          layoutData = findLinkedLayoutData(childKey);
          if (layoutData != null) {
            break;
          }
        }

        if (layoutData != null) {
          break;
        }
      }
      //TODO we should also check combination of all keyProviders..
    }

    if (key instanceof CapellaDecoratorLayoutDataKey) {
      AbstractCapellaLayoutDataKey parentKey = ((CapellaDecoratorLayoutDataKey) key).getParent();
      if (parentKey != null) {
        layoutData = getLinkedLayoutData(parentKey);
      }
    }

    return layoutData;
  }

  protected AbstractLayoutData findLinkedLayoutData(LayoutDataKey key) {
    return decorateLayoutData(key, layoutDataMap.get(key));
  }

  protected AbstractLayoutData decorateLayoutData(LayoutDataKey key_p, AbstractLayoutData layoutData_p) {
    return layoutData_p;
  }
}
