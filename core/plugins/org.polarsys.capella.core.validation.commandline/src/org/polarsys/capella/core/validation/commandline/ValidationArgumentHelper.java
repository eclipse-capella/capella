/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.commandline;

import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;

/**
 */
public class ValidationArgumentHelper extends CommandLineArgumentHelper {
  private String validationContext;
  private String validationRuleSet;

  /**
   * {@inheritDoc}
   */
  @Override
  public void parseArgs(String[] args) {
    super.parseArgs(args);

    // parse validation specific args
    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (ValidationCommandLineConstants.VALIDATION_CONTEXT.equalsIgnoreCase(arg)) {
        validationContext = args[++i];

      } else if (ValidationCommandLineConstants.VALIDATION_RULE_SET.equalsIgnoreCase(arg)) {
        validationRuleSet = args[++i];
      }
    }
  }

  /**
   * @return the validationContext
   */
  public String getValidationContext() {
    return validationContext;
  }

  /**
   * @return the validationRuleSet
   */
  public String getValidationRuleSet() {
    return validationRuleSet;
  }
}
