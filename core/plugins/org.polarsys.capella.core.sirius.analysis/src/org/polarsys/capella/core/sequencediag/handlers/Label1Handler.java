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

public class Label1Handler extends AbstractFilterHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			SequenceDiagramDataHelper.setCurrentData(SequenceFilterLabels.Label1);
			check (event.getCommand());
		} catch (Exception e) {
			// Catch exception silently,
			e.printStackTrace();
		}
		return super.execute(event);
	}



}
