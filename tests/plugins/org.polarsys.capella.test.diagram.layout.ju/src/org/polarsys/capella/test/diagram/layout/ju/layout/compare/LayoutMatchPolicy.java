/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.layout.ju.layout.Bounds;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;

public class LayoutMatchPolicy extends DefaultMatchPolicy {
  
  HashMap<String, String> match = new HashMap<String, String>();
  HashMap<String, String> mappingMatch = new HashMap<String, String>();

  public void addMappingMatch(String source, String target) {
    mappingMatch.put(target, source);
  }
  
  public void addMatch(EObject element1, EObject element2) {
    String id1 = IdManager.getInstance().getId(element1);
    String id2 = IdManager.getInstance().getId(element2);
    match.put(id2, id1);
  }
  
  @Override
  public boolean keepMatchIDs() {
    return true;
  }

  protected String getMappingId(String mapping) {
    if (mappingMatch.containsKey(mapping)) {
      return mappingMatch.get(mapping);
    }
    return mapping;
  }
  
  protected String getSemanticId(ISemanticLayout layout) {
    if (match.containsKey(layout.getId())) {
      return match.get(layout.getId());
    }
    return layout.getId();
  }
  
  @Override
  public Object getMatchID(EObject element_p, ITreeDataScope<EObject> scope_p) {
    if (element_p == null) {
      return "null";
    }
    if (element_p instanceof SessionLayout) {
      return SessionLayout.class.getSimpleName();
    }
    if (element_p instanceof NodeLayout) {
      NodeLayout layout = (NodeLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + getSemanticId(layout);
      id += " mapping:" + getMappingId(layout.getActualMapping());
      return id;
    }
    if (element_p instanceof EdgeLayout) {
      EdgeLayout layout = (EdgeLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + getSemanticId(layout);
      id += " source:" + (String) getMatchID(layout.getSource(), scope_p);
      id += " target:" + (String) getMatchID(layout.getTarget(), scope_p);
      id += " mapping:" + getMappingId(layout.getActualMapping());
      return id;
    }
    if (element_p instanceof DiagramLayout) {
      DiagramLayout layout = (DiagramLayout) element_p;
      return getSemanticId(layout) + " " + layout.getName() + " ";
    }
    if (element_p instanceof Bounds || element_p instanceof Location || element_p instanceof Size) {
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += "." + element_p.eContainingFeature().getName();
      if (element_p.eContainingFeature().isMany()) {
        id += "[" + ((EList) element_p.eContainer().eGet(element_p.eContainingFeature())).indexOf(element_p) + "]";
      }
      return id;
    }
    if (element_p instanceof NoteLayout) {
      NoteLayout layout = (NoteLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + getSemanticId(layout);
      return id;
    }
    return super.getMatchID(element_p, scope_p);
  }

  /**
   * Add traceabilities between RPL elements and their REC
   */
  public void addMatchRPLtoREC(CatalogElement rpl) {
    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      addMatch(link.getTarget(), link.getOrigin().getTarget());
    }
  }

  /**
   * Add a traceability between Architecture Blank mappings
   */
  public void addArchitectureBlankMappingMatches() {
    
    addMappingMatch("ACTOR", IMappingNameConstants.SAB_ACTOR_MAPPING_NAME);
    addMappingMatch("ACTOR", IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME);
    addMappingMatch("ACTOR", IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);

    addMappingMatch("FCT", IMappingNameConstants.SAB_SYSTEM_FUNCTION_MAPPING_NAME);
    addMappingMatch("FCT", IMappingNameConstants.LAB_LOGICAL_FUNCTION_MAPPING_NAME);
    addMappingMatch("FCT", IMappingNameConstants.PAB_PHYSICAL_FUNCTION_MAPPING_NAME);

    addMappingMatch("FP", IMappingNameConstants.SAB_FUNCTION_PORT_MAPPING_NAME);
    addMappingMatch("FP", IMappingNameConstants.LAB_FUNCTION_PORT_MAPPING_NAME);
    addMappingMatch("FP", IMappingNameConstants.PAB_FUNCTION_PORT_MAPPING_NAME);

    addMappingMatch("EXC", IMappingNameConstants.SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME);
    addMappingMatch("EXC", IMappingNameConstants.LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME);
    addMappingMatch("EXC", IMappingNameConstants.PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME);
  }

  /**
   * From a given architecture, add mapping between the given architecture elements and their allocated elements.
   */
  public void addMatchForTransition(BlockArchitecture architecture) {

    //Return whether the given trace is used for Transition traceability
    Predicate<AbstractTrace> fromTransition = new Predicate<AbstractTrace>() {
      
      @Override
      public boolean test(AbstractTrace trace) {
        BlockArchitecture s = BlockArchitectureExt.getRootBlockArchitecture(trace.getSourceElement());
        BlockArchitecture t = BlockArchitectureExt.getRootBlockArchitecture(trace.getTargetElement());
        return s.getAllocatedArchitectures().contains(t);
      }
      
    };
    
    Iterator<EObject> objects = EcoreUtil.getAllContents(architecture, false);
    while (objects.hasNext()) {
      EObject object = objects.next();
      if (object instanceof TraceableElement) {
        List<AbstractTrace> p = ((TraceableElement) object).getOutgoingTraces().stream().filter(fromTransition).collect(Collectors.toList());
        if (!p.isEmpty()) {
          addMatch(object, p.get(0).getTargetElement());
          if (object instanceof Component) {
            addMatch(((Component) object).getRepresentingParts().get(0),
                ((Component) p.get(0).getTargetElement()).getRepresentingParts().get(0));
          }
        }
      }
    }
  }

}
