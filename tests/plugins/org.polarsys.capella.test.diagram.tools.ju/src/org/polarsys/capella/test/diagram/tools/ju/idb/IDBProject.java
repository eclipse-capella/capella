/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class IDBProject extends EmptyProject {
  protected String componentContext;

  protected abstract void testOnDiagram(SessionContext context, String diagramKind, String targetId);

  protected void init(SessionContext context) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME,
        LA__LOGICAL_SYSTEM);
    componentContext = idb.createComponent();
  }
}
