/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.junit.Assert;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.platform.sirius.ui.app.CapellaApplication;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaVersionConsistencyTest extends BasicTestCase {

  @Override
  public void test() throws Exception {

    // Get capella application feature version
    String capellaFeatureShortVersion = FrameworkUtil.getBundle(CapellaApplication.class).getVersion().toString()
        .substring(0, 5);

    // Check product version only if a product is available at the end of the Tycho build
    if (null != Platform.getProduct()) {
      String productBundleShortVersion = Platform.getProduct().getDefiningBundle().getHeaders().get("Bundle-version")
          .substring(0, 5);
      Assert.assertEquals(NLS.bind(Messages.wrongProductVersion, productBundleShortVersion, capellaFeatureShortVersion),
          productBundleShortVersion, capellaFeatureShortVersion);
    }
  }
}
