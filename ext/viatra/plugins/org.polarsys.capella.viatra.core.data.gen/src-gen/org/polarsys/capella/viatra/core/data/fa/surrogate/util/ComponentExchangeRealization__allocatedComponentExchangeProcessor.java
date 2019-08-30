/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.fa.surrogate.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatedComponentExchangeMatch;

/**
 * A match processor tailored for the org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatedComponentExchange pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ComponentExchangeRealization__allocatedComponentExchangeProcessor implements IMatchProcessor<ComponentExchangeRealization__allocatedComponentExchangeMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSelf the value of pattern parameter self in the currently processed match
   * @param pTarget the value of pattern parameter target in the currently processed match
   * 
   */
  public abstract void process(final ComponentExchangeRealization pSelf, final ComponentExchange pTarget);
  
  @Override
  public void process(final ComponentExchangeRealization__allocatedComponentExchangeMatch match) {
    process(match.getSelf(), match.getTarget());
  }
}
