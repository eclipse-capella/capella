/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.handlers.traceability;

import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This traceability handler configuration allows to use
 * {@link org.polarsys.capella.core.transition.common.rules.IRuleAttachment.retrieveDefaultContainer} implementations
 * already defined in Capella transitions while we are looking for ILocationHandler.getDefaultLocation
 */
public class LocationTraceabilityConfiguration extends ExtendedTraceabilityConfiguration {
  
  boolean kind = true;
  
  public boolean isSourceKind() {
    return kind;
  }

  public void setKind(boolean kind) {
    this.kind = kind;
  }

  @Override
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new LocationTraceabilityHandler());
  }

  @Override
  protected String getExtensionIdentifier(IContext context) {
    return "MATCH";
  }

}
