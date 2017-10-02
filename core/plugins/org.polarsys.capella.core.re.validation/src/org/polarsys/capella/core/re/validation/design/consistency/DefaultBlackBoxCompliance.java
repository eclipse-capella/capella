/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.validation.design.consistency;

import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler;

import com.google.common.base.Predicate;

public class DefaultBlackBoxCompliance extends AbstractComplianceConstraint {

  public DefaultBlackBoxCompliance() {
    super(new Predicate<CompliancyDefinition>() {
        @Override
        public boolean apply(CompliancyDefinition input) {
          return ReplicableElementHandler.COMPLIANCY_BLACK_BOX_NAME.equals(input.getName());
        }
      });
  };
}