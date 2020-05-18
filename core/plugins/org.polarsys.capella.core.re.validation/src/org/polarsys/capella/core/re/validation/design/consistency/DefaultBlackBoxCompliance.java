/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.validation.design.consistency;

import java.util.Collections;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A blackbox where nothing can be modified.
 */
public class DefaultBlackBoxCompliance extends BlackBoxComplianceWithExceptions {

  public DefaultBlackBoxCompliance() {
    super(Collections.<EStructuralFeature>emptyList());
  };

}