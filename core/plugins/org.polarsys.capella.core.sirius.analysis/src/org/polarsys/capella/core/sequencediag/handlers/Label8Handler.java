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
package org.polarsys.capella.core.sequencediag.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.polarsys.capella.core.sequencediag.datas.SequenceDiagramDataHelper;
import org.polarsys.capella.core.sequencediag.datas.SequenceFilterLabels;

public class Label8Handler extends AbstractFilterHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SequenceDiagramDataHelper.setCurrentData(SequenceFilterLabels.Label8);
		return super.execute(event);

	}

}
