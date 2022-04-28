/*
 * d2_rectangle.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Mar 11 2022, 22:35 by COMSOL 5.6.0.280. */
public class d2_rectangle {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/home/michaelm/Desktop/cylinder");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").activate("es", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{1, 3});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().remove("mat1");
    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("youngsmodulus", "126e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("poissonsratio", "0.34");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").set("groups", new String[][]{});
    model.component("comp1").material("mat1").set("family", "copper");

    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(3);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", 2);
    model.component("comp1").physics("es").create("term2", "Terminal", 1);
    model.component("comp1").physics("es").feature("term2").selection().set(2);
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");

    model.component("comp1").mesh("mesh1").run();

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "1");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Electric Potential (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.sol("sol1").create("a1", "Assemble");
    model.sol("sol1").feature().move("a1", 1);
    model.sol("sol1").feature("a1").set("K", true);
    model.sol("sol1").feature().move("a1", 2);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Q_Viridis");
    model.result().numerical().create("sys1", "SystemMatrix");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 8);
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result("pg1").run();
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "table");
    model.result("pg2").feature("tblp1").set("table", "tbl1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumninput", "all");
    model.result("pg2").feature("tblp1").set("linewidth", 1);
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result("pg1").run();
    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "mesh1");
    model.result("pg3").set("inherithide", true);
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("shownumerical", false);
    model.nodeGroup().remove("grp1");

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");

    model.sol("sol1").feature("a1").set("NF", true);
    model.sol("sol1").feature("a1").set("N", true);
    model.sol("sol1").feature("a1").set("M", true);
    model.sol("sol1").feature("a1").set("E", true);
    model.sol("sol1").feature("a1").set("D", true);
    model.sol("sol1").feature("a1").set("L", true);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg1").run();
    model.result().table().clear();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "damping");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().numerical("sys1").set("matrixassem", "mass");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "constraint");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").physics("es").feature("term1").set("V0", 0);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").physics("es").feature("term2").set("V0", 1.2);
    model.component("comp1").physics("es").feature("term1").set("V0", 2.7);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("matrixassem", "upperboundconstraint");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "lowerboundconstraint");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "optconstraint");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "optconstraintjac");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "uscale");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "partsol");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "constraintforcenullbasis");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "constraintnullbasis");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "elimmass");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "constraint");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "mass");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().numerical("sys1").set("matrixassem", "damping");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "stiffness");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("solution", "none");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("solution", "sol1");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().table("tbl1").save("/home/michaelm/Desktop/cylinder/Untitled.txt");

    model.sol("sol1").feature("a1").set("L", false);
    model.sol("sol1").feature("a1").set("K", false);
    model.sol("sol1").feature("a1").set("D", false);
    model.sol("sol1").feature("a1").set("E", false);
    model.sol("sol1").feature("a1").set("NF", false);
    model.sol("sol1").feature("a1").set("N", false);
    model.sol("sol1").feature("a1").set("M", false);
    model.sol("sol1").feature("a1").set("L", true);
    model.sol("sol1").feature("a1").set("K", true);
    model.sol("sol1").feature("a1").set("L", false);
    model.sol("sol1").feature("a1").feature("aDef").set("matrixformat", "matrixfree");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");

    model.sol("sol1").feature("a1").feature("aDef").set("symmetric", "hermitian");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export().create("tbl1", "tbl1", "Table");
    model.result().numerical("sys1").set("table", "tbl1");

    model.sol("sol1").feature("a1").feature("aDef").set("symmetric", "auto");
    model.sol("sol1").feature("a1").feature("aDef").set("matrixformat", "sparse");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 9);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "constraint");
    model.result().numerical("sys1").set("table", "tbl1");

    model.sol("sol1").feature("a1").set("M", true);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("matrixassem", "mass");
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("matrixassem", "stiffness");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().table("tbl1").save("/home/michaelm/Desktop/cylinder/stiffness_table.txt");
    model.result("pg1").run();
    model.result("pg1").run();

    model.sol("sol1").feature("a1").feature("aDef").set("rowscale", false);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().table("tbl2").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().table().remove("tbl1");
    model.result().table().remove("tbl2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "statvolt");
    model.result("pg1").feature("surf1").set("expr", "h");
    model.result("pg1").feature("surf1").set("descr", "Element size");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.normE");
    model.result("pg1").feature("surf1").set("descr", "Electric field norm");
    model.result("pg1").run();

    model.component("comp1").physics("es").create("term3", "Terminal", 1);
    model.component("comp1").physics("es").feature("term3").selection().set(4);
    model.component("comp1").physics("es").feature("term3").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term3").set("V0", 0.75);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.normJ");
    model.result("pg1").feature("surf1").set("descr", "Current density norm");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.intWe");
    model.result("pg1").feature("surf1").set("descr", "Total electric energy");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "V");
    model.result("pg1").feature("surf1").set("descr", "Electric potential");
    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term3").set("V0", -0.75);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.Jy");
    model.result("pg1").feature("surf1").set("descr", "Current density, y component");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.normE");
    model.result("pg1").feature("surf1").set("descr", "Electric field norm");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.V0_1");
    model.result("pg1").feature("surf1").set("descr", "Terminal voltage");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.V0_2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "es.intWe");
    model.result("pg1").feature("surf1").set("descr", "Total electric energy");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "V");
    model.result("pg1").feature("surf1").set("descr", "Electric potential");
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 9);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.sol("sol1").feature("a1").feature("aDef").set("blocksizeactive", true);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();

    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{1, 1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tbls1", "TableSurface");
    model.result("pg2").feature("tbls1").set("source", "table");
    model.result("pg2").feature("tbls1").set("table", "tbl1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();

    model.component("comp1").physics("es").feature("term3").active(false);
    model.component("comp1").physics("es").feature("term2").active(false);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tbls1", "TableSurface");
    model.result("pg2").feature("tbls1").set("source", "table");
    model.result("pg2").feature("tbls1").set("table", "tbl1");
    model.result("pg2").run();

    model.component("comp1").physics("es").feature("term2").active(true);
    model.component("comp1").physics("es").feature("term3").active(true);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tbls1", "TableSurface");
    model.result("pg2").feature("tbls1").set("source", "table");
    model.result("pg2").feature("tbls1").set("table", "tbl1");
    model.result("pg2").run();

    model.component("comp1").physics("es").feature("term3").set("V0", 8000);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().table("tbl1").clearTableData();
    model.result("pg2").feature().remove("tbls1");
    model.result("pg2").run();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tbls1", "TableSurface");
    model.result("pg3").feature("tbls1").set("source", "table");
    model.result("pg3").feature("tbls1").set("table", "tbl1");
    model.result("pg3").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl1");
    model.result("pg4").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 8);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("format", "filled");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").set("format", "sparse");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().table().clear();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tbls1", "TableSurface");
    model.result("pg3").feature("tbls1").set("source", "table");
    model.result("pg3").feature("tbls1").set("table", "tbl1");
    model.result("pg3").run();

    model.component("comp1").physics("es").feature("term3").active(false);
    model.component("comp1").physics("es").feature("term2").active(false);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().table("tbl1").clearTableData();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tbls1", "TableSurface");
    model.result("pg3").feature("tbls1").set("source", "table");
    model.result("pg3").feature("tbls1").set("table", "tbl1");
    model.result("pg3").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature().remove("tbls1");
    model.result("pg3").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tbls1", "TableSurface");
    model.result("pg4").feature("tbls1").set("source", "table");
    model.result("pg4").feature("tbls1").set("table", "tbl1");
    model.result("pg4").run();

    model.component("comp1").physics("es").feature("term2").active(true);
    model.component("comp1").physics("es").feature("term3").active(true);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg4").feature().remove("tbls1");
    model.result("pg4").run();
    model.result().table().clear();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tbls1", "TableSurface");
    model.result("pg5").feature("tbls1").set("source", "table");
    model.result("pg5").feature("tbls1").set("table", "tbl1");
    model.result("pg5").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table().clear();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("System Matrix 1");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tbls1", "TableSurface");
    model.result("pg5").feature("tbls1").set("source", "table");
    model.result("pg5").feature("tbls1").set("table", "tbl1");
    model.result("pg5").run();

    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").run();

    return model;
  }

  public static Model run2(Model model) {

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg5").feature().remove("tbls1");
    model.result("pg5").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tbls1", "TableSurface");
    model.result("pg6").feature("tbls1").set("source", "table");
    model.result("pg6").feature("tbls1").set("table", "tbl1");
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result("pg6").run();
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{1, 3});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg6").feature().remove("tbls1");
    model.result("pg6").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tbls1", "TableSurface");
    model.result("pg7").feature("tbls1").set("source", "table");
    model.result("pg7").feature("tbls1").set("table", "tbl1");
    model.result("pg7").run();

    model.component("comp1").physics("es").feature("term2").set("V0", 4000.2);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term2").set("V0", 8000);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tbls1", "TableSurface");
    model.result("pg7").feature("tbls1").set("source", "table");
    model.result("pg7").feature("tbls1").set("table", "tbl1");
    model.result("pg7").run();

    model.component("comp1").physics("es").feature("term2").set("V0", -1.2);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term2").set("V0", -1000.2);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term2").set("V0", -4000.2);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term2").set("V0", -7000.2);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table("tbl1").clearTableData();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").setResult();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().numerical("sys1").set("table", "tbl1");
    model.result().numerical("sys1").appendResult();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tbls1", "TableSurface");
    model.result("pg7").feature("tbls1").set("source", "table");
    model.result("pg7").feature("tbls1").set("table", "tbl1");
    model.result("pg7").run();

    model.label("2d_rectangle.mph");

    model.result("pg7").run();

    model.component("comp1").physics("es").create("term4", "Terminal", 1);
    model.component("comp1").physics("es").feature("term4").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term4").selection().set(1);
    model.component("comp1").physics("es").feature("term3").set("V0", 8);
    model.component("comp1").physics("es").feature("term2").set("V0", 4);
    model.component("comp1").physics("es").feature("term4").set("V0", 0);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics("es").feature("term3").set("V0", 2);

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.label("2d_rectangle.mph");

    model.result("pg1").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
