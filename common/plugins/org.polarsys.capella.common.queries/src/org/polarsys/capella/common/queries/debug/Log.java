/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.queries.debug;

import java.util.Arrays;
import java.util.List;

/**
 */
public interface Log {

  public static final Integer QUERY_INTERPRETOR = Integer.valueOf(0);
  public static final Integer QUERY_REFACTOR_DEBUG = Integer.valueOf(1);
  public static final Integer REFERENCE_MANAGER_DEBUG = Integer.valueOf(2);
  public static final Integer RESOURCE_ACCESS_POLICY_LISTENER = Integer.valueOf(3);

  /** Only messages notified by groups in that list will be effectively prompted by the FormatedLogger. */
  public static final List<Integer> ACTIVE_DEBUG_GROUPS = Arrays.asList(new Integer[] { /* RESOURCE_ACCESS_POLICY_LISTENER */});

}
