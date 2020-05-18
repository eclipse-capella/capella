/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.helper.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * A container object for the common results of a selection of Business Queries.
 * 
 * @author Sandu Postaru
 * 
 */
public class CommonBQResult {

  private final List<EObject> commonCurrentElements;
  private final List<EObject> commonAvailableElements;

  public CommonBQResult(List<EObject> commonAvailableElements, List<EObject> commonCurrentElements) {
    this.commonAvailableElements = commonAvailableElements;
    this.commonCurrentElements = commonCurrentElements;
  }

  public CommonBQResult(Set<EObject> commonAvailableElements, Set<EObject> commonCurrentElements) {
    this(new ArrayList<>(commonAvailableElements), new ArrayList<>(commonCurrentElements));
  }

  public List<EObject> getCommonCurrentElements() {
    return commonCurrentElements;
  }

  public List<EObject> getCommonAvailableElements() {
    return commonAvailableElements;
  }

  public static CommonBQResult emptyResult() {
    return new CommonBQResult(Collections.emptyList(), Collections.emptyList());
  }

  public boolean isEmpty() {
    return commonAvailableElements.isEmpty() && commonCurrentElements.isEmpty();
  }
}
