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
package org.polarsys.capella.test.re.updateconnections.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTest.Library;
import org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTest.Multipart;
import org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTest.Simple;

import junit.framework.Test;

public class UpdateConnectionsTestSuite extends BasicTestSuite {

    /**
     * Returns the suite. This is required to unary launch this test.
     */
    public static Test suite() {
      return new UpdateConnectionsTestSuite();
    }

    @Override
    protected List<BasicTestArtefact> getTests() {
      List<BasicTestArtefact> list = new ArrayList<BasicTestArtefact>();
      list.add(new Simple());
      list.add(new Library());
      list.add(new Multipart());
      return list;
    }

  }