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
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A sample connection matcher that returns the connections 'name' as the match id.
 */
public class ByNameMatcher extends ConnectionMatcher {

  // TODO this is a hack to give some context to the user.
  public String toString() {
    return Messages.ConnectionMatcher_0;
  }

  @Override
  public Object getMatchID(Connection connection, ITreeDataScope<EObject> scope, UpdateConnectionsMatchPolicy context) {

    Object result = null;

    EStructuralFeature name = connection.getConnection().eClass().getEStructuralFeature("name"); //$NON-NLS-1$
    if (name != null) {
      result = connection.getConnection().eGet(name);
    }
    if (result == null) {
      // FIXME Log This, or better, avoid use of this matcher if some connections in the scope have no name
      result = new Object();
    }
    return result;
  }
}