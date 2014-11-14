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
package org.polarsys.capella.common.re.launcher;

import org.polarsys.capella.common.re.constants.IReConstants;

/**
 */
public class CreateReplicaLauncher extends UpdateReplicaLauncher {

  @Override
  protected String getMapping() {
    return "org.polarsys.capella.common.re.createReplica";
  }

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.createReplica";
  }

  @Override
  protected String getKind() {
    return IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE;
  }

}
