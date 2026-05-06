package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager.messages"; //$NON-NLS-1$
  public static String LibraryColumnName;
  public static String AccessPolicyColumnName;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
