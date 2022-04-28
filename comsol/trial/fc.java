/*
 * feeder_clamp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;
++
/** Model exported on Mar 12 2022, 10:49 by COMSOL 5.6.0.280. */
public class feeder_clamp {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/home/michaelm/Downloads");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").activate("solid", true);

    model.param().set("Ffeeder", "2000[N]");
    model.param().descr("Ffeeder", "Feeder force");
    model.param().set("Afeeder", "pi*20[mm]*20[mm]");
    model.param().descr("Afeeder", "Feeder area");
    model.param().set("Fscrew", "0.2*4500[N]");
    model.param().descr("Fscrew", "Screw force");
    model.param().set("D", "7[mm]");
    model.param().descr("D", "Outer diameter");
    model.param().set("d", "3.75[mm]");
    model.param().descr("d", "Inner diameter");
    model.param().set("Awasher", "pi/4*(D^2-d^2)");
    model.param().descr("Awasher", "Washer area");
    model.param().set("W", "20[mm]");
    model.param().descr("W", "Clamp width, z direction");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 15);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new int[]{15, 35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{20, 55});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{5, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 15);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new int[]{15, 35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("r", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("pos", new int[]{15, 35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{1.5, 20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{14.25, 40});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").selection("input")
         .set("c2", "c3", "dif1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("formula", "(dif1+c2)-(c3+r2)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("spl1").selection("input").set("co1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("spl1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("spl1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{30, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1.r3");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 40, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", -10);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext2").selection("input")
         .set("wp1.spl1(1)", "wp1.spl1(2)", "wp1.spl1(4)");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 10, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", 5);
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp1.spl1(3)");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 20, 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", 5);
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp2").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp2").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("pos", new int[]{55, 10});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 1.875);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("pos", new int[]{55, 10});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", 3.5);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("pos", new int[]{55, 10});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("r", 1.875);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new int[]{5, 10});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new int[]{55, 5});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c4").set("pos", new int[]{55, 10});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c4").set("r", 5);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c4");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c4");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("co1").selection("input")
         .set("c3", "c4", "r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("co1").set("formula", "r1-c4+c3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("co1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp2.co1");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", 9.25, 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("ext4");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 10.75);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("wp2.dif1");
    model.component("comp1").geom("geom1").feature("copy2").set("displx", 20);
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("mov2(2)", "mov2(3)");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1", "ext4");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("mov1", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("pos", new double[]{-5.5, -15});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("size", new int[]{11, 5});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", 2.5);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("pos", new double[]{-5.5, -12.5});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c2").set("pos", new double[]{5.5, -12.5});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c2").set("r", 2.5);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("uni1").selection("input")
         .set("c1", "c2", "r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").set("disply", 25);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext5").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", 5, 0);
    model.component("comp1").geom("geom1").feature("ext5").set("reverse", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("ext5");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("copy2", "dif1", "dif2", "ext3", "mov2(1)", "wp2.dif1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("rot1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("youngsmodulus", "70e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("poissonsratio", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-2.5e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-3.3e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-3.5e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "5.1e10[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "2.6e10[Pa]");
    model.component("comp1").material("mat1").set("groups", new String[][]{});
    model.component("comp1").material("mat1").set("family", "aluminum");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(22);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("FperArea", new String[]{"Fscrew/Awasher", "0", "0"});
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(68);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("FperArea", new String[]{"-Fscrew/Awasher", "0", "0"});
    model.component("comp1").physics("solid").create("bndl3", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl3").selection().set(11, 12, 43, 47);
    model.component("comp1").physics("solid").feature("bndl3")
         .set("FperArea", new String[]{"0", "Ffeeder/Afeeder", "0"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(34, 35, 38, 39, 62, 63);

    model.group().create("cg1", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix1").set("constraintGroup", "cg1");
    model.component("comp1").physics("solid").feature().duplicate("fix2", "fix1");
    model.component("comp1").physics("solid").feature("fix2").selection().set(32, 33, 36, 37, 60, 61);

    model.group().create("cg2", "ConstraintGroup");

    model.component("comp1").physics("solid").feature("fix2").set("constraintGroup", "cg2");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(18, 22);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "Load case 1", 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "Load case 1", 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "Load case 2", 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "Load case 2", 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 0, 1);
    model.study("std1").feature("stat").setIndex("constraintgroup", true, 1, 0);

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
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("control", "stat");
    model.sol("sol1").feature("s1").set("control", "stat");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (solid)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol1").feature("s1").feature("i1").label("Suggested Iterative Solver (solid)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("s1").set("reacf", false);

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.mises"});
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "Displacement field");

    model.nodeGroup().create("dset1solidlgrp", "Results");
    model.nodeGroup("dset1solidlgrp").label("Applied Loads (solid)");
    model.nodeGroup("dset1solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("dset1solidlgrp", 0);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("Boundary Loads (solid)");

    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg2");

    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("Gray Surfaces");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 0);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.F_Ax", "solid.bndl1.F_Ay", "solid.bndl1.F_Az"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("arrowbase", "tail");
    model.result("pg2").feature("arws1").label("Boundary Load 1");
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("expr", "solid.bndl1.F_A_Mag");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws1").set("color", "red");
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 0);

    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("dset1solidlgrp", 0);

    model.result("pg2").create("arws2", "ArrowSurface");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"solid.bndl2.F_Ax", "solid.bndl2.F_Ay", "solid.bndl2.F_Az"});
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("arrowbase", "tail");
    model.result("pg2").feature("arws2").label("Boundary Load 2");
    model.result("pg2").feature().move("surf1", 2);
    model.result("pg2").feature("arws2").set("inheritplot", "arws1");
    model.result("pg2").feature("arws2").create("col", "Color");
    model.result("pg2").feature("arws2").feature("col").set("expr", "solid.bndl2.F_A_Mag");
    model.result("pg2").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws2").feature("col").set("bottomcolor", "custom");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws2").set("color", "red");
    model.result("pg2").feature("arws2").create("def", "Deform");
    model.result("pg2").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws2").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws2").feature("def").set("scale", 0);

    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("dset1solidlgrp", 0);

    model.result("pg2").create("arws3", "ArrowSurface");
    model.result("pg2").feature("arws3")
         .set("expr", new String[]{"solid.bndl3.F_Ax", "solid.bndl3.F_Ay", "solid.bndl3.F_Az"});
    model.result("pg2").feature("arws3").set("placement", "gausspoints");
    model.result("pg2").feature("arws3").set("arrowbase", "tail");
    model.result("pg2").feature("arws3").label("Boundary Load 3");
    model.result("pg2").feature().move("surf1", 3);
    model.result("pg2").feature("arws3").set("inheritplot", "arws2");
    model.result("pg2").feature("arws3").create("col", "Color");
    model.result("pg2").feature("arws3").feature("col").set("expr", "solid.bndl3.F_A_Mag");
    model.result("pg2").feature("arws3").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws3").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws3").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws3").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws3").set("color", "red");
    model.result("pg2").feature("arws3").create("def", "Deform");
    model.result("pg2").feature("arws3").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws3").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws3").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws3").feature("def").set("scale", 0);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("y-Displacement LC1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "v");
    model.result("pg1").feature("surf1").set("descr", "Displacement field, Y component");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("y-Displacement LC2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("x-Displacement");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "u*(abs(u)>0.25[mm])");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("Rotation");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").selection().set(95);
    model.result("pg5").feature("lngr1").set("expr", "solid.RotyZ");
    model.result("pg5").feature("lngr1").set("descr", "Rotation of deformation tensor, yZ component");
    model.result("pg5").feature("lngr1").set("expr", "solid.RotyZ*180/pi");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Rotation of deformation tensor, yz component (deg)");
    model.result("pg4").run();

    model
         .comments("Deformation of a Feeder Clamp\n\nThis example computes the static deformation of a clamp subject to a load applied on a feeder fastened in the clamp. The feeder is clamped with an M3 screw.");

    model.label("feeder_clamp.mph");

    model.result("pg4").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
