/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;


import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.vp.ms.CSConfiguration;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;

public class GeneralMode {

  private EList<CSConfiguration> configurations;
  private EList<Mode> modes;

  /**
   * 
   */
  public GeneralMode() {
    configurations = new BasicEList<CSConfiguration>();
    modes = new BasicEList<Mode>();
  }

  /**
   * @return the configurations
   */
  public EList<CSConfiguration> getConfigurations() {
    return configurations;
  }

  /**
   * @param configurations the configurations to set
   */
  public void setConfigurations(EList<CSConfiguration> configurations) {
    this.configurations = configurations;
  }

  /**
   * @return the modes
   */
  public EList<Mode> getModes() {
    return modes;
  }

  /**
   * @param mdoes the modes to set
   */
  public void setModes(EList<Mode> mdoes) {
    modes = mdoes;
  }
  
  public String getName(){
    return Joiner.on(", ").join(Collections2.transform(getModes(), new Function<Mode, String>() { public String apply(Mode m) { return m.getName(); }}));
  }
  
  public String getConfigurationsName(){
    return Joiner.on(", ").join(Collections2.transform(getConfigurations(), new Function<CSConfiguration, String>() { public String apply(CSConfiguration m) { return m.getName(); }}));
  }

}
