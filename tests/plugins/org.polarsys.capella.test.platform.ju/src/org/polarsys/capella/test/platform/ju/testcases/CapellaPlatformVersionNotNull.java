/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.test.framework.api.BasicTestCaseWithoutTestModel;

public class CapellaPlatformVersionNotNull extends BasicTestCaseWithoutTestModel {

	@Override
	public void test() throws Exception {
		assertNotNull(FeatureHelper.getCapellaVersion(true));		
	}

}
