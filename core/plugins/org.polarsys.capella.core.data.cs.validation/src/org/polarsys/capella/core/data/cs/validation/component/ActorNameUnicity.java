/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.validation.rule.AbstractNameUnicity;

/**
 * Functional Chains shall start from an End User and finish with an End User
 */
public class ActorNameUnicity extends AbstractNameUnicity {

  @Override
  protected boolean testMetaClass(final EObject theTarget) {
    return ((theTarget instanceof Component) && (((Component) theTarget).isActor()));
  }

}
