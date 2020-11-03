/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sequencediag.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.polarsys.capella.core.sequencediag.datas.SequenceDiagramDataHelper;
import org.polarsys.capella.core.sequencediag.datas.SequenceFilterLabels;

public class Label5Handler extends AbstractFilterHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SequenceDiagramDataHelper.setCurrentData(SequenceFilterLabels.Label5);
		return super.execute(event);

	}

}
