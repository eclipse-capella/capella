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
package org.polarsys.capella.core.model.utils.saxparser;

/**
 */
public interface IConstantValidation {
  // html root node
  public static final String ROOT_NODE = "<rootSAX>"; //$NON-NLS-1$
  // html root node end
  public static final String ROOT_NODE_END = "</rootSAX>"; //$NON-NLS-1$
  // x-html href attribute name
  public static final String XHTML_HREF_ATT = "href"; //$NON-NLS-1$
  public static final String XHTML_BREAK_ELEMENT = "br"; //$NON-NLS-1$
  public static final String XHTML_BREAK_ELEMENT_END = "<br />"; //$NON-NLS-1$
  // less than
  public static final String LESS_THAN = "<"; //$NON-NLS-1$
  // greater than
  public static final String GREATER_THAN = ">"; //$NON-NLS-1$
  // less than unicode
  public static final String LESS_THAN_CODE = "&#60;"; //$NON-NLS-1$
  // greater than unicode
  public static final String GREATER_THAN_CODE = "&#62;"; //$NON-NLS-1$
  // equal
  public static final String EQUAL = "="; //$NON-NLS-1$
  // DOUBLE_QUOTES
  public static final String DOUBLE_QUOTES = "\""; //$NON-NLS-1$
  // DOUBLE_QUOTES unicode
  public static final String DOUBLE_QUOTES_CODE = "&#34;"; //$NON-NLS-1$
  // space
  public static final String SPACE = " "; //$NON-NLS-1$
  // ampersand
  public static final String AMP = "&"; //$NON-NLS-1$
  // apostrophe
  public static final String APOSTROPHE = "'"; //$NON-NLS-1$
  // apostrophe unicode
  public static final String APOSTROPHE_CODE = "&#39;"; //$NON-NLS-1$
  // close tab prefix
  public static final String CLOSE_TAB_PREFIX = "</"; //$NON-NLS-1$
  // x-html a tag
  public static final String XHTML_A_TAG = "a"; //$NON-NLS-1$
  // x-html a tag
  public static final String RESOURCE = "resource"; //$NON-NLS-1$
  // x-html a tag
  public static final String PLATFORM = "platform"; //$NON-NLS-1$
  // x-html a tag
  public static final String HASH = "#"; //$NON-NLS-1$
  // number code of nbsp("non-breaking space")
  public static final String NON_BREAKING_SPACE_NUMBER_CODE = "&#160;"; //$NON-NLS-1$
  // name code of nbsp("non-breaking space")
  public static final String NON_BREAKING_SPACE_NAME_CODE = "&nbsp;"; //$NON-NLS-1$
  // replacing string for euro
  public static final String EURO_NUMBER_CODE = "&#8364;"; //$NON-NLS-1$
  // euro
  public static final String EURO_NAME_CODE = "&euro;"; //$NON-NLS-1$
  // replacing string for euro
  public static final String TRADE_NUMBER_CODE = "&#8482;"; //$NON-NLS-1$
  // euro
  public static final String TRADE_NAME_CODE = "&trade;"; //$NON-NLS-1$
  // replacing string for euro
  public static final String AMP_NUMBER_CODE = "&#38;"; //$NON-NLS-1$
  // euro
  public static final String AMP_NAME_CODE = "&amp;"; //$NON-NLS-1$

}
