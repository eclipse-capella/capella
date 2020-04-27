/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaPlatformVersionNotNull extends BasicTestCase {

	@Override
	public void test() throws Exception {
		assertNotNull(FeatureHelper.getCapellaVersion(true));		
	}

}
