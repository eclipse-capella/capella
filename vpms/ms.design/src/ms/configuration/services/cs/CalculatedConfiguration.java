/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package ms.configuration.services.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.selector_Type;

public class CalculatedConfiguration {

  public CalculatedConfiguration(Result r) {
    owning_result = r;
    ErrorListText = new ArrayList<String>();
    ErrorListRuleType = new ArrayList<String>();
  }

  private Result owning_result;
  private Situation owning_situation;

  private LogicalComponent included_logicalComp;
  private LogicalFunction included_functions;
  private FunctionalChain included_fc;

  private static ArrayList<String> ErrorListText;
  private static ArrayList<String> ErrorListRuleType;

  private ConfList calculated_cl;

  private BooleanExpr root_be;

  private List<CSConfiguration> configList = new ArrayList<CSConfiguration>();

  public ArrayList<String> getErrorListText() {
    return ErrorListText;
  }

  public ArrayList<String> getErrorListRuleType() {
    return ErrorListRuleType;
  }

  // represents the content of a Modes & State CSConfiguration but does not corresponds to an item of the capella data
  // model.
  // used only for the calculation of configurations associated to situation object

  public static class LightConfiguration {
    String Name;
    List<AbstractFunction> lF;
    List<FunctionalChain> lCF;
    List<Component> lC;

    boolean isExcluded;
    List<AbstractFunction> excl_lF;
    List<FunctionalChain> excl_lCF;
    List<Component> excl_lC;

    public LightConfiguration(CSConfiguration initial_conf) // construction from an existing CSConfiguration
    {
      Name = initial_conf.getName();
      lF = new ArrayList<AbstractFunction>();
      lCF = new ArrayList<FunctionalChain>();
      lC = new ArrayList<Component>();
      excl_lF = new ArrayList<AbstractFunction>();
      excl_lCF = new ArrayList<FunctionalChain>();
      excl_lC = new ArrayList<Component>();

      EList<AbstractFunction> f = initial_conf.getFunctions();
      EList<FunctionalChain> cf = initial_conf.getFunctionalChains();
      EList<Component> c = initial_conf.getComponents();

      if (initial_conf.getSelector() == selector_Type.INCLUSION) {

        lF = initial_conf.getFunctions();
        lCF = initial_conf.getFunctionalChains();
        lC = initial_conf.getComponents();
      }
      if (initial_conf.getSelector() == selector_Type.EXCLUSION) {

        excl_lF = initial_conf.getFunctions();
        excl_lCF = initial_conf.getFunctionalChains();
        excl_lC = initial_conf.getComponents();
      }
      isExcluded = false;
    }

    public LightConfiguration(String name) // constructor
    {
      Name = name;

      lF = new ArrayList<AbstractFunction>();
      lCF = new ArrayList<FunctionalChain>();
      lC = new ArrayList<Component>();
      excl_lF = new ArrayList<AbstractFunction>();
      excl_lCF = new ArrayList<FunctionalChain>();
      excl_lC = new ArrayList<Component>();

      isExcluded = false;

    }

    public void display() {
      System.out.println(" 	Configuration : " + Name);

      System.out.print(" 		Function included : ");
      for (int i = 0; i < lF.size(); i++)
        System.out.print(lF.get(i).getName() + ", ");
      System.out.println();
      System.out.print(" 		FC included :");
      for (int i = 0; i < lCF.size(); i++)
        System.out.print(lCF.get(i).getName() + ", ");
      System.out.println();
      System.out.print(" 		Component included :");
      for (int i = 0; i < lC.size(); i++)
        System.out.print(lC.get(i).getName() + ", ");
      System.out.println();

      System.out.print(" 		Function excluded :");
      for (int i = 0; i < excl_lF.size(); i++)
        System.out.print(excl_lF.get(i).getName() + ", ");
      System.out.println();
      System.out.print(" 		FC excluded :");
      for (int i = 0; i < excl_lCF.size(); i++)
        System.out.print(excl_lCF.get(i).getName() + ", ");
      System.out.println();
      System.out.print(" 		Component excluded :");
      for (int i = 0; i < excl_lC.size(); i++)
        System.out.print(excl_lC.get(i).getName() + ", ");
      System.out.println();

    }

    public String getName() {
      return Name;
    }

    public List<AbstractFunction> getLogFct() {
      return lF;
    }

    public List<FunctionalChain> getFctChain() {
      return lCF;
    }

    public List<Component> getComponents() {
      return lC;
    }

    public List<AbstractFunction> getExclLogFct() {
      return excl_lF;
    }

    public List<FunctionalChain> getExclFctChain() {
      return excl_lCF;
    }

    public List<Component> getExclComponents() {
      return excl_lC;
    }

  }

  public static class ConfList {
    String Name;
    List<LightConfiguration> Cl;

    public ConfList(String name, LightConfiguration... Cfl) {
      Name = name;
      Cl = new ArrayList<LightConfiguration>();

      if (Cfl != null)
        for (int i = 0; i < Cfl.length; i++) {
          Cl.add(Cfl[i]);
        }
    }

    public void display() {
      System.out.println("=== Display Configuration list : " + Name + "===");
      for (int i = 0; i < Cl.size(); i++)
        Cl.get(i).display();
    }

    public List<LightConfiguration> getLightConfigurationList() {
      return Cl;
    }
  }

  public class BooleanExpr {
    String label;
    String BooleanOperator;
    boolean isComposite;
    boolean isExcluded;
    boolean valStatus; // applicable a un noeud composite : mis à 1 si la configuration résultante sur ce noeud est
                       // valide

    List<BooleanExpr> children;

    ConfList linked_configurations; // liste des cnofigurations associées à ce noeud. (liste séparées par des OU)

    public BooleanExpr() {
      children = new ArrayList<BooleanExpr>();
      linked_configurations = new ConfList("a", null);
      isExcluded = false;
    }
  }

  // XOR applicable sur des configurations
  private LightConfiguration compute_XOR(LightConfiguration... cfgs) {
    LightConfiguration rcfg;
    rcfg = new LightConfiguration("r");

    if (cfgs.length >= 2) {

      for (int i = 0; i < cfgs[0].lF.size(); i++) {
        rcfg.lF.add(cfgs[0].lF.get(i));

      }
      for (int i = 0; i < cfgs[0].excl_lF.size(); i++) {
        rcfg.excl_lF.add(cfgs[0].excl_lF.get(i));

      }
      for (int i = 0; i < cfgs[1].lF.size(); i++) {
        rcfg.lF.add(cfgs[1].lF.get(i));

      }
      for (int i = 0; i < cfgs[1].excl_lF.size(); i++) {
        rcfg.excl_lF.add(cfgs[1].excl_lF.get(i));

      }

    }
    return rcfg;
  }

  // NOT operation applicable sur des configurations
  private static LightConfiguration compute_NOT(LightConfiguration cfgs) {
    LightConfiguration rcfg = new LightConfiguration("r");

    // range les fonctions inclues dans la liste des fonctions exclue de la configuration retournée
    for (int i = 0; i < cfgs.lF.size(); i++) {
      rcfg.excl_lF.add(cfgs.lF.get(i));
    }
    for (int i = 0; i < cfgs.lCF.size(); i++) {
      rcfg.excl_lCF.add(cfgs.lCF.get(i));
    }
    for (int i = 0; i < cfgs.lC.size(); i++) {
      rcfg.excl_lC.add(cfgs.lC.get(i));
    }
    // range les fonctions exclues dans la liste des fonctions inclues de la configuration retournée
    for (int i = 0; i < cfgs.excl_lF.size(); i++) {
      rcfg.lF.add(cfgs.excl_lF.get(i));
    }
    for (int i = 0; i < cfgs.excl_lCF.size(); i++) {
      rcfg.lCF.add(cfgs.excl_lCF.get(i));
    }
    for (int i = 0; i < cfgs.excl_lC.size(); i++) {
      rcfg.lC.add(cfgs.excl_lC.get(i));
    }

    boolean b = cfgs.isExcluded;
    // if(!rcfg.isExcluded)
    rcfg.isExcluded = true;
    // else
    // rcfg.isExcluded = false;

    return rcfg;
  }

  private static boolean CHK_ERR_Alloc_on_exluded_comp(LightConfiguration... cfgs) {
    boolean rule_failed = false;

    // parcours deux à deux les cfgs[ ] et verifie si un élément inclus dans le premier n'est pas exlu dans le deuxieme
    // et inversément
    if (cfgs.length >= 2) {

      // for each configurations in parameters :
      for (int i = 0; i < cfgs.length; i++) {
        for (int j = 0; j < cfgs.length; j++) {
          ArrayList<AbstractFunction> incl_Fs = new ArrayList<AbstractFunction>(cfgs[i].lF);

          // recupere la liste des fonctinos allouée sur les composants exclus
          List<AbstractFunction> F_allocatedOnExclComp = new ArrayList<AbstractFunction>();

          for (int iexcl_comp = 0; iexcl_comp < cfgs[j].excl_lC.size(); iexcl_comp++)
            F_allocatedOnExclComp.addAll(cfgs[j].excl_lC.get(iexcl_comp).getAllocatedFunctions());

          // conserve seulement les fonctions inclues et alloues sur composants exclus
          F_allocatedOnExclComp.retainAll(incl_Fs);

          for (int i_err = 0; i_err < F_allocatedOnExclComp.size(); i_err++) {
            rule_failed = true;
            String erroneous_comp_name = new String("");
            if (F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0) != null)
              erroneous_comp_name = F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0).getName();

            ErrorListText.add("SITUATION COMPILATION ERROR  : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                + "' is included and allocated on an excluded component '" + erroneous_comp_name + "' ");
            ErrorListRuleType.add("INCL_EXCL_INVOLVED_ELEMENT");
            System.out.println("SITUATION COMPILATION ERROR  : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                + "' is included and allocated on an excluded component '" + erroneous_comp_name + "' ");
          }
          incl_Fs.clear();
          F_allocatedOnExclComp.clear();

          ArrayList<AbstractFunction> involved_Fs = new ArrayList<AbstractFunction>();

          // recupere la liste des fonctinos allouée sur les composants exclus
          F_allocatedOnExclComp = new ArrayList<AbstractFunction>();

          for (int iexcl_comp = 0; iexcl_comp < cfgs[j].excl_lC.size(); iexcl_comp++)
            F_allocatedOnExclComp.addAll(cfgs[j].excl_lC.get(iexcl_comp).getAllocatedFunctions());

          // conserve seulement les fonctions inclues et alloues sur composants exclus
          F_allocatedOnExclComp.retainAll(involved_Fs);

          for (int i_err = 0; i_err < F_allocatedOnExclComp.size(); i_err++) {
            rule_failed = true;
            String erroneous_comp_name = new String("");
            if (F_allocatedOnExclComp.get(i_err).getAllocationBlocks().size() > 0)
              if (F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0) != null)
                erroneous_comp_name = F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0).getName();

            ErrorListText.add("SITUATION COMPILATION ERROR  : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                + "' is included and allocated on an excluded component '" + erroneous_comp_name + "' ");
            ErrorListRuleType.add("INCL_EXCL_INVOLVED_ELEMENT");
            System.out.println("SITUATION COMPILATION ERROR  : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                + "' is included and allocated on an excluded component '" + erroneous_comp_name + "' ");

          }
          incl_Fs.clear();
          F_allocatedOnExclComp.clear();

        }

      }
    }

    return rule_failed;
  }

  private static boolean CHK_ERR_Alloc_FC_on_exluded_comp(LightConfiguration... cfgs) {
    boolean rule_failed = false;

    // parcours deux à deux les cfgs[ ] et verifie si un élément inclus dans le premier n'est pas exlu dans le deuxieme
    // et inversément
    if (cfgs.length >= 2) {

      // for each configurations in parameters :
      for (int i = 0; i < cfgs.length; i++) {
        for (int j = 0; j < cfgs.length; j++) {

          ArrayList<FunctionalChain> incl_CFs = new ArrayList<FunctionalChain>(cfgs[i].lCF);

          // for each included functional chain:
          for (int ifc = 0; ifc < incl_CFs.size(); ifc++) {
            System.out.println("nom fc : " + incl_CFs.get(ifc).getName());
            ArrayList<AbstractFunction> incl_Fs = new ArrayList<AbstractFunction>(
                incl_CFs.get(ifc).getInvolvedFunctions());

            // recupere la liste des fonctions allouée sur les composants exclus
            List<AbstractFunction> F_allocatedOnExclComp = new ArrayList<AbstractFunction>();

            for (int iexcl_comp = 0; iexcl_comp < cfgs[j].excl_lC.size(); iexcl_comp++) {
              F_allocatedOnExclComp.addAll(cfgs[j].excl_lC.get(iexcl_comp).getAllocatedFunctions());
            }
            // conserve seulement les fonctions inclues et alloues sur composants exclus
            F_allocatedOnExclComp.retainAll(incl_Fs);

            for (int i_err = 0; i_err < F_allocatedOnExclComp.size(); i_err++) {
              rule_failed = true;
              String erroneous_comp_name = new String("");
              if (F_allocatedOnExclComp.get(i_err).getAllocationBlocks().size() > 0)
                if (F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0) != null)
                  erroneous_comp_name = F_allocatedOnExclComp.get(i_err).getAllocationBlocks().get(0).getName();

              ErrorListText.add("SITUATION COMPILATION ERROR : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                  + "' is involved in functional chain '" + incl_CFs.get(ifc).getName()
                  + "' and allocated on an excluded component '" + erroneous_comp_name + "' ");
              ErrorListRuleType.add("INCL_EXCL_INVOLVED_ELEMENT");
              System.out.println("SITUATION COMPILATION ERROR : Function '" + F_allocatedOnExclComp.get(i_err).getName()
                  + "' is involved in functional chain '" + incl_CFs.get(ifc).getName()
                  + "' and allocated on an excluded component '" + erroneous_comp_name + "' ");
            }
            incl_Fs.clear();
            F_allocatedOnExclComp.clear();
          }
          incl_CFs.clear();
        }

      }
    }

    return rule_failed;
  }

  private static boolean CHK_ERR_FC_vs_F_inclusion_exclusion(LightConfiguration... cfgs) {
    boolean rule_failed = false;

    // parcours deux à deux les cfgs[ ] et verifie si un élément inclus dans le premier n'est pas exlu dans le deuxieme
    // et inversément
    if (cfgs.length >= 2) {

      // for each configurations in parameters :
      for (int i = 0; i < cfgs.length; i++) {
        for (int j = 0; j < cfgs.length; j++) {
          // included functional chains :
          ArrayList<FunctionalChain> incl_CFs = new ArrayList<FunctionalChain>(cfgs[i].lCF);
          ArrayList<FunctionalChain> excl_CFs = new ArrayList<FunctionalChain>(cfgs[i].excl_lCF);

          // for each included functional chain :
          for (int ifc = 0; ifc < incl_CFs.size(); ifc++) {
            ArrayList<AbstractFunction> common_inclFunctions = new ArrayList<AbstractFunction>(
                incl_CFs.get(ifc).getInvolvedFunctions());

            // list of erroneous elements : function involved and excluded :
            ArrayList<AbstractFunction> common_inclexclFunctions = new ArrayList<AbstractFunction>(
                common_inclFunctions);
            common_inclexclFunctions.retainAll(cfgs[j].excl_lF); // keep only the elements included in excl_F

            for (int i_err = 0; i_err < common_inclexclFunctions.size(); i_err++) {
              rule_failed = true;
              ErrorListText
                  .add("SITUATION COMPILATION ERROR  : Function '" + common_inclexclFunctions.get(i_err).getName()
                      + "' is involed in a functional chain '" + incl_CFs.get(ifc).getName() + "' and excluded");
              ErrorListRuleType.add("INCL_EXCL_INVOLVED_ELEMENT");
              System.out
                  .println("SITUATION COMPILATION ERROR  : Function '" + common_inclexclFunctions.get(i_err).getName()
                      + "' is involed in a functional chain '" + incl_CFs.get(ifc).getName() + "' and excluded");
            }
            common_inclFunctions.clear();
            common_inclexclFunctions.clear();
          }

          // for each excluded functional chain :
          for (int ifc = 0; ifc < excl_CFs.size(); ifc++) {
            ArrayList<AbstractFunction> common_exclFunctions = new ArrayList<AbstractFunction>(
                excl_CFs.get(ifc).getInvolvedFunctions());

            // list of erroneous elements : function involved in exluced fc and included :
            ArrayList<AbstractFunction> common_inclexclFunctions = new ArrayList<AbstractFunction>(
                common_exclFunctions);
            common_inclexclFunctions.retainAll(cfgs[j].lF); // keep only the elements included in excl_F

            for (int i_err = 0; i_err < common_inclexclFunctions.size(); i_err++) {
              rule_failed = true;
              ErrorListText
                  .add("SITUATION COMPILATION ERROR  : Function '" + common_inclexclFunctions.get(i_err).getName()
                      + "' is both included and involved in a excluded functional chain : '"
                      + excl_CFs.get(ifc).getName() + "' ");
              ErrorListRuleType.add("INCL_EXCL_INVOLVED_ELEMENT");
              System.out
                  .println("SITUATION COMPILATION ERROR  : Function '" + common_inclexclFunctions.get(i_err).getName()
                      + "' is both included and involved in a excluded functional chain : '"
                      + excl_CFs.get(ifc).getName() + "' ");
            }
            common_exclFunctions.clear();
            common_inclexclFunctions.clear();
          }
          incl_CFs.clear();
          excl_CFs.clear();

        }
      }
    }

    return rule_failed;
  }

  private static boolean CHK_ERR_AND_inclusion_exclusion(LightConfiguration... cfgs) {
    boolean rule_failed = false;

    // parcours deux à deux les cfgs[ ] et verifie si un élément inclus dans le premier n'est pas exlu dans le deuxieme
    // et inversément
    if (cfgs.length >= 2) {

      for (int i = 0; i < cfgs.length; i++) {
        for (int j = 0; j < cfgs.length; j++) {
          ArrayList<AbstractFunction> common_Functions = new ArrayList<AbstractFunction>(cfgs[i].lF);
          ArrayList<FunctionalChain> common_CFs = new ArrayList<FunctionalChain>(cfgs[i].lCF);
          ArrayList<Component> common_Cs = new ArrayList<Component>(cfgs[i].lC);

          common_Functions.retainAll(cfgs[j].excl_lF); // keep only the elements included in excl_F
          common_CFs.retainAll(cfgs[j].excl_lCF);
          common_Cs.retainAll(cfgs[j].excl_lC);

          if (true) {
            for (int i_err = 0; i_err < common_Functions.size(); i_err++) {
              rule_failed = true;
              ErrorListText.add("SITUATION COMPILATION ERROR  : Function both included and excluded : '"
                  + common_Functions.get(i_err).getName() + "' ");
              ErrorListRuleType.add("INCL_EXCL_ELEMENT");
              System.out.println("SITUATION COMPILATION ERROR  : Function both included and excluded : '"
                  + common_Functions.get(i_err).getName() + "' ");
            }

            for (int i_err = 0; i_err < common_CFs.size(); i_err++) {
              rule_failed = true;
              ErrorListText.add("SITUATION COMPILATION ERROR  : Functional chain both included and excluded : '"
                  + common_CFs.get(i_err).getName() + "' ");
              ErrorListRuleType.add("INCL_EXCL_ELEMENT");
              System.out.println("SITUATION COMPILATION ERROR  : Functional chain both included and excluded : '"
                  + common_CFs.get(i_err).getName() + "' ");

            }
            for (int i_err = 0; i_err < common_Cs.size(); i_err++) {
              rule_failed = true;
              ErrorListText.add("SITUATION COMPILATION ERROR  : Component both included and excluded : '"
                  + common_Cs.get(i_err).getName() + "' ");
              ErrorListRuleType.add("INCL_EXCL_ELEMENT");
              System.out.println("SITUATION COMPILATION ERROR  : Component both included and excluded : '"
                  + common_Cs.get(i_err).getName() + "' ");

            }
          }
          common_Functions.clear();
          common_CFs.clear();
          common_Cs.clear();

        }
      }
    }

    return rule_failed;
  }

  // AND applicable sur des configuration
  private static LightConfiguration compute_AND(LightConfiguration... cfgs) {
    LightConfiguration rcfg = new CalculatedConfiguration.LightConfiguration("r");

    if (cfgs.length >= 2) {

      CHK_ERR_AND_inclusion_exclusion(cfgs);
      CHK_ERR_FC_vs_F_inclusion_exclusion(cfgs);
      CHK_ERR_Alloc_on_exluded_comp(cfgs);
      CHK_ERR_Alloc_FC_on_exluded_comp(cfgs);

      for (int icfg = 0; icfg < cfgs.length; icfg++) {
        for (int i = 0; i < cfgs[icfg].lF.size(); i++)
          rcfg.lF.add(cfgs[icfg].lF.get(i));

        for (int i = 0; i < cfgs[icfg].excl_lF.size(); i++)
          rcfg.excl_lF.add(cfgs[icfg].excl_lF.get(i));

        for (int i = 0; i < cfgs[icfg].lCF.size(); i++)
          rcfg.lCF.add(cfgs[icfg].lCF.get(i));

        for (int i = 0; i < cfgs[icfg].excl_lCF.size(); i++)
          rcfg.excl_lCF.add(cfgs[icfg].excl_lCF.get(i));

        for (int i = 0; i < cfgs[icfg].lC.size(); i++)
          rcfg.lC.add(cfgs[icfg].lC.get(i));

        for (int i = 0; i < cfgs[icfg].excl_lC.size(); i++)
          rcfg.excl_lC.add(cfgs[icfg].excl_lC.get(i));

      }
      return rcfg;
    }
    return cfgs[0];
  }

  // réalise l'operation ET sur deux ou plus de deux listes de configuerations
  private static ConfList Operator_AND(ArrayList<ConfList> operandes) {
    ConfList cl = new ConfList("r");
    // cl = operandes.get(0);
    // realise le développement du ET sur les listes de configurations de chaque operande, 2 a 2 :
    int k_oper = 0;

    if (operandes.size() == 1) {
      cl = operandes.get(0); // cas d'un ET d'un seul élément
    }
    if (operandes.size() > 1) {
      while (k_oper < operandes.size() - 1) {
        // copie les deux premiers opérandes, ou, pour le premier, le résultat de l'iteration precedente pour
        // l'itération actuelle
        ConfList operandes_1 = new ConfList("r");
        if (k_oper == 0) // premiere iteration
          operandes_1 = operandes.get(0);
        else { // iteration suivante
          for (int k = 0; k < cl.Cl.size(); k++) {
            operandes_1.Cl.add(cl.Cl.get(k));

          }
        }
        cl.Cl.clear();

        ConfList operandes_2 = operandes.get(k_oper + 1);

        for (int i = 0; i < operandes_1.Cl.size(); i++)
          for (int j = 0; j < operandes_2.Cl.size(); j++)
            cl.Cl.add(compute_AND(operandes_1.Cl.get(i), operandes_2.Cl.get(j)));
        k_oper++;
      }
    }

    return cl;
  }

  // realise l'operation NOT sur une expression booleene contenant une plusieurs listes de configurations
  // si le NOT s'applique à une configuration calculée, contenant une liste de configurations potentielles
  // séparées par des OU, alors, fusionne ces configurations potentielles en une seule car NON (A ou B) = NON(A) et
  // NON(B)

  private static ConfList Operator_NOT(ArrayList<ConfList> operandes) {

    ConfList cl = new ConfList("r", null);
    for (int i = 0; i < operandes.size(); i++)
      for (int j = 0; j < operandes.get(i).Cl.size(); j++)
        cl.Cl.add(compute_NOT(operandes.get(i).Cl.get(j)));

    return cl;
  }

  // réalise l'operation XOR sur deux ou plus de deux listes de configuerations
  private static ConfList Operator_XOR(ArrayList<ConfList> operandes) {
    ConfList cl = new ConfList("r", null);

    // realise le développement du ET sur les listes de configurations de chaque operande, 2 a 2 :
    int k_oper = 0;
    if (operandes.size() == 1) {
      cl = operandes.get(0); // cas d'un OU d'un seul élément
    }
    if (operandes.size() > 1) {
      while (k_oper < operandes.size() - 1) {
        // copie les deux premiers opérandes, ou, pour le premier, le résultat de l'iteration precedente pour
        // l'itération actuelle
        ConfList operandes_1 = new ConfList("r", null);
        if (k_oper == 0) // premiere iteration
          operandes_1 = operandes.get(0);
        else { // iteration suivante
          for (int k = 0; k < cl.Cl.size(); k++)
            operandes_1.Cl.add(cl.Cl.get(k));
        }
        cl.Cl.clear();

        ConfList operandes_2 = operandes.get(k_oper + 1);

        for (int i = 0; i < operandes_1.Cl.size(); i++)
          cl.Cl.add(operandes_1.Cl.get(i));
        for (int j = 0; j < operandes_2.Cl.size(); j++)
          cl.Cl.add(operandes_2.Cl.get(j));

        k_oper++;
      }
    }

    return cl;
  }

  // explore récursivement l'arborescence, et calcule la liste de configurations
  // résultantes en remontant depuis les noeuds feuilles.
  private static ConfList Explore_be(BooleanExpr be) {
    // ConfList cl = new ConfList("cl", null);
    // si le noeud est un noeud composite (operateur AND OR NOT)
    if (be.isComposite) {
      int ji;
      ArrayList<ConfList> operandes;
      operandes = new ArrayList<ConfList>();

      // appel recursif pour tous les enfants du noeud
      for (ji = 0; ji < be.children.size(); ji++) {
        operandes.add(Explore_be(be.children.get(ji)));
      }

      switch (be.BooleanOperator) {
      case "AND":
        System.out.println("Operator AND");
        be.linked_configurations = Operator_AND(operandes);
        break;
      case "OR":
        System.out.println("Operator XOR");
        be.linked_configurations = Operator_XOR(operandes);
        break;
      case "NOT":
        System.out.println("Operator NOT");
        be.linked_configurations = Operator_NOT(operandes);
        break;
      }

      // fin de parcourt de tous les enfants, réaliser la compilation sur ce noeud
      System.out.print(" calcul " + be.label + " ");
      for (int i = 0; i < operandes.size(); i++) {
        System.out.print(" operandes " + operandes.get(i).Name);
      }

      System.out.println(" compilation : " + be.linked_configurations.Cl.size());

      for (int i = 0; i < be.linked_configurations.Cl.size(); i++) {

        for (int j = 0; j < be.linked_configurations.Cl.get(i).lF.size(); j++) {
          // if (be.linked_configurations.Cl.get(i).isExcluded) System.out.print('x');
          // System.out.print(be.linked_configurations.Cl.get(i).F.get(j)+ ' ');
        }
        // System.out.println("");
      }

      return be.linked_configurations;

    } else // noeud not composite : c'est un Mode ou Etat. => faire un ET des configurations associées à ce M/S
    {
      // System.out.println(" % ");

      ArrayList<ConfList> operandes; // liste de configurations
      operandes = new ArrayList<ConfList>();

      for (int i = 0; i < be.linked_configurations.Cl.size(); i++) {
        // cree un conflist a partir des configurations associée a la
        ConfList cl = new ConfList("w", be.linked_configurations.Cl.get(i));
        operandes.add(cl);
      }

      return Operator_AND(operandes);
    }
    // ET, cree la conflist resultante a partir de chaque branches

  }

  private boolean initializeBoolExprTree(BooleanExpr parent_be, EObject be_object) {
    for (EObject jObj : be_object.eContents()) {

      BooleanExpr be2 = new BooleanExpr();

      if (jObj instanceof BooleanOperation) {

        be2.isComposite = true;
        be2.label = "N/A";
        parent_be.children.add(be2);

        if (jObj instanceof AndOperation) {
          java.lang.System.out.println("   AndOperation");
          be2.BooleanOperator = "AND";
        }
        if (jObj instanceof OrOperation) {
          java.lang.System.out.println("   OrOperation");
          be2.BooleanOperator = "OR";
        }
        if (jObj instanceof NotOperation) {
          java.lang.System.out.println("   NotOperation");
          be2.BooleanOperator = "NOT";
        }
        // explore the children of this boolean expression :
        initializeBoolExprTree(be2, jObj);
      }
      // if it is a Mode or State (leaf in the boolean expression tree) :
      if (jObj instanceof InStateExpression) {
        InStateExpression ise = (InStateExpression) jObj;
        java.lang.System.out.println("   InStateExpression");
        AbstractState ModeState = ise.getState();
        be2.label = ModeState.getName();

        // find 0, 1 or several linked configuration(s) to this mode / state :
        int nb_linked_conf = 0;
        ArrayList<LightConfiguration> list = new ArrayList<LightConfiguration>();

        for (CSConfiguration conf : configList) {
          if (conf.getSupportedModes().contains(ModeState)) {
            nb_linked_conf++;
            LightConfiguration c2 = new LightConfiguration(conf);
            list.add(c2);
          }
        }
        if (list.size() == 1) {
          be2.linked_configurations.Cl.add(list.get(0));
        }
        be2.BooleanOperator = "";
        be2.isComposite = false;

        parent_be.children.add(be2);
      }

    }
    return true;
  }

  public ConfList Calculate() {
    owning_situation = owning_result.getSituation().get(0);
    java.lang.System.out.println("start calculate"); // JVS

    // fill the configurations list:
    // Project project = ProjectExt.getProject(_CG);
    BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(owning_situation);
    TreeIterator<EObject> it = archi.eAllContents();
    while (it.hasNext()) {
      EObject eo = it.next();
      if (eo instanceof CSConfiguration) {
        configList.add((CSConfiguration) eo);
        java.lang.System.out.println("config list : " + ((CSConfiguration) eo).getName());
      }
    }
    // initialize the boolean expression tree recursively :
    for (EObject iObj : owning_situation.eContents()) {
      BooleanOperation rootBoolOp = (BooleanOperation) iObj;

      root_be = new BooleanExpr();
      root_be.label = "be1";
      if (rootBoolOp instanceof AndOperation)
        root_be.BooleanOperator = "AND";
      if (rootBoolOp instanceof OrOperation)
        root_be.BooleanOperator = "OR";
      if (rootBoolOp instanceof NotOperation)
        root_be.BooleanOperator = "NOT";

      root_be.isComposite = true;
      // explore recursively the rest of the boolean expression:
      initializeBoolExprTree(root_be, rootBoolOp);

    }

    // compute calculated configurations & detect associated inconsistency
    calculated_cl = Explore_be(root_be);
    calculated_cl.display();

    return calculated_cl;
  }
}
