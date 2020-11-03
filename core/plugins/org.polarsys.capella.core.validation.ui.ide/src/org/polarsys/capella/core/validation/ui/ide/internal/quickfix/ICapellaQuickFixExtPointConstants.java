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
package org.polarsys.capella.core.validation.ui.ide.internal.quickfix;

/**
 * Useful constants for the capellaQuickFix extension point.
 *
 */
public interface ICapellaQuickFixExtPointConstants {

  /** the capella marker resolution extension point ID */
  public final static String EXT_POINT_ID = "capellaQuickFix"; //$NON-NLS-1$
  
  /** the resolver node */
  public final static String RESOLVER_NODE = "resolver"; //$NON-NLS-1$
  
  /** its class attribute */
  public final static String CLASS_ATT = "class"; //$NON-NLS-1$
  
  /** its label attribute */
  public final static String LABEL_ATT = "label"; //$NON-NLS-1$

  /** its description attribute */
  public final static String DESC_ATT = "desc"; //$NON-NLS-1$
  
  /** its icon attribute */
  public final static String ICON_ATT = "icon"; //$NON-NLS-1$
  
  /** the rules node */
  public final static String TARGET_RULES_NODE = "rules"; //$NON-NLS-1$
  
  /** its ruleID pattern attribute */
  public final static String RULE_ID_PATTERN_ATT = "ruleId"; //$NON-NLS-1$
    
}
