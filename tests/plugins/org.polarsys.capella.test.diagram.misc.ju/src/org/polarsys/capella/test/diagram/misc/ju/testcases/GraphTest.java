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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import org.polarsys.capella.core.model.helpers.graph.BasicGraph;
import org.polarsys.capella.core.model.helpers.graph.BasicGraph.Edge;
import org.polarsys.capella.core.model.helpers.graph.BasicGraph.Node;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class GraphTest extends BasicTestCase {
  
  @Override
  public void test() throws Exception {

    BasicGraph<String> g = new BasicGraph<String>("ij");
    
    Node<String> a = g.getOrCreateNode("A");
    Node<String> b = g.getOrCreateNode("B");
    Node<String> c = g.getOrCreateNode("C");

    Edge<String> ab = g.getOrCreateEdge("AB");
    ab.setSource(a);
    ab.setTarget(b);

    Edge<String> ac = g.getOrCreateEdge("AC");
    ac.setSource(a);
    ac.setTarget(c);

    Edge<String> ba = g.getOrCreateEdge("BA");
    ba.setSource(b);
    ba.setTarget(a);

    assertTrue(c.getIncomingEdges().contains(ac));
    assertTrue(c.getOutgoingEdges().isEmpty());
    assertTrue(a.getIncomingEdges().contains(ba));
    assertTrue(a.getOutgoingEdges().contains(ac));
    assertTrue(a.getOutgoingEdges().contains(ab));
    assertTrue(b.getIncomingEdges().contains(ab));
    assertTrue(b.getOutgoingEdges().contains(ba));

    // Test merge of two nodes
    g.mergeNodes(b, c, "B2");
    assertTrue(g.hasNode("B2"));
    assertTrue(b.getIncomingEdges().isEmpty());
    assertTrue(b.getOutgoingEdges().isEmpty());
    assertTrue(c.getIncomingEdges().isEmpty());
    assertTrue(c.getOutgoingEdges().isEmpty());
    assertTrue(!(g.hasNode("B")));
    assertTrue(!(g.hasNode("C")));

    Node<String> b2 = g.getOrCreateNode("B2");
    assertTrue(ab.getTarget() == b2);
    assertTrue(ac.getTarget() == b2);
    assertTrue(ba.getSource() == b2);
    assertTrue(b2.getIncomingEdges().contains(ab));
    assertTrue(b2.getIncomingEdges().contains(ac));
    assertTrue(b2.getOutgoingEdges().contains(ba));

    // Test deletion of an edge
    g.removeEdge(ab);
    assertTrue(ab.getSource() == null);
    assertTrue(ab.getTarget() == null);
    assertTrue(!g.hasEdge(ab.getSemantic()));
    assertTrue(!b2.getIncomingEdges().contains(ab));
    assertTrue(!a.getOutgoingEdges().contains(ab));

  }

}
