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
package org.polarsys.capella.common.ui.toolkit.services.validators;

/**
 * The regular expression validators factory.
 */
public abstract class RegExpValidatorFactory {
  // Id of the validator created by this validators factory.
  private final String _id;

  /**
   * Creates a new regular expression validator with the given identifier.
   * @param validatorId_p The identifier of the validator factory.
   */
  protected RegExpValidatorFactory(String validatorId_p) {
    _id = validatorId_p;
  }

  /**
   * Creates a new standard regular expression validator.
   * @param errorMessage_p The error message.
   * @return The regular expression validator.
   */
  public abstract RegExpValidator create(String errorMessage_p, int style_p);

  /**
   * Gets the identifier of this validator factory.
   * @return The identifier of this validator factory.
   */
  public String getId() {
    return _id;
  }
  
  /**
   * The validator which allows only digits.
   */
  public static final RegExpValidatorFactory ONLY_DIGITS = new RegExpValidatorFactory("onlyDigits") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "\\d*|\\*", style_p); //$NON-NLS-1$
    }
  };

  /**
   * The validator which allows only lower case characters.
   */
  public static final RegExpValidatorFactory ONLY_LOWER_CASE = new RegExpValidatorFactory("onlyLowerCase") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "\\p{Lower}", style_p); //$NON-NLS-1$
    }    
  };
  
  
  /**
   * The validator which allows only upper case characters.
   */
  public static final RegExpValidatorFactory ONLY_UPPER_CASE = new RegExpValidatorFactory("onlyUpperCase") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "\\{Upper}", style_p); //$NON-NLS-1$
    }    
  };
  
  /**
   * The validator which doesn't allow numeric start.
   */
  public static final RegExpValidatorFactory NOT_NUMERIC_START = new RegExpValidatorFactory("notNumericStart") { //$NON-NLS-1$
    /** 
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "\\D+(.| )+", style_p); //$NON-NLS-1$
    }
  };
  
  /**
   * The validator which doesn't allow punctuation characters (One of ! " # $ % & ' ( ) * + , - . / : ; < => ? @ [ \ ] ^ _ ` { | } ~).
   */
  public static final RegExpValidatorFactory NO_PUNCTUATION = new RegExpValidatorFactory("noPunctuation") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "[^\\p{Punct}]*", style_p); //$NON-NLS-1$
    }
  };
  
  /***
   * The validator which doesn't allow space characters (One of [ ] \t \n \x0B \f \r).
   */
  public static final RegExpValidatorFactory NO_SPACE = new RegExpValidatorFactory("noSpace") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "[^\\s]*", style_p); //$NON-NLS-1$
    }
  };
  
  /**
   * The IP digit validator is used to validate each digit of an IP Address. each digit must respect 
   */
  public static final RegExpValidatorFactory IP_DIGIT = new RegExpValidatorFactory("ipDigit") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, "[1]{0,1}\\d{0,2}|[2][0-5]{0,2}", style_p); //$NON-NLS-1$
    }
  };
  
  /**
   * The not empty validator doesn't allow empty strings. 
   */
  public static final RegExpValidatorFactory NOT_EMPTY = new RegExpValidatorFactory("notEmpty") { //$NON-NLS-1$
    /**
     * @see org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory#create(java.lang.String)
     */
    @Override
    public RegExpValidator create(String errorMessage_p, int style_p) {
      return new RegExpValidator(errorMessage_p, ".+", RegExpValidator.NONE); //$NON-NLS-1$
    }    
  };
}
