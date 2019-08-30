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
package org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedEPBSArchitecturesMatch;

/**
 * A match processor tailored for the org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedEPBSArchitectures pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SystemEngineering__containedEPBSArchitecturesProcessor implements IMatchProcessor<SystemEngineering__containedEPBSArchitecturesMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSelf the value of pattern parameter self in the currently processed match
   * @param pTarget the value of pattern parameter target in the currently processed match
   * 
   */
  public abstract void process(final SystemEngineering pSelf, final EPBSArchitecture pTarget);
  
  @Override
  public void process(final SystemEngineering__containedEPBSArchitecturesMatch match) {
    process(match.getSelf(), match.getTarget());
  }
}
