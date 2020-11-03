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
package org.polarsys.capella.test.framework.api;

import java.io.File;
import java.util.List;

import junit.framework.Test;

public interface BasicTestArtefact extends Test {

  /**
   * Must be overriden to define capella projects that must be loaded in the
   * workspace for this test. Use method getLoadedMelodyModel to get one of
   * these models in the test implementation.<br>
   * Return null or void list if this test case does not require any models to perform the test.
   */
  public List<String> getRequiredTestModels();
  
  /** @return the execution duration of this test artefact (setUp and tearDown are not in the scope) */
  public long getExecutionDuration();
  
  public BasicTestSuite getParentTestSuite();
  
  public List<BasicTestSuite> getAllParentTestSuites();

	public void setParentTestSuite(BasicTestSuite basicTestSuite);

	public File getFolderInTestModelRepository(String relativeModelPath);

}
