/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.odesign.error.detection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.diagram.DDiagram;

public class SiriusRuntimeLogger implements RuntimeLogger {

  public static boolean enabled = false;

  public static final String ERROR_SEPARATOR = "==================ERROR==================";
  public static final String INFO_SEPARATOR = "==================INFO==================";
  public static final String WARNING_SEPARATOR = "==================WARNING==================";

  public static final String LOG_SEPARATOR = "----------------------------------------";
  public static final String MSG_SEPARATOR = "------------------MESSAGE-----------------";
  public static final String DIAGRAM_SEPARATOR = "------------------DIAGRAM-----------------";
  public static final String DUMP_SEPARATOR = "------------------DUMP------------------";

  protected enum Criticality {
    ERROR, WARNING, INFO
  }

  protected static Map<Criticality, StringBuilder> loggedInformation = new HashMap<>();

  protected static DDiagram diagram;

  public static void setAnalyzedDiagram(DDiagram diagram) {
    SiriusRuntimeLogger.diagram = diagram;
  }

  @Override
  public void error(EObject odesignObject, EStructuralFeature feature, String message) {
    if (enabled) {
      StringBuilder log = loggedInformation.putIfAbsent(Criticality.ERROR, new StringBuilder());
      String dump = generateDump();
      updateLogMessage(log, message, dump);
    }

  }

  @Override
  public void error(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
    error(odesignObject, feature, exception.getMessage());
  }

  @Override
  public void warning(EObject odesignObject, EStructuralFeature feature, String message) {
    if (enabled) {
      StringBuilder log = loggedInformation.putIfAbsent(Criticality.WARNING, new StringBuilder());
      String dump = generateDump();
      updateLogMessage(log, message, dump);
    }
  }

  @Override
  public void warning(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
    warning(odesignObject, feature, exception.getMessage());
  }

  @Override
  public void info(EObject odesignObject, EStructuralFeature feature, String message) {
    if (enabled) {
      StringBuilder log = loggedInformation.putIfAbsent(Criticality.INFO, new StringBuilder());
      String dump = generateDump();
      updateLogMessage(log, message, dump);
    }
  }

  @Override
  public void info(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
    info(odesignObject, feature, exception.getMessage());
  }

  @Override
  public void clearAll() {

  }

  @Override
  public void clear(EObject eObject) {

  }

  protected void updateLogMessage(StringBuilder existingLog, String message, String dump) {
    existingLog.append(LOG_SEPARATOR + "\n");
    existingLog.append(DIAGRAM_SEPARATOR + "\n");
    existingLog.append("[" + diagram.getName() + "] " + diagram + "\n");
    existingLog.append(MSG_SEPARATOR + "\n");
    existingLog.append(message + "\n");
    existingLog.append(DUMP_SEPARATOR + "\n");
    existingLog.append(dump + "\n");
  }

  protected String generateDump() {
    StringWriter stringWritter = new StringWriter();
    PrintWriter printWritter = new PrintWriter(stringWritter);
    new Exception().printStackTrace(printWritter);

    return stringWritter.toString();
  }

  public static void activate() {
    enabled = true;
  }

  public static void deactivate() {
    enabled = false;
  }

  public static void persistLogs(File outputFile) {

    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, true), "utf-8"))) {

      StringBuilder errorLogs = loggedInformation.getOrDefault(Criticality.ERROR, new StringBuilder());
      writer.write(ERROR_SEPARATOR + "\n" + errorLogs.toString());

      StringBuilder warningLogs = loggedInformation.getOrDefault(Criticality.WARNING, new StringBuilder());
      writer.write(WARNING_SEPARATOR + "\n" + warningLogs.toString());

      StringBuilder infoLogs = loggedInformation.getOrDefault(Criticality.INFO, new StringBuilder());
      writer.write(INFO_SEPARATOR + "\n" + infoLogs.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
