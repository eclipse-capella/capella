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
package org.polarsys.capella.test.diagram.tools.ju.model;

public abstract class SequenceDiagramProject extends EmptyProject {
  public static final String OA_ES = "OA_ES";
  public static final String OA_AS = "OA_AS";
  public static final String SA_ES = "SA_ES";
  public static final String SA_IS = "SA_IS";
  public static final String SA_FS = "SA_FS";
  public static final String LA_ES = "LA_ES";
  public static final String LA_IS = "LA_IS";
  public static final String LA_FS = "LA_FS";
  public static final String PA_ES = "PA_ES";
  public static final String PA_IS = "PA_IS";
  public static final String PA_FS = "PA_FS";
  
  @Override
  public String getRequiredTestModel() {
    return SequenceDiagramProject.class.getSimpleName();
  }
}
