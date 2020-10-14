/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;

/**
 * A sample connection matcher that uses a connection's source and target port as the match ID. This is the best
 * matcher if each pair of ports is used by at most one connection.
 */
public class SingleUsePortsMatcher extends ConnectionMatcher {

  public String toString() {
    return Messages.ConnectionMatcher_1;
  }

  @Override
  public Object getMatchID(Connection connection, ITreeDataScope<EObject> scope, UpdateConnectionsMatchPolicy context) {
    return new RelationshipMatchID(context.getMatchID(connection.getSource(), scope),
        context.getMatchID(connection.getTarget(), scope), getClass());
  }
}