/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;

public abstract class FragmentModelTestProject extends NonDirtyTestCase {
  protected String _SF11Id = "9c605285-99ce-4dff-82c9-567d262021fd";
  protected String _SF12Id = "a2c62066-81e0-4b36-af26-525d59713bd0";
  protected String _SF121Id = "109db67c-e5ca-4567-9f92-23ced72013f9";
  protected String _SF122Id = "d622f1bf-433f-4ea8-b1f6-6899de1a4f45";
  protected IFile airdFile;
  protected IFile m2File;
  protected IFile SF11aird;
  protected IFile SF11m2;
  protected IFile SF12aird;
  protected IFile SF12m2;
  protected String SA_SDFB_SF121_DiagName = "[SDFB] SystemFunction 1.2.1";
  protected String _currentProjectName = "FragmentTestModel";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(_currentProjectName);
  }
}
