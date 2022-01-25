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
package org.polarsys.capella.common.platform.eclipse.tools.report.ui.pref;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.ReportManagerActivator;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.ui.pref.CreateBaseComponentTable;
import org.polarsys.capella.common.tools.report.ui.pref.IReportManagerPrefPage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

public class ReportManagerPrefPage extends PreferencePage implements IWorkbenchPreferencePage, IReportManagerPrefPage {
  private Combo componentCombo;

  public static Properties preferenceStore = new Properties();
  public static Properties tempStore = new Properties();
  public static Map<String, String> comboItems = new HashMap<String, String>();

  private static final String[] levelsName = new String[] { ReportManagerConstants.LOG_LEVEL_DEBUG,
      ReportManagerConstants.LOG_LEVEL_INFO, ReportManagerConstants.LOG_LEVEL_WARN,
      ReportManagerConstants.LOG_LEVEL_ERROR, ReportManagerConstants.LOG_LEVEL_FATAL };

  private ReportManagerRegistry registry;
  private CreateBaseComponentTable componentTable;

  @Override
  protected void performDefaults() {
    super.performDefaults();
    componentTable.defaultValues();

  }

  public ReportManagerPrefPage() {
    registry = ReportManagerRegistry.getInstance();
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public Control createContents(Composite parent) {
    // Creates the root composite.
    Composite root = new Composite(parent, SWT.NONE);
    root.setLayout(new GridLayout(1, false));

    // The component selection combo & label.
    Label comboLabel = new Label(root, SWT.NONE);
    comboLabel.setText("Select Category :"); //$NON-NLS-1$
    componentCombo = createComponentCombo(root);
    // Creates the logger viewer.
    componentTable = new CreateBaseComponentTable(root, SWT.NONE, registry, this, levelsName);
    componentCombo.notifyListeners(SWT.Selection, null);

    return root;
  }

  /**
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(IWorkbench)
   */
  public void init(IWorkbench workbench) {
    // do nothing.
  }

  public Combo createComponentCombo(Composite parent) {

    // force loading of default loggers, even if they haven't been used yet
    Field[] fields = IReportManagerDefaultComponents.class.getDeclaredFields();
    for (Field f : fields) {
      try {
        String componentName = (String) f.get(null);
        registry.subscribe(componentName);
      } catch (Exception exception) {
        Platform.getLog(ReportManagerActivator.class).log(new Status(IStatus.ERROR, FrameworkUtil.getBundle(ReportManagerActivator.class).getSymbolicName(), exception.getMessage(), exception));
      }
    }

    Combo combo = new Combo(parent, SWT.READ_ONLY);
    // Load combo box items.
    Object[] componentList = registry.getComponentsList();
    String[] items = new String[componentList.length];
    for (int i = 0; i < componentList.length; i++) {
      items[i] = componentList[i].toString();
    }
    // Sort items, Default is the first.
    Arrays.sort(items, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        if (IReportManagerDefaultComponents.DEFAULT.equals(o1)) {
          return -1;
        }
        if (IReportManagerDefaultComponents.DEFAULT.equals(o2)) {
          return 1;
        }
        return o1.compareTo(o2);
      }
    });

    // Check combo box content.
    if (0 == items.length) {
      combo.setEnabled(false);
      setErrorMessage("No Application Component available."); //$NON-NLS-1$
    } else {
      if (!combo.isEnabled()) {
        combo.setEnabled(true);
      }
      combo.setItems(items);
      combo.select(0);
    }

    // Add the selection listener.
    SelectionListener componentHandler = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        if (event.widget instanceof Combo) {
          Combo comboWidget = (Combo) event.widget;
          componentTable.selectPage(comboWidget.getText());
        }
      }
    };
    combo.addSelectionListener(componentHandler);
    return combo;
  }

  /**
   * 
   * @see org.eclipse.jface.preference.PreferencePage#performOk()
   */
  @Override
  public boolean performOk() {
    componentTable.updateConfigurationHashMap(registry.getConfigurations());
    registry.saveConfiguration();
    return super.performOk();
  }

  public Properties get_preferenceStore() {
    return preferenceStore;
  }
}
