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
package org.polarsys.capella.core.sequencediag.datas;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.commands.ToggleState;

public class LabelToggleState extends ToggleState implements IExecutableExtension {
	
	public LabelToggleState() {
		readDefault(""); //$NON-NLS-1$
	}
	
	public final void setInitializationData(
			final IConfigurationElement configurationElement,
			final String propertyName, final Object data) {
		readDefault((String) data);
	}
	
	private final void readDefault(final String defaultString) {
		// defaultString contains the value of the label
		SequenceFilterLabels data = SequenceDiagramDataHelper.getCurrentData();
		if (data.name().toUpperCase().equals(defaultString.toUpperCase()))
			setValue(true);
	}
}
