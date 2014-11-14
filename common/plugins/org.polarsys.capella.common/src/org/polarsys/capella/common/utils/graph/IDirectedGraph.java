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
package org.polarsys.capella.common.utils.graph;

import java.util.Iterator;

/**
 * Models a directed graph.
 */
public interface IDirectedGraph<T> {

  /**
   * Given a node 'source', which are this nodes children, i.e. the nodes that 
   * are directly reachable from the source node
   * @param source the source node
   * @return the list of child nodes of source
   */
  Iterator<T> getSucessors(T source);
  
  /**
   * Which are the nodes of the graph?
   * @return
   */
  Iterator<T> getNodes();
  
}
