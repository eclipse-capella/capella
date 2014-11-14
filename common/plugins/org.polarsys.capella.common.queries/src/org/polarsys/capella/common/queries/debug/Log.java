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
package org.polarsys.capella.common.queries.debug;

import java.util.Arrays;
import java.util.List;

/**
 */
public interface Log {

  public static final Integer QUERY_INTERPRETOR = new Integer(0);
  public static final Integer QUERY_REFACTOR_DEBUG = new Integer(1);
  public static final Integer REFERENCE_MANAGER_DEBUG = new Integer(2);
  public static final Integer RESOURCE_ACCESS_POLICY_LISTENER = new Integer(3);

  /** Only messages notified by groups in that list will be effectively prompted by the FormatedLogger. */
  public static final List<Integer> ACTIVE_DEBUG_GROUPS = Arrays.asList(new Integer[] { /* RESOURCE_ACCESS_POLICY_LISTENER */});

}
