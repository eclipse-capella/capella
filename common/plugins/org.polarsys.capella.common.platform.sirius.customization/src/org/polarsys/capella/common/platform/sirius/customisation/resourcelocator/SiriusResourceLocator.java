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
package org.polarsys.capella.common.platform.sirius.customisation.resourcelocator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

/**
 * A specific resource locator for Sirius to override some preferences. Some
 * methods are copied from
 * {@link org.eclipse.emf.common.util.DelegatingResourceLocator}.
 * 
 * 
 */
public class SiriusResourceLocator implements ResourceLocator {

    private static final String DOT = ".";

    private static final URI DOT_URI = URI.createURI(DOT);

    private static final String PLUGIN_PROPERTIES_FILE = "plugin.properties";

    /**
     * The cached base URL.
     */
    protected URL baseURL;

    /**
     * The resource bundle containing untranslated strings.
     */
    protected ResourceBundle untranslatedResourceBundle;

    /**
     * The resource bundle containing translated strings.
     */
    protected ResourceBundle resourceBundle;

    /**
     * Whether to translate strings by default.
     */
    protected boolean shouldTranslate = true;

    /**
     * A cache of the translated strings.
     */
    protected Map<String, String> strings = new HashMap<String, String>();

    /**
     * A cache of the untranslated strings.
     */
    protected Map<String, String> untranslatedStrings = new HashMap<String, String>();

    /**
     * Default constructor.
     */
    public SiriusResourceLocator() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getBaseURL()
     */
    public URL getBaseURL() {
        if (baseURL == null) {
            try {
                Class<? extends ResourceLocator> theClass = getClass();
                URL pluginPropertiesURL = theClass.getResource(PLUGIN_PROPERTIES_FILE);
                if (pluginPropertiesURL == null) {
                    // If that fails, determine the URL for the class itself.
                    // The URL will be of one of the following forms,
                    // so there are a few good places to consider looking for
                    // the plugin.properties.
                    //
                    //
                    // * For a plugin.xml with runtime="common.jar":
                    // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/common.jar!/org/eclipse/common/CommonPlugin.class
                    // * For a plugin.xml with runtime="runtime/common.jar":
                    // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/runtime/common.jar!/org/eclipse/common/CommonPlugin.class
                    // * For a plugin.xml with runtime="." where the plugin is
                    // jarred:
                    // jar:file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common.jar!/org/eclipse/common/CommonPlugin.class
                    // * For a plugin.xml with runtime="." where the plugin is
                    // not
                    // jarred.
                    // file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/org/eclipse/emf/common/CommonPlugin.class
                    // * Running in PDE with bin on classpath:
                    // file:/D:/sandbox/unpackage1-3.1M7/eclipse/plugins/org.eclipse.emf.common/bin/org/eclipse/emf/common/CommonPlugin.class
                    String className = theClass.getName();
                    int index = className.lastIndexOf(DOT);
                    URL classURL = theClass.getResource((index == -1 ? className : className.substring(index + 1)) + ".class");
                    URI uri = URI.createURI(classURL.toString());

                    // Trim off the segments corresponding to the package
                    // nesting.
                    int count = 1;
                    // CHECKSTYLE:OFF
                    for (int i = 0; (i = className.indexOf('.', i)) != -1; ++i) {
                        ++count;
                    }
                    // CHECKSTYLE:ON
                    uri = uri.trimSegments(count);

                    // For an archive URI, check for the plugin.properties in
                    // the archive.
                    if (URI.isArchiveScheme(uri.scheme())) {
                        try {
                            // If we can open an input stream, then the
                            // plugin.properties is there, and we have a good
                            // base URL.
                            InputStream inputStream = new URL(uri.appendSegment(PLUGIN_PROPERTIES_FILE).toString()).openStream();
                            inputStream.close();
                            baseURL = new URL(uri.toString());
                        } catch (IOException exception) {
                            // If the plugin.properties isn't within the root of
                            // the archive,
                            // create a new URI for the folder location of the
                            // archive,
                            // so we can look in the folder that contains it.
                            uri = URI.createURI(uri.authority()).trimSegments(1);
                        }
                    }

                    // If we didn't find the plugin.properties in the usual
                    // place nor in the archive...
                    if (baseURL == null) {
                        // Trim off the "bin" or "runtime" segment.
                        String lastSegment = uri.lastSegment();
                        if ("bin".equals(lastSegment) || "runtime".equals(lastSegment)) {
                            uri = uri.trimSegments(1);
                        }
                        uri = uri.appendSegment(PLUGIN_PROPERTIES_FILE);
                        try {
                            // If we can open an input stream, then the
                            // plugin.properties is in the folder, and we have a
                            // good base URL.
                            InputStream inputStream = new URL(uri.toString()).openStream();
                            inputStream.close();
                            baseURL = new URL(DOT_URI.resolve(uri).toString());
                        } catch (IOException exception) {
                            // Continue with the established base URL.
                        }
                    }

                    // If we still don't have a good base URL, complain about
                    // it.
                    if (baseURL == null) {
                        String resourceName = index == -1 ? PLUGIN_PROPERTIES_FILE : className.substring(0, index + 1).replace('.', '/') + PLUGIN_PROPERTIES_FILE;
                        throw new MissingResourceException("Missing properties: " + resourceName, theClass.getName(), PLUGIN_PROPERTIES_FILE);
                    }
                } else {
                    baseURL = new URL(DOT_URI.resolve(URI.createURI(pluginPropertiesURL.toString())).toString());
                }
            } catch (IOException exception) {
                throw new WrappedException(exception);
            }
        }
        return baseURL;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getImage(java.lang.String)
     */
    public Object getImage(String key) {
        // We override no image
        throw new MissingResourceException(CommonPlugin.INSTANCE.getString("_UI_ImageResourceNotFound_exception", new Object[] { key }), getClass().getName(), key);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getString(java.lang.String)
     */
    public String getString(String key) {
        return getString(key, shouldTranslate());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getString(java.lang.String,
     *      boolean)
     */
    public String getString(String key, boolean translate) {
        Map<String, String> stringMap = translate ? strings : untranslatedStrings;
        String result = stringMap.get(key);
        if (result == null) {
            result = doGetString(key, translate);
            stringMap.put(key, result);
        }
        return result;
    }

    /**
     * Does the work of fetching the string associated with the key. It ensures
     * that the string exists.
     * 
     * @param key
     *            the key of the string to fetch.
     * @param translate
     *            whether the result is to be translated to the current locale.
     * @exception MissingResourceException
     *                if a string doesn't exist.
     * @return the string associated with the key.
     */
    protected String doGetString(String key, boolean translate) throws MissingResourceException {
        ResourceBundle bundle = translate ? resourceBundle : untranslatedResourceBundle;
        if (bundle == null) {
            String packageName = getClass().getName();
            int index = packageName.lastIndexOf(DOT);
            if (index != -1) {
                packageName = packageName.substring(0, index);
            }
            if (translate) {
                try {
                    resourceBundle = ResourceBundle.getBundle(packageName + ".plugin");
                    bundle = resourceBundle;
                } catch (MissingResourceException exception) {
                    // If the bundle can't be found the normal way, try to find
                    // it as the base URL.
                    // If that also doesn't work, rethrow the original
                    // exception.
                    //
                    try {
                        InputStream inputStream = new URL(getBaseURL().toString() + PLUGIN_PROPERTIES_FILE).openStream();
                        resourceBundle = new PropertyResourceBundle(inputStream);
                        untranslatedResourceBundle = resourceBundle;
                        bundle = untranslatedResourceBundle;
                        inputStream.close();
                    } catch (IOException ioException) {
                        // We'll rethrow the original exception, not this one.
                    }
                    if (bundle == null) {
                        throw exception;
                    }
                }
            } else {
                String resourceName = getBaseURL().toString() + PLUGIN_PROPERTIES_FILE;
                try {
                    InputStream inputStream = new URL(resourceName).openStream();
                    untranslatedResourceBundle = new PropertyResourceBundle(inputStream);
                    bundle = untranslatedResourceBundle;
                    inputStream.close();
                } catch (IOException ioException) {
                    throw new MissingResourceException("Missing properties: " + resourceName, getClass().getName(), PLUGIN_PROPERTIES_FILE);
                }
            }
        }
        return bundle.getString(key);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getString(java.lang.String,
     *      java.lang.Object[])
     */
    public String getString(String key, Object[] substitutions) {
        return getString(key, substitutions, shouldTranslate());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.ResourceLocator#getString(java.lang.String,
     *      java.lang.Object[], boolean)
     */
    public String getString(String key, Object[] substitutions, boolean translate) {
        return MessageFormat.format(getString(key, translate), substitutions);
    }

    /**
     * Indicates whether strings should be translated by default.
     * 
     * @return <code>true</code> if strings should be translated by default;
     *         <code>false</code> otherwise.
     */
    public boolean shouldTranslate() {
        return shouldTranslate;
    }

    /**
     * Sets whether strings should be translated by default.
     * 
     * @param shouldTranslate
     *            whether strings should be translated by default.
     */
    public void setShouldTranslate(boolean shouldTranslate) {
        this.shouldTranslate = shouldTranslate;
    }
}
