/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.generator;

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;

/**
 */
public enum EmfProjectType {
  /**
   * The project type constant representing a model project.<br>
   * This is the string "org.eclipse.emf.codegen.ecore.genmodel.generator.ModelProject".
   */
  MODEL_PROJECT_TYPE(GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE),

  /**
   * The project type constant representing an edit project.<br>
   * This is the string "org.eclipse.emf.codegen.ecore.genmodel.generator.EditProject".
   */
  EDIT_PROJECT_TYPE(GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE),

  /**
   * The project type constant representing an editor project.<br>
   * This is the string "org.eclipse.emf.codegen.ecore.genmodel.generator.EditorProject".
   */
  EDITOR_PROJECT_TYPE(GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE),

  /**
   * The project type constant representing a tests project.<br>
   * This is the string "org.eclipse.emf.codegen.ecore.genmodel.generator.TestsProject".
   */
  TESTS_PROJECT_TYPE(GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE);
  
  /**
   * Value defined by the enumeration.
   */
  private String _value;

  /**
   * Constructor.
   * @param description_p
   * @param id_p
   */
  private EmfProjectType(String value_p) {
    _value = value_p;
  }

  /**
   * Return the value for the current enumeration instance.
   * @return
   */
  public String getValue() {
    return _value;
  }
}
