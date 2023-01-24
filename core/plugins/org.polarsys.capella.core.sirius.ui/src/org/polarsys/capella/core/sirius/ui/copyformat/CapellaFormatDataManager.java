/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.ui.copyformat.keyproviders.IKeyProvider;

public class CapellaFormatDataManager extends AbstractSiriusFormatDataManager implements SiriusFormatDataManager {

  protected final Map<AbstractCapellaFormatDataKey, Map<String, AbstractFormatData>> formatDataMap = new HashMap<>();

  protected Collection<IKeyProvider> keyProviders = null;

  /**
   * @return the providers
   */
  public Collection<IKeyProvider> getKeyProviders() {
    if (keyProviders == null) {
      keyProviders = new ArrayList<>();

      try {
        for (IConfigurationElement element : Platform.getExtensionRegistry().getConfigurationElementsFor(
            "org.polarsys.capella.core.sirius.ui.copyformatProvider")) {
          try {
            IKeyProvider provider = (IKeyProvider) element.createExecutableExtension("class");
            keyProviders.add(provider);

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
    return keyProviders;
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
      formatData = getLinkedFormatData((AbstractCapellaFormatDataKey) key, mapping);
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
      if (key instanceof CapellaDecoratorFormatDataKey && addFormatOnSemantic((CapellaDecoratorFormatDataKey) key)) {
        updateFormatDataMap(((CapellaDecoratorFormatDataKey) key).getParent(), mapping, formatData);
      }
      updateFormatDataMap((AbstractCapellaFormatDataKey) key, mapping, formatData);
    }
  }

  private void updateFormatDataMap(AbstractCapellaFormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData) {
    Map<String, AbstractFormatData> formatsMap = formatDataMap.computeIfAbsent(key, x -> new TreeMap<>());
    formatsMap.put(mapping.getName(), formatData);
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
      if (!entity.getRepresentingParts().isEmpty()) {
        result = entity.getRepresentingParts().get(0);
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

  protected boolean addFormatOnSemantic(CapellaDecoratorFormatDataKey key) {
    if (((CapellaDecoratorFormatDataKey) key).getSemantic() instanceof FunctionalChain) {
      // On FunctionalChains, we don't want to add the layout on the chain, only on graphical node.
      // Indeed, otherwise a layout from an internal FC into an FCD will be matched to the DNode of FC in an
      // xAB/xDFB.
      return false;
    }
    if (((CapellaDecoratorFormatDataKey) key).getSemantic() instanceof PhysicalPath) {
      // On PhysicalPaths, we don't want to add the layout on the path, only on graphical node.
      // Indeed, otherwise a layout from an internal PP into an PPD will be matched to the DNode of PP in an
      // xAB.
      return false;
    }
    return true;
  }
  
  protected AbstractFormatData getLinkedFormatData(AbstractCapellaFormatDataKey key,
      RepresentationElementMapping mapping) {
    AbstractFormatData formatData = findLinkedFormatData(key, mapping);

    if (formatData == null) {
      // Retrieve first format data found!
      for (IKeyProvider provider : getKeyProviders()) {

        for (FormatDataKey childKey : provider.getKeys(key)) {
          formatData = findLinkedFormatData(childKey, mapping);
          if (formatData != null) {
            break;
          }
        }

        if (formatData != null) {
          break;
        }
      }
      // TODO we should also check combination of all keyProviders..
    }

    if (formatData == null && key instanceof CapellaDecoratorFormatDataKey) {
      AbstractCapellaFormatDataKey parentKey = ((CapellaDecoratorFormatDataKey) key).getParent();
      if (parentKey != null) {
        formatData = getLinkedFormatData(parentKey, mapping);
      }
    }

    return formatData;
  }

  protected AbstractFormatData findLinkedFormatData(FormatDataKey key, RepresentationElementMapping mapping) {
    if (!formatDataMap.containsKey(key))
      return null;

    Map<String, AbstractFormatData> mappingFormatDataMap = formatDataMap.get(key);
    if (mappingFormatDataMap.containsKey(mapping.getName()))
      return decorateFormatData(key, mappingFormatDataMap.get(mapping.getName()));
    // Return the first found linked FormatData regardless of its mapping
    if (!mappingFormatDataMap.entrySet().isEmpty())
      return decorateFormatData(key, mappingFormatDataMap.entrySet().iterator().next().getValue());
    return null;
  }

  protected AbstractFormatData decorateFormatData(FormatDataKey key, AbstractFormatData formatData) {
    return formatData;
  }
}
