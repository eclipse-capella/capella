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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

  public static final String COMMAND__ID = "id";
  public static final String COMMAND__ID_DEFAULT_VALUE = COMMAND__ID;

  public static final String COMMAND__NAME = "name";

  public static final String COMMAND__DESCRIPTION = "description";

  public static final String COMMAND__CATEGORY = "category";
  public static final String COMMAND__CATEGORY_DEFAULT_VALUE = COMMAND__CATEGORY;

  String name();

  String description();

  String category() default COMMAND__CATEGORY_DEFAULT_VALUE;

  String id() default COMMAND__ID_DEFAULT_VALUE;
}
