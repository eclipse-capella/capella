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

package org.polarsys.capella.core.model.utils.saxparser;

/**
 */
public interface IConstantValidation {
  // html root node
  String ROOT_NODE = "<rootSAX>"; //$NON-NLS-1$
  // html root node end
  String ROOT_NODE_END = "</rootSAX>"; //$NON-NLS-1$
  // x-html href attribute name
  String XHTML_HREF_ATT = "href"; //$NON-NLS-1$
  String XHTML_BREAK_ELEMENT = "br"; //$NON-NLS-1$
  String XHTML_BREAK_ELEMENT_END = "<br />"; //$NON-NLS-1$
  // less than
  String LESS_THAN = "<"; //$NON-NLS-1$
  // greater than
  String GREATER_THAN = ">"; //$NON-NLS-1$
  // less than unicode
  String LESS_THAN_CODE = "&#60;"; //$NON-NLS-1$
  // less than unicode
  String LESS_THAN_NAME_CODE = "&lt;"; //$NON-NLS-1$
  // greater than unicode
  String GREATER_THAN_CODE = "&#62;"; //$NON-NLS-1$
  // greater than unicode
  String GREATER_THAN_NAME_CODE = "&gt;"; //$NON-NLS-1$
  // equal
  String EQUAL = "="; //$NON-NLS-1$
  // DOUBLE_QUOTES
  String DOUBLE_QUOTES = "\""; //$NON-NLS-1$
  // DOUBLE_QUOTES unicode
  String DOUBLE_QUOTES_CODE = "&#34;"; //$NON-NLS-1$
  // DOUBLE_QUOTES unicode
  String DOUBLE_QUOTES_NAME_CODE = "&quot;"; //$NON-NLS-1$
  // space
  String SPACE = " "; //$NON-NLS-1$
  // space unicode
  String SPACE_CODE = "&#32;"; //$NON-NLS-1$
  // space unicode
  String SPACE_NAME_CODE = "&nbsp;"; //$NON-NLS-1$
  // ampersand
  String AMP = "&"; //$NON-NLS-1$
  // apostrophe
  String APOSTROPHE = "'"; //$NON-NLS-1$
  // apostrophe unicode
  String APOSTROPHE_CODE = "&#39;"; //$NON-NLS-1$
  // apostrophe unicode
  String APOSTROPHE_NAME_CODE = "&apos;"; //$NON-NLS-1$
  // close tab prefix
  String CLOSE_TAB_PREFIX = "</"; //$NON-NLS-1$
  // x-html a tag
  String XHTML_A_TAG = "a"; //$NON-NLS-1$
  // x-html a tag
  String RESOURCE = "resource"; //$NON-NLS-1$
  // x-html a tag
  String PLATFORM = "platform"; //$NON-NLS-1$
  // x-html a tag
  String HASH = "#"; //$NON-NLS-1$
  // number code of nbsp("non-breaking space")
  String NON_BREAKING_SPACE_NUMBER_CODE = "&#160;"; //$NON-NLS-1$
  // name code of nbsp("non-breaking space")
  String NON_BREAKING_SPACE_NAME_CODE = "&nbsp;"; //$NON-NLS-1$
  // euro
  String EURO = "\u20a0";
  // replacing string for euro
  String EURO_NUMBER_CODE = "&#8364;"; //$NON-NLS-1$
  // euro
  String EURO_NAME_CODE = "&euro;"; //$NON-NLS-1$
  // trade
  String TRADE = "™";
  // replacing string for euro
  String TRADE_NUMBER_CODE = "&#8482;"; //$NON-NLS-1$
  // euro
  String TRADE_NAME_CODE = "&trade;"; //$NON-NLS-1$
  // replacing string for euro
  String AMP_NUMBER_CODE = "&#38;"; //$NON-NLS-1$
  // euro
  String AMP_NAME_CODE = "&amp;"; //$NON-NLS-1$

}
