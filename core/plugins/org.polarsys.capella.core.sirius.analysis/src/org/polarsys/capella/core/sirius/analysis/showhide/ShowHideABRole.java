/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A ShowHide definition for ABCategory
 * 
 * containers of category pins must be set with sourceParts and targetParts variables
 * 
 */
public class ShowHideABRole extends ShowHideABComponent {

  /**
   * @param content_p
   */
  public ShowHideABRole(DDiagramContents content_p) {
    super(content_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    ContextItemElement lastContext = context_p.getLast();

    HashMapSet<String, EObject> value = super.getRelatedObjects(semantic_p, context_p);

    if (lastContext.getValue() instanceof Role) {
      // Retrieve all parts containing the given part
      Collection<EObject> result = new HashSet<EObject>();
      Role role = (Role) lastContext.getValue();
      for (RoleAllocation allocation : role.getRoleAllocations()) {
        if (allocation.getSourceElement() instanceof Entity) {
          result.add(allocation.getSourceElement());
        }
      }
      value.putAll(CONTAINER, result);
    }

    return value;
  }

  @Override
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    if (originCouple_p.getValue() instanceof Role) {
      for (ContextItemView view : originCouple_p.getViews()) {
        if (view.getViews().get(INITIAL_VIEWS).size() > 0) {
          return false;
        }
      }
    }
    return super.mustShow(originCouple_p, context_p, relatedViews_p);
  }

  @Override
  public DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    DiagramElementMapping mapping = super.getMapping(semantic_p, context_p, relatedViews_p);

    if (semantic_p instanceof Role) {
      String mappingName = MappingConstantsHelper.getMappingABRole(getContent().getDDiagram());
      mapping = getContent().getMapping(mappingName);
    }
    return mapping;
  }

  @Override
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    EObject semantic = originCouple_p.getValue();
    return semantic instanceof Role;
  }

}
