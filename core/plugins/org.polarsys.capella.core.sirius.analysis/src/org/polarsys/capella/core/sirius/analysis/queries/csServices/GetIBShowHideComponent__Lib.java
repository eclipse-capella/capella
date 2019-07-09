/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveContextFilter;

@ExtendingQuery (extendingQuery = GetIBShowHideComponent.class)
public class GetIBShowHideComponent__Lib extends AbstractQuery {

  @Override
  // has been extended to give all the components from this level and superior levels
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    EObject target = getIBTarget((DSemanticDecorator) input);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(target);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(target, (CapellaModel) library);
      result.addAll(QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COMPONENTS, correspondingInput, context, new RemoveActorsFilter()));
    }
    return QueryInterpretor.executeFilter(result, new MultiFilter(new IQueryFilter[] { new RemoveActorsFilter(), new RemoveContextFilter() }));
  }

  private EObject getIBTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      if ((decorator.getTarget() instanceof AbstractActor) || (decorator.getTarget() instanceof System)) {
        return getParentContainer(decorator.getTarget());
      }
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
