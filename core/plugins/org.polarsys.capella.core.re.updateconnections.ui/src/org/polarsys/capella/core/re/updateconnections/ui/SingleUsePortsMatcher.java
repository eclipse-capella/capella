/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;

/**
 * A sample connection matcher that uses a connection's source and target port as the match ID. This is the best
 * matcher if each pair of ports is used by at most one connection.
 */
public class SingleUsePortsMatcher extends ConnectionMatcher {

  public String toString() {
    return Messages.ConnectionMatcher_1;
  }

  @Override
  public Object getMatchID(Connection connection, IModelScope scope, UpdateConnectionsMatchPolicy context) {
    return new RelationshipMatchID(context.getMatchID(connection.getSource(), scope),
        context.getMatchID(connection.getTarget(), scope), getClass());
  }
}