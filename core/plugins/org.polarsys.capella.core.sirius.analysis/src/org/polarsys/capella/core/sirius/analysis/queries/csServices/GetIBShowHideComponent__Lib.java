/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;

@ExtendingQuery(extendingQuery = GetIBShowHideComponent.class)
public class GetIBShowHideComponent__Lib extends AbstractQuery {

  @Override
  // has been extended to give all the components from this level and superior levels
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Component> result = new ArrayList<>();
    EObject target = getIBTarget((DSemanticDecorator) input);
    IModel currentProject = ILibraryManager.INSTANCE.getModel(target);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);

    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(target, (CapellaModel) library);
      List<Component> components = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COMPONENTS,
          correspondingInput, context);

      result.addAll(components);
    }

    return filter(result).stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
  }

  protected List<Component> filter(List<Component> result) {
    return QueryInterpretor.executeFilter(result, new MultiFilter(new IQueryFilter[] { new RemoveActorsFilter() }));
  }

  private EObject getIBTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator.getTarget()) {
          return getParentContainer(decorator.getTarget());
        }
      }
      return decorator.getTarget();
    }
    return decorator.getTarget();
  }

  private EObject getParentContainer(EObject current) {
    EObject object = current;
    for (object = current.eContainer(); object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return object;
      }
    }
    return null;
  }
}
