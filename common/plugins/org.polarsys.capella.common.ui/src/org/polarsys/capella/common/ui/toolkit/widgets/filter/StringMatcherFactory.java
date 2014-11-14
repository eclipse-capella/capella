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
package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import org.eclipse.ui.internal.misc.StringMatcher;

/**
 * A factory that creates StringMatcher instances for PatternFilters
 * 
 */
public class StringMatcherFactory {
  
  public StringMatcher createStringMatcher(String pattern){
    return new StringMatcher(pattern, true, false);
  }
  
}
