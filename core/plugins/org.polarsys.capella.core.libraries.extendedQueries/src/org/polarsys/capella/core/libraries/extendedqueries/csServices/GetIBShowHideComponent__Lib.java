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
package org.polarsys.capella.core.libraries.extendedqueries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveContextFilter;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetIBShowHideComponent__Lib extends AbstractQuery {

  @Override
  // has been extended to give all the components from this level and superior levels
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    EObject target = getIBTarget((DSemanticDecorator) input_p);
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(target);
    Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true);
    for (IAbstractLibrary library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(target, (CapellaLibrary) library);
      result.addAll(QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COMPONENTS, correspondingInput, context_p, new RemoveActorsFilter()));
    }
    return QueryInterpretor.executeFilter(result, new MultiFilter(new IQueryFilter[] { new RemoveActorsFilter(), new RemoveContextFilter() }));
  }

  private EObject getIBTarget(DSemanticDecorator decorator_p) {
    if (decorator_p instanceof DDiagram) {
      if ((decorator_p.getTarget() instanceof AbstractActor) || (decorator_p.getTarget() instanceof System)) {
        return getParentContainer(decorator_p.getTarget());
      }
      for (DDiagramElement element : ((DDiagram) decorator_p).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator_p.getTarget()) {
          return getParentContainer(decorator_p.getTarget());
        }
      }
      return decorator_p.getTarget();
    }
    return decorator_p.getTarget();
  }

  private EObject getParentContainer(EObject current_p) {
    EObject object = current_p;
    for (object = current_p.eContainer(); object != null; object = object.eContainer()) {
      if ((object instanceof Component) || (object instanceof BlockArchitecture)) {
        return object;
      }
    }
    return null;
  }

}
