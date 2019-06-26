/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * Must be overriden to define melody projects that must be loaded in the
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
