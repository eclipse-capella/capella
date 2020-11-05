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

/**
 * Represents an object that must not pass the validation and define how many time this object must fail for the test
 * case it is defined in.
 * 
 * @author Erwan Brottier
 */
public class OracleDefinition implements IOracleDefinition {

  protected String objectID;
  protected int nbExpectedErrors;
  protected int nbFoundErrors = 0;

  public OracleDefinition(String objectID, int nbExpectedErrors) {
    this.objectID = objectID;
    this.nbExpectedErrors = nbExpectedErrors;
  }

  @Override
  public String getObjectID() {
    return objectID;
  }

  @Override
  public int getNbExpectedErrors() {
    return nbExpectedErrors;
  }

  @Override
  public void countOneError() {
    nbFoundErrors++;
  }

  @Override
  public int getNbFoundErrors() {
    return nbFoundErrors;
  }
}
