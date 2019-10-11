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
package org.polarsys.capella.core.transition.system.retrievers;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.transition.common.handlers.scope.EReferenceScopeRetriever;

public class DeployedElementRetriever extends EReferenceScopeRetriever {
  public DeployedElementRetriever() {
    super(CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
  }
}
