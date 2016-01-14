/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.headless;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.test.refinement.ju.TestRefinementPlugin;

/**
 * Utility class in order to avoid any UI interaction with user.
 */
@SuppressWarnings("synthetic-access")
final public class ResolvingExtensionProvider {

  /** The shared instance for this class */
  public static final ResolvingExtensionProvider INSTANCE = new ResolvingExtensionProvider();

  /** state of the extension registry */
  private boolean _isRegistryHacked;

  /** for internal use */
  private final String EXTID_TAG = "tmp"; //$NON-NLS-1$
  private static final String OLD_JAVAEXT_LOC = "org.polarsys.capella.core.refinement.resolver";  //$NON-NLS-1$
  private static final String NEW_JAVAEXT_LOC = "org.polarsys.capella.test.refinement.ju.headless"; //$NON-NLS-1$

  /**
   * id for the extension contribution created by this class
   * Not really nice but it seems that getting extension with their unique identifier does not work properly
   */
  public final String CREATED_EXTENSION_ID = NEW_JAVAEXT_LOC + ICommonConstants.POINT_CHARACTER + "HeadlessSelectionResolver"; //$NON-NLS-1$

  /** (Key, Value) <==> (actionClass to change, new actionClass) */
  private final Map<String, String> _targetClasses = Init.newMap();

  static class Init {
    private Init() {
      // Do nothing
    }

    private static Map<String, String> newMap() {
      Map<String, String> map = new HashMap<String, String>();
      // Select Elements from Transfer Wizard
      map.put(OLD_JAVAEXT_LOC + ICommonConstants.POINT_CHARACTER + "UISelectionResolver", //$NON-NLS-1$
          NEW_JAVAEXT_LOC + ICommonConstants.POINT_CHARACTER + "HeadlessSelectionResolver" //$NON-NLS-1$
      );

      return Collections.unmodifiableMap(map);
    }

  }

  private class InternalCache {
    public InternalCache(IExtension extension) {
      Assert.isNotNull(extension);

      uniqueIdentifier = extension.getUniqueIdentifier();
      contributor = extension.getContributor();
      ResolvingExtensionReader reader = new ResolvingExtensionReader(extension);
      map = new HashMap<String, String>();
      while (reader.hasNext()) {
        reader.next();
        map.put(reader.getIdAtt(), reader.getClassAtt());
      }

    }

    public IContributor getContributor() {
      return contributor;
    }

    public String getUniqueIdentifier() {
      return uniqueIdentifier;
    }

    public Map<String, String> getMap() {
      return map;
    }

    private IContributor contributor;
    private String uniqueIdentifier;
    private Map<String, String> map;
  }

  /** this plugin contributor; useful */
  private IContributor _contributor = null;

  /** the extension registry; useful */
  private IExtensionRegistry _registry = null;

  /** access key to the extension registry */
  private Object _key = null;

  /** cache with modified Extension */
  private List<InternalCache> _modifiedExtensionCache = null;

  private ResolvingExtensionProvider() {
    Bundle bundle = TestRefinementPlugin.getDefault().getBundle();
    _contributor = ContributorFactoryOSGi.createContributor(bundle);
    _registry = Platform.getExtensionRegistry();
    _key = getExtRegistryMasterToken();
    _isRegistryHacked = false;
  }

  /**
   * This method find the contribution to the extension point org.eclipse.sirius.externalJavaAction and replace the default class linked to each contribution
   * with new ones which does not make any call to UI.
   */
  public void init() {

    if (_isRegistryHacked) { // The extension registry has been already modified
      return;
    }

    _modifiedExtensionCache = new ArrayList<InternalCache>();

    // All contributing extensions to the JAVA_ACTION_EXT_POINT extension point
    IExtension[] extensions = _registry.getExtensionPoint(ResolvingExtensionReader.RESOLVING_EXT_POINT).getExtensions();

    // cache for all interesting contributions
    // (Key, Value) <==> (id, actionClass)
    Map<String, String> cacheToReplace = new HashMap<String, String>();

    IContributor contributor = null;
    String extensionId = null;

    ResolvingExtensionReader reader = null;

    boolean mustBeTreated;
    for (IExtension extension : extensions) {

      // cache for all contributions that we must remove and activate once again
      // (Key, Value) <==> (id, actionClass)
      Map<String, String> cacheToRestore = new HashMap<String, String>();

      // Check if this extension point contribution contains at least one interesting element
      mustBeTreated = false;

      reader = new ResolvingExtensionReader(extension);

      while (reader.hasNext() && !mustBeTreated) {
        reader.next();

        if (_targetClasses.containsKey(reader.getClassAtt())) {
          mustBeTreated = true;
          contributor = extension.getContributor(); // the current contributor
          extensionId = extension.getUniqueIdentifier(); // the current extension Id
        }
      }

      if (mustBeTreated) { // let's cache all interesting elements
        // Let's store this extension point contribution before modifying it
        _modifiedExtensionCache.add(new InternalCache(extension));

        reader.reset();

        while (reader.hasNext()) {
          reader.next();

          if (_targetClasses.containsKey(reader.getClassAtt())) {
            cacheToReplace.put(reader.getIdAtt(), _targetClasses.get(reader.getClassAtt()));
          } else {
            cacheToRestore.put(reader.getIdAtt(), reader.getClassAtt());
          }
        }

        // Let's remove the current extension as we can not modify it
        _registry.removeExtension(extension, _key);

        // Let's replace needed and unchanged element
        ByteArrayInputStream is = generateXMLDescStream(cacheToRestore, (null == extensionId ? "" : extensionId) + EXTID_TAG); //$NON-NLS-1$
        if (is != null) {
          _registry.addContribution(is, contributor, true, null, null, _key);
        }
      }
    }

    // Let's add new contribution
    ByteArrayInputStream is = generateXMLDescStream(cacheToReplace, CREATED_EXTENSION_ID);
    if (is != null) {
      _registry.addContribution(is, _contributor, false, null, null, _key);
    }
  }

  /**
   * Restore the original extension registry
   */
  public void restore() {
    if (!_isRegistryHacked) { // No job to perform
      return;
    }

    // First, let's remove our contribution
    for (IExtension extension : _registry.getExtensionPoint(ResolvingExtensionReader.RESOLVING_EXT_POINT).getExtensions()) {
      String uniqueIdentifier = extension.getUniqueIdentifier();
      if (uniqueIdentifier != null && uniqueIdentifier.endsWith(EXTID_TAG)) {
        _registry.removeExtension(extension, _key);
      }
    }

    // On a second hand, let's restore the initial registry state
    for (InternalCache current : _modifiedExtensionCache) {
      ByteArrayInputStream is = generateXMLDescStream(current.getMap(), current.getUniqueIdentifier());
      if (is != null) {
        _registry.addContribution(is, current.getContributor(), true, null, null, _key);
      }
    }
  }

  /**
   * return whether the extension registry has been modified
   * @return
   */
  public boolean isExtensionRegistryModified() {
    return _isRegistryHacked;
  }

  /**
   * generate a stream with a plugin.xml format "as" for the JAVA_ACTION_EXT_POINT
   * @param map (Key, Value) <==> (id, actionClass)
   * @param id the extension id
   * @return null whether the map is null
   */
  private ByteArrayInputStream generateXMLDescStream(Map<String, String> map, String id) {

    ByteArrayInputStream result = null;

    if (!map.isEmpty()) {

      String buffer = "<plugin><extension point=\"" + ResolvingExtensionReader.RESOLVING_EXT_POINT + "\">"; //$NON-NLS-1$ //$NON-NLS-2$

      for (Map.Entry<String, String> entry : map.entrySet()) {
        buffer += "<resolvingExtension " //$NON-NLS-1$
          + ResolvingExtensionReader.ID_ATTRIBUTE + "=\"" + entry.getKey() + "\" " //$NON-NLS-1$ //$NON-NLS-2$
          + ResolvingExtensionReader.CLASS_ATTRIBUTE + "=\"" + entry.getValue() + "\" " //$NON-NLS-1$ //$NON-NLS-2$
          + ResolvingExtensionReader.PRIORITY_ATTRIBUTE + "=\"low\"/>" //$NON-NLS-1$
        ;
      }

      buffer += "</extension></plugin>";//$NON-NLS-1$	

      // Let's finish the job
      result = new ByteArrayInputStream(buffer.toString().getBytes());
    }

    return result;
  }

  /**
   * Hack on Extension registry in order to grant all permission on this one.
   * @return the masterToken whether accessible, null otherwise
   * see org.eclipse.core.internal.registry.osgi.Activator
   */
  private Object getExtRegistryMasterToken() {

    Field[] fs = _registry.getClass().getDeclaredFields();
    Field fTgt = null;
    for (Field f : fs) {
      if (f.getName().equals("masterToken")) { //$NON-NLS-1$
        fTgt = f;
        break;
      }
    }
    fTgt.setAccessible(true);

    Object mtoken = null;

    try {
      mtoken = fTgt.get(_registry);
    } catch (IllegalArgumentException e) {
      // Do nothing
    } catch (IllegalAccessException e) {
      // Do nothing
    }

    return mtoken;
  }

}
