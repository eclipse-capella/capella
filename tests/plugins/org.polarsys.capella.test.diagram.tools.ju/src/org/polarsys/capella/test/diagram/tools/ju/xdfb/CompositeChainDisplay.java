/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph;
import org.polarsys.capella.core.model.helpers.graph.InvolvementGraph;
import org.polarsys.capella.core.sirius.analysis.cache.FunctionalChainCache;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.tools.ju.model.CompositeChains;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CompositeChainDisplay extends CompositeChains {

  private final Collection<EObject> NO_GRAPH_CHECK = null;

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    testComposite(context);
    testParentFunctions(context);
  }

  /**
   * Ensure that composite chain is properly computed and displayed
   */
  private void testComposite(SessionContext context) {
    DiagramContext lab = DiagramContext.getDiagram(context, LDFB_ROOT_LOGICAL_FUNCTION);
    lab.refreshDiagram();

    FunctionalChain chain = context.getSemanticElement(COMPOUND);
    InvolvementGraph graph = FunctionalChainCache.getInstance().getInvolvementGraph(chain);

    // Compute starts of composite chain
    Collection<EObject> starts = graph.getNodes().values().stream().filter(graph::isInvolvingFunction)
        .filter(graph::isStartingFunction)
        .map(n -> ((FunctionalChainInvolvementFunction) n.getSemantic()).getInvolved()).map(EObject.class::cast)
        .collect(Collectors.toSet());
    assertTrue("Chain doesn't have expected starts", starts.size() == 2);
    hasStyle(lab, LOGICALFUNCTION_5, starts);
    hasStyle(lab, LOGICALFUNCTION_13, starts);

    // Compute ends of composite chain
    Collection<EObject> ends = graph.getNodes().values().stream().filter(graph::isInvolvingFunction)
        .filter(graph::isEndingFunction).map(n -> ((FunctionalChainInvolvementFunction) n.getSemantic()).getInvolved())
        .map(EObject.class::cast).collect(Collectors.toSet());
    assertTrue("Chain doesn't have expected ends", ends.size() == 3);
    hasStyle(lab, LOGICALFUNCTION_9, ends);
    hasStyle(lab, LOGICALFUNCTION_12, ends);
    hasStyle(lab, LOGICALFUNCTION_14, ends);
    hasntStyle(lab, LOGICALFUNCTION_6, ends);
    hasntStyle(lab, LOGICALFUNCTION_8, ends);
    hasntStyle(lab, LOGICALFUNCTION_10, ends);
    hasntStyle(lab, LOGICALFUNCTION_11, ends);

    Collection<EObject> exchanges = graph.getEdges().values().stream().filter(graph::isInvolvingFunctionalExchange)
        .map(n -> ((FunctionalChainInvolvementLink) n.getSemantic()).getInvolved()).map(EObject.class::cast)
        .collect(Collectors.toSet());
    hasStyle(lab, FUNCTIONALEXCHANGE_4, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_11, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_5, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_8, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_7, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_12, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_9, exchanges);
    hasStyle(lab, FUNCTIONALEXCHANGE_10, exchanges);
    hasntStyle(lab, FUNCTIONALEXCHANGE_13, exchanges);
    hasntStyle(lab, FUNCTIONALEXCHANGE_14, exchanges);

    InternalLinksGraph internalLinks = FunctionalChainCache.getInstance().getInternalLinksGraph(graph);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_4, FUNCTIONALEXCHANGE_6);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_4, FUNCTIONALEXCHANGE_5);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_11, FUNCTIONALEXCHANGE_6);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_11, FUNCTIONALEXCHANGE_5);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_6, FUNCTIONALEXCHANGE_7);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_6, FUNCTIONALEXCHANGE_8);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_7, FUNCTIONALEXCHANGE_12);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_7, FUNCTIONALEXCHANGE_9);
    hasInternalLink(lab, internalLinks, FUNCTIONALEXCHANGE_8, FUNCTIONALEXCHANGE_10);
    assertTrue("Chain doesn't have expected internalLinks", internalLinks.getEdges().values().size() == 9);
  }

  /**
   * Ensure that chain is properly displayed on parents
   */
  private void testParentFunctions(SessionContext context) {
    DiagramContext lab = DiagramContext.getDiagram(context, LDFB_PARENT_FUNCTIONS);
    lab.refreshDiagram();

    FunctionalChain chain = context.getSemanticElement(COMPOUND);
    InvolvementGraph graph = FunctionalChainCache.getInstance().getInvolvementGraph(chain);

    // Compute starts of composite chain
    Collection<EObject> starts = graph.getNodes().values().stream().filter(graph::isInvolvingFunction)
        .filter(graph::isStartingFunction)
        .map(n -> ((FunctionalChainInvolvementFunction) n.getSemantic()).getInvolved()).map(EObject.class::cast)
        .collect(Collectors.toSet());
    assertTrue(starts.size() == 2);
    assertTrue(starts.contains(context.getSemanticElement(LOGICALFUNCTION_5)));
    hasStyle(lab, PARENTFUNCTION_5, NO_GRAPH_CHECK); // parent is not a start but has style as LF5 isn't displayed
    hasStyle(lab, PARENTFUNCTION_13, NO_GRAPH_CHECK); // parent is not a start but has style as LF13 isn't displayed

    // Compute ends of composite chain
    Collection<EObject> ends = graph.getNodes().values().stream().filter(graph::isInvolvingFunction)
        .filter(graph::isEndingFunction).map(n -> ((FunctionalChainInvolvementFunction) n.getSemantic()).getInvolved())
        .map(EObject.class::cast).collect(Collectors.toSet());
    assertTrue(ends.size() == 3);
    hasStyle(lab, LOGICALFUNCTION_9, ends);
    hasntStyle(lab, PARENTFUNCTION_9, NO_GRAPH_CHECK); // parent is not an end and hasn't style as LF9 is displayed
    hasStyle(lab, PARENTFUNCTION_12, NO_GRAPH_CHECK); // parent is not an end but has style as LF12 isn't displayed
    hasStyle(lab, PARENTFUNCTION_14, NO_GRAPH_CHECK); // parent is not an end but has style as LF14 isn't displayed
  }

  /**
   * Returns whether the element is contained in the given list, and appears as highlighted
   */
  private void hasStyle(DiagramContext lab, String element, Collection<EObject> semantics) {
    DDiagramElement view = (DDiagramElement) lab.getView(element);
    if (semantics != NO_GRAPH_CHECK) {
      assertTrue(semantics.contains(view.getTarget()));
    }

    String message = NLS.bind("Element {0} shall be highlighted", element);
    if (view instanceof DNodeContainer) {
      assertTrue(message, ((DNodeContainer) view).getOwnedStyle().getBorderSize() == 4);

    } else if (view instanceof DEdge) {
      assertTrue(message, ((DEdge) view).getOwnedStyle().getSize() == 4);
    }
  }

  /**
   * Returns whether the element is not contained in the given list, and appears as normal
   */
  private void hasntStyle(DiagramContext lab, String element, Collection<EObject> semantics) {
    DDiagramElement view = (DDiagramElement) lab.getView(element);
    if (semantics != NO_GRAPH_CHECK) {
      assertTrue(!semantics.contains(view.getTarget()));
    }

    String message = NLS.bind("Element {0} shall be normal", element);
    if (view instanceof DNodeContainer) {
      assertTrue(message, ((DNodeContainer) view).getOwnedStyle().getBorderSize() == 1);

    } else if (view instanceof DEdge) {
      assertTrue(message, ((DEdge) view).getOwnedStyle().getSize() == 1);
    }
  }

  /**
   * Returns whether the graph contains an internalLink between both exchanges, including in the diagram
   */
  private void hasInternalLink(DiagramContext context, InternalLinksGraph internalLinks, String startExchange,
      String endExchange) {
    FunctionalExchange start = context.getSessionContext().getSemanticElement(startExchange);
    FunctionalExchange end = context.getSessionContext().getSemanticElement(endExchange);

    String message = NLS.bind("Missing internal link between {0} and {1}", start.getTarget().getName(),
        end.getTarget().getName());

    // Check graph contains internalLink
    assertTrue(message, internalLinks.hasInternalLink(start.getTarget(), end.getSource()));

    // Check diagram contains internalLink
    assertTrue(message, context.getDiagram().getEdges().stream().anyMatch(e -> {
      return (e.getTarget() == internalLinks.getSemantic())
          && ((DSemanticDecorator) e.getSourceNode()).getTarget() == start.getTarget()
          && ((DSemanticDecorator) e.getTargetNode()).getTarget() == end.getSource();
    }));

  }
}
