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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Allows to get the parent of a component in the semantic browser
 * 
 * 
 */
public class Component_parentComponent implements IQuery {

  /**
	 * Constructor
	 */
  public Component_parentComponent() {
    //Does nothing
  }

  /**
   * 
   * ownerComponent
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Component) {
      Component lc = (Component) object;
      //This test prevented the Operational Entities from displaying their parents in the semantic browser
      result.addAll(getDirectParents(lc));
    }
    return result;
  }

  /*
   * Return the direct Component parents list for the Component given in parameter
   */
  private List<Component> getDirectParents(Component component) {
    List<Component> parentsComponents = new ArrayList<Component>();

    for (Partition partition : component.getRepresentingPartitions()) {
      PartitionableElement partitionanableElt = (PartitionableElement) partition.eContainer();
      if (partitionanableElt instanceof Component)
        parentsComponents.add((Component) partitionanableElt);
    }
    return parentsComponents;
  }

}
