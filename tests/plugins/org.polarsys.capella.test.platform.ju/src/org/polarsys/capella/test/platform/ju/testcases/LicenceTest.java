/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test ensures that all features and plugins are fulfilling Eclipse Legal Documentation Requirements
 * 
 * @see https://www.eclipse.org/projects/handbook/#legaldoc-plugins
 */
public class LicenceTest extends BasicTestCase {

  @SuppressWarnings("restriction")
  @Override
  public void test() throws Exception {
    String id = FrameworkUtil.getBundle(getClass()).getSymbolicName();
    
    HashMap<IModel, MultiStatus> statuses = new HashMap<IModel, MultiStatus>();

    for (IPluginModelBase base : PDECore.getDefault().getModelManager().getActiveModels()) {
      MultiStatus status = statuses.computeIfAbsent(base, x -> new MultiStatus(id, Status.OK, "ok", null));

      if (base.getPluginBase().getId().contains("org.polarsys.capella")) {

        // All plugins must contain an EPL-2.0 license.html
        String content = getContent(base, "about.html");
        if (content == null) {
          status.add(new Status(Status.WARNING, id, "[about.html] Missing file"));
        } else {
          if (!content.contains("epl-2.0")) {
            status.add(new Status(Status.WARNING, id, "[about.html] is not EPL-2.0"));
          }
        }
      }
    }

    for (IFeatureModel feature : PDECore.getDefault().getFeatureModelManager().getModels()) {
      MultiStatus status = statuses.computeIfAbsent(feature, x -> new MultiStatus(id, Status.OK, "ok", null));

      if (feature.getFeature().getId().contains("org.polarsys.capella")) {

        // All features must contains an EPL-2.0 license.html
        String content = getContent(feature, "license.html");
        if (content == null) {
          status.add(new Status(Status.WARNING, id, "[licence.html] Missing file"));
        } else {
          if (!content.contains("epl-2.0")) {
            status.add(new Status(Status.WARNING, id, "[licence.html] is not EPL-2.0"));
          }
        }

        String epl = getContent(feature, "epl-2.0.html");
        if (epl == null) {
          status.add(new Status(Status.WARNING, id, "[epl-2.0.html] Missing file"));
        }

        Properties featureProperties = getProperties(feature, "feature.properties");

        // All feature.properties must contain a licenseURL towards the licence.html
        if (!featureProperties.getProperty("licenseURL", "").equals("license.html")) {
          status.add(new Status(Status.WARNING, id, "[feature.properties] licenceURL not license.html"));
        }

        // All feature.properties must contain a license description
        if (!featureProperties.getProperty("license", "").contains("epl-2.0")) {
          status.add(new Status(Status.WARNING, id, "[feature.properties] licence not EPL-2.0"));
        }

        // All feature.xml must contain a licenseURL to %licenseURL
        if (!"%licenseURL".equals(feature.getFeature().getFeatureInfo(IFeature.INFO_LICENSE).getURL())) {
          status.add(new Status(Status.WARNING, id, "[feature.xml] licenceURL not %licenseURL"));
        }

        // All feature.xml must contain a license to %license
        if (!"%license".equals(feature.getFeature().getFeatureInfo(IFeature.INFO_LICENSE).getDescription())) {
          status.add(new Status(Status.WARNING, id, "[feature.xml] licence not %license"));
        }

        if (!"%copyright".equals(feature.getFeature().getFeatureInfo(IFeature.INFO_COPYRIGHT).getDescription())) {
          status.add(new Status(Status.WARNING, id, "[feature.xml] licence not %copyright"));
        }

        // feature.properties must have an updated copyright according to plugin version (only if its packaged
        // a jar)
        String year = new Version(feature.getFeature().getVersion()).getQualifier().substring(0, 4);
        if (year.matches("\\d+")) {
          if (!featureProperties.getProperty("copyright", "").contains(year)) {
            status.add(new Status(Status.WARNING, id, "[feature.properties] Copyright year not updated"));
          }
        }
        if (!featureProperties.getProperty("copyright", "").contains("epl-2.0")) {
          status.add(new Status(Status.WARNING, id, "[feature.properties] Copyright year not updated"));
        }
        
        
        // For a feature referencing a Bundle-Plugin
        String plugin = feature.getFeature().getPlugin();
        if (plugin != null) {
          IPluginModelBase plu = PDECore.getDefault().getModelManager().findModel(plugin);
          // Plugin must exist
          if (plu == null) {
            status.add(new Status(Status.WARNING, id, "[feature.xml] Invalid plugin for Feature (see feature.xml > Overview > Branding Plug-in)"));

          } else {

            // Plugin about.ini must have an aboutText=%blurb
            Properties aboutIni = getProperties(feature, "about.ini");
            if (!aboutIni.getProperty("aboutText", "").equals("%blurb")) {
              status.add(
                  new Status(Status.WARNING, id, "[about.ini of branding-plugin] Property aboutText=%blurb missing"));
            }

            // Plugin about.properties must have an blurb property
            Properties aboutProperties = getProperties(feature, "about.properties");
            if (aboutProperties.getProperty("blurb", "").isEmpty()) {
              status.add(new Status(Status.WARNING, id, "[about.properties of branding-plugin] Licence not EPL-2.0"));
            }

            // Plugin about.properties must have an updated copyright according to plugin version (only if its packaged
            // a jar)
            String year2 = plu.getBundleDescription().getVersion().getQualifier().substring(0, 4);
            if (year2.matches("\\d+")) {
              if (!aboutProperties.getProperty("blurb", "").contains(year)) {
                status.add(new Status(Status.WARNING, id, "[about.ini of branding-plugin] Copyright date not updated"));
              }
            }
          }
        }

        Platform.getBundle(feature.getFeature().getId());
      }
    }

    Collection<IModel> invalids = statuses.keySet().stream().filter(x -> !statuses.get(x).isOK())
        .collect(Collectors.toList());
    assertTrue(
        invalids.stream()
            .map(k -> getId(k) + "\n" + Arrays.asList(statuses.get(k).getChildren()).stream()
                .map(x -> "- " + x.getMessage()).collect(Collectors.joining("\n")))
            .collect(Collectors.joining("\n\n")),
        invalids.isEmpty());
    System.out.println();

  }

  private String getId(IModel k) {
    return k instanceof IFeatureModel ? ((IFeatureModel) k).getFeature().getId()
        : ((IPluginModelBase) k).getBundleDescription().getSymbolicName();
  }

  public InputStream getFile(IModel pluginOrFeature, String name) {
    String installLocation = pluginOrFeature instanceof IFeatureModel
        ? ((IFeatureModel) pluginOrFeature).getInstallLocation()
        : ((IPluginModelBase) pluginOrFeature).getInstallLocation();

    try {
      File file = new File(installLocation);
      if (file.isDirectory()) {
        return new FileInputStream(new File(file, name));
      }

      Bundle bundle = Platform.getBundle(getId(pluginOrFeature));
      if (bundle != null) {
        URL url = bundle.getEntry(name);
        if (url != null) {
          return url.openConnection().getInputStream();
        }
      }

    } catch (IOException e) {
    }
    return null;
  }

  public String getContent(IModel plugin, String name) {
    InputStream stream = getFile(plugin, name);
    if (stream != null) {
      String content = new String(FileHelper.readFile(stream), StandardCharsets.UTF_8);
      return content;
    }
    return null;
  }

  public Properties getProperties(IModel model, String file) {
    Properties properties = new Properties();
    try {
      properties.load(getFile(model, file));
    } catch (Exception e) {
    }
    return properties;
  }

}
