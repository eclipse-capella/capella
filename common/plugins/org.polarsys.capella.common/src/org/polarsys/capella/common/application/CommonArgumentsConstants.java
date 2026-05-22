/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.application;

import java.text.MessageFormat;

public class CommonArgumentsConstants {
	public static String LOG_FILE_PATH = "-logfile";
	public static String LOG_FILE_PATH__DESCRIPTION = MessageFormat.format(Messages.LOG_FILE_PATH__DESCRIPTION,
			LOG_FILE_PATH);

	private CommonArgumentsConstants() {
	}
}
