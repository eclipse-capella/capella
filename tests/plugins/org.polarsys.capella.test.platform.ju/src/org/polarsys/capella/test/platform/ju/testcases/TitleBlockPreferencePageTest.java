package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class TitleBlockPreferencePageTest extends BasicTestCase {
  private ScopedPreferenceStore preferenceStore;

  public TitleBlockPreferencePageTest() {
    preferenceStore = (ScopedPreferenceStore) MdeCommonUiActivator.getDefault().getPreferenceStore();
  }

  @Override
  public void test() throws Exception {
    // TODO Auto-generated method stub
    System.out.println("lalq");

    updateTitleBlockPreferencePageColumnsNumber(4);
    updateTitleBlockPreferencePageLinesNumber(3);
    updateTitleBlockPreferencePageDefault();

  }

  private void updateTitleBlockPreferencePageColumnsNumber(int value) {
    preferenceStore.setValue("columnsNumberTitleBlock", value);
    int newValue = preferenceStore.getInt("columnsNumberTitleBlock");
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageLinesNumber(int value) {
    preferenceStore.setValue("columnsNumberTitleBlock", value);
    int newValue = preferenceStore.getInt("linesNumberTitleBlock");
    assertTrue(value == newValue);
  }

  private void updateTitleBlockPreferencePageDefault() {
    boolean value = preferenceStore.getBoolean("defaultTitleBlock");
    preferenceStore.setValue("defaultTitleBlock", !value);
    boolean newValue = preferenceStore.getBoolean("defaultTitleBlock");
    assertTrue(value != newValue);
  }

}
