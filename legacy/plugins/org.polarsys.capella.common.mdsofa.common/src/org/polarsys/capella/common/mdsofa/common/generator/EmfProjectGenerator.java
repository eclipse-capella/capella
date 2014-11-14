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
package org.polarsys.capella.common.mdsofa.common.generator;

import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.EMPTY_STRING;
import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.TAB_CHARACTER;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.polarsys.capella.common.mdsofa.common.internal.generator.emf.CustomizedGenerator;

/**
 * Helps to deal with EMF project generation.
 */
public class EmfProjectGenerator {
  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = Logger.getLogger(EmfProjectGenerator.class.getPackage().getName());
  /**
   * Code generator.
   */
  private Generator _generator;
  /**
   * Generator adapter factory.
   */
  private GeneratorAdapterFactory _generatorAdapterFactory;
  /**
   * Project type.
   */
  private EmfProjectType _projectType;

  /**
   * Constructor.
   * @param generatorAdapterFactory_p
   * @param projectType_p
   */
  public EmfProjectGenerator(GeneratorAdapterFactory generatorAdapterFactory_p, EmfProjectType projectType_p) {
    _generatorAdapterFactory = generatorAdapterFactory_p;
    _projectType = projectType_p;
  }

  /**
   * Initialize the genModel
   * @param genModel_p
   * @return false if an error occurs; false otherwise.
   */
  protected boolean initialize(GenModel genModel_p) {
    // Initialize given genmodel.
    genModel_p.reconcile();
    genModel_p.setCanGenerate(true);
    boolean result = validate(genModel_p);
    // Check pre-conditions
    if (!result) {
      return result;
    }
    // Initialize the code _generator.
    _generator = new CustomizedGenerator(_generatorAdapterFactory);
    _generator.setInput(genModel_p);
    JControlModel jControlModel = _generator.getJControlModel();
    // Handle code formatting
    if (genModel_p.isCodeFormatting()) {
      jControlModel.setLeadingTabReplacement(null);
      jControlModel.setConvertToStandardBraceStyle(false);
    } else {
      Map<?, ?> options = JavaCore.getOptions();
      String tabSize = (String) options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
      String braceStyle = (String) options.get(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION);
      String tabCharacter = (String) options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
      if (JavaCore.TAB.equals(tabCharacter)) {
        jControlModel.setLeadingTabReplacement(String.valueOf(TAB_CHARACTER));
      } else {
        StringBuffer spaces = new StringBuffer(EMPTY_STRING);
        for (int i = Integer.parseInt(tabSize); i > 0; --i) {
          spaces.append(EMPTY_STRING);
        }
        jControlModel.setLeadingTabReplacement(spaces.toString());
      }
      jControlModel.setConvertToStandardBraceStyle(DefaultCodeFormatterConstants.END_OF_LINE.equals(braceStyle));
    }
    return result;
  }

  /**
   * Generate required project type for given genModel.
   * @param monitor_p
   * @param genModel_p
   */
  public boolean generate(Monitor monitor_p, GenModel genModel_p) {
    // Is the generation possible ?
    String projectTypeValue = _projectType.getValue();
    boolean isCorrect = initialize(genModel_p) && _generator.canGenerate(genModel_p, projectTypeValue);
    if (isCorrect) {
      Diagnostic diagnostic = _generator.generate(genModel_p, projectTypeValue, null, monitor_p);
      isCorrect = handleDiagnostic(diagnostic, "Java Code generation failed for: " + genModel_p.toString()); //$NON-NLS-1$
    }
    return isCorrect;
  }

  /**
   * Validate given genModel.
   * @param genModel_p
   */
  protected boolean validate(GenModel genModel_p) {
    boolean result = true;
    // TODO Stephane: Look again in July the post with title "EcoreImporterWizard can use invalid genModel ?" on the EMF forum.
    // genModel_p.setValidateModel(true);
    // Diagnostic diagnostic = genModel_p.diagnose();
    // StringBuffer loggerMessage = new StringBuffer("GenModel is invalid to generate java code:"); //$NON-NLS-1$
    // loggerMessage.append(genModel_p.toString());
    // result = handleDiagnostic(diagnostic, loggerMessage.toString());
    return result;
  }

  /**
   * Handle a diagnostic.
   * @param diagnostic_p
   * @param message_p the displayed message if an error occurs.
   * @return false if an error occurs; true otherwise.
   */
  protected boolean handleDiagnostic(Diagnostic diagnostic_p, String message_p) {
    boolean result = true;
    if (Diagnostic.OK != diagnostic_p.getSeverity()) {
      StringBuffer loggerMessage = new StringBuffer("AbstractGenerator.handleDiagnostic(..) _ "); //$NON-NLS-1$
      loggerMessage.append(message_p);
      loggerMessage.append(' ');
      loggerMessage.append(diagnostic_p.getMessage());
      for (Diagnostic diagnostic : diagnostic_p.getChildren()) {
        handleDiagnostic(diagnostic, message_p);
      }
      Throwable exception = diagnostic_p.getException();
      if (null != exception) {
        loggerMessage.append(exception.getMessage());
        __logger.fatal(loggerMessage.toString(), exception);
      } else {
        __logger.fatal(loggerMessage.toString());
      }
      result = false;
    }
    return result;
  }
}
