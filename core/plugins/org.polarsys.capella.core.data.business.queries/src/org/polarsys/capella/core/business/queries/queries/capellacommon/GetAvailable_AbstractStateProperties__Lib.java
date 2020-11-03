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

package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.ListExt;

@ExtendingQuery (extendingQuery = GetAvailable_AbstractStateProperties.class)
public class GetAvailable_AbstractStateProperties__Lib extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }
  
  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject = ILibraryManager.INSTANCE.getModel(element);
    if (element instanceof IState) {
      Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        if (library instanceof CapellaModel) {
          BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaModel) library);
          
          for (BlockArchitecture block : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
            TreeIterator<Object> allContents = EcoreUtil.getAllContents(block, false);
            while (allContents.hasNext()) {
        	    Object object = allContents.next();
          	    if ((object instanceof ExchangeItem) || (object instanceof Operation)) {
        	      availableElements.add((CapellaElement) object);
        	    }
            }
          }
          
          TreeIterator<Object> allContents = EcoreUtil.getAllContents(correspondingBlock, false);
          while (allContents.hasNext()) {
        	  Object object = allContents.next();
        	  if ((object instanceof AbstractEvent) && !(object instanceof Event) && !(object instanceof AbstractFunction)) {
        		  availableElements.add((CapellaElement) object);
        	  }
          }
        }
      }
    availableElements = ListExt.removeDuplicates(availableElements);
    }
    return availableElements;
  }
}
