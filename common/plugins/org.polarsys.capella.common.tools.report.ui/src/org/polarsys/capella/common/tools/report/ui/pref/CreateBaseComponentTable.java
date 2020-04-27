/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.ui.pref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.LogLevel;
import org.polarsys.capella.common.tools.report.config.persistence.OutputConfiguration;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;

/**
 * CreateBaseComponentTable
 */
public class CreateBaseComponentTable {
  private ReportManagerRegistry _registry;
  private List<String> _appendersName = new ArrayList<String>();
  private String[] _lineLevelNames;
  private Map<String, Control> _stackMapping = new HashMap<String, Control>();
  private Map<String, Button> _buttons = new HashMap<String, Button>();

  private StackLayout _stackLayout = new StackLayout();

  /**
   * @param composite
   * @param style
   * @param registry
   * @param page
   * @param levelsName
   */
  public CreateBaseComponentTable(Composite composite, int style, ReportManagerRegistry registry,
      IReportManagerPrefPage page, String[] levelsName) {
    _registry = registry;
    _lineLevelNames = levelsName;
    // Initialize appenders name list
    _appendersName.addAll(registry.getAppenderKinds());
    // new create table
    createLoggingTable(composite, registry, levelsName);
  }

  private void createLoggingTable(Composite composite, ReportManagerRegistry registry, String[] levelsName) {
    // new container, for the style
    Group group = new Group(composite, SWT.SHADOW_OUT);
    group.setText("Report options"); //$NON-NLS-1$

    // in this group, add a StackLayout. Each page will contain the
    // configuration for a component
    _stackLayout = new StackLayout();
    group.setLayout(_stackLayout);

    // for each component, create a Group

    for (Object obj : registry.getComponentsList()) {
      String componentName = (String) obj;

      // create a group
      Composite componentGroup = new Composite(group, SWT.NONE);
      // inside, we create a GridLayout, the number of column depends of
      // the number of appenders
      GridLayout grid = new GridLayout();
      grid.numColumns = _appendersName.size() + 1;
      grid.horizontalSpacing = 5;
      grid.makeColumnsEqualWidth = true;
      componentGroup.setLayout(grid);

      _stackLayout.topControl = componentGroup;

      // creation of the Title line
      Label levelLabel = new Label(componentGroup, 0);
      levelLabel.setText(" "); //$NON-NLS-1$
      _stackMapping.put(componentName, componentGroup);
      for (String appender : _appendersName) {
        Label titleLabel = new Label(componentGroup, 0);
        titleLabel.setText(appender);
      }

      // Next : one line by LogLevel.
      for (String levelName : levelsName) {
        // First entry of the line : name of level
        Label levelLabel2 = new Label(componentGroup, 0);
        levelLabel2.setText(levelName);

        for (String appender : _appendersName) {
          // then one tickbox by Appender
          Button tickBox = new Button(componentGroup, SWT.CHECK | SWT.CENTER);
          String key = computeButtonKey(componentName, levelName, appender);
          _buttons.put(key, tickBox);
        }
      }
    }
    // initialize values
    initializeValuesForReport();

  }

  private String computeButtonKey(String componentName, String levelName, String appenderName) {
    StringBuilder builder = new StringBuilder();
    builder.append(componentName);
    builder.append(levelName);
    builder.append(appenderName);
    return builder.toString();
  }

  /**
   * Initialize values from
   */
  private void initializeValuesForReport() {
    for (Object componentName_obj : _registry.getComponentsList()) {
      String componentName = (String) componentName_obj;
      ConfigurationInstance instance = _registry.getConfigurations().get(componentName);

      List<OutputConfiguration> confList = instance.getOutputConfiguration();
      Iterator<OutputConfiguration> opConfIter = confList.listIterator();
      while (opConfIter.hasNext()) {
        OutputConfiguration opConfiguration = opConfIter.next();
        List<LogLevel> loglevel = opConfiguration.getLogLevel();
        Iterator<LogLevel> logLevelIter = loglevel.listIterator();
        while (logLevelIter.hasNext()) {
          LogLevel ll = logLevelIter.next();
          setButtonState(componentName, opConfiguration.getOutputName(), ll.getName(), ll.isValue());
        }
      }
    }
  }

  private void setButtonState(String componentName, String appenderName, String levelName, boolean value) {
    String key = computeButtonKey(componentName, levelName, appenderName);
    Button button = _buttons.get(key);
    if (button != null)
      button.setSelection(value);
  }

  /**
   * Retrieve state selection button for each level for current component (given in parameter) and store them
   * 
   * @param configurationMap
   */
  public void updateConfigurationHashMap(Map<String, ConfigurationInstance> configurationMap) {
    for (Object componentName_obj : _registry.getComponentsList()) {
      String componentName = (String) componentName_obj;
      updateConfigurationHashMap(componentName, configurationMap);
    }
  }

  /**
   * @param componentName
   * @param configurationMap
   */
  public void updateConfigurationHashMap(String componentName, Map<String, ConfigurationInstance> configurationMap) {
    ConfigurationInstance instance = configurationMap.get(componentName);
    List<OutputConfiguration> opConf = instance.getOutputConfiguration();
    Iterator<OutputConfiguration> opConfIter = opConf.listIterator();

    while (opConfIter.hasNext()) {
      OutputConfiguration opConfiguration = opConfIter.next();
      updateConfigurationHashMap(componentName, opConfiguration);
    }
  }

  /**
   * @param componentName
   * @param configuration
   *          The configuration to update.
   */
  public void updateConfigurationHashMap(String componentName, OutputConfiguration configuration) {
    List<LogLevel> logLevel = configuration.getLogLevel();
    Iterator<LogLevel> logLevelIter = logLevel.listIterator();
    try {
      while (logLevelIter.hasNext()) {
        LogLevel ll = logLevelIter.next();
        String key = computeButtonKey(componentName, ll.getName(), configuration.getOutputName());
        Button button = _buttons.get(key);
        boolean state = button.getSelection();
        ll.setValue(state);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param text
   *          The text.
   */
  public void selectPage(String text) {
    Control page = _stackMapping.get(text);
    _stackLayout.topControl = page;
    page.getParent().layout();

  }

  /**
   * Default values. FIXME this is a workaround.
   */
  public void defaultValues() {
    for (Object componentName_obj : _registry.getComponentsList()) {
      String componentName = (String) componentName_obj;
      for (String currentAppenderName : _appendersName) {
        for (String currentLevelName : _lineLevelNames) {
          String key = computeButtonKey(componentName, currentLevelName, currentAppenderName);
          Button button = _buttons.get(key);
          if (button != null) {
            button.setSelection(!(currentLevelName.equals(ReportManagerConstants.LOG_LEVEL_DEBUG)
                || (currentAppenderName.equals(ReportManagerConstants.LOG_OUTPUT_FILE))));
          }
        }
      }
    }
  }

}