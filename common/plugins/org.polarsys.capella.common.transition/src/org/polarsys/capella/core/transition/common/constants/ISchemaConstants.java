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

package org.polarsys.capella.core.transition.common.constants;

/**
 *
 */
public class ISchemaConstants {

  public static final String EXTENSION_ID = "org.polarsys.capella.core.transition.handlers"; //$NON-NLS-1$

  public static final String ID = "id"; //$NON-NLS-1$
  public static final String CLASS = "class"; //$NON-NLS-1$
  protected static final String ALL = "all"; //$NON-NLS-1$

  public static final String HANDLERS = "handlers"; //$NON-NLS-1$
  public static final String HANDLERS__PURPOSE = "purpose"; //$NON-NLS-1$
  public static final String HANDLERS__MAPPING = "mapping"; //$NON-NLS-1$
  public static final String HANDLERS__PURPOSE__ALL_PURPOSES = ALL;
  public static final String HANDLERS__MAPPING__ALL_MAPPINGS = ALL;

  public static final String DOMAIN = "domain"; //$NON-NLS-1$

  public static final String SCOPE_RETRIEVER = "scopeRetriever"; //$NON-NLS-1$
  public static final String SCOPE_RETRIEVER__ID = ID;
  public static final String SCOPE_RETRIEVER__CLASS = CLASS;

  public static final String SCOPE_FILTER = "scopeFilter"; //$NON-NLS-1$
  public static final String SCOPE_FILTER__ID = ID;
  public static final String SCOPE_FILTER__CLASS = CLASS;

  public static final String SOURCE_TRACEABILITY_CONFIGURATION = "sourceTraceabilityConfiguration"; //$NON-NLS-1$
  public static final String TARGET_TRACEABILITY_CONFIGURATION = "targetTraceabilityConfiguration"; //$NON-NLS-1$
  public static final String TRANSFORMATION_TRACEABILITY_CONFIGURATION = "transformationTraceabilityConfiguration"; //$NON-NLS-1$

  public static final String ACTIVITY_EXTENDER = "activityExtender"; //$NON-NLS-1$
  public static final String ACTIVITY_EXTENDER__ACTIVITY_IDENTIFIER = "activityIdentifier"; //$NON-NLS-1$
  public static final String ACTIVITY_EXTENDER__ACTIVITY_IDENTIFIER__ALL_ACTIVITIES = ALL;

}
