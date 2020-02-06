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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 * This command removes non visible elements. This includes hidden elements but also non visible elements like a
 * FunctionalExchange between a visible Function and a hidden one.<br>
 * There is two operating modes: <br>
 * - if {@link RemoveHiddenElementsCommand#_doUnsynchronizeDiagrams} = true then all elements with "Unsynchronizable"
 * and "Not synchronized" mappings will be removed from diagrams.<br>
 * - if {@link RemoveHiddenElementsCommand#_doUnsynchronizeDiagrams} = false then only elements with "Not synchronized"
 * mappings will be removed from diagrams.
 */
public class RemoveHiddenElementsCommand extends AbstractReadWriteCommand implements ICommand {
  private Collection<DRepresentationDescriptor> representationsToClean;
  private Logger _logger;
  private boolean _doUnsynchronizeDiagrams;

  public RemoveHiddenElementsCommand(Collection<DRepresentationDescriptor> representationsToRefresh_p,
      ExecutionManager executionManager, boolean doUnsynchronizeDiagrams_p) {
    representationsToClean = representationsToRefresh_p;
    _doUnsynchronizeDiagrams = doUnsynchronizeDiagrams_p;

  }

  @Override
  public String getName() {
    return "Remove hidden elements";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    _logger = ReportManagerRegistry.getInstance().subscribe(getName()); // $NON-NLS-1$
    deleteHidden(representationsToClean);
  }

  /**
   * Removes non visible elements see {@link RemoveHiddenElementsCommand} Processes only diagrams
   * 
   * @param selection_p
   */
  private void deleteHidden(Collection<DRepresentationDescriptor> selection_p) {
    for (DRepresentationDescriptor descriptor : selection_p) {
      RepresentationDescription description = descriptor.getDescription();
      if (!(description instanceof DiagramDescription)) {
        continue;
      }
      DRepresentation representation = descriptor.getRepresentation();
      if (!(representation instanceof DDiagram)) {
        continue;
      }
      DDiagram diagram = (DDiagram) representation;
      int count = 0;
      ArrayList<DDiagramElement> elements = new ArrayList<DDiagramElement>();
      Iterable<DDiagramElement> diagramElements = DiagramServices.getDiagramServices().getDiagramElements(diagram);
      for (DDiagramElement element : diagramElements) {
        if (!element.isVisible()) {
          elements.add(element);
        }
      }
      if (_doUnsynchronizeDiagrams) {
        diagram.setSynchronized(false);
      }

      for (DDiagramElement element : elements) {
        RepresentationElementMapping mapping = element.getMapping();
        if (mapping instanceof DiagramElementMapping) {
          if (_doUnsynchronizeDiagrams) { // delete all hidden elements
            removeDiagramElt(element);
            count++;
          } else {// diagrams will not be unsynchronized => delete only elements with Not synchronized mappings
            boolean toRemove = isNotSynchronizedMapping((DiagramElementMapping) mapping);
            if (toRemove) {
              removeDiagramElt(element);
              count++;
            }
          }
        }
      }
      // report information message to Information View
      if (count > 0) {
        StringBuilder sb = new StringBuilder(" element"); //$NON-NLS-1$
        if (count > 1) {
          sb.append("s"); //$NON-NLS-1$
        }
        _logger.info("Removing " + count + sb + " from diagram: " + descriptor.getName()); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
  }

  /**
   * Returns true if the mapping is Not synchronized
   * 
   * @param mapping_p
   */
  public static boolean isNotSynchronizedMapping(DiagramElementMapping mapping_p) {
    boolean synchronizationLock = mapping_p.isSynchronizationLock();
    boolean createElts = mapping_p.isCreateElements();
    return (synchronizationLock == false) && (createElts == false);

  }

  /**
   * Removes element from diagram
   * 
   * @param element
   */
  private void removeDiagramElt(DDiagramElement element) {
    if ((element instanceof DDiagram) || (element instanceof DDiagramElementContainer)) {
      DiagramServices.getDiagramServices().removeContainerView(element);

    } else if (element instanceof DNode) {
      DiagramServices.getDiagramServices().removeNodeView((DNode) element);

    } else if (element instanceof DEdge) {
      DEdge edge = (DEdge) element;
      if ((edge.getSourceNode() != null) && (edge.getTargetNode() != null)) {
        DiagramServices.getDiagramServices().removeEdgeView(edge);
      }
    }
  }

}
