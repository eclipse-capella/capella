/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;

public final class CapellaSearchMatchEntry extends AbstractCapellaSearchEntry {

	public CapellaSearchMatchEntry(AbstractCapellaSearchEntry parent, Object source, boolean matched, IProject project) {
		super(parent, source, matched, project);
	}
	
	Set<CapellaSearchMatchOccurence> getMatchOccurrences() {
	  Set<CapellaSearchMatchOccurence> occurences = new HashSet<CapellaSearchMatchOccurence>();
	  for(Object childMatch : getChildren()) {
      if(childMatch instanceof CapellaSearchMatchOccurence)
        occurences.add((CapellaSearchMatchOccurence)childMatch);
    }
	  return occurences;
	}
}
