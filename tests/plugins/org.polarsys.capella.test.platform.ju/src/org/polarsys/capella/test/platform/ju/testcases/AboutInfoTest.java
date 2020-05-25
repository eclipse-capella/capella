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
import org.eclipse.pde.internal.core.PDEManager;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;
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
public class AboutInfoTest extends BasicTestCase {

  @SuppressWarnings("restriction")
  @Override
  public void test() throws Exception {
    String id = FrameworkUtil.getBundle(getClass()).getSymbolicName();

    HashMap<IModel, MultiStatus> statuses = new HashMap<IModel, MultiStatus>();

    for (IPluginModelBase base : PDECore.getDefault().getModelManager().getActiveModels()) {
      MultiStatus status = statuses.computeIfAbsent(base, x -> new MultiStatus(id, Status.OK, "ok", null));

      if (base.getPluginBase().getId().contains("org.polarsys.capella") && !base.getPluginBase().getId().endsWith(".source")) {
        if (!"%pluginName".equals(base.getPluginBase().getName())) {
          status.add(new Status(Status.WARNING, id, "[MANIFEST.MF] name not %pluginName"));
        }
        if (!"%providerName".equals(base.getPluginBase().getProviderName())) {
          status.add(new Status(Status.WARNING, id, "[MANIFEST.MF] provider-name not %providerName"));
        }
        if (PDEManager.getBundleLocalization(base) == null) {
          status.add(new Status(Status.WARNING, id, "[MANIFEST.MF] Missing 'Bundle-Localization: plugin'"));
        }
        
        Properties pluginProperties = getProperties(base, "plugin.properties");
        if (pluginProperties.isEmpty()) {
          System.out.println();
        }
        // All feature.properties must contain a licenseURL towards the licence.html
        if (pluginProperties.getProperty("pluginName", "").isEmpty()) {
          status.add(new Status(Status.WARNING, id, "[plugin.properties] pluginName is empty"));
        }

        // All feature.properties must contain a license description
        if (!("Eclipse.org".equals(pluginProperties.getProperty("providerName", "")))) {
          status.add(new Status(Status.WARNING, id, "[plugin.properties] providerName not Eclipse.org: "));
        }

      }
    }

    for (IFeatureModel feature : PDECore.getDefault().getFeatureModelManager().getModels()) {
      MultiStatus status = statuses.computeIfAbsent(feature, x -> new MultiStatus(id, Status.OK, "ok", null));

      if (feature.getFeature().getId().contains("org.polarsys.capella") && !feature.getFeature().getId().endsWith(".source")) {
        Properties featureProperties = getProperties(feature, "feature.properties");

        // All feature.properties must contain a licenseURL towards the licence.html
        if (featureProperties.getProperty("featureName", "").isEmpty()) {
          status.add(new Status(Status.WARNING, id, "[feature.properties] featureName is empty"));
        }

        // All feature.properties must contain a license description
        if (!("Eclipse.org".equals(featureProperties.getProperty("providerName", "")))) {
          status.add(new Status(Status.WARNING, id, "[feature.properties] providerName not Eclipse.org"));
        }

        // All feature.xml must contain a licenseURL to %licenseURL
        if (!"%providerName".equals(feature.getFeature().getProviderName())) {
          status.add(new Status(Status.WARNING, id, "[feature.xml] provider-name not %providerName"));
        }
        
        if (!"%featureName".equals(feature.getFeature().getLabel())) {
          status.add(new Status(Status.WARNING, id, "[feature.xml] label not %featureName"));
        }
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
      InputStream stream = getFile(model, file);
      if (stream != null) {
        properties.load(stream);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return properties;
  }

}
