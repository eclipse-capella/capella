package org.polarsys.capella.core.preferences.project.configuration.utils;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class Utils {
    
    Utils() {}
    
    public static final String ICONS_PATH = "icons/"; //$NON-NLS-1$
    
    /**
     * <p>
     * Returns an {@link ImageDescriptor}whose path, relative to the plugin directory's <tt>icons/</tt> directory, is <code>imageFile</code>. If the image
     * descriptor cannot be created, either because the file does not exist or because of an internal error, then the result is the Eclipse default
     * "missing image" descriptor.
     * </p>
     * <p>
     * <b>Note </b> that the file specified must not have any leading "." or path separators "/" or "\". It is strictly relative to the <tt>icons/</tt> directory.
     * @param imageFile the name of the image file to retrieve
     * @return the corresponding image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String imageFile) {
        Bundle bundle = FrameworkUtil.getBundle(Utils.class);
      URL fullPath = FileLocator.find(bundle, new Path(ICONS_PATH + imageFile), null);
      if (fullPath != null) {
        return ImageDescriptor.createFromURL(fullPath);
      }

      return ImageDescriptor.getMissingImageDescriptor();
    }
}
