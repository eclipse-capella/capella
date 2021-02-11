/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.context.SessionContext;

public class BlackInternalLinks extends AbstractDiagramTestCase {

  @Override
  public String getRequiredTestModel() {
    return BlackInternalLinks.class.getSimpleName();
  }
  
  @Override
  public void test() throws Exception {
    
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    odesignMappings(session);
    internalLinksProperlyMerged(session, context);
    refreshAllNonDirty(session, context);
  }
  
  /**
   * Ensure that when we do a refresh when links are blacked, there is no dirty in this session.
   */
  private void refreshAllNonDirty(Session session, SessionContext context) {
    session.save(new NullProgressMonitor());
    
    // refresh all representation
    for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
      DiagramContext dc = new DiagramContext(context, (DDiagram)descriptor.getRepresentation());
      dc.open();
      dc.refreshDiagram();
      dc.close();
    }
    assertTrue(SessionStatus.SYNC.equals(session.getStatus()));
  }

  /**
   * This test ensures in all diagrams FC/PP that interal links are properly merged into one when several path are going through the same ports.
   * 
   * On PAB-PP, the PhysicalPath 1 and 2 are merged even if path are created in inverse order. (internal links are unordered)
   * If failing in this diagram, this may be due to when we retrieve the to-be-black internalLink, we retrieve a different link for PP1 and PP2 
   * instead of the same one. (findInternalLinkEdge)
   */
  private void internalLinksProperlyMerged(Session session, SessionContext context) {

    for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
      DiagramContext dc = new DiagramContext(context, (DDiagram)descriptor.getRepresentation());
      dc.open();
      dc.refreshDiagram();
      
      //For all (internal links) edges of FC or PP, we create <Source,Target>.
      //If there is more than one FC or PP edge going through same ports, then links are not properly merged.
      Set<String> bounds = new HashSet<String>();
      for (DEdge edge : dc.getDiagram().getEdges()) {
        if (edge.getTarget() instanceof FunctionalChain || edge.getTarget() instanceof PhysicalPath) {
          String key = Arrays.asList(edge.getSourceNode().getUid(), edge.getTargetNode().getUid()).stream().sorted().collect(Collectors.joining(":"));
          if (bounds.contains(key)) {
            System.err.println(dc.getDiagram().getName()+" diagram internal links not merged");
          }
          bounds.add(key);
        }
      }
      dc.close();
    }
    
  }

  /**
   * On odesign, we check also that the semanticExpressions is properly filled. 
   * Indeed, when it's empty, sirius will erase edge.getSemanticElements() for all internalLinks
   * making the session always dirty at each refresh.
   */
  public void odesignMappings(Session session) {

    HashSet<DiagramElementMapping> mappings = new HashSet<DiagramElementMapping>();
    for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
      for (DEdge edge : ((DDiagram)descriptor.getRepresentation()).getEdges()) {
        if (edge.getTarget() instanceof FunctionalChain || edge.getTarget() instanceof PhysicalPath) {
          mappings.add(edge.getDiagramElementMapping());
        }
      }
    }
    for (DiagramElementMapping mapping : mappings) {
      assertTrue(mapping.getSemanticElements().equals("aql:view.getPPInternalLinkSemanticElements()") || mapping.getSemanticElements().equals("aql:view.getFCInternalLinkSemanticElements()"));
    }
    
  }

}
