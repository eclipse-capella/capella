/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.ctx.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;

/**
 * MissionPkg helpers.
 */
public class MissionPkgExt {

  /**
   * Get all the missions in a missionPkg (and SUB PKGS) recursively
   * @param missionPkg the missionPkg
   * @return list of missions
   */
  public static List<Mission> getAllMissions(MissionPkg missionPkg) {
    List<Mission> list = new ArrayList<>(1);
    if (null != missionPkg) {
      list.addAll(missionPkg.getOwnedMissions());
      for (MissionPkg subPkg : missionPkg.getOwnedMissionPkgs()) {
        list.addAll(getAllMissions(subPkg));
      }
    }

    return list;
  }
}
